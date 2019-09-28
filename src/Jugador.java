

public class Jugador implements Comparable<Jugador> {
private int nroTurno;
private String personaje;
private int puntos = 0;
private int objEfectos;
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

public Jugador(String personaje,String nombre) {
	this.personaje = personaje;
	Nombre = nombre;
}

@Override
public String toString() {
	return "Jugador [personaje=" + personaje + ", puntos=" + puntos + ", objEfectos=" + objEfectos + ", lugarTableroX="
			+ lugarTableroX + ", lugarTableroY=" + lugarTableroY + ", posicionAnteriorX=" + posicionAnteriorX
			+ ", posicionAnteriorY=" + posicionAnteriorY + ", Nombre=" + Nombre + "]";
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

public int getObjEfectos() {
	return objEfectos;
}

public void setObjEfectos(int objEfectos) {
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

public void setPuntos(int puntos) {
	this.puntos =puntos;
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
public int elegirCaminoEnUnion(int x, int y) {
		System.out.println("Llegaste a una union. Elegi hacia donde queres ir:");
		System.out.println("X: "+x+" Y: "+y);
		return x;
}
public Sala crearSala(int puntosObjetivo,int maxPartidas) {
	Sala sala= new Sala(this,puntosObjetivo,maxPartidas);
	return sala;
}
public boolean salirSala(Sala sala) {
	return sala.sacarJugadorSala(this);
	
}
public boolean entrarEnSala(Sala sala) {
	return sala.addJugadorSala(this);
	
}

public int tirarDado(){
	return 3; // Hardcodeado para las pruebas
}

//Para comparar jugadores por puntos y si son iguales por nombre
@Override
public int compareTo(Jugador jugador2) {
	if (puntos < jugador2.puntos) {
        return -1;
    }
    if (puntos > jugador2.puntos) {
        return 1;
    }
    if(puntos == jugador2.puntos)
    	return this.getNombre().compareTo(jugador2.getNombre());
    return 0;
}
	
public boolean UsarObjeto(Jugador atacado) {
	if(this.objEfectos==1) {
		atacado.puntos-=5;
		return true;
	}
	return false;
}

}
