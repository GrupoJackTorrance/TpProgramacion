import java.util.LinkedList;
import java.util.List;

public abstract class Tablero {
	protected String nombre;
	protected Casilla  mapa[][];
	protected List<Jugador> jugadores=new LinkedList<Jugador>();



	// ---------- C O N S T R U C T O R---------------------
	public Tablero(List<Jugador> jugadores) {
		this.nombre = "Tablero partida";
		this.jugadores = jugadores;
		// EfectoSumarPuntos verde
		// EfectoRestarPuntos rojo
		// EfectoNeutro blanco
		// EfectoDarObjeto amarillo
	
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
		return cantidad;
	}

	public String[] obtenerOpciones(Jugador jugador) {
		String opciones[] = new String[12];
		int i = 0;
		if (puedeAvanzar(jugador, "arriba")) {
			opciones[i] = ((Integer) (jugador.getLugarTableroX() - 1)).toString();
			i++;
			opciones[i] = ((Integer) jugador.getLugarTableroY()).toString();
			i++;
			opciones[i] ="arriba";
			i++;
		}
		if (puedeAvanzar(jugador, "abajo")) {
			opciones[i] = ((Integer) (jugador.getLugarTableroX() + 1)).toString();
			i++;
			opciones[i] = ((Integer) jugador.getLugarTableroY()).toString();
			i++;
			opciones[i] = "abajo";
			i++;
		}

		if (puedeAvanzar(jugador, "derecha")) {
			opciones[i] = ((Integer) jugador.getLugarTableroX()).toString();
			i++;
			opciones[i] = ((Integer) (jugador.getLugarTableroY() + 1)).toString();
			i++;
			opciones[i] = "derecha";
			i++;
		}
		if (puedeAvanzar(jugador, "izquierda")) {
			opciones[i] = ((Integer) jugador.getLugarTableroX()).toString();
			i++;
			opciones[i] = ((Integer) (jugador.getLugarTableroY() - 1)).toString();
			i++;
			opciones[i] = "izquierda";

		}
		//opciones[0]=((Integer)((i-1)/3)).toString();
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
