import org.junit.Test;

public class JugadorTest {

	@Test
	public void test() {
		Jugador jugador = new Jugador(1, "pepe", 0, 0, 0, 0, 0, 0, "pepe");
		Sala sala = jugador.crearSala(10, 1);
		Jugador jugador2 = new Jugador(2, "juan", 0, 0, 0, 0, 0, 0, "juan");
		jugador2.entrarEnSala(sala);
		sala.getJugadores();
	}

}
