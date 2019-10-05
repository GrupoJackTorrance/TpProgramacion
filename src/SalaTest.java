import static org.junit.Assert.*;

import org.junit.Test;

public class SalaTest {

	@Test
	public void test() {
		
		Jugador jugador= new Jugador("pep","pep");
		Sala sala = new Sala(jugador,10,1);
		Jugador jugador2= new Jugador("pepy","pepy");
		try {
			
			assertEquals(true, sala.addJugadorSala(jugador2));
			//Verifico que ordene bien los jugadores por puntos de menor a mayor
			assertEquals(false, sala.addJugadorSala(jugador2));
			assertEquals(true, sala.sacarJugadorSala(jugador));
			
			assertEquals(false, sala.sacarJugadorSala(jugador));
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
