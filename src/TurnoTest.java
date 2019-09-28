import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

public class TurnoTest {

	@Test
	public void test() throws IOException {
		Turno turno = new Turno(2, 5);
		Tablero tablero = new Tablero();
		List<Jugador> listaJugadores = new LinkedList<Jugador>();
		Scanner reader = new Scanner(new File("AccionesJugadores.txt"));

		listaJugadores.add(new Jugador(2, "Perro", 10, 0, 0, 1, 0, 0, "pepe3"));
		listaJugadores.add(new Jugador(1, "Mono", 20, 0, 0, 1, 0, 0, "pepe4"));
		listaJugadores.add(new Jugador(3, "Rana", 25, 0, 0, 1, 0, 0, "pepe2"));
		listaJugadores.add(new Jugador(4, "Orca", 15, 0, 0, 1, 0, 0, "pepe1"));

		try {
			// Verificar que le devuelva el turno siguiente que le paso
			assertEquals(2, turno.turno(1, listaJugadores.get(0), tablero, reader, listaJugadores));
			// Verifico que setee bien el turno del jugador
			turno.setNumeroTurno(2);
			assertEquals(2, turno.getNumeroTurno());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
