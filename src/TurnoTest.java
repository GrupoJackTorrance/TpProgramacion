import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

public class TurnoTest {

	@Test
	public void test() throws IOException {
		Turno turno = new Turno(2, 5);
		Tablero tablero = new Tablero();
		List<Jugador> listaJugadores = new LinkedList<Jugador>();
		Scanner reader= new Scanner(new File("AccionesJugadores.txt"));

		
		Jugador jugador= new Jugador(0, "pepe", 2, 0, 0, 1, 0, 0, "pepe");
		Jugador jugador2 =  new Jugador(0, "Luigi", 5, 2, 0, 0, 0, 0, "Elmer");
		listaJugadores.add(jugador);
				
		try {
			//Verificar que le devuelva el mismo turno que le paso
			assertEquals(0, turno.turno(0, jugador2, tablero, reader, listaJugadores));
			//Verifico que setee bien el turno del jugador
			turno.setNumeroTurno(2);
			assertEquals(2, turno.getNumeroTurno());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
