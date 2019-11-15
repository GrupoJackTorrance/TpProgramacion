package logica;

import java.util.List;

public class Jugador implements Comparable<Jugador> {
	private int nroTurno;
	private String personaje;
	private int puntos;
	private int cantidadObjetos;
	private EfectoDarObjeto obj1;
	private EfectoDarObjeto obj2;
	private EfectoDarObjeto obj3;
	private int lugarTableroX;
	private int lugarTableroY;
	private int posicionAnteriorX;
	private int posicionAnteriorY;
	private String nombre;
	private Personaje miPersonaje;
	private String ubicacion;

	public String getUbicacion() {
		return ubicacion;
	}




	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}




	public Jugador(String personaje,String nombre) {
		
		this.personaje = personaje;
		this.puntos = 0;
		this.obj1 = new ObjSinEfecto();
		this.obj2 = new ObjSinEfecto();
		this.obj3 = new ObjSinEfecto();
		this.nombre = nombre;
		this.miPersonaje = new Personaje (nombre, 100, 100, 10);
	}



	
	@Override
	public String toString() {
		return "Jugador [personaje=" + personaje + ", puntos=" + puntos + ", objEfectos=" + obj1
				+ ", lugarTableroX=" + lugarTableroX + ", lugarTableroY=" + lugarTableroY + ", posicionAnteriorX="
				+ posicionAnteriorX + ", posicionAnteriorY=" + posicionAnteriorY + ", Nombre=" + nombre + "]";
	}

	//Esto no se uso por ahora
//	public void update() {
//		miPersonaje.actualizarCoordenadasGraficas(lugarTableroX, lugarTableroY);
//	}
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

	public EfectoDarObjeto getObjEfectos() {
		if(this.obj1.getIdObjeto()!=0)	
			return this.obj1;
		else if(this.obj2.getIdObjeto()!=0)
			return this.obj2;
		else 
			return this.obj3;
	}
	
	
	public EfectoDarObjeto getObj2() {
		return obj2;
	}

	public EfectoDarObjeto getObj3() {
		return obj3;
	}


	public EfectoDarObjeto getObj1() {
		return obj1;
	}

	public void setObj1(EfectoDarObjeto obj1) {
		this.obj1 = obj1;
	}


	public void setObjEfectos(EfectoDarObjeto objEfectos) {
		if(this.obj1.getIdObjeto() ==0) {
			setObj1(objEfectos);
			cantidadObjetos++;
		}else if(this.obj2.getIdObjeto() ==0) {	
			setObj2(objEfectos);
			cantidadObjetos++;
		}else {	
			setObj3(objEfectos);
			cantidadObjetos++;
		}
	}

	public void setObj2(EfectoDarObjeto obj2) {
		this.obj2 = obj2;
	}

	public void setObj3(EfectoDarObjeto obj3) {
		this.obj3 = obj3;
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
		//this.update();
	}

	public Sala crearSala(String nombre, int cantMaxJugadores, int puntosObjetivo, int maxPartidas) {
		Sala sala = new Sala(this, puntosObjetivo, maxPartidas, nombre, cantMaxJugadores);
		//return new Sala(this, puntosObjetivo, maxPartidas);
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

	public boolean usarObjeto(Jugador atacado, List <Jugador> listaJugadores, int obj) {
		if(this.getObj1().getIdObjeto() == obj) {
			this.getObj1().aplicarObj(atacado, listaJugadores);
			this.setObj1(new ObjSinEfecto());
			cantidadObjetos--;
		}
		if(this.getObj2().getIdObjeto() == obj) {
			this.getObj2().aplicarObj(atacado, listaJugadores);	
			this.setObj2(new ObjSinEfecto());
			cantidadObjetos--;
		}
		if(this.getObj3().getIdObjeto() == obj) {
			this.getObj3().aplicarObj(atacado, listaJugadores);		
			this.setObj3(new ObjSinEfecto());
			cantidadObjetos--;
		}
		return true;
		
	}




	public int getCantidadObjetos() {
		return cantidadObjetos;
	}
	
	
	//Esto no se uso por ahora
//	public void update() {
//		miPersonaje.actualizarCoordenadasGraficas(lugarTableroX, lugarTableroY);
//	}

}
