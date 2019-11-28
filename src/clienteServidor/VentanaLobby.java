package clienteServidor;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import clienteServidor.MiServidor.HiloServidor;
import grafica.JVentanaTablero;
import grafica.PanelVentanaTablero;
import grafica.VentanaTablero;
import logica.AbstractAdapter;
import logica.Casilla;
import logica.Dado;
import logica.EfectoDarObjeto;
import logica.Jugador;
import logica.MyExclusionStrategy;
import logica.Partida;
import logica.Ronda;
import logica.Sala;
import logica.Tablero;

public class VentanaLobby extends JFrame implements Runnable {
	private static final long serialVersionUID = 8896812117446574226L;
	private static Socket socketClienteServidor;
	private static Socket socketServidorCliente;
	static PanelLobby panel = new PanelLobby();
	boolean corriendo=true;

	public PanelLobby getPanel() {
		return panel;
	}

	public void setPanel(PanelLobby panel) {
		this.panel = panel;
	}

	public static Socket getSocketClienteServidor() {
		return socketClienteServidor;
	}

	public static Socket getSocketServidorCliente() {
		return socketServidorCliente;
	}

	public static void salir() {
		VentanaLobby.panel.setVisible(false);
		System.exit(0);
	}
	
	public void cerrarVentana() {
		dispose();
	}

	public static void setsocketClienteServidor(Socket socketClienteServidor) {
		VentanaLobby.socketClienteServidor = socketClienteServidor;
	}

	public VentanaLobby(Socket socketClienteServidor, Socket socketServidorCliente) {
		this.socketClienteServidor = socketClienteServidor;
		this.socketServidorCliente=socketServidorCliente;
		Thread hiloCliente = new Thread(this);
		hiloCliente.start();
		add(panel);
		setSize(500, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void run() {
		Gson gson= new Gson();
		try {
			DataInputStream flujoEntrada = new DataInputStream(socketServidorCliente.getInputStream());
			while (corriendo) {
				String mensaje = flujoEntrada.readUTF();
				System.out.println("se recibio un mensaje desde el servidor");
				PaqueteMensaje mensajeRecibido=gson.fromJson(mensaje, PaqueteMensaje.class);
				rePintar(mensajeRecibido);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	private void rePintar(PaqueteMensaje mensajeRecibido) {
		System.out.println("Entrada: "+mensajeRecibido.getObj());
		if(mensajeRecibido.accion.equals("SalaNueva")){
			actualizarSalas(mensajeRecibido.getObj());
		}
		if(mensajeRecibido.accion.equals("NuevoJugadorSala")) {
			actualizarJugadorSala((String)mensajeRecibido.getObj());
		}
		if(mensajeRecibido.accion.equals("InicioPartida")){
			cerrarVentana();
			/**corriendo=false;***/
			mostrarPartida((String)mensajeRecibido.getObj());
		}	
	}

	private void mostrarPartida(String partida) {
		panel.mostrarPartida(partida);
		
	}

	private void actualizarJugadorSala(String obj) {
		panel.settinfoPartida(obj);
		panel.repintarSalaEspera(2);
	}

	private void actualizarSalas(Object obj) {		
		panel.setSalaDisponibles((List<String>) obj);
		panel.mostrarSalasDisponibles();
	}

}
