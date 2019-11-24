package grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.Query;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import clienteServidor.Querys;
import logica.Usuario;

public class JVentanalogin extends JFrame{

	private JLabel labelUsuario, labelPassword, labelRepitaContraseña, labelCorreoElectronico;
	private JButton crearCuenta;
	private JTextField usuario, correoElectronico;
	private JPasswordField password, repitePassword;
	private JPanel contentPane;
	public static final int ERROR_USUARIO = 1;
	public static final int ERROR_PASSWORD = 2;
	public static final int TODO_OK = 3;
	public static final Pattern DIRECCION_CORREO_VALIDA = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


	public JVentanalogin(Querys query)  {
		
		setResizable(false);
		setTitle("Crear cuenta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		labelUsuario = new JLabel("Usuario: ");
		labelUsuario.setBounds(50, 80, 120, 20);
		add(labelUsuario);
		usuario = new JTextField();
		usuario.setBounds(184, 80, 120, 20);
		contentPane.add(usuario);
		usuario.setColumns(20);
		
		labelPassword = new JLabel("Contraseña: ");
		labelPassword.setBounds(50, 100, 120, 20);
		add(labelPassword);
		password = new JPasswordField();
		password.setBounds(184, 100, 120, 20);
		contentPane.add(password);
		password.setColumns(20);
		
		labelRepitaContraseña = new JLabel("repita Contraseña: ");
		labelRepitaContraseña.setBounds(50, 120, 120, 20);
		add(labelRepitaContraseña);
		repitePassword = new JPasswordField();
		repitePassword.setBounds(184, 120, 120, 20);
		contentPane.add(repitePassword);
		repitePassword.setColumns(10);
		
		labelCorreoElectronico = new JLabel("Direccion de correo: ");
		labelCorreoElectronico.setBounds(50, 140, 120, 20);
		add(labelCorreoElectronico);
		correoElectronico = new JTextField();
		correoElectronico.setBounds(184, 140, 120, 20);
		contentPane.add(correoElectronico);
		correoElectronico.setColumns(30);

		crearCuenta = new JButton("Crear");
		crearCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(String.valueOf(password.getPassword()).compareTo(String.valueOf(repitePassword.getPassword())) != 0) {
						JOptionPane.showMessageDialog(null, "No se pudo crear el usuario porque las contraseñas no coinciden");
					}
					else if (!validarMail(correoElectronico.getText()))
						JOptionPane.showMessageDialog(null, "No se pudo crear el usuario porque direccion de correo no es válida");
					else {
						query.grabarUsuario(usuario.getText(), String.valueOf(password.getPassword()), correoElectronico.getText());
						dispose();
					}
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			}
		});
		crearCuenta.setBounds(130, 200, 80, 30);
		contentPane.add(crearCuenta);

		setLocationRelativeTo(null);
	}	


	public static boolean validarMail(String mail) {
	    Matcher buscarPatron = DIRECCION_CORREO_VALIDA.matcher(mail);
	    return buscarPatron.find();
	}

}