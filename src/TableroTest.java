import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TableroTest {

	List<Jugador> jugadores;
	Tablero tablero;
	Jugador jugador;

	@Before
	public void iniciar() {

		List<Jugador> jugadores = new LinkedList<Jugador>();
		jugador = new Jugador("pepe", "pepe");
		jugadores.add(jugador);
		tablero = new Tablero(jugadores);
	}

	@Test
	public void devuelveCantidadFaltanteTest() {

		// verifico que devuelva cuanto le falta moverse
		assertEquals(2, tablero.avanzarJugador(jugador, 6));
	}

	@Test
	public void mueveCorrectamenteTest() {
		// verifico que mueve hasta la posicion correcta
		tablero.avanzarJugador(jugador, 6);
		assertEquals(0, jugador.getLugarTableroX());
		assertEquals(5, jugador.getLugarTableroY());

	}

}
