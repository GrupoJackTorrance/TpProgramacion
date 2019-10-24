package logica;
import java.io.*;
import sun.audio.*;

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
	
		int puntosobj = 40;
		Sala sala = jugador1.crearSala(puntosobj, 20);

		sala.addJugadorSala(jugador2);
		sala.addJugadorSala(jugador3);
		sala.addJugadorSala(jugador4);
		Partida partida1 = sala.crearPartida();
		String sonido="sonidos/playing_the_game.wav";
		InputStream in= new FileInputStream(sonido);
		AudioStream audio= new AudioStream(in);
		AudioPlayer.player.start(audio);
		partida1.InicioPartida();
		AudioPlayer.player.stop(audio);
	
	}

}