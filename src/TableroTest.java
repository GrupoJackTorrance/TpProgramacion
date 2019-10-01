import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class TableroTest {

	@Test
	public void test() {
		Jugador jugador1 = new Jugador(1,"pepe",0,0,0,0,0,0,"pepe");
		List<Jugador> jugadores = new LinkedList<Jugador>();
		jugadores.add(jugador1);
		Tablero tablero = new Tablero(jugadores);

		Jugador jugador = new Jugador(0, "pepe", 2, 0, 0, 1, 0, 0, "pepe");

		// verifico que devuelva cuanto le falta moverse
		assertEquals(2, tablero.avanzarJugador(jugador, 6));

		// verifico que mueve hasta la posicion correcta
		assertEquals(0, jugador.getLugarTableroX());
		assertEquals(5, jugador.getLugarTableroY());

		// verifico las opciones cuando llega a la Union
		
		
		// verifico que se le aplique el efecto cuando el jugador termina de moverse
		assertEquals(0, tablero.avanzarJugador(jugador, 1));
		// veo que se haya aplicado el efecto de la casilla al jugador
		assertEquals(1, jugador.getObjEfectos());
	}

}
