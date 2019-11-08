package logica;
import java.util.LinkedList;
import java.util.List;

public class Sala {
	private int id;
	private int maxPartidas;
	private int cantJugadores;
	private String creador;
	private int puntosObjetivo;
	private String nombreSala;
	
	public String getNombreSala() {
		return nombreSala;
	}

	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}

	public int getPuntosObj() {
		return puntosObjetivo;
	}
	
	public int getMaxPartidas() {
		return maxPartidas;
	}
	
	private List<Jugador> jugadores = new LinkedList<Jugador>();

	private Partida p1 =null;


	/****************************
	 * FUNCIONES DE SALA
	 *******************************************************************/
	public Sala(Jugador jugador, int puntosObjetivo, int maxPartidas, String nombre, int cantJugadores) {
		this.creador = jugador.getNombre();
		this.puntosObjetivo = puntosObjetivo;
		this.maxPartidas = maxPartidas;
		nombreSala = nombre;
		this.cantJugadores = cantJugadores;
		addJugadorSala(jugador);
	}
	
//	public Sala(Jugador jugador, int puntosObjetivo, int maxPartidas, String nombre, int cantJugadores) {
//		this.creador = jugador.getNombre();
//		this.puntosObjetivo = puntosObjetivo;
//		this.maxPartidas = maxPartidas;
//		this.nombreSala = nombre;
//		this.cantJugadores = cantJugadores;
//		addJugadorSala(jugador);
//	}

	public boolean addJugadorSala(Jugador jugador) {

		if (!jugadores.contains(jugador)) {

			jugador.setPosicionAnteriorX(0);
			jugador.setPosicionAnteriorY(0);
			jugador.setLugarTableroX(0);
			jugador.setLugarTableroY(0);
			cantJugadores++;
			return jugadores.add(jugador);

		}

		return false;
	}

	public boolean sacarJugadorSala(Jugador jugador) {

		int index = jugadores.indexOf(jugador);
		if (index == -1)
			return false;
		cantJugadores--;
		jugadores.remove(index);

		return true;
	}

	/****************************
	 * FUNCIONES DE PARTIDA
	 *******************************************************************/
	// Modificado: devuelve la partida creada
	public Partida crearPartida() {
		 p1 = new Partida(maxPartidas, puntosObjetivo, cantJugadores, jugadores);
		return p1;
	}

	public boolean eliminarPartida(Partida partida) {

		return true;
	}

	/****************************
	 * GET Y SET
	 *******************************************************************/
	public void getJugadores() {
		for (Jugador jugador : jugadores) {
			System.out.println(jugador);
		}
	}

	public String getCreador() {
		return creador;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getmaxPartidas() {
		return maxPartidas;
	}

	public void setmaxPartidas(int maxPartidas) {
		this.maxPartidas = maxPartidas;
	}

	public int getcantJugadores() {
		return cantJugadores;
	}
}
