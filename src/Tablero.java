
public class Tablero {
String nombre;
Casilla mapa[][];

public void tirarDado() {}

public boolean avanzarJugador() {
	return true;
}

public boolean puedeAvanzar() {
	return true;
}

public void crearMapa1(Casilla mapa[][]){ //mapa1 contiene 3 casilleros de 1 fila
	
	mapa[0][0] = new Casilla ();
	mapa[0][0].color = "azul";
	mapa[0][1] = new Casilla ();
	mapa[0][2] = new Casilla ();
	this.mapa = mapa;
	
}

}
