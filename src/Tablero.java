import java.util.LinkedList;
import java.util.List;

public class Tablero {

//  					 ------------	A T R I B U T O S    -------------

	String nombre;
	Casilla mapa[][] = new Casilla[8][12];
	List<Jugador> jugadores = new LinkedList<Jugador>();

	// ---------- C O N S T R U C T O R---------------------
	public Tablero(List<Jugador> jugadores) {
		this.jugadores = jugadores;

		
	

// 						 ------------	M E T O D O S    -------------

	/*
	 * Se crea cada objeto Casilla para cada posición de la matriz. Aquellas
	 * posiciones en las que no se creó ningun objeto toman el valor null. */

		this.nombre = "Tablero partida";
		//EfectoSumarPuntos verde
		//EfectoRestarPuntos rojo
		//EfectoNeutro blanco
		//EfectoDarObjeto amarillo
		mapa[0][0] = new Casilla("blanco",new EfectoNeutro(), true);
		mapa[0][1] = new Casilla("blanco", new EfectoNeutro(), false);
		mapa[0][2] = new Casilla("amarillo", new EfectoDarObjeto(), false);
		mapa[0][3] = new Casilla("verde", new EfectoSumarPuntos(1), false);
		mapa[0][4] = new Casilla("verde", new EfectoSumarPuntos(1), false);
		mapa[0][5] = new Casilla("rojo",  new EfectoRestarPuntos(1), true);
		mapa[0][6] = new Casilla("amarillo", new EfectoDarObjeto(), false);
		mapa[0][7] = new Casilla("rojo",  new EfectoRestarPuntos(1), false);
		mapa[0][8] = new Casilla("verde",  new EfectoSumarPuntos(1), false);
		mapa[0][9] = new Casilla("blanco",new EfectoNeutro(), false);
		mapa[0][10] = new Casilla("verde", new EfectoSumarPuntos(1), false);

		mapa[1][0] = new Casilla("blanco",new EfectoNeutro(), false);
		mapa[1][5] = new Casilla("rojo",  new EfectoRestarPuntos(1), false);
		mapa[1][10] = new Casilla("rojo", new EfectoRestarPuntos(1), false);

		mapa[2][0] = new Casilla("verde",  new EfectoSumarPuntos(1), false);
		mapa[2][5] = new Casilla("amarillo", new EfectoDarObjeto(), false);
		mapa[2][8] = new Casilla("verde", new EfectoSumarPuntos(1), false);
		mapa[2][9] = new Casilla("blanco", new EfectoNeutro(), false);
		mapa[2][10] = new Casilla("amarillo", new EfectoDarObjeto(), false);

		mapa[3][0] = new Casilla("rojo",  new EfectoRestarPuntos(1), false);
		mapa[3][2] = new Casilla("verde", new EfectoRestarPuntos(1), false);
		mapa[3][3] = new Casilla("verde", new EfectoRestarPuntos(1), false);
		mapa[3][4] = new Casilla("amarillo", new EfectoDarObjeto(), false);
		mapa[3][5] = new Casilla("rojo", new EfectoRestarPuntos(1), true);
		mapa[3][8] = new Casilla("rojo",  new EfectoRestarPuntos(1), false);

		mapa[4][0] = new Casilla("blanco",new EfectoNeutro(), false);
		mapa[4][2] = new Casilla("amarillo", new EfectoDarObjeto(), false);
		mapa[4][5] = new Casilla("blanco",new EfectoNeutro(), false);
		mapa[4][8] = new Casilla("verde ",new EfectoRestarPuntos(1), false);

		mapa[5][0] = new Casilla("blanco", new EfectoNeutro(), true);
		mapa[5][1] = new Casilla("blanco", new EfectoNeutro(), false);
		mapa[5][2] = new Casilla("amarillo", new EfectoDarObjeto(), false);
		mapa[5][5] = new Casilla("rojo", new EfectoRestarPuntos(1), false);
		mapa[5][8] = new Casilla("rojo", new EfectoRestarPuntos(1), false);
		mapa[5][9] = new Casilla("verde", new EfectoRestarPuntos(1), false);
		mapa[5][10] = new Casilla("blanco",new EfectoNeutro(), false);
		mapa[5][11] = new Casilla("blanco",new EfectoNeutro(), false);

		mapa[6][0] = new Casilla("verde",  new EfectoSumarPuntos(1), false);
		mapa[6][5] = new Casilla("amarillo", new EfectoDarObjeto(), false);
		mapa[6][11] = new Casilla("verde", new EfectoSumarPuntos(1), false);

		mapa[7][0] = new Casilla("verde", new EfectoSumarPuntos(1), false);
		mapa[7][1] = new Casilla("verde", new EfectoSumarPuntos(1), false);
		mapa[7][2] = new Casilla("amarillo", new EfectoDarObjeto(), false);
		mapa[7][3] = new Casilla("rojo", new EfectoRestarPuntos(1), false);
		mapa[7][4] = new Casilla("blanco",new EfectoNeutro(), false);
		mapa[7][5] = new Casilla("blanco", new EfectoNeutro(), true);
		mapa[7][6] = new Casilla("amarillo", new EfectoDarObjeto(), false);
		mapa[7][7] = new Casilla("rojo", new EfectoRestarPuntos(1), false);
		mapa[7][8] = new Casilla("rojo", new EfectoRestarPuntos(1), false);
		mapa[7][9] = new Casilla("blanco",new EfectoNeutro(), false);
		mapa[7][10] = new Casilla("verde",  new EfectoSumarPuntos(1), false);
		mapa[7][11] = new Casilla("verde",  new EfectoSumarPuntos(1), false);
	}


