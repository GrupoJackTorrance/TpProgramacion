public class Main {

	public static void main(String[] args) throws Exception {
		/*
		 * //Creo tablero de 3 casillas y coloco 1 jugador en (0,0) Tablero tablero =
		 * new Tablero(); Casilla[][] mapa = new Casilla[1][3];
		 * tablero.crearMapa1(mapa); Jugador j1 = new Jugador(); j1.lugarTableroX =
		 * j1.lugarTableroY = 0; //////////////////////
		 * 
		 * //Simulo que jugador recien llego a casilla 1 y aplico efecto
		 * tablero.mapa[j1.lugarTableroY][j1.lugarTableroX].aplicarEfecto(j1);
		 * System.out.println(j1.puntos);
		 */

		/*
		 * Entonces el orden de arrancar seria: Jugador crea sala sala crea partida
		 * segun los parametros que tiene sala sala empieza partida (aunque es iniciado
		 * por el jugador creador) partida controla las rondas Ronda controla los turnos
		 * de cada jugador. Los turnos son los que accionan el juego (Dice quien tira
		 * dado, hace los movimientos,etc.) Despues de cada ronda, se verifica que no se
		 * llego al objetivo y sigue jugando o determina jugador. termina la partida
		 */
		Jugador jugador1 = new Jugador(1, "pepe", 0, 1, 0, 0, 0, 0, "pepe");
		Jugador jugador2 = new Jugador("Dragon", "pepe2");
		Jugador jugador3 = new Jugador("Rana", "pepe3");
		Jugador jugador4 = new Jugador("Mono", "pepe4");
		int puntosobj = 15;
		Sala sala = jugador1.crearSala(puntosobj, 2);
		sala.addJugadorSala(jugador2);
		sala.addJugadorSala(jugador3);
		sala.addJugadorSala(jugador4);
		Partida partida1 = sala.crearPartida();
		partida1.InicioPartida();
	}

}
