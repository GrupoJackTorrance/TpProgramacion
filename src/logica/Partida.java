package logica;


import java.util.LinkedList;
import java.util.List;


import grafica.VentanaResultado;

public class Partida {

	/**************************************
	 * PARAMETROS DE PARTIDA
	 ***********************************/
	// /int estado; PARA SABER SI UNA PARTIDA SE ENCUENTRA ACTIVA O NO

	private int rondaMax; // cantidad de rondas maximas para la partida
	private int puntosObjetivo; // cantidad de puntos para ganar
	private Tablero tablero; // Tablero de juego
	private List<Jugador> jugadores = new LinkedList<Jugador>(); // Lista de jugadores
	private int turnos; // Cantidad de turnos que va haber segun los jugadores que haya
	private static boolean terminaMiniJuego = false;
	private VentanaResultado resultados;
	private Ronda ronda; 



	/**********************************************************************************************/

	/*****************************
	 * CONSTRUCTOR DE PARTIDA
	 *******************************************/
	// Generamos la partida
	public Partida(int rondaMax, int puntosObjetivo, int cantJugadores, List<Jugador> listaJugadores) {
		this.turnos = cantJugadores;
		this.puntosObjetivo = puntosObjetivo;
		this.rondaMax = rondaMax;
		this.jugadores = listaJugadores;

	}

	/***********************************************************************************************/

	/*******************************
	 * DESAROLLO DE PARTIDA
	 ******************************************/
	public void InicioPartida() throws Exception {
		int j=0;
		this.tablero = this.elegirTablero(); // Designo tablero
		//this.posicionesInciales(this.getJugadores()); // Posicion Inicial // NO HACE FALTA PORQUE LO SETEAMOS EN SALA
		ronda = new Ronda(this.turnos); // Creo la ronda
		determinarOrdenTurno(this.jugadores); // Jugadores por turno
		boolean terminaJuego = false;
		for (int i = 0; i < rondaMax && terminaJuego == false; i++) {
			terminaJuego = ronda.InicioRonda(this.jugadores, this.getTablero(), this.getPuntosObjetivo());
			if(terminaJuego == false) {
				Minijuego mini;
				if(j%2==0)
				{
					mini= new MiniJuegoAlaSuerte(this.jugadores);
				}else {
					mini= new MiniJuegoPalabras(this.jugadores);
				}
				 j++;
				synchronized(mini){
					if(terminaMiniJuego==false)
						mini.wait();
				}
				synchronized(mini){
					if(terminaMiniJuego==true)
						mini.notify();
				}
			}
			
		}
		this.OrdenarporPuntos(jugadores);
		this.tablero.getVentanaTablero().dispose();
		this.resultados= new VentanaResultado();
		this.resultados.setVisible(true);
		this.resultados.resultadosVentana(jugadores);
		this.mostrarPosicionesFinales(); // Muestro Resultado final
	}
		
	
	/***********************************************************************************************/

	/*************************
	 * FUNCIONES PARA EL DESARROLLO DE PARTIDA
	 *******************************/
	// Designa el orden de los turnos y tambien puntos iniciales para cada
	// jugador equitativamente
	private void determinarOrdenTurno(List<Jugador> listaJugadores) {
		for (int i = 0; i < listaJugadores.size(); i++) {
			listaJugadores.get(i).setNroTurno(i + 1);
			listaJugadores.get(i).setPuntos(10); // ¿Para que?
		}
		/* Otra forma de modificar los turnos 
			int j=1;
		for (Jugador jugador : listaJugadores) {
			jugador.setNroTurno(j);
			j++;
		}
		*/
	}

	// Asigno tablero a la partida
	public Tablero elegirTablero() {
		return new TableroNormal(jugadores);
	}

	// Al finalizar el juego muestra el Resultado final
	public void mostrarPosicionesFinales() {
		List<Jugador> jugadores = this.OrdenarporPuntos(this.jugadores);
		for (int i = 1; i <= this.jugadores.size(); i++) {
			System.out.println("Puesto" + i + ": " + jugadores.get(i - 1).getNombre() + " Personaje: "
					+ jugadores.get(i - 1).getPersonaje() + "Puntos: " + jugadores.get(i - 1).getPuntos());
		}
	}

	// Todos empiezan de la posicion X:0 Y:0
	public void posicionesInciales(List<Jugador> listaJugadores) {
		for (int i = 0; i < listaJugadores.size(); i++) {
			listaJugadores.get(i).setPosicionAnteriorX(1);
			listaJugadores.get(i).setPosicionAnteriorY(0);
			listaJugadores.get(i).setLugarTableroX(0);
			listaJugadores.get(i).setLugarTableroY(0);
		}
	}

	/***********************************************************************************************/
	// Ordenar lista de jugadores
	public List<Jugador> OrdenarporPuntos(List<Jugador> jugadores) {
		List<Jugador> arrayJugadores = jugadores;
		arrayJugadores.sort((j1, j2) -> j1.compareTo(j2));
		return arrayJugadores;
	}

	// PARA MOSTRAR Y MODIFICAR
	public int getTurnos() {
		return turnos;
	}

	public int getRondaMax() {
		return rondaMax;
	}

	public void setRondaMax(int rondaMax) {
		this.rondaMax = rondaMax;
	}

	public int getPuntosObjetivo() {
		return puntosObjetivo;
	}

	public void setPuntosObjetivo(int puntosObjetivo) {
		this.puntosObjetivo = puntosObjetivo;
	}

	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public void setTurnos(int turnos) {
		this.turnos = turnos;
	}


}