	public int avanzarJugador(Jugador jugador, int cantidad) {

		while (cantidad > 0 && mapa[jugador.getLugarTableroX()][jugador.getLugarTableroY()].getEsUnion() == false) {
			if (puedeAvanzar(jugador, "izquierda")) {
				jugador.setPosicionAnteriorX(jugador.getLugarTableroX());
				jugador.setPosicionAnteriorY(jugador.getLugarTableroY());
				jugador.setLugarTableroY(jugador.getLugarTableroY() - 1);
			} else if (puedeAvanzar(jugador, "derecha")) {
				jugador.setPosicionAnteriorX(jugador.getLugarTableroX());
				jugador.setPosicionAnteriorY(jugador.getLugarTableroY());
				jugador.setLugarTableroY(jugador.getLugarTableroY() + 1);
			} else if (puedeAvanzar(jugador, "arriba")) {
				jugador.setPosicionAnteriorX(jugador.getLugarTableroX());
				jugador.setPosicionAnteriorY(jugador.getLugarTableroY());
				jugador.setLugarTableroX(jugador.getLugarTableroX() - 1);
			} else if (puedeAvanzar(jugador, "abajo")) {
				jugador.setPosicionAnteriorX(jugador.getLugarTableroX());
				jugador.setPosicionAnteriorY(jugador.getLugarTableroY());
				jugador.setLugarTableroX(jugador.getLugarTableroX() + 1);
			}

			cantidad--;
		}
		if (cantidad == 0)
			mapa[jugador.getLugarTableroX()][jugador.getLugarTableroY()].aplicarEfecto(jugador);
		
		jugador.update();
		return cantidad;
	}

	public String obtenerOpciones(Jugador jugador) {
		String opciones="";
		if(puedeAvanzar(jugador,"arriba"))
			opciones="arriba ";
		if(puedeAvanzar(jugador,"abajo"))
			opciones=opciones+"abajo ";
		if(puedeAvanzar(jugador,"derecha"))
			opciones=opciones+"derecha ";
		if(puedeAvanzar(jugador,"izquierda"))
			opciones=opciones+"izquierda ";
		return opciones;
	}


	private boolean puedeAvanzar(Jugador jugador, String direccion) {
		int posicionX = jugador.getLugarTableroX();
		int posicionY = jugador.getLugarTableroY();
		int posicionAnteriorX = jugador.getPosicionAnteriorX();
		int posicionAnteriorY = jugador.getPosicionAnteriorY();
		int cantidadFilas = mapa.length;
		int cantidadColumnas = mapa[0].length;

		if (direccion.equals("arriba") && posicionX > 0 && posicionAnteriorX != posicionX - 1
				&& mapa[posicionX - 1][posicionY] != null) {
			return true;

		} else if (direccion.equals("abajo") && posicionX + 1 < cantidadFilas && posicionAnteriorX != posicionX + 1
				&& mapa[posicionX + 1][posicionY] != null) {
			return true;

		} else if (direccion.equals("izquierda") && posicionY > 0 && posicionAnteriorY != posicionY - 1
				&& mapa[posicionX][posicionY - 1] != null) {
			return true;
		} else if (direccion.equals("derecha") && posicionY + 1 < cantidadColumnas && posicionAnteriorY != posicionY + 1
				&& mapa[posicionX][posicionY + 1] != null) {
			return true;
		}

		return false;

	}

}
