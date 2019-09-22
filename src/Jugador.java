
public class Jugador {
int nroTurno;
String personaje;
int puntos = 0;
String objEfectos;
int lugarTableroX;
int lugarTableroY;
String Nombre;

public void setPuntos(int pto) {
	puntos = pto;
}

public void sumarPuntos(int pto) {
	puntos += pto;
}

public boolean usarObjeto() {
	return true;
}
public void elegirCaminoEnUnion() {
	
}
public boolean crearSala() {
	return true;
}
public boolean salirSala() {
	return true;
}
public boolean entrarEnSalir() { //Que es este?
	return true;
}



}
