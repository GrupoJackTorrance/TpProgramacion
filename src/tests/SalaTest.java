package tests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class SalaTest {
	Sala sala;
	Jugador jugador;
	Jugador jugador2;
	Jugador jugador3;
	Jugador jugador4;

	@Before
	public void iniciar() {
		jugador= new Jugador("pepa","pepa");
		sala = new Sala(jugador,10,1);
		jugador2= new Jugador("pepy","pepy");
		jugador3= new Jugador("pepo","pepo");
		jugador4= new Jugador("pepu","pepu");
	}

	@Test
	public void agregarYsacarJugadoresSalaTest() {
			assertEquals(true, sala.addJugadorSala(jugador2));
			assertEquals(true, sala.addJugadorSala(jugador3));
			
			//No se puede agrgar al jugador2 nuevamente
			assertEquals(false, sala.addJugadorSala(jugador2));
			
			assertEquals(true, sala.sacarJugadorSala(jugador2));
			assertEquals(false, sala.sacarJugadorSala(jugador2));
			
			//Se comprueba la cantidad de jugadores en la Sala
			assertEquals(2, sala.getcantJugadores());
			
			sala.getJugadores();
	}
}
