import java.util.ArrayList;


public class Partida {
	
	int rondaMax;
	int puntosObjetivo;
	
	//Tablero de juego
	 Tablero tablero;
	 //Lista de jugadores
	ArrayList<Jugador> jugadores;
	
	int turno;
	 
	 //Generamos la partida
	 public Partida(int rondaMax,int puntosObjetivo, int cantJugadores){
		 
		 //Se empieza del turno 0
		 turno = 0;
		 
		 //Se elige la cantidad de puntos para ganar
		 this.puntosObjetivo = puntosObjetivo;
		 
		 //Se elige una cantidad de rondas maximas para la partida
		 this.rondaMax = rondaMax;
		 
		 //Construimos los jugadores de la partida
		 crearJugadores(cantJugadores); 
		 
		 //Construimos el tablero de partida 
		 tablero = new Tablero();
		 
		 //Ponemos a todos los jugadores para empezar
		 for(int i=0; i<jugadores.size(); i++) {
			//ACA NO SE COMO HACER..depende como este hecho casilla
		 }
	 }
	 
	 private void crearJugadores(int num_jugadores) {
		 //inicializamos jugadores;
		 jugadores = new ArrayList<Jugador>(num_jugadores);
		 
		 //Por cada uno de los jugadores 
		 for(int i=0; i<num_jugadores; i++) {
			 Jugador jugador = new Jugador();
			 //metemos al jugador en la lista de jugadores de la partida
			 jugadores.add(jugadores.size(),jugador);
		 }
	 }
	 
	//Este metodo es para que lo llame ronda..para que juegue el jugador
	 //faltaria el limite del tiempo del turno
	 
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
	 }	 


	public void elegirTablero() {
	
	}

}

