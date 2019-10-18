package tests;
import static org.junit.Assert.*;

import java.util.Iterator;
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
		jugador.setLugarTableroX(0);
		jugador.setLugarTableroY(1);
		jugador.setPosicionAnteriorX(0);
		jugador.setPosicionAnteriorY(0);
		jugadores.add(jugador);
		tablero = new TableroNormal(jugadores);

	}
	/*POR LOS EVENTOS ESTA PARTE NO VA*/
	/*
	@Test
	public void devuelveCantidadFaltanteTest() throws InterruptedException {
		// verifico que devuelva cuanto le falta moverse
		assertEquals(2, tablero.avanzarJugador(jugador, 6));

	}
	 */
	/*@Test
	public void mueveCorrectamenteTest() throws InterruptedException {
		// verifico que mueve hasta la posicion correcta
		tablero.avanzarJugador(jugador, 6);
		assertEquals(0, jugador.getLugarTableroX());
		assertEquals(5, jugador.getLugarTableroY());

	}*/

	@Test
	public void obtengoLasOpciones1Test() {
		jugador.setLugarTableroX(0);
		jugador.setLugarTableroY(5);
		jugador.setPosicionAnteriorX(0);
		jugador.setPosicionAnteriorY(4);
		String esperado[] = { "1", "5", "abajo", "0", "6", "derecha", null, null, null, null, null, null };
		String obtenido[] = tablero.obtenerOpciones(jugador);
	
		assertArrayEquals(esperado, obtenido);
	}

	@Test
	public void obtengoLasOpciones2Test() {
		jugador.setLugarTableroX(3);
		jugador.setLugarTableroY(5);
		jugador.setPosicionAnteriorX(4);
		jugador.setPosicionAnteriorY(5);
		String esperado[] = { "2", "5", "arriba", "3", "4", "izquierda", null, null, null, null, null, null };
		String obtenido[] = tablero.obtenerOpciones(jugador);

		assertArrayEquals(esperado, obtenido);
	}

	@Test
	public void obtengoLasOpciones3Test() {
		jugador.setLugarTableroX(3);
		jugador.setLugarTableroY(5);
		jugador.setPosicionAnteriorX(3);
		jugador.setPosicionAnteriorY(4);
		String esperado[] = { "2", "5", "arriba", "4", "5", "abajo", null, null, null, null, null, null };
		String obtenido[] = tablero.obtenerOpciones(jugador);

		assertArrayEquals(esperado, obtenido);
	}
}
