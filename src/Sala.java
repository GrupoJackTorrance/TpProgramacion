import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
	addJugadorPartida(jugador);
}

//Modificado: devuelve la partida creada
public Partida crearPartida() {
	Partida p1 = new Partida(maxPartidas, puntosObjetivo, cantJugadores, jugadores);
	return p1;
}



public boolean eliminarPartida(Partida partida) {
	
	return true;
}

public boolean addJugadorPartida(Jugador jugador) {

	
			if(!jugadores.contains(jugador)) {
		
			jugador.setPosicionAnteriorX(0);
		    jugador.setPosicionAnteriorY(0);
		    jugador.setLugarTableroX(0);
		    jugador.setLugarTableroY(0);
			return jugadores.add(jugador);
			}
	
	return false;	
}

public boolean sacarJugadorPartida(Jugador jugador) 
{ 
	Iterator it = jugadores.iterator();
	boolean jugadorEliminado=false;
	if(!jugadores.contains(jugador))
		return jugadorEliminado;
	while(it.hasNext()  && jugadorEliminado) {
		it.next();
		if(it.equals(jugador)) {
			it.remove();
			jugadorEliminado=true;
		}
	}
	return true;
}
public void getJugadores() {
	Iterator it= jugadores.iterator();
	while(it.hasNext()) {
		it.next();
	  System.out.println(it);
	}
}

}
