import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CasillaTest {
	
	Efecto efectoSumarPuntos = new EfectoSumarPuntos(1); // verde
	Efecto sinEfecto = new EfectoNeutro(); // blanco
	Efecto efectoRestarPuntos = new EfectoRestarPuntos(1); // rojo
	Efecto efectoDarObjeto = new EfectoDarObjeto(); // amarillo
	Casilla casilla = new Casilla("blanco", sinEfecto, true);
	Casilla casilla2 = new Casilla("rojo", efectoRestarPuntos, true);
	Casilla casilla3 = new Casilla("amarillo", efectoDarObjeto, false);
	Casilla casilla4 = new Casilla("verde", efectoSumarPuntos, false);
	Jugador jugador = new Jugador(0, "pepe", 20, 0, 0, 1, 0, 0, "pepe");
	
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
		System.out.println(jugador.getPuntos());
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
