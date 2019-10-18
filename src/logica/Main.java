package logica;


public class Main {

	public static void main(String[] args) throws Exception {
		
		/*
		 * Entonces el orden de arrancar seria: Jugador crea sala sala crea partida
		 * segun los parametros que tiene sala sala empieza partida (aunque es iniciado
		 * por el jugador creador) partida controla las rondas Ronda controla los turnos
		 * de cada jugador. Los turnos son los que accionan el juego (Dice quien tira
		 * dado, hace los movimientos,etc.) Despues de cada ronda, se verifica que no se
		 * llego al objetivo y sigue jugando o determina jugador. termina la partida
		 */
		Jugador jugador1 = new Jugador("pepe","Pedro264");
		Jugador jugador2 = new Jugador("Dragon", "Maria46a");
		Jugador jugador3 = new Jugador("Rana", "Julian32");
		Jugador jugador4 = new Jugador("Mono", "Marlen16");
		jugador1.setObjEfectos(1);
		jugador3.setObjEfectos(1);
		int puntosobj = 15;
		Sala sala = jugador1.crearSala(puntosobj, 2);
		sala.addJugadorSala(jugador2);
		sala.addJugadorSala(jugador3);
		sala.addJugadorSala(jugador4);
		Partida partida1 = sala.crearPartida();
		partida1.InicioPartida();
	
	
	}

}
