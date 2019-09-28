import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class JugadorTest {

	@Test
	public void test() {
 //nroTurno, String personaje, int puntos, int lugarTableroX, int lugarTableroY, int posicionAnteriorX,
	//			int posicionAnteriorY, String nombre
	    Jugador jugador= new Jugador(1,"pepe",0,0,0,0,0,0,"pepe");
		Sala  sala =jugador.crearSala(10, 1);
		Jugador jugador2=new Jugador(2,"juan",0,0,0,0,0,0,"juan");
		jugador2.entrarEnSala(sala);
		sala.getJugadores();
	}

}
