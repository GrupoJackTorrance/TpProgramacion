package clienteServidor;

import java.io.DataInputStream;
import logica.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

public class MiServidor implements Runnable {
	
	HashMap< Jugador, Socket> jugadoresLobby= new HashMap<Jugador, Socket>();
	HashMap <String,Sala> salasDisponibles= new HashMap<String, Sala>();
	public static void main(String[] args) throws Exception {
		MiServidor log = new MiServidor();
		
	}
	public MiServidor() throws Exception {
		

		
		Thread hilo = new Thread(this);
		
		hilo.start();
	}
	
	
	@Override
	public void run() {
		try {
			ServerSocket servidor = new ServerSocket(10001);
			
			while(true) {
			Socket cliente = servidor.accept();
			
			DataInputStream entrada = new DataInputStream(cliente.getInputStream());
			
			String jugador= entrada.readUTF();
			Gson gson = new Gson();
			
			//System.out.println(nombrePersonaje);
			
			/* Acá habría que plasmar la lógica y los métodos que se ejecutan en el main? */ 
			Jugador jugadorCliente =gson.fromJson(jugador,Jugador.class);
			
			// agrego al HashMap al jugador con su socket
			jugadoresLobby.put(jugadorCliente, cliente);
			
			DataOutputStream salida= new DataOutputStream(cliente.getOutputStream());
			salida.writeUTF("MostrarLobby");
		
			
			}
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
public HashMap<Jugador, Socket> getJugadoresLobby() {
		return jugadoresLobby;
	}
	public void setJugadoresLobby(HashMap<Jugador, Socket> jugadoresLobby) {
		this.jugadoresLobby = jugadoresLobby;
	}


class jugadorLobby{
	Jugador jugador;
	Socket socket;
	public void JugadorLobby(Jugador jugador, Socket socket) {
		this.jugador=jugador;
		this.socket=socket;
		
	}
	public Jugador getJugador() {
		return jugador;
	}
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
}

class HiloServidor extends Thread{
	Socket cliente;
	@Override
	public void run() {
		try {
			
			while(true) {
			DataInputStream entrada = new DataInputStream(cliente.getInputStream());
			String mensajeCliente= entrada.readUTF();
			DataOutputStream salida= new DataOutputStream(cliente.getOutputStream());
			Gson gson = new Gson();
		
			}
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

}
