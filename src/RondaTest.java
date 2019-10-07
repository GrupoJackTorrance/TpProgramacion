import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class RondaTest {
	List<Jugador> jugadores = new LinkedList<Jugador>();
	int puntosObjetivo;
	Ronda ronda;
	Jugador jugador1;
	Jugador jugador2;
	Jugador jugador3;
	Jugador jugador4;
	Tablero tablero;
	Scanner reader; 
	
	@Before
	public void inicialize() throws IOException {
		puntosObjetivo = 5;
		reader = new Scanner(new File("AccionesJugadores.txt"));
		jugador1 = new Jugador("Perro","pepe3");
		jugador2 = new Jugador("Mono","pepe4");
		jugador3 = new Jugador("Rana","pepe2");
		//jugador4 = new Jugador("Orca", "pepe1");
		jugadores.add(jugador1);
		jugadores.add(jugador2);
		jugadores.add(jugador3);
		//jugadores.add(jugador4);
		tablero = new TableroNormal(jugadores);
		ronda = new Ronda(3);
	}
	
	@Test
	public void verificarInicioRondaTest() throws Exception{
		// Verificar que se pueda iniciar la ronda
		assertEquals(false, ronda.InicioRonda(jugadores, tablero, puntosObjetivo, reader));
		// Verificar que termine la ronda cuando el turno sea mayor a la cantidad de
		// jugadores
		assertEquals(true, ronda.terminaRonda(3));
		// Verificar que se termine la ronda cuando se llega a los puntos del objetivo
		assertEquals(false, ronda.GanadorporObjetivo(3, 3, jugadores));
		jugadores.get(0).setPuntos(5);
		//Compruebo que un jugador llego a los puntos para ganar la partida
		assertEquals(true, ronda.GanadorporObjetivo(3, 3, jugadores));
	}

}
