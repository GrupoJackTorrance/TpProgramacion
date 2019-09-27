import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.internal.runners.model.EachTestNotifier;

public class Sala {
private int id;
private int maxPartidas;
private int cantJugadores;
private String creador;
private int puntosObjetivo;//Agregado por Daniel
List<Jugador> jugadores = new LinkedList<Jugador>();
public Sala(Jugador jugador,int puntosObjetivo, int maxPartidas) {
	this.creador=jugador.getNombre();
	this.puntosObjetivo=puntosObjetivo;
	this.cantJugadores=1;
	this.maxPartidas=maxPartidas;
	addJugadorSala(jugador);
}

//Modificado: devuelve la partida creada
public Partida crearPartida() {
	Partida p1 = new Partida(maxPartidas, puntosObjetivo, cantJugadores, jugadores);
	return p1;
}



public boolean eliminarPartida(Partida partida) {
	
	return true;
}

public boolean addJugadorSala(Jugador jugador) {

	
			if(!jugadores.contains(jugador)) {
		
			jugador.setPosicionAnteriorX(0);
		    jugador.setPosicionAnteriorY(0);
		    jugador.setLugarTableroX(0);
		    jugador.setLugarTableroY(0);
			return jugadores.add(jugador);
			}
	
	return false;	
}

public boolean sacarJugadorSala(Jugador jugador) 
{ 

		int index=jugadores.indexOf(jugador);
		if(index==-1)
			return false;
		jugadores.remove(index);
		
	return true;
}
public void getJugadores() {
	for (Jugador jugador : jugadores) {
		System.out.println(jugador);
	}
}

}
