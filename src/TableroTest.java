import static org.junit.Assert.*;

import org.junit.Test;

public class TableroTest {

	@Test
	public void test() {
		Tablero tablero = new Tablero();
		//nroTurno,personaje,puntos, lugarTableroX,lugarTableroY,posicionAnteriorX,posicionAnteriorY,nombre
		Jugador jugador= new Jugador(0, "pepe",0, 0,6,0,5, "pepe");
		Jugador jugador2=new Jugador(0,"pepe",0,0,1,0,0,"pepe");
		//verifico que mueva hasta una union
	    assertEquals(1,tablero.avanzarJugador(jugador,23));
		assertEquals(0,tablero.avanzarJugador(jugador2,3));
		 
		//verifico que mueve hasta la posicion correcta
		assertEquals(7,jugador.getLugarTableroX());
		assertEquals(5,jugador.getLugarTableroY());
	}

}
