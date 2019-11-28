package clienteServidor;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import com.google.gson.Gson;

import logica.Dado;
import logica.Partida;

public class VentanaPartida implements Runnable {
	private static Socket socketClienteServidor;
	private static Socket socketServidorCliente;
	private static Partida partida;
	private Dado dado;
	
	public VentanaPartida(Socket socketClienteServidor, Socket socketServidorCliente,Partida partida,Dado dado) {
		this.socketClienteServidor = socketClienteServidor;
		this.socketServidorCliente=socketServidorCliente;
		this.partida=partida;
		this.dado=dado;
		Thread hiloCliente = new Thread(this);
		hiloCliente.start();
	}

	public static Socket getSocketClienteServidor() {
		return socketClienteServidor;
	}

	public static void setSocketClienteServidor(Socket socketClienteServidor) {
		VentanaPartida.socketClienteServidor = socketClienteServidor;
	}

	public static Socket getSocketServidorCliente() {
		return socketServidorCliente;
	}

	public static void setSocketServidorCliente(Socket socketServidorCliente) {
		VentanaPartida.socketServidorCliente = socketServidorCliente;
	}

	public static Partida getPartida() {
		return partida;
	}

	public static void setPartida(Partida partida) {
		VentanaPartida.partida = partida;
	}

	public Dado getDado() {
		return dado;
	}
	
	@Override
	public void run() {
		Gson gson= new Gson();

		try {
			DataInputStream flujoEntrada = new DataInputStream(socketServidorCliente.getInputStream());
			while (true) {
				String mensaje = flujoEntrada.readUTF();
				System.out.println("se recibio un mensaje desde el servidor a la partida "+partida.getNombre());
				PaqueteMensaje mensajeRecibido=gson.fromJson(mensaje, PaqueteMensaje.class);
				rePintar(mensajeRecibido);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	
	private void rePintar(PaqueteMensaje mensajeRecibido) {
		if(mensajeRecibido.accion.equals("EmpiezaPartida")){
			actualizarPartida((String) mensajeRecibido.getObj());
		}
	}
	
	private void actualizarPartida(String infoTurno) {		
		try {
			partida.getTablero().getVentanaTablero().getPanelTablero().empiezaTurno(infoTurno);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
}
