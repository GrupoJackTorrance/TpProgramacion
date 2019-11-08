package clienteServidor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import logica.AbstractAdapter;
import logica.EfectoDarObjeto;
import logica.Jugador;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class JVentanaIngreso extends JFrame {

	/**
	 * 
	 */
	private transient static final long serialVersionUID = 6618747801020234776L;
	private JPanel contentPane;
	private JLabel label, labelUsuario, labelPersonaje;
	private JButton botonIngreso;
	private JTextField textFieldUsuario, nombrePersonaje;
	Socket miSocket ;

	public static void main(String[] args) throws UnknownHostException, IOException {
		JVentanaIngreso ventana = new JVentanaIngreso();
		ventana.setVisible(true);
	}

	public JVentanaIngreso() {
		try {
			this.miSocket = new Socket("localhost", 9836); //"192.168.0.55"
		} catch (UnknownHostException e) {
			// TODO Bloque catch generado automáticamente
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			System.out.println(e.getMessage());
		}
		setResizable(false);
		setTitle("Bienvenido al juego");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 227);
		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		label = new JLabel("Ingrese su nombre de usuario y de personaje");
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
		labelPersonaje.setBounds(100, 100, 80, 20);
		add(labelPersonaje);
		nombrePersonaje = new JTextField();
		nombrePersonaje.setBounds(184, 100, 86, 20);
		contentPane.add(nombrePersonaje);
		textFieldUsuario.setColumns(10);

		botonIngreso = new JButton("Aceptar");
		botonIngreso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ingresarALobby();
			}
		});
		botonIngreso.setBounds(130, 120, 200, 30);
		contentPane.add(botonIngreso);

		setLocationRelativeTo(null);
	}

	private void ingresarALobby() {
//		new JVentanaTablero(textFieldUsuario.getText());

		try {
			

			DataOutputStream info = new DataOutputStream(miSocket.getOutputStream());
			Jugador jugador= new Jugador(nombrePersonaje.getText(),textFieldUsuario.getText());
			
			GsonBuilder builder = new GsonBuilder();
			builder.registerTypeAdapter(EfectoDarObjeto.class, new AbstractAdapter());
			Gson gson = builder.create();
//			Gson gson = new Gson();
			String mensaje= gson.toJson(jugador);
			info.writeUTF(mensaje);
			DataInputStream entrada = new DataInputStream(miSocket.getInputStream());
			String messajeJ=entrada.readUTF();
			if(messajeJ.equals("MostrarLobby")) {
				System.out.println("entro a la accion");
				new VentanaLobby(miSocket);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
