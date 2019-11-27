package clienteServidor;

import grafica.VentanaTablero;

import java.io.DataInputStream;

import logica.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.SocketSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.tools.JavaFileObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MiServidor implements Runnable {

	HashMap<Jugador, HiloServidor> jugadoresLobby = new HashMap<Jugador, HiloServidor>();
	HashMap<Jugador, HiloPartida> jugadoresSala = new HashMap<Jugador, HiloPartida>();
	HashMap<String, Sala> salasDisponibles = new HashMap<String, Sala>();
	List<String> salasDisponiblesClientes = new ArrayList<String>();
	Socket clienteServidor;
	Socket ServidorCliente;
	private static VentanaServidor ventana;
	String respuesta;

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
			ServerSocket servidor = new ServerSocket(9836);//9836
			ventana = new VentanaServidor(servidor);
			do {
				System.out.println("esperando cliente");
				clienteServidor = servidor.accept();
				InetAddress ip = clienteServidor.getInetAddress();
				Gson gson = new Gson();
				DataInputStream entrada = new DataInputStream(clienteServidor.getInputStream());
				
				String jugador = gson.fromJson(entrada.readUTF(),String.class);

				//GsonBuilder builder = new GsonBuilder();
				//builder.registerTypeAdapter(EfectoDarObjeto.class, new AbstractAdapter());
				//Gson gson = builder.create();

				System.out.println("datos: "+jugador);
				 String nombre=jugador.split(";")[1];
				 String personaje=jugador.split(";")[0];
				 System.out.println("nombre: "+ nombre);
				 System.out.println("personaje: "+personaje);
				Jugador jugadorCliente = new Jugador(personaje, nombre);
				jugadorCliente.setUbicacion("Lobby");
				System.out.println("LLEGO EL CLIENTE " + jugadorCliente.getNombre());
				DataOutputStream salida = new DataOutputStream(clienteServidor.getOutputStream());
				salida.writeUTF("MostrarLobby");
				HiloServidor hiloCliente = new HiloServidor(clienteServidor,ip, jugadorCliente,"Lobby");
				hiloCliente.start();
				// agrego al HashMap al jugador con su socket
				jugadoresLobby.put(jugadorCliente, hiloCliente);
				System.out.println("fin del while");
			} while (!servidor.isClosed());
			desconectarTodos();
		} catch (IOException e) {

			// TODO Auto-generated catch block

			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public HashMap<Jugador, HiloServidor> getJugadoresLobby() {
		return jugadoresLobby;
	}

	public void setJugadoresLobby(HashMap<Jugador, HiloServidor> jugadoresLobby) {
		this.jugadoresLobby = jugadoresLobby;
	}

	class jugadorLobby {
		Jugador jugador;
		Socket socket;

		public void JugadorLobby(Jugador jugador, Socket socket) {
			this.jugador = jugador;
			this.socket = socket;

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

	class HiloServidor extends Thread {
		private Socket clienteServidor;
		private Socket servidorCliente;
		private Jugador jugador;
		private DataInputStream entrada;
		private DataOutputStream salida;
		boolean corriendo = true;
		private String ubicacion;

		public HiloServidor(Socket clienteServidor, InetAddress ip, Jugador jugador,String ubicacion) throws IOException {
			this.clienteServidor = clienteServidor;
			this.servidorCliente = new Socket(ip, 9837); // "192.168.0.55";
			this.jugador = jugador;
			this.ubicacion=ubicacion;
		}
		public String getUbicacion() {
			return this.ubicacion;
		}

		public Jugador getJugador() {
			return jugador;
		}

		public Socket getClienteServidor() {
			return clienteServidor;
		}
		public Socket getSocketServidorCliente() {
			return servidorCliente;
		}

		@Override
		public void run() {
			try {
				while (corriendo) {
					entrada = new DataInputStream(clienteServidor.getInputStream());
					salida = new DataOutputStream(clienteServidor.getOutputStream());
					String mensajeCliente = entrada.readUTF();
					String accion = determinarAccion(mensajeCliente);
					String respuesta = hacerAccion(accion, mensajeCliente);
					// String respuesta=determinarMensajeRespuesta(accion);
					if(respuesta.equals("InicioPartida"))
						jugadoresLobby.get(this.jugador).setCorriendo(false);
					else {
						salida.writeUTF(respuesta);
						if (respuesta.equals("OK")) {
							entrada.close();
							salida.close();
							corriendo = false;
						}
					}

				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public boolean isCorriendo() {
			return corriendo;
		}
		public void setCorriendo(boolean corriendo) {
			this.corriendo = corriendo;
		}
		
		public String determinarAccion(String mensajeCliente) {
			// Gson gson= new Gson();
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			String mensajeAccion = gson.fromJson(mensajeCliente, PaqueteMensaje.class).getAccion();
			if (mensajeAccion.equals("mostrarSalas"))
				return "devolverSalas";
			else if (mensajeAccion.equals("crearSala"))
				return "crearSala";
			else if (mensajeAccion.equals("Unirse"))
				return "Unirse";
			else if (mensajeAccion.equals("sacarJugadorSala"))
				return "sacarJugadorSala";
			else if (mensajeAccion.equals("iniciarPartida"))
				return "muestraPartida";
			return "Salir";
		}

		public String hacerAccion(String accion, String mensajeCliente) throws Exception {
			// Gson gson= new Gson();
			//GsonBuilder builder = new GsonBuilder().setExclusionStrategies(new MyExclusionStrategy());
			//builder.registerTypeAdapter(EfectoDarObjeto.class, new AbstractAdapter());
//			builder.registerTypeAdapter(JPanel.class, new AbstractAdapter());
//			builder.registerTypeAdapter(JFrame.class, new AbstractAdapter());
			//Gson gson = builder.create();
			Gson gson=new Gson();
			String respuesta = null;
			if (accion.equals("devolverSalas")) {
				this.ubicacion="viendoSalasDisponibles";
				respuesta = gson.toJson(salasDisponiblesClientes);
				// Sala[] desechable = gson.fromJson(respuesta, Sala[].class);
				// int indice =
				// (salasDisponiblesClientes.get(0).indexOf(","))-1;
				// respuesta =
				// salasDisponiblesClientes.get(0).substring(15,indice);

			} else if (accion.equals("crearSala")) {
				/*
				 * Esto codigo obtiene los atributos individuales de un Json Ej) Si tenemos esto
				 * como mensajeCliente... {"accion":"crearSala","obj":
				 * "{'nombreSala':'Salasa','cantJugadores':4,'maxPartidas':1,'puntosObjetivo':20}"}
				 * 
				 * --> gson.fromJson(jobjetocompleto.get("obj").getAsString(),
				 * JsonObject.class); obtiene a
				 * {'nombreSala':'Salasa','cantJugadores':4,'maxPartidas':1,'puntosObjetivo':20}
				 * 
				 * --> getobj.get("nombreSala").getAsString(); obtiene a 'Salasa'
				 */
				/*
				 * JsonElement jelement = new JsonParser().parse(mensajeCliente); JsonObject
				 * jobjetocompleto = jelement.getAsJsonObject(); JsonObject jSala =
				 * gson.fromJson(jobjetocompleto.get("obj").getAsString(), JsonObject.class);
				 * String nombreSala = jSala.get("nombreSala").getAsString();
				 */

				String salaString = (String) gson.fromJson(mensajeCliente, PaqueteMensaje.class).getObj();
				String nombreSala=salaString.split(";")[0];
				int cantMaxJugadores=Integer.parseInt(salaString.split(";")[1]);
				int puntosObjetivo=Integer.parseInt(salaString.split(";")[2]);
				int maxPartidas=Integer.parseInt(salaString.split(";")[3]);
							
				
				Sala sala = jugador.crearSala(nombreSala, cantMaxJugadores, puntosObjetivo, maxPartidas);
				salasDisponibles.put(sala.getNombreSala(), sala);
				salasDisponiblesClientes.add(nombreSala);
				respuesta = gson.toJson(sala.getNombreSala() + " " + sala.getcantJugadores());
				this.ubicacion="CrearSala";
				ManejadorDeImputOutput.avisarCambio(this.jugador, new PaqueteMensaje("SalaNueva",salasDisponiblesClientes,"viendoSalasDisponibles" ),jugadoresLobby);
				this.ubicacion="EnSala"+sala.getNombreSala();
			}
			
			
			else if (accion.equals("Unirse")) {
				System.out.println("mi ubicacion: "+this.ubicacion);
				String nombreSala = (String) gson.fromJson(mensajeCliente, PaqueteMensaje.class).getObj();
				Sala sala = salasDisponibles.get(nombreSala);
				sala.addJugadorSala(jugador);
				respuesta = gson.toJson("Sala: " + sala.getNombreSala() + "    Jugadores unidos: "
						+ sala.getcantJugadores() + "/" + sala.getCantMaxJugadores());
				ManejadorDeImputOutput.avisarCambio(this.jugador,new PaqueteMensaje("NuevoJugadorSala",respuesta,"EnSala"+sala.getNombreSala()),jugadoresLobby);
				this.ubicacion="EnSala"+sala.getNombreSala();
			} 
			
			
			else if (accion.equals("sacarJugadorSala")) {
				String salaString = (String) gson.fromJson(mensajeCliente, PaqueteMensaje.class).getObj();
				Sala sala = salasDisponibles.get(salaString);
				sala.sacarJugadorSala(jugador);
				respuesta = gson.toJson(sala.getNombreSala());

			} 
			
			
			else if (accion.equals("muestraPartida")) { //Creador selecciono "iniciar partida".
				String salaString = (String) gson.fromJson(mensajeCliente, PaqueteMensaje.class).getObj();
				
				//cargo la sala con la nueva partida
				
				Sala sala = salasDisponibles.get(salaString); 
				sala.crearPartida();
				
				//salasDisponibles.put(salaString, sala);
				
				//Empiezo el hilo de la partida
//				gson = builder.excludeFieldsWithoutExposeAnnotation().create();
				HashMap<Jugador,Socket> clienteServidor=new HashMap<Jugador,Socket>();
				HashMap<Jugador,Socket> servidorCliente=new HashMap<Jugador,Socket>();
				for(Jugador jugador : sala.getJugadores2()) {
					clienteServidor.put(jugador,jugadoresLobby.get(jugador).clienteServidor);
					servidorCliente.put(jugador,jugadoresLobby.get(jugador).servidorCliente);
				}
				
				
				HiloPartida hiloPartida = new HiloPartida(sala,clienteServidor,servidorCliente);
				hiloPartida.start();
				agregarHilo(sala,hiloPartida);
				
				String datosPartida=sala.getPartida().getNombre()+";"+sala.getPartida().getRondaMax()+";"+sala.getPartida().getPuntosObjetivo()+";"+sala.getPartida().getJugadores().size();
				
				for (Jugador jugador : sala.getJugadores2()){
					System.out.println(jugador);
					datosPartida+=";"+jugador.getNombre()+";"+jugador.getPersonaje();
				}
				
				PaqueteMensaje mensaje = new PaqueteMensaje("InicioPartida",datosPartida,"EnSala"+sala.getNombreSala());
				ManejadorDeImputOutput.avisarCambio(new Jugador("NULL","NULL"),mensaje,jugadoresLobby);
				respuesta ="InicioPartida"+";"+datosPartida;
			} else {
				jugadoresLobby.remove(jugador);
				respuesta = "OK";
			}
			return respuesta;
		}

		public String determinarMensajeRespuesta(String accion) {
			return accion;
		}
		
		


	}
	
	public void agregarHilo(Sala sala,HiloPartida partida){
		List<Jugador> jugadores = salasDisponibles.get(sala.getNombreSala()).getJugadores2();
		for (Jugador jugador : jugadores) {
			jugadoresSala.put(jugador, partida);
		}
	}
	
	class HiloPartida  extends Thread {
		private DataInputStream entrada;
		private DataOutputStream salida;
		boolean corriendo = true;
		private Sala sala;
		HashMap<Jugador,Socket> clienteServidor;
		HashMap<Jugador,Socket> servidorCliente;
		

		public HiloPartida (Sala sala, HashMap clienteServidor, HashMap servidorCliente) throws IOException {
			this.sala = sala;
			this.clienteServidor=clienteServidor;
			this.servidorCliente=servidorCliente;
		}

		
		@Override
		public void run() {
			/*try {
				//this.sala.iniciarPartida();
//				while (corriendo) {
//					entrada = new DataInputStream(clienteServidor.getInputStream());
//					salida = new DataOutputStream(clienteServidor.getOutputStream());
//					String mensajeCliente = entrada.readUTF();
//					String respuesta = hacerAccion(mensajeCliente);
//					salida.writeUTF(respuesta);
//					if (respuesta.equals("termino partida")) {
//						entrada.close();
//						salida.close();
//						corriendo = false;
//					}
//				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}*/
				

	}
	
		private String hacerAccion(String mensajeCliente) throws Exception {
			GsonBuilder builder = new GsonBuilder();
			builder.registerTypeAdapter(EfectoDarObjeto.class,
					new AbstractAdapter());
			Gson gson = builder.create();
			String respuesta = null;
			if (mensajeCliente.equals("iniciar partida")) {
				this.sala.iniciarPartida();
			}
			return respuesta = "OK";
		}
	}


	public void desconectarTodos() {
		Set<Map.Entry<Jugador, HiloServidor>> set = jugadoresLobby.entrySet();
		for (@SuppressWarnings("rawtypes")
		Entry entry : set) {
			try {
				HiloServidor h = (HiloServidor) entry.getValue();
				h.getClienteServidor().close();
				jugadoresLobby.remove(entry.getKey());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
	
