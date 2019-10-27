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

	
		/**
		 * 1-Descuenta 5 puntos a un jugador
		 * 2-Saltear turno de un jugador
		 * 3-Robar 30% de los puntos a un jugador y sumarselos al jugador
			int objeto = (int) (Math.random()*4) +1;
			if(jugador.getObjEfectos()==0)
				jugador.setObjEfectos(objeto);		
		 */

}
