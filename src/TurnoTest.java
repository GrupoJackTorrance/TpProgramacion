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
		List<Jugador> listaJugadores = new LinkedList<Jugador>();
		Scanner reader = new Scanner(new File("AccionesJugadores.txt"));

		listaJugadores.add(new Jugador("Perro","pepe3"));
		listaJugadores.add(new Jugador("Mono","pepe4"));
		listaJugadores.add(new Jugador("Rana","pepe2"));
		listaJugadores.add(new Jugador("Orca","pepe1"));
		Tablero tablero = new TableroNormal(listaJugadores);

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
