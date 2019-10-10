import java.util.LinkedList;
import java.util.List;

public class Maint {

	public static void main(String[] args) throws InterruptedException {
		// TODO Apéndice de método generado automáticamente
		Jugador jugador1 = new Jugador("pepe","pepe");
		jugador1.setLugarTableroX(0);
		jugador1.setLugarTableroY(1);
		jugador1.setPosicionAnteriorX(0);
		jugador1.setPosicionAnteriorY(0);
		Jugador jugador2 = new Jugador("pepe","pepe");
		jugador2.setLugarTableroX(3);
		jugador2.setLugarTableroY(0);
		jugador2.setPosicionAnteriorX(2);
		jugador2.setPosicionAnteriorY(0);
		Jugador jugador3 = new Jugador("pepe3","pepe");
		jugador3.setLugarTableroX(5);
		jugador3.setLugarTableroY(5);
		jugador3.setPosicionAnteriorX(4);
		jugador3.setPosicionAnteriorY(5);
		Jugador jugador4 = new Jugador("pepe","pepe");
		jugador4.setLugarTableroX(7);
		jugador4.setLugarTableroY(10);
		jugador4.setPosicionAnteriorX(7);
		jugador4.setPosicionAnteriorY(9);

		List<Jugador> jugadores= new LinkedList<Jugador>();
		jugadores.add(jugador1);
		jugadores.add(jugador2);
		jugadores.add(jugador3);
		jugadores.add(jugador4);
		
		Tablero tablero = new TableroNormal(jugadores);
		Thread.sleep(2000);
		tablero.avanzarJugador(jugador3, 10);
		Thread.sleep(2000);
		tablero.avanzarJugador(jugador1, 10);
		Thread.sleep(2000);
		tablero.avanzarJugador(jugador2, 10);
		Thread.sleep(2000);
		tablero.avanzarJugador(jugador4, 10);
	}
	

}
