package clienteServidor;

import java.io.DataInputStream;

import logica.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.tools.JavaFileObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MiServidor implements Runnable {

	HashMap<Jugador, HiloServidor> jugadoresLobby = new HashMap<Jugador, HiloServidor>();
	HashMap<String, Sala> salasDisponibles = new HashMap<String, Sala>();
	List<String> salasDisponiblesClientes = new ArrayList<String>();
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
			ServerSocket servidor = new ServerSocket(9836);
			ventana = new VentanaServidor(servidor);
			do {
				System.out.println("esperando cliente");
				Socket cliente = servidor.accept();

				DataInputStream entrada = new DataInputStream(
						cliente.getInputStream());

				String jugador = entrada.readUTF();

				GsonBuilder builder = new GsonBuilder();
				builder.registerTypeAdapter(EfectoDarObjeto.class,
						new AbstractAdapter());
				Gson gson = builder.create();

				// Gson gson = new Gson();

				// System.out.println(nombrePersonaje);

				Jugador jugadorCliente = gson.fromJson(jugador, Jugador.class);
				System.out.println("LLEGO EL CLIENTE "
						+ jugadorCliente.getNombre());
				DataOutputStream salida = new DataOutputStream(
						cliente.getOutputStream());
				salida.writeUTF("MostrarLobby");
				HiloServidor hiloCliente = new HiloServidor(cliente,
						jugadorCliente);
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
		private Socket cliente;
		private Jugador jugador;
		DataInputStream entrada;
		DataOutputStream salida;
		boolean corriendo = true;

		public HiloServidor(Socket cliente, Jugador jugador) {
			this.cliente = cliente;
			this.jugador = jugador;
		}

		public Socket getCliente() {
			return cliente;
		}

		@Override
		public void run() { 
			try {
				while (corriendo) {
					entrada = new DataInputStream(cliente.getInputStream());
					salida = new DataOutputStream(cliente.getOutputStream());
					String mensajeCliente = entrada.readUTF();
					String accion = determinarAccion(mensajeCliente);
					String respuesta = hacerAccion(accion, mensajeCliente);
					// String respuesta=determinarMensajeRespuesta(accion);
					salida.writeUTF(respuesta);
					if (respuesta.equals("OK")) {
						entrada.close();
						salida.close();
						corriendo = false;
					}

				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public String determinarAccion(String mensajeCliente) {
			// Gson gson= new Gson();
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			String mensajeAccion = gson.fromJson(mensajeCliente,
					PaqueteMensaje.class).getAccion();
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

		public String hacerAccion(String accion, String mensajeCliente) {
			// Gson gson= new Gson();
			GsonBuilder builder = new GsonBuilder();
			builder.registerTypeAdapter(EfectoDarObjeto.class,
					new AbstractAdapter());
			Gson gson = builder.create();
			String respuesta = null;
			if (accion.equals("devolverSalas")) {
				respuesta = gson.toJson(salasDisponiblesClientes);
				// Sala[] desechable = gson.fromJson(respuesta, Sala[].class);
				// int indice =
				// (salasDisponiblesClientes.get(0).indexOf(","))-1;
				// respuesta =
				// salasDisponiblesClientes.get(0).substring(15,indice);
				
			} else if (accion.equals("crearSala")) {
				/*
				 * Esto codigo obtiene los atributos individuales de un Json
				 * Ej)
				 * Si tenemos esto como mensajeCliente... 
				 * {"accion":"crearSala","obj":"{'nombreSala':'Salasa','cantJugadores':4,'maxPartidas':1,'puntosObjetivo':20}"}
				 * 
				 * --> gson.fromJson(jobjetocompleto.get("obj").getAsString(), JsonObject.class);
				 * obtiene a {'nombreSala':'Salasa','cantJugadores':4,'maxPartidas':1,'puntosObjetivo':20}
				 * 
				 * --> getobj.get("nombreSala").getAsString(); 
				 * obtiene a 'Salasa'
				 */
				/*
				JsonElement jelement = new JsonParser().parse(mensajeCliente);
				JsonObject jobjetocompleto = jelement.getAsJsonObject(); 				
			    JsonObject jSala = gson.fromJson(jobjetocompleto.get("obj").getAsString(), JsonObject.class);
			    String nombreSala = jSala.get("nombreSala").getAsString();
				 */
				
			    String salaString =(String) gson.fromJson(mensajeCliente,PaqueteMensaje.class).getObj();
				Sala s = gson.fromJson(salaString, Sala.class);
				Sala sala = jugador.crearSala(s.getNombreSala(), s.getCantMaxJugadores(),s.getPuntosObj(),s.getmaxPartidas());
				salasDisponibles.put(sala.getNombreSala(),sala);
				salasDisponiblesClientes.add(salaString);
				respuesta=gson.toJson(sala.getNombreSala()+" "+sala.getcantJugadores());
				
			} else if (accion.equals("Unirse")) {
				
				String salaString = (String) gson.fromJson(mensajeCliente,PaqueteMensaje.class).getObj();
				Sala sala = salasDisponibles.get(salaString);
				sala.addJugadorSala(jugador);
				respuesta = gson.toJson("Sala: "+sala.getNombreSala()+"    Jugadores unidos: "+sala.getcantJugadores()+"/"+sala.getCantMaxJugadores());

			} else if (accion.equals("sacarJugadorSala")) {
				String salaString = (String) gson.fromJson(mensajeCliente,
						PaqueteMensaje.class).getObj();
				Sala sala = salasDisponibles.get(salaString);
				sala.sacarJugadorSala(jugador);
				respuesta = gson.toJson(sala.getNombreSala());
				
			} else if (accion.equals("muestraPartida")) {
				String salaString = (String) gson.fromJson(mensajeCliente, PaqueteMensaje.class).getObj();
				Sala sala = salasDisponibles.get(salaString);
				Partida partida = new Partida(sala.getmaxPartidas(),
						sala.getPuntosObj(), sala.getcantJugadores(),
						sala.getJugadores2());
				try {
					partida.InicioPartida();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
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

	public void desconectarTodos() {
		Set<Map.Entry<Jugador, HiloServidor>> set = jugadoresLobby.entrySet();
		for (@SuppressWarnings("rawtypes")
		Entry entry : set) {
			try {
				HiloServidor h = (HiloServidor) entry.getValue();
				h.getCliente().close();
				jugadoresLobby.remove(entry.getKey());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
