package clienteServidor;

import java.util.HashMap;

import clienteServidor.MiServidor.HiloServidor;
import logica.Jugador;

public interface ObserverServidor {
	
	HashMap<Jugador, HiloServidor> jugadoresLobby = new HashMap<Jugador, HiloServidor>();
	
}
