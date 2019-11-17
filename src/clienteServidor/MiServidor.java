package clienteServidor;

import java.io.DataInputStream;

import logica.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
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
			ServerSocket servidor = new ServerSocket(9836);
			ventana = new VentanaServidor(servidor);
			do {
				System.out.println("esperando cliente");
				clienteServidor = servidor.accept();
				InetAddress ip = clienteServidor.getInetAddress();
				
				DataInputStream entrada = new DataInputStream(clienteServidor.getInputStream());

				String jugador = entrada.readUTF();

				GsonBuilder builder = new GsonBuilder();
				builder.registerTypeAdapter(EfectoDarObjeto.class, new AbstractAdapter());
				Gson gson = builder.create();

				// Gson gson = new Gson();

				// System.out.println(nombrePersonaje);

				Jugador jugadorCliente = gson.fromJson(jugador, Jugador.class);
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

		public String hacerAccion(String accion, String mensajeCliente) {
			// Gson gson= new Gson();
			GsonBuilder builder = new GsonBuilder();
			builder.registerTypeAdapter(EfectoDarObjeto.class, new AbstractAdapter());
			Gson gson = builder.create();
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
				Sala s = gson.fromJson(salaString, Sala.class);
				Sala sala = jugador.crearSala(s.getNombreSala(), s.getCantMaxJugadores(), s.getPuntosObj(),
						s.getmaxPartidas());
				salasDisponibles.put(sala.getNombreSala(), sala);
				salasDisponiblesClientes.add(salaString);
				respuesta = gson.toJson(sala.getNombreSala() + " " + sala.getcantJugadores());
				this.ubicacion="CrearSala";
				avisarCambio(this.jugador, new PaqueteMensaje("SalaNueva",salasDisponiblesClientes,"viendoSalasDisponibles" ));
				this.ubicacion="EnSala"+sala.getNombreSala();
			} else if (accion.equals("Unirse")) {
				System.out.println("mi ubicacion: "+this.ubicacion);
				String salaString = (String) gson.fromJson(mensajeCliente, PaqueteMensaje.class).getObj();
				Sala sala = salasDisponibles.get(salaString);
				sala.addJugadorSala(jugador);
				respuesta = gson.toJson("Sala: " + sala.getNombreSala() + "    Jugadores unidos: "
						+ sala.getcantJugadores() + "/" + sala.getCantMaxJugadores());
				avisarcambio2(this.jugador,new PaqueteMensaje("NuevoJugadorSala",respuesta,"EnSala"+sala.getNombreSala()),sala.getNombreSala());
				this.ubicacion="EnSala"+sala.getNombreSala();
			} else if (accion.equals("sacarJugadorSala")) {
				String salaString = (String) gson.fromJson(mensajeCliente, PaqueteMensaje.class).getObj();
				Sala sala = salasDisponibles.get(salaString);
				sala.sacarJugadorSala(jugador);
				respuesta = gson.toJson(sala.getNombreSala());

			} else if (accion.equals("muestraPartida")) {
				String salaString = (String) gson.fromJson(mensajeCliente, PaqueteMensaje.class).getObj();
				Sala sala = salasDisponibles.get(salaString);
				Partida partida = new Partida(sala.getmaxPartidas(), sala.getPuntosObj(), sala.getcantJugadores(),
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

		private void avisarcambio2(Jugador jugador2, PaqueteMensaje paquete, String nombresala) {
			Gson gson = new Gson();
			String mensaje = gson.toJson(paquete);
			DataOutputStream salida;
			List<Jugador> jugadores = salasDisponibles.get(nombresala).getJugadores2();
			for (Jugador jugador : jugadores) {
				System.out.println("ubicacion de cliente: "+jugadoresLobby.get(jugador).getUbicacion());
				System.out.println("ubicacion destino: "+ paquete.getUbicacionDestino());
				if (jugadoresLobby.get(jugador).getUbicacion().equals(paquete.getUbicacionDestino())) {
					try {
						salida= new DataOutputStream(jugadoresLobby.get(jugador).getSocketServidorCliente().getOutputStream());
						salida.writeUTF(mensaje);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
		
		public void avisarCambio(Jugador jugador, PaqueteMensaje paquete) {

			Gson gson = new Gson();
			String mensaje = gson.toJson(paquete);
			DataOutputStream salida;
			for (Map.Entry<Jugador, HiloServidor> cliente : jugadoresLobby.entrySet()) {
				System.out.println("ubicacion de cliente: "+cliente.getValue().getUbicacion());
				System.out.println("ubicacion destino: "+ paquete.getUbicacionDestino());
				if (!cliente.getValue().getJugador().getNombre().equals(jugador.getNombre()) && cliente.getValue().getUbicacion().equals(paquete.getUbicacionDestino())) {
					try {
						salida = new DataOutputStream(cliente.getValue().getSocketServidorCliente().getOutputStream());
						salida.writeUTF(mensaje);
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}

				}
			}
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
