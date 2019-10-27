package logica;

import java.util.List;


public class Ronda {

	private int turno;
	private int cantTurnos;
	private int contadorTurnos;

	Turno suTurno;

	public Ronda(int cantTurnos) {
		this.cantTurnos = cantTurnos;
	}

	public boolean InicioRonda(List<Jugador> listaJugadores, Tablero tablero, int puntosObjetivo)
			throws Exception {
		this.turno = 1;// Inicio el turno en 1
		suTurno = null;
		contadorTurnos = 0;
		while (contadorTurnos < this.cantTurnos && GanadorporObjetivo(puntosObjetivo, this.cantTurnos, listaJugadores) == false) {
			suTurno = new Turno(this.turno);// inicializo un turno
			// Si esta ordenado por turno la lista
			this.turno = suTurno.turno(this.turno, listaJugadores.get(this.turno - 1), tablero,  listaJugadores);// Inicio
																														// turno
			contadorTurnos++;
		}
		return GanadorporObjetivo(puntosObjetivo, this.cantTurnos, listaJugadores);
	}
	
	   
	   
	public boolean terminaRonda(int cantJugadores) {
		if (this.turno <= cantJugadores)
			return false;
		return true;
	}

	public boolean GanadorporObjetivo(int puntosObjetivo, int cantJugadores, List<Jugador> listaJugadores) {
		int i = 0;
		while (i < cantJugadores) {
			if (listaJugadores.get(i).getPuntos() >= puntosObjetivo) {
				return true;
			}
			i++;
		}
		return false;
		
	}

}
