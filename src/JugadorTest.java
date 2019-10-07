import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JugadorTest {
	Jugador jugador;
	Jugador jugador2;
	@Before //sirve para que antes de hacer cada test se empiece desde el escenario elegido
	public void inicialize() {
		jugador = new Jugador("pepe","pepe");
		jugador2 = new Jugador("juan","juan");
		jugador.setPuntos(20);
		jugador2.setPuntos(20);
	}

	@Test
	public void sumarPuntosJugadorTest(){
		jugador.sumarPuntos(1);
		Assert.assertEquals(21, jugador.getPuntos());
	}
	@Test
	public void restarPuntosJugadorTest(){
		jugador.restarPuntos(1);
		Assert.assertEquals(19, jugador.getPuntos());
	}
	@Test
	public void restarTodosLosPuntosJugadorTest(){
		//Compruebo que la vida no sea un valor negativo
		jugador.restarPuntos(50);
		Assert.assertEquals(0, jugador.getPuntos());
	}
	@Test
	public void ataqueConObjetoAJugadorTest(){
		//Compruebo que jugador no tenga objetos
		Assert.assertEquals(false, jugador.usarObjeto(jugador2));
		jugador.setObjEfectos(1);
		//Agrego un objeto a jugador y ataco a jugador 2..el ataque quita 5 puntos
		Assert.assertEquals(true, jugador.usarObjeto(jugador2));
		Assert.assertEquals(15, jugador2.getPuntos());
	}
	
	@Test
	public void comprobarSalaTest() {
		//Compruebo que se genere la Sala, y haya 2 jugadores
		Sala sala = jugador.crearSala(10, 1);
		jugador2.entrarEnSala(sala);
		sala.getJugadores();
	}

}