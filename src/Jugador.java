
public class Jugador {
static private int nroTurno;
private String personaje;
private int puntos = 0;
private String objEfectos;
private int lugarTableroX;
private int lugarTableroY;
private int posicionAnteriorX;
private int posicionAnteriorY;
private String Nombre;



public Jugador(int nroTurno, String personaje, int puntos, int lugarTableroX, int lugarTableroY, int posicionAnteriorX,
		int posicionAnteriorY, String nombre) {
	this.nroTurno = nroTurno;
	this.personaje = personaje;
	this.puntos = puntos;
	this.lugarTableroX = lugarTableroX;
	this.lugarTableroY = lugarTableroY;
	this.posicionAnteriorX = posicionAnteriorX;
	this.posicionAnteriorY = posicionAnteriorY;
	Nombre = nombre;
}

public int getNroTurno() {
	return nroTurno;
}

public void setNroTurno(int nroTurno) {
	this.nroTurno = nroTurno;
}

public String getPersonaje() {
	return personaje;
}

public void setPersonaje(String personaje) {
	this.personaje = personaje;
}

public String getObjEfectos() {
	return objEfectos;
}

public void setObjEfectos(String objEfectos) {
	this.objEfectos = objEfectos;
}

public int getLugarTableroX() {
	return lugarTableroX;
}

public void setLugarTableroX(int lugarTableroX) {
	this.lugarTableroX = lugarTableroX;
}

public int getLugarTableroY() {
	return lugarTableroY;
}

public void setLugarTableroY(int lugarTableroY) {
	this.lugarTableroY = lugarTableroY;
}

public int getPosicionAnteriorX() {
	return posicionAnteriorX;
}

public void setPosicionAnteriorX(int posicionAnteriorX) {
	this.posicionAnteriorX = posicionAnteriorX;
}

public int getPosicionAnteriorY() {
	return posicionAnteriorY;
}

public void setPosicionAnteriorY(int posicionAnteriorY) {
	this.posicionAnteriorY = posicionAnteriorY;
}

public String getNombre() {
	return Nombre;
}

public void setNombre(String nombre) {
	Nombre = nombre;
}

public int getPuntos() {
	return puntos;
}

public void setPuntos(int pto) {
	puntos = pto;
}

public void sumarPuntos(int puntos) {
	this.puntos += puntos;
}
public void restarPuntos(int puntos) {
	this.puntos-=puntos;
}

public boolean usarObjeto() {
	return true;
}
public void elegirCaminoEnUnion() {
	
}
public boolean crearSala() {
	Sala sala= new Sala();
	return true;
}
public boolean salirSala() {
	return true;
}
public boolean entrarEnSala() {
	return true;
}



}
