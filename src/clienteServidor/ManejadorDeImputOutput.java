package clienteServidor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import clienteServidor.MiServidor.HiloServidor;
import logica.AbstractAdapter;
import logica.EfectoDarObjeto;
import logica.Jugador;
import logica.MyExclusionStrategy;

public class ManejadorDeImputOutput {
	
	
	
	public static void avisarCambio(Jugador jugador, PaqueteMensaje paquete,HashMap<Jugador, HiloServidor> jugadoresLobby) {

		GsonBuilder builder = new GsonBuilder();
//		builder.registerTypeAdapter(EfectoDarObjeto.class, new AbstractAdapter()).setExclusionStrategies(new MyExclusionStrategy());
		builder.setExclusionStrategies(new MyExclusionStrategy());
		builder.registerTypeAdapter(EfectoDarObjeto.class, new AbstractAdapter());
		Gson gson = builder.create();
		String mensaje = gson.toJson(paquete);
		DataOutputStream salida;
		for (Map.Entry<Jugador, HiloServidor> cliente : jugadoresLobby.entrySet()) {
			System.out.println("ubicacion de cliente: "+cliente.getValue().getUbicacion());
			System.out.println("ubicacion destino: "+ paquete.getUbicacionDestino());
			if (!cliente.getValue().getJugador().getNombre().equals(jugador.getNombre()) && cliente.getValue().getUbicacion().equals(paquete.getUbicacionDestino())) {
				try {
					
					salida = new DataOutputStream(cliente.getValue().getSocketServidorCliente().getOutputStream());
					salida.writeUTF(mensaje);
					if(paquete.accion.equals("InicioPartida")) {
						cliente.getValue().setCorriendo(false);
						System.out.println("se cerraron los hilos servidores");
					}
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}

			}
		}
	}
	
	public static void avisarCambioPartida(Jugador jugador, PaqueteMensaje paquete,HashMap<Jugador,Socket> servidorCliente) {
		GsonBuilder builder = new GsonBuilder();
//		builder.registerTypeAdapter(EfectoDarObjeto.class, new AbstractAdapter()).setExclusionStrategies(new MyExclusionStrategy());
		builder.setExclusionStrategies(new MyExclusionStrategy());
		builder.registerTypeAdapter(EfectoDarObjeto.class, new AbstractAdapter());
		Gson gson = builder.create();
		DataOutputStream salida;
		int i=0;
		for (Map.Entry<Jugador,Socket> cliente : servidorCliente.entrySet()) {
			System.out.println("Partida");
			System.out.println("ubicacion de cliente: "+cliente.getKey().getUbicacion());
			System.out.println("ubicacion destino: "+ paquete.getUbicacionDestino());
			if(paquete.getAccion().equals("EmpiezaTurno") || paquete.getAccion().equals("muestraDado")) {
				paquete.setObj(paquete.getObj()+";"+i);
				i++;
			}
			String mensaje = gson.toJson(paquete);
			if (!cliente.getKey().getNombre().equals(jugador.getNombre()) && cliente.getKey().getUbicacion().equals(paquete.getUbicacionDestino())) {
				try {
					salida = new DataOutputStream(cliente.getValue().getOutputStream());
					salida.writeUTF(mensaje);
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}

			}
		}
	}

	

}
