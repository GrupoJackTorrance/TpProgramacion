import org.junit.Test;

public class JugadorTest {

	@Test
	public void test() {
		Jugador jugador = new Jugador("pepe","pepe");
		Sala sala = jugador.crearSala(10, 1);
		Jugador jugador2 = new Jugador("juan","juan");
		jugador2.entrarEnSala(sala);
		jugador2.restarPuntos(1);
	
		sala.getJugadores();
	}

}
