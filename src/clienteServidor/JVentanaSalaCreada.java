package clienteServidor;

import java.awt.Component;
import java.awt.Graphics;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import logica.Jugador;

public class JVentanaSalaCreada extends JFrame{

	private static final long serialVersionUID = 8896812117446574226L;
	private static Socket socketCliente;	
	static PanelVentanaSalaCreada panel= new PanelVentanaSalaCreada();
	
	
	public static void main(String[] args) {
		JVentanaSalaCreada ventana= new JVentanaSalaCreada(socketCliente);
	}

	
	public PanelVentanaSalaCreada getPanel() {
		return panel;
	}

	public void setPanel(PanelVentanaSalaCreada panel) {
		this.panel = panel;
	}

	public static Socket getSocketCliente() {
		return socketCliente;
	}

	public static void salir() {
		VentanaLobby.panel.setVisible(false);
		System.exit(0);
	}
	
	
	public static void setSocketCliente(Socket socketCliente) {
		JVentanaSalaCreada.socketCliente = socketCliente;
	}


	public JVentanaSalaCreada(Socket socketCliente) {
		this.socketCliente= socketCliente;
		this.setTitle("Ventana Sala Creada");
		add(panel);
		setSize(500,500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}

class PanelVentanaSalaCreada extends JPanel{
	
	JList<String> jugadores;
	JLabel tituloJugadores, cantRondas, nombreSala, creador, puntosObjetivo;
	JButton salir, iniciarPartida;
	JScrollPane desplaza;
	
	public PanelVentanaSalaCreada() {
		
		tituloJugadores = new JLabel("JUGADORES");
		String[] lista = {"Jugador 1", "Jugador 2", "Jugador 3"};
		jugadores =  new JList<String>(lista);
		jugadores.setVisibleRowCount(2);
		desplaza = new JScrollPane(jugadores);
		cantRondas = new JLabel("CANTIDAD DE RONDAS");
		nombreSala = new JLabel("NOMBRE DE SALA");
		creador = new JLabel("CREADOR DE LA SALA");
		puntosObjetivo = new JLabel("PUNTOS DE OBJETIVO");
		
		salir = new JButton("Salir");
		iniciarPartida = new JButton("Iniciar Partida");
		
		this.add(desplaza);
		this.add(tituloJugadores);
		this.add(cantRondas);
		this.add(nombreSala);
		this.add(creador);
		this.add(puntosObjetivo);
		this.add(salir);
		this.add(iniciarPartida);
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		tituloJugadores.setBounds(10, 10, 150, 15);
		jugadores.setBounds(300, 30, 150, 15);
		cantRondas.setBounds(30, 70, 150, 15);
		nombreSala.setBounds(30, 110, 510, 15);
		creador.setBounds(30, 150, 150, 15);
		puntosObjetivo.setBounds(30, 190, 150, 15);
		salir.setBounds(50, 300, 150, 30);
		iniciarPartida.setBounds(300, 300, 150, 30);

	}

}
