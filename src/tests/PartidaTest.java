package tests;
import static org.junit.Assert.*;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class PartidaTest {
	List<Jugador> jugadores = new LinkedList<Jugador>();
	Jugador jugador1;
	Jugador jugador2;
	Jugador jugador3;
	Jugador jugador4;
	Partida partida;
	@Before 
	public void inicialize() {
	jugador1 = new Jugador("Perro","pepe3");
	jugador2 = new Jugador("Mono","pepe4");
	jugador3 = new Jugador("Rana","pepe2");
	jugador4 = new Jugador("Orca", "pepe1");
	
	jugadores.add(jugador1);
	jugadores.add(jugador2);
	jugadores.add(jugador3);
	jugadores.add(jugador4);
	
	partida = new Partida(10, 30, 4, jugadores);
	
	}
	
	@Test
	public void ordenarJugadoresPartidaTest() {

			// Verifico que setee bien el punto por objetivo
			partida.setPuntosObjetivo(5);
			assertEquals(5, partida.getPuntosObjetivo());
			// Verifico que setee bien la cantidad maxima de rondas
			partida.setRondaMax(2);
			assertEquals(2, partida.getRondaMax());
			// Verifico que ordene bien los jugadores por puntos de menor a mayor
			jugadores.get(0).setPuntos(10);
			jugadores.get(1).setPuntos(100);
			jugadores.get(2).setPuntos(10);
			jugadores.get(3).setPuntos(50);
			partida.OrdenarporPuntos(jugadores);
			assertEquals(jugadores.get(0).getNombre(), "pepe4");
			assertEquals(jugadores.get(1).getNombre(), "pepe1");
			assertEquals(jugadores.get(2).getNombre(), "pepe2");
			assertEquals(jugadores.get(3).getNombre(), "pepe3");
	}
	
	@Test
	public void posicionesIncialesTest(){
		//Compruebo que todos los jugadores inicien del casillero 0,0
		partida.posicionesInciales(jugadores);
		assertEquals(0,jugadores.get(0).getLugarTableroX());
		assertEquals(0,jugadores.get(0).getLugarTableroY());
		assertEquals(0,jugadores.get(1).getLugarTableroX());
		assertEquals(0,jugadores.get(1).getLugarTableroY());
		assertEquals(0,jugadores.get(2).getLugarTableroX());
		assertEquals(0,jugadores.get(2).getLugarTableroY());
		assertEquals(0,jugadores.get(3).getLugarTableroX());
		assertEquals(0,jugadores.get(3).getLugarTableroY());
		
	}
	
	//@Test
	//public void InicioPartidaTest() {
	//	
	//}

}
