import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

public class RondaTest {

	@Test
	public void test() throws IOException {
		Tablero tablero = new Tablero();
		List<Jugador> jugadores = new LinkedList<Jugador>();
		int puntosObjetivo = 5;
		Scanner reader = new Scanner(new File("AccionesJugadores.txt"));
		jugadores.add(new Jugador(2, "Perro", 1, 0, 0, 1, 0, 0, "pepe3"));
		jugadores.add(new Jugador(1, "Mono", 2, 0, 0, 1, 0, 0, "pepe4"));
		jugadores.add(new Jugador(3, "Rana", 3, 0, 0, 1, 0, 0, "pepe2"));
		jugadores.add(new Jugador(4, "Orca", 4, 0, 0, 1, 0, 0, "pepe1"));
		Ronda ronda = new Ronda(4);

		try {
			// Verificar que se pueda iniciar la ronda
			assertEquals(true, ronda.InicioRonda(jugadores, tablero, puntosObjetivo, reader));
			// Verificar que termine la ronda cuando el turno sea mayor a la cantidad de
			// jugadores
			assertEquals(true, ronda.terminaRonda(-1));
			// Verificar que se termine la ronda cuando se llega a los puntos del objetivo
			assertEquals(true, ronda.GanadorporObjetivo(4, 4, jugadores));

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
