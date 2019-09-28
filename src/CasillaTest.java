import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;
public class CasillaTest {
	@Test
	public void aplicaEfectoTest() {
	Efecto efectoSumarPuntos= new Efecto("sumarPuntos"); //verde
	Efecto sinEfecto= new Efecto("sinEfecto"); // blanco
	Efecto efectoRestarPuntos= new Efecto("restarPuntos"); // rojo
	Efecto efectoDarObjeto= new Efecto("objetoPoder"); // amarillo	
	Casilla casilla= new Casilla("blanco",sinEfecto,true);
	Casilla casilla2= new Casilla("rojo",efectoRestarPuntos,true);
	Casilla casilla3= new Casilla("amarillo",efectoDarObjeto,false);
	Casilla casilla4= new Casilla("verde",efectoSumarPuntos,false);
	Jugador jugador= new Jugador(0, "pepe",20,1,0,1,0,0, "pepe");
	
	   casilla3.aplicarEfecto(jugador);
	   //veo que se haya aplicado el efecto dar Objeto al jugador 
		Assert.assertEquals(1,jugador.getObjEfectos());
	
		casilla2.aplicarEfecto(jugador);
		//veo que se haya aplicado el efecto de restar puntos al jugador
		Assert.assertEquals(19,jugador.getPuntos());
	
		casilla4.aplicarEfecto(jugador);
		//veo que se haya aplicado el efecto de sumar puntos al jugador
		Assert.assertEquals(20,jugador.getPuntos());
		
		casilla.aplicarEfecto(jugador);
		//veo que no se aplique efecto , ya que la casilla es sin efecto 
		Assert.assertEquals(20,jugador.getPuntos());
				
		
	}
	
}
