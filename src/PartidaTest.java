import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class PartidaTest {

	@Test
	public void test() {
		List<Jugador> jugadores = new LinkedList<Jugador>();
		Jugador jugador1=new Jugador("Perro","pepe3");
		Jugador jugador2=new Jugador("Mono","pepe4");
		Jugador jugador3=new Jugador("Rana","pepe2");
		Jugador jugado4=new Jugador( "Orca", "pepe1");
		jugadores.add(jugador1);
		jugadores.add(jugador2);
		jugadores.add(jugador3);
		jugadores.add(jugado4);
		Partida partida = new Partida(10, 30, 4, jugadores);

		try {
			// Verifico que setee bien el punto por objetivo
			partida.setPuntosObjetivo(5);
			assertEquals(5, partida.getPuntosObjetivo());
			// Verifico que setee bien la cantidad maxima de rondas
			partida.setRondaMax(2);
			assertEquals(2, partida.getRondaMax());
			// Verifico que ordene bien los jugadores por puntos de menor a mayor
			partida.OrdenarporPuntos(jugadores);
			assertEquals(jugadores.get(0).getNombre(), "pepe2");
			assertEquals(jugadores.get(1).getNombre(), "pepe4");
			assertEquals(jugadores.get(2).getNombre(), "pepe1");
			assertEquals(jugadores.get(3).getNombre(), "pepe3");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
