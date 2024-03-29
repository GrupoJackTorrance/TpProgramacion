package tests;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import logica.Jugador;
import logica.ObjDescuentaPuntos;
import logica.Sala;

public class JugadorTest {
	Jugador jugador;
	Jugador jugador2;
	List <Jugador> listaJugadores;
	@Before //sirve para que antes de hacer cada test se empiece desde el escenario elegido
	public void inicialize() {
		jugador = new Jugador("pepe","pepe");
		jugador2 = new Jugador("juan","juan");
		jugador.setPuntos(20);
		jugador2.setPuntos(20);
		listaJugadores.add(jugador);
		listaJugadores.add(jugador2);
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
		///Assert.assertEquals(false, jugador.usarObjeto(jugador2, listaJugadores));
		jugador.setObjEfectos(new ObjDescuentaPuntos());
		//Agrego un objeto a jugador y ataco a jugador 2..el ataque quita 5 puntos
		Assert.assertEquals(true, jugador.usarObjeto(jugador2, listaJugadores,jugador.getObjEfectos().getIdObjeto()));
		Assert.assertEquals(15, jugador2.getPuntos());
	}
	
	@Test
	public void comprobarSalaTest() {
		//Compruebo que se genere la Sala 
		Sala sala = jugador.crearSala(10, 1);
		jugador2.entrarEnSala(sala);
		//Compruebo que un jugador entre a la Sala
		Assert.assertEquals(2, sala.getcantJugadores());
		jugador2.salirSala(sala);
		//Compruebo que un jugador salga de la Sala
		Assert.assertEquals(1, sala.getcantJugadores());
	}

}