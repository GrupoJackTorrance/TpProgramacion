
import java.util.LinkedList;
import java.util.List;

public class Partida {
	
	/**************************************PARAMETROS DE PARTIDA***********************************/
	///int estado; PARA SABER SI UNA PARTIDA SE ENCUENTRA ACTIVA O NO
	 int rondaMax; //cantidad de rondas maximas para la partida
	 int puntosObjetivo; //cantidad de puntos para ganar
	 Tablero tablero; //Tablero de juego
	 List<Jugador> jugadores = new LinkedList<Jugador>();  //Lista de jugadores
	 int turnos; //Cantidad de turnos que va haber segun los jugadores que haya 
	/**********************************************************************************************/
	
	
	
	/*****************************CONSTRUCTOR DE PARTIDA*******************************************/
	 //Generamos la partida
	 public Partida(int rondaMax,int puntosObjetivo, int cantJugadores, List<Jugador> listaJugadores){
		 turnos = cantJugadores;
		 this.puntosObjetivo = puntosObjetivo;
		 this.rondaMax = rondaMax;
		 jugadores = listaJugadores;
		 	 
	 }
	 /***********************************************************************************************/
	
	 
	 
	 /*******************************DESAROLLO DE PARTIDA******************************************/
	public boolean InicioPartida(Partida partida) {
		if(partida.getTurnos()==4) {
		 	partida.tablero=partida.elegirTablero(); //Designo tablero
		 	partida.posicionesInciales(partida.getJugadores()); //Posicion Inicial
		 	Ronda ronda=new Ronda(partida.getTurnos()); //Creo la ronda
		 	partida.determinarOrdenTurno(partida.getJugadores()); //Jugadores por turno
		for(int i=0;i<rondaMax;i++) {
			ronda.InicioRonda(partida.jugadores,partida.getTurnos(),partida.getTablero(),partida.getPuntosObjetivo());
			i++;
		}
		partida.mostrarPosicionesFinales(partida.getJugadores()); // Muestro Resultado final
		return true;
		}
		return false;
	 }
	/***********************************************************************************************/
	
	
	
	/*************************FUNCIONES PARA EL DESARROLLO DE PARTIDA*******************************/
	//Designa el orden de los turnos y tambien puntos iniciales para cada jugador equitativamente 
	private void determinarOrdenTurno(List<Jugador> listaJugadores) { 
		for(int i=0;i<listaJugadores.size();i++) {
			listaJugadores.get(i).setNroTurno(i+1);
			listaJugadores.get(i).setPuntos(10);
		}
	 }	 
	
	//Asigno tablero a la partida
	public Tablero elegirTablero() {
		return new Tablero();
	}
	
	
	//Al finalizar el juego muestra el Resultado final
	public void mostrarPosicionesFinales(List<Jugador> listaJugadores) {
		List<Jugador> jugadores=listaJugadores.get(0).OrdenarporPuntos(listaJugadores);
		for(int i=1;i<=listaJugadores.size();i++) {
			System.out.println("Puesto"+i+": "+jugadores.get(i-1).getNombre()+" Personaje: "+ jugadores.get(i-1).getPersonaje()+"Puntos: "+jugadores.get(i-1).getPuntos());
		}
	}
	
	//Todos empiezan de la posicion X:0 Y:0
	public void posicionesInciales(List<Jugador> listaJugadores) {
		for(int i=0;i<listaJugadores.size();i++) {
			listaJugadores.get(i).setLugarTableroX(0);
			listaJugadores.get(i).setLugarTableroY(0);
		}
	}
	/***********************************************************************************************/
		
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

