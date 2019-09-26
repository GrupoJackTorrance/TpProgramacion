import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Partida {
	
	///int estado; PARA SABER SI UNA PARTIDA SE ENCUENTRA ACTIVA O NO
	int rondaMax;
	int puntosObjetivo;
	
	//Tablero de juego
	 Tablero tablero;
	 //Lista de jugadores
	 List<Jugador> jugadores = new LinkedList<Jugador>();
	 
	int turnos;
	 
	 //Generamos la partida
	 public Partida(int rondaMax,int puntosObjetivo, int cantJugadores, List<Jugador> listaJugadores){
		 
		 //Se empieza del turno 0
		 turnos = cantJugadores;
		 
		 //Se elige la cantidad de puntos para ganar
		 this.puntosObjetivo = puntosObjetivo;
		 
		 //Se elige una cantidad de rondas maximas para la partida
		 this.rondaMax = rondaMax;
		 
		 //Construimos los jugadores de la partida
		 jugadores = listaJugadores;
		 
		 //Construimos el tablero de partida 
		 tablero = new Tablero();
		 
		 //Construimos la ronda
		 	Ronda ronda=new Ronda(cantJugadores);
		 	
		 //Ponemos a todos los jugadores para empezar
		 for(int i=0; i<jugadores.size(); i++) {
			//ACA NO SE COMO HACER..depende como este hecho casilla
		 }
	 }
	 
	/* private void crearJugadores(int num_jugadores) {
		 //inicializamos jugadores;
		 jugadores = new ArrayList<Jugador>(num_jugadores);
		 
		 //Por cada uno de los jugadores 
		 for(int i=0; i<num_jugadores; i++) {
			 Jugador jugador = new Jugador();
			 //metemos al jugador en la lista de jugadores de la partida
			 jugadores.add(jugadores.size(),jugador);
		 }
	 }*/
	 
	//Este metodo es para que lo llame ronda..para que juegue el jugador
	 //faltaria el limite del tiempo del turno
	

	public void InicioPartida(Partida partida) {
		 //Construimos la ronda
		 	Ronda ronda=new Ronda(partida.getTurnos());
		for(int i=0;i<rondaMax;i++) {
			ronda.InicioRonda(partida.jugadores,partida.getTurnos(),partida.getTablero());
			i++;
		}
		ronda.hayGanador(partida.getPuntosObjetivo(),partida.getTurnos(),partida.getJugadores()); 
	 }
	
	/* 
	private int determinarOrdenTurno() { 
		 //Comprobamos si ya ha terminado la partida o no 
		 //if(tiempoTerminado() ) {
			 ///Lanzamos la excexpión de que ya hay un ganador.
			 
		 //}
		 
		 //incrementamos para pasar de turno
		 turno++;
		 //Si todos los jugadores tuvieron un turno, empieza la ronda de vuelta
		 if(turno > (jugadores.size() -1) )
			 turno = 0;

		 return turno;
	 }	 */
	
	
	
	public void elegirTablero() {
		
	}
	
		//PARA MOSTRAR Y MODIFICAR 
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

