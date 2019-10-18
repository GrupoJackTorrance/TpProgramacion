package logica;
import java.util.List;

import java.awt.Color;
import java.util.List;


public class TableroNormal  extends Tablero {
	
	public TableroNormal(List<Jugador> jugadores) {
		super(jugadores);
		super.mapa=new Casilla[8][12];
		super.mapa[0][0] = new Casilla(Color.GRAY, new EfectoNeutro(), true);
		super.mapa[0][1] = new Casilla(Color.RED, new EfectoRestarPuntos(1), false);
		super.mapa[0][2] = new Casilla(Color.GREEN, new EfectoSumarPuntos(1), false);
		super.mapa[0][3] = new Casilla(Color.RED, new EfectoRestarPuntos(1), false);
		super.mapa[0][4] = new Casilla(Color.GREEN, new EfectoSumarPuntos(1), false);
		super.mapa[0][5] = new Casilla(Color.YELLOW, new EfectoDarObjeto(), true);
		super.mapa[0][6] = new Casilla(Color.GREEN, new EfectoSumarPuntos(1), false);
		super.mapa[0][7] = new Casilla(Color.RED, new EfectoRestarPuntos(1), false);
		super.mapa[0][8] = new Casilla(Color.GRAY, new EfectoNeutro(), false);
		super.mapa[0][9] = new Casilla(Color.YELLOW, new EfectoDarObjeto(), false);
		super.mapa[0][10] = new Casilla(Color.GREEN, new EfectoSumarPuntos(1), false);

		super.mapa[1][0] = new Casilla(Color.RED, new EfectoRestarPuntos(1), false);
		super.mapa[1][5] = new Casilla(Color.GREEN, new EfectoSumarPuntos(1), false);
		super.mapa[1][10] = new Casilla(Color.RED, new EfectoRestarPuntos(1), false);

		super.mapa[2][0] = new Casilla(Color.GREEN, new EfectoSumarPuntos(1), false);
		super.mapa[2][5] = new Casilla(Color.RED, new EfectoRestarPuntos(1), false);
		super.mapa[2][8] = new Casilla(Color.GREEN, new EfectoSumarPuntos(1), false);
		super.mapa[2][9] = new Casilla(Color.RED, new EfectoRestarPuntos(1), false);
		super.mapa[2][10] = new Casilla(Color.GREEN, new EfectoSumarPuntos(1), false);

		super.mapa[3][0] = new Casilla(Color.RED, new EfectoRestarPuntos(1), false);
		super.mapa[3][2] = new Casilla(Color.RED, new EfectoRestarPuntos(1), false);
		super.mapa[3][3] = new Casilla(Color.GREEN, new EfectoRestarPuntos(1), false);
		super.mapa[3][4] = new Casilla(Color.RED, new EfectoRestarPuntos(1), false);
		super.mapa[3][5] = new Casilla(Color.GRAY, new EfectoNeutro(), true);
		super.mapa[3][8] = new Casilla(Color.YELLOW, new EfectoDarObjeto(), false);

		super.mapa[4][0] = new Casilla(Color.GREEN, new EfectoSumarPuntos(1), false);
		super.mapa[4][2] = new Casilla(Color.GRAY, new EfectoNeutro(), false);
		super.mapa[4][5] = new Casilla(Color.GREEN, new EfectoSumarPuntos(1), false);
		super.mapa[4][8] = new Casilla(Color.GREEN, new EfectoRestarPuntos(1), false);

		super.mapa[5][0] = new Casilla(Color.YELLOW, new EfectoDarObjeto(), true);
		super.mapa[5][1] = new Casilla(Color.GREEN, new EfectoSumarPuntos(1), false);
		super.mapa[5][2] = new Casilla(Color.RED, new EfectoRestarPuntos(1), false);
		super.mapa[5][5] = new Casilla(Color.YELLOW, new EfectoDarObjeto(), false);
		super.mapa[5][8] = new Casilla(Color.RED, new EfectoRestarPuntos(1), false);
		super.mapa[5][9] = new Casilla(Color.GREEN, new EfectoRestarPuntos(1), false);
		super.mapa[5][10] = new Casilla(Color.RED, new EfectoRestarPuntos(1), false);
		super.mapa[5][11] = new Casilla(Color.GREEN, new EfectoSumarPuntos(1), false);

		super.mapa[6][0] = new Casilla(Color.GREEN, new EfectoSumarPuntos(1), false);
		super.mapa[6][5] = new Casilla(Color.RED, new EfectoRestarPuntos(1), false);
		super.mapa[6][11] = new Casilla(Color.GRAY, new EfectoNeutro(), false);

		super.mapa[7][0] = new Casilla(Color.RED, new EfectoRestarPuntos(1), false);
		super.mapa[7][1] = new Casilla(Color.GREEN, new EfectoSumarPuntos(1), false);
		super.mapa[7][2] = new Casilla(Color.RED, new EfectoRestarPuntos(1), false);
		super.mapa[7][3] = new Casilla(Color.GREEN, new EfectoSumarPuntos(1), false);
		super.mapa[7][4] = new Casilla(Color.GRAY, new EfectoNeutro(), false);
		super.mapa[7][5] = new Casilla(Color.GREEN, new EfectoSumarPuntos(1), true);
		super.mapa[7][6] = new Casilla(Color.RED, new EfectoRestarPuntos(1), false);
		super.mapa[7][7] = new Casilla(Color.GREEN, new EfectoSumarPuntos(1), false);
		super.mapa[7][8] = new Casilla(Color.YELLOW, new EfectoDarObjeto(), false);
		super.mapa[7][9] = new Casilla(Color.GREEN, new EfectoSumarPuntos(1), false);
		super.mapa[7][10] = new Casilla(Color.RED, new EfectoRestarPuntos(1), false);
		super.mapa[7][11] = new Casilla(Color.GREEN, new EfectoSumarPuntos(1), false);
	}
}
