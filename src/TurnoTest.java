import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class TurnoTest {
	Turno turno;
	Scanner reader;
	Tablero tablero;
	List<Jugador> listaJugadores;
	@Before //sirve para que antes de hacer cada test se empiece desde el escenario elegido
	public void inicialize() throws IOException {
		turno = new Turno(2, 5);
		listaJugadores = new LinkedList<Jugador>();
		reader = new Scanner(new File("AccionesJugadores.txt"));
		listaJugadores.add(new Jugador("Perro","pepe3"));
		listaJugadores.add(new Jugador("Mono","pepe4"));
		listaJugadores.add(new Jugador("Rana","pepe2"));
		listaJugadores.add(new Jugador("Orca","pepe1"));
		tablero = new TableroNormal(listaJugadores);
	}
	
	@Test
	public void comprobarTurnoTest() throws Exception{
			// Verificar que le devuelva el turno siguiente que le paso
			assertEquals(2, turno.turno(1, listaJugadores.get(0), tablero, reader, listaJugadores));
			listaJugadores.get(1).setObjEfectos(1);
			assertEquals(3, turno.turno(2, listaJugadores.get(1), tablero, reader, listaJugadores));
			listaJugadores.get(2).setObjEfectos(1);
			assertEquals(4, turno.turno(3, listaJugadores.get(2), tablero, reader, listaJugadores));
			// Verifico que setee bien el turno del jugador
			turno.setNumeroTurno(2);
			assertEquals(2, turno.getNumeroTurno());
	}
}
