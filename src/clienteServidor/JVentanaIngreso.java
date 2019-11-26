package clienteServidor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import grafica.*;
import logica.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;

public class JVentanaIngreso extends JFrame {
	private transient static final long serialVersionUID = 6618747801020234776L;
	private JPanel contentPane;
	private JLabel label, labelUsuario, labelPersonaje, labelContraseña;
	private JButton botonIngreso, crearCuenta;
	private JTextField textFieldUsuario, nombrePersonaje;
	private JPasswordField contraseña;
	Socket socketClienteServidor;
	Socket socketServidorCliente;
	static JVentanaIngreso ventana;
	private static Querys query;

	public static void main(String[] args) throws UnknownHostException, IOException {
		query = new Querys();
		ventana = new JVentanaIngreso();
		ventana.setVisible(true);
		ventana.setTitle("Ventana ingreso");
	}

	public JVentanaIngreso() {
		try {
			
			this.socketClienteServidor = new Socket("192.168.0.28", 9836); //"192.168.0.55"
			System.out.println(this.socketClienteServidor);
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		setResizable(false);
		setTitle("Bienvenido al juego");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		label = new JLabel("Ingrese su nombre de usuario y contraseña");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setBounds(100, 40, 300, 30);
		contentPane.add(label);
		
		labelUsuario = new JLabel("Usuario: ");
		labelUsuario.setBounds(100, 80, 80, 20);
		add(labelUsuario);
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(184, 80, 86, 20);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		labelPersonaje = new JLabel("Personaje: ");
		labelPersonaje.setBounds(100, 120, 80, 20);
		add(labelPersonaje);
		nombrePersonaje = new JTextField();
		nombrePersonaje.setBounds(184, 120, 86, 20);
		contentPane.add(nombrePersonaje);
		textFieldUsuario.setColumns(10);
		
		labelContraseña = new JLabel("contraseña: ");
		labelContraseña.setBounds(100, 100, 80, 20);
		add(labelContraseña);
		contraseña = new JPasswordField();
		contraseña.setBounds(184, 100, 86, 20);
		contentPane.add(contraseña);
		textFieldUsuario.setColumns(10);

		botonIngreso = new JButton("Aceptar");
		botonIngreso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int resultado = 0;
				int cont = 0;
				try {
					if((resultado = query.verificarUsuario(textFieldUsuario.getText(), contraseña.getPassword()))!= Querys.TODO_OK && cont!= 3) {
						textFieldUsuario.setText(null);
						contraseña.setText(null);
						nombrePersonaje.setText(null);
					}
				} catch (HeadlessException | NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				if(resultado == 3)
					ingresarALobby();
				if (cont == 3){
					JOptionPane.showMessageDialog(null, "Cantidad de intentos fallidos, proceda a crear una cuenta");
						crearCuenta(query);
				}
			}
		});
		botonIngreso.setBounds(130, 250, 100, 30);
		contentPane.add(botonIngreso);
		
		crearCuenta =  new JButton("Crear cuenta");
		crearCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
						crearCuenta(query);	
			}
		});
		
		crearCuenta.setBounds(270, 250, 130, 30);
		contentPane.add(crearCuenta);

		setLocationRelativeTo(null);
	}

	private void ingresarALobby(){
//		new JVentanaTablero(textFieldUsuario.getText());

		try {
			DataOutputStream info = new DataOutputStream(socketClienteServidor.getOutputStream());
			//Jugador jugador= new Jugador(nombrePersonaje.getText(),textFieldUsuario.getText());
			
		//	GsonBuilder builder = new GsonBuilder();
			//builder.registerTypeAdapter(EfectoDarObjeto.class, new AbstractAdapter());
			//Gson gson = builder.create();
			Gson gson = new Gson();
			System.out.println(nombrePersonaje.getText()+";"+textFieldUsuario.getText());
			String mensaje= gson.toJson(nombrePersonaje.getText()+";"+textFieldUsuario.getText());
			info.writeUTF(mensaje);
			DataInputStream entrada = new DataInputStream(socketClienteServidor.getInputStream());
			String messajeJ=entrada.readUTF();
			if(messajeJ.equals("MostrarLobby")) {
				System.out.println("entro a la accion");
				ventana.setVisible(false);
				ServerSocket serverSocket= new ServerSocket(9837);
				socketServidorCliente=serverSocket.accept();
				new VentanaLobby(socketClienteServidor,socketServidorCliente);
			}
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(this,
					"No encontro el servidor.",
					 "Error",
					 JOptionPane.ERROR_MESSAGE);
			
			dispose();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void crearCuenta(Querys query) {
		JVentanalogin login = new JVentanalogin(query);
		login.setVisible(true);
	}

}
