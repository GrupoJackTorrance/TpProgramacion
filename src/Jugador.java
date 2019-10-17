
public class Jugador implements Comparable<Jugador> {
	private int nroTurno;
	private String personaje;
	private int puntos;
	private int objEfectos;
	private int lugarTableroX;
	private int lugarTableroY;
	private int posicionAnteriorX;
	private int posicionAnteriorY;
	private String nombre;

	public Jugador(String personaje,String nombre) {
		
		this.personaje = personaje;
		this.puntos = 0;
		this.objEfectos=0;
		this.nombre = nombre;
	}


	@Override
	public String toString() {
		return "Jugador [personaje=" + personaje + ", puntos=" + puntos + ", objEfectos=" + objEfectos
				+ ", lugarTableroX=" + lugarTableroX + ", lugarTableroY=" + lugarTableroY + ", posicionAnteriorX="
				+ posicionAnteriorX + ", posicionAnteriorY=" + posicionAnteriorY + ", Nombre=" + nombre + "]";
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
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntos() {
		return this.puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public void sumarPuntos(int puntos) {
		this.puntos += puntos;
	}

	public void restarPuntos(int puntos) {
		this.puntos = Math.max(0, this.puntos - puntos);
	}

	public void elegirCaminoEnUnion(int x, int y) {
		this.posicionAnteriorX = this.lugarTableroX;
		this.posicionAnteriorY = this.lugarTableroY;
		this.lugarTableroX = x;
		this.lugarTableroY = y;

	}

	public Sala crearSala(int puntosObjetivo, int maxPartidas) {
		Sala sala = new Sala(this, puntosObjetivo, maxPartidas);
		return sala;
	}

	public boolean salirSala(Sala sala) {
		return sala.sacarJugadorSala(this);

	}

	public boolean entrarEnSala(Sala sala) {
		return sala.addJugadorSala(this);

	}

	public int tirarDado() {
		Dado dado=new Dado(); 
		return dado.tirar();
	}

	// Para comparar jugadores por puntos y si son iguales por nombre
	@Override
	public int compareTo(Jugador jugador2) {
		if (puntos < jugador2.puntos) {
			return 1;
		}
		if (puntos > jugador2.puntos) {
			return -1;
		}
		if (puntos == jugador2.puntos)
			return this.getNombre().compareTo(jugador2.getNombre());
		return 0;
	}

	public boolean usarObjeto(Jugador atacado) {
		if (this.objEfectos == 1) {
			atacado.puntos -= 5;
			return true;
		}
		return false;
	}

}
