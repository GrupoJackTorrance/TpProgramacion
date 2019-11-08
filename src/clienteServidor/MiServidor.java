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
import com.google.gson.GsonBuilder;

public class MiServidor implements Runnable {
	
	HashMap< Jugador, Socket> jugadoresLobby= new HashMap<Jugador, Socket>();
	HashMap <String,Sala> salasDisponibles= new HashMap<String, Sala>();
	List <String> salasDisponiblesClientes= new ArrayList<String>();
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
			ServerSocket servidor = new ServerSocket(9836);
			
			while(true) {
				System.out.println("esperando cliente");
			Socket cliente = servidor.accept();
			System.out.println("LLEGO");
			DataInputStream entrada = new DataInputStream(cliente.getInputStream());
			
			String jugador= entrada.readUTF();
			
			GsonBuilder builder = new GsonBuilder();
			builder.registerTypeAdapter(EfectoDarObjeto.class, new AbstractAdapter());
			Gson gson = builder.create();
			
//			Gson gson = new Gson();
			
			//System.out.println(nombrePersonaje);
			
			/* Acá habría que plasmar la lógica y los métodos que se ejecutan en el main? */ 
			Jugador jugadorCliente =gson.fromJson(jugador,Jugador.class);
			
			// agrego al HashMap al jugador con su socket
			jugadoresLobby.put(jugadorCliente, cliente);
			
			DataOutputStream salida= new DataOutputStream(cliente.getOutputStream());
			salida.writeUTF("MostrarLobby");
			HiloServidor  hiloCliente = new HiloServidor(cliente,jugadorCliente);
			hiloCliente.start();
			System.out.println("fin del while");
			}
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
	private Socket cliente;
	private Jugador jugador;

	public HiloServidor(Socket cliente,Jugador jugador) {
		this.cliente=cliente;
		this.jugador=jugador;
	}
	
	@Override
	public void run() {
		try {
			
			while(true) {
			DataInputStream entrada = new DataInputStream(cliente.getInputStream());
			String mensajeCliente= entrada.readUTF();
			String accion =determinarAccion(mensajeCliente);
			String respuesta=hacerAccion(accion,mensajeCliente);
			//String respuesta=determinarMensajeRespuesta(accion);
			DataOutputStream salida= new DataOutputStream(cliente.getOutputStream());
			salida.writeUTF(respuesta);
		
		
			}
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	public String determinarAccion(String mensajeCliente) {
		//Gson gson= new Gson();
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String mensajeAccion= gson.fromJson(mensajeCliente, PaqueteMensaje.class).getAccion();
		if(mensajeAccion.equals("mostrarSalas"))
			return "devolverSalas";
		else if( mensajeAccion.equals("crearSala"))
			return "crearSala";
		return "d";
	}
	public String hacerAccion(String accion,String mensajeCliente) {
		//Gson gson= new Gson();
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(EfectoDarObjeto.class, new AbstractAdapter());
		Gson gson = builder.create();
		String respuesta = null;
		if(accion.equals("devolverSalas")) {
			respuesta=gson.toJson(salasDisponiblesClientes);
		}
		else if(accion.equals("crearSala")) {
			String nombreSala=(String) gson.fromJson(mensajeCliente,PaqueteMensaje.class).getObj();
			Sala sala=jugador.crearSala(40,2);
			salasDisponibles.put(nombreSala,sala);
			salasDisponiblesClientes.add(nombreSala);
			respuesta=gson.toJson(sala);
		}
		else {
			respuesta= "nada por ahora";
		}
		return respuesta;
	}
	public String determinarMensajeRespuesta(String accion) {
		return accion;
	}
}
}

