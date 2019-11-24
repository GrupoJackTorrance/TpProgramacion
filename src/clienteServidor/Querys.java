package clienteServidor;

import java.awt.HeadlessException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Query;
import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import logica.Usuario;



public class Querys {
	
	Configuration config;
	public static final int ERROR_USUARIO = 1;
	public static final int ERROR_PASSWORD = 2;
	public static final int TODO_OK = 3;
	
	public Querys() {
		config = new Configuration();
		config.configure("hibernate.cfg.xml");
		
	}
	
	public int verificarUsuario(String usuario, char[] pass) throws HeadlessException, NoSuchAlgorithmException {
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Usuario usu = (Usuario)session.createQuery("select u from Usuario u where u.nombreUsuario =" + "'" + usuario + "'").uniqueResult();
		int estado = 0;
		byte[] passIngresada =  aplicarHash(String.valueOf(pass));
		byte[] passDeBase;
		if(estado == 0) {
			if(usu == null) {
				JOptionPane.showMessageDialog(null, "el usuario es incorrecto. Ingreselo nuevamente");
				estado = ERROR_USUARIO;
			}
			else {
				passDeBase = usu.getPassword();
				System.out.println(passIngresada);
				System.out.println(passDeBase);
				if( Arrays.equals(passIngresada, passDeBase) == false ) {
					JOptionPane.showMessageDialog(null, "La contraseña es incorrecta");
					estado = ERROR_PASSWORD;
				}
			
				else 
					estado = TODO_OK;//OK
			}
		}
		return estado;
		
	}

	public byte[] aplicarHash(String password) throws NoSuchAlgorithmException {
		MessageDigest  digest = MessageDigest.getInstance("SHA-256");
		byte [] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));		
		return hash;
	}

	public void grabarUsuario(String nombreUsuario, String pass, String correo) throws NoSuchAlgorithmException {		
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Usuario usu = (Usuario)session.createQuery("select u from Usuario u where u.nombreUsuario =" + "'" + nombreUsuario + "'").uniqueResult();
		if(usu != null)
			JOptionPane.showMessageDialog(null, "Ya existe un usuario con el nombre que desea crear");
		else{
			Usuario user =  new Usuario(nombreUsuario, aplicarHash(pass), correo);
			Transaction tx = session.beginTransaction();
			try {
	
				session.save(user);
				tx.commit();
				
			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				session.close();
				factory.close();
			}
		}	
	}
}
