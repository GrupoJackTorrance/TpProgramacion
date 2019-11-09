package tests;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import logica.*;
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
		jugadores.add(jugador1);
		jugadores.add(jugador2);
		jugadores.add(jugador3);
		ronda = new Ronda(3);
	}
	
	@Test
	public void verificarInicioRondaTest() throws Exception{
		// Verificar que termine la ronda cuando el turno sea mayor a la cantidad de
		// jugadores
		assertEquals(false, ronda.terminaRonda(3));
		ronda.setTurno(4);
		assertEquals(true, ronda.terminaRonda(3));
		
		//Compruebo que un jugador llego a los puntos para ganar la partida
		assertEquals(false, ronda.GanadorporObjetivo(3, 3, jugadores));
		jugadores.get(0).setPuntos(10);
		assertEquals(true, ronda.GanadorporObjetivo(3, 3, jugadores));
	}

}
