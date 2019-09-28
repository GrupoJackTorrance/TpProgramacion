import static org.junit.Assert.*;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

public class PartidaTest {

	@Test
	public void test() {
		List<Jugador> listaJugadores = new LinkedList<Jugador>();
		List<Jugador> listaJugadoresOrdenada = new LinkedList<Jugador>();
		Jugador jugador= new Jugador(0, "Mario", 20, 0, 0, 1, 0, 0, "pepe");//20 puntos
		Jugador jugador2 =  new Jugador(0, "Luigi", 15, 2, 0, 0, 0, 0, "Elmer");//15 puntos
		Jugador jugador3 = new Jugador(3, "Kupa", 10, 0, 0, 0, 0, 0, "Juan");//10 puntos
		listaJugadores.add(jugador);
		listaJugadores.add(jugador2);
		listaJugadores.add(jugador3);
		Partida partida = new Partida(2, 5, 2, listaJugadores);
		
				
		try {
			//Verifico que setee bien el punto por objetivo
			partida.setPuntosObjetivo(5);
			assertEquals(5, partida.getPuntosObjetivo());
			//Verifico que setee bien la cantidad maxima de rondas
			partida.setRondaMax(2);
			assertEquals(2, partida.getRondaMax());
			//Verifico que ordene bien los jugadores por puntos de menor a mayor
			listaJugadoresOrdenada = partida.OrdenarporPuntos(listaJugadores);
			assertEquals(listaJugadoresOrdenada.get(0), jugador3);
			assertEquals(listaJugadoresOrdenada.get(1), jugador2);
			assertEquals(listaJugadoresOrdenada.get(2), jugador);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
