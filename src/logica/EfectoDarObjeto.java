package logica;

import java.util.List;

public abstract class EfectoDarObjeto extends Efecto{
	
	int idObjeto;
	String nombreObjeto;
	
	
	public EfectoDarObjeto(int idObjeto, String nombreObjeto) {
		this.idObjeto = idObjeto;
		this.nombreObjeto = nombreObjeto;
	}

	@Override
	public abstract void accion(Jugador jugador);
	
	public abstract boolean aplicarObj(Jugador jugador, List<Jugador> listaJugadores);	


	public int getIdObjeto() {
		return idObjeto;
	}


	public void setIdObjeto(int idObjeto) {
		this.idObjeto = idObjeto;
	}


	public String getNombreObjeto() {
		return nombreObjeto;
	}


	public void setNombreObjeto(String nombreObjeto) {
		this.nombreObjeto = nombreObjeto;
	}

}
