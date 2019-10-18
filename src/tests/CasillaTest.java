package tests;
import java.awt.Color;
import logica.*;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class CasillaTest {
	Efecto efectoSumarPuntos;
	Efecto sinEfecto ;
	Efecto efectoRestarPuntos;
	Efecto efectoDarObjeto;
	Casilla casilla ;
	Casilla casilla2;
	Casilla casilla3;
	Casilla casilla4;
	Jugador jugador ;
	@Before //sirve para que antes de hacer cada test se empiece desde el escenario elegido
	public void inicialize() {
	efectoSumarPuntos = new EfectoSumarPuntos(1); // verde
	sinEfecto = new EfectoNeutro(); // blanco
	efectoRestarPuntos = new EfectoRestarPuntos(1); // rojo
	efectoDarObjeto = new EfectoDarObjeto(); // amarillo
	casilla = new Casilla(Color.GRAY, sinEfecto, true);
	casilla2 = new Casilla(Color.RED, efectoRestarPuntos, true);
	casilla3 = new Casilla(Color.YELLOW, efectoDarObjeto, false);
	casilla4 = new Casilla(Color.GREEN, efectoSumarPuntos, false);
	jugador = new Jugador("pepe","pepe");
	jugador.setPuntos(20);
	}
	@Test
	public void aplicaEfectoDarObjetoTest() {
		
		casilla3.aplicarEfecto(jugador);
		// veo que se haya aplicado el efecto dar Objeto al jugador
		Assert.assertEquals(1, jugador.getObjEfectos());
	}
	@Test
	public void aplicaEfectoRestarPuntosTest() {
		
		casilla2.aplicarEfecto(jugador);
		// veo que se haya aplicado el efecto de restar puntos al jugador
	
		Assert.assertEquals(19, jugador.getPuntos());
	}
	@Test
	public void aplicaEfectoSumarPuntosTest() {
		
		casilla4.aplicarEfecto(jugador);
		// veo que se haya aplicado el efecto de sumar puntos al jugador
		
		Assert.assertEquals(21, jugador.getPuntos());
	}
	@Test
	public void aplicaEfectoNeutro() {
		
		casilla.aplicarEfecto(jugador);
		// veo que no se aplique efecto , ya que la casilla es sin efecto
		Assert.assertEquals(20, jugador.getPuntos());
		Assert.assertEquals(0, jugador.getObjEfectos());
	}
	

}
