import static org.junit.Assert.*;

import org.junit.Test;

public class TableroTest {

	@Test
	public void test() {
		Tablero tablero = new Tablero();
		
		Jugador jugador= new Jugador(0, "pepe",0, 0,1,0,0, "pepe");
		
		//verifico que devuelva cuanto le falta moverse
	    assertEquals(1,tablero.avanzarJugador(jugador,5));

		 
		//verifico que mueve hasta la posicion correcta
		assertEquals(0,jugador.getLugarTableroX());
		assertEquals(5,jugador.getLugarTableroY());
		
		// verifico las opciones cuando llega a la Union
		assertEquals(1,tablero.obtenerOpcionX(jugador));
		assertEquals(6, tablero.obtenerOpcionY(jugador));
		
	}

}
