import java.util.LinkedList;
import java.util.List;

public class Sala {
int id;
int maxPartidas;
int cantJugadores;
String creador;
int puntosObjetivo;//Agregado por Daniel
List<Jugador> jugadores = new LinkedList<Jugador>();

//Modificado: devuelve la partida creada
public Partida crearPartida() {
	Partida p1 = new Partida(maxPartidas, puntosObjetivo, cantJugadores, jugadores);
	return p1;
}

public void crearJugador(String personaje, String nombre) {

		 addJugadorPartida(new Jugador(0, personaje, 0, 0, 0, 0, 0, nombre));
}

public boolean eliminarPartida() {
	return true;
}

public boolean addJugadorPartida(Jugador j1) {

	try {
		if(!jugadores.contains(j1))
			jugadores.add(j1);
		else
			System.out.println("El jugador ya se encuentra añadido a la partida");
	} catch (Exception e) {
		e.printStackTrace();
	}
	return true;	
}

public boolean sacarJugadorPartida() {
	return true;
}

}
