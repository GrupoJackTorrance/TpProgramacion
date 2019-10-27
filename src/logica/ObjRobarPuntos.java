package logica;

import java.util.List;

public class ObjRobarPuntos extends EfectoDarObjeto{
	private int cantPuntosARobar;
	
	public ObjRobarPuntos() {
		super(2, "Robar puntos");
		cantPuntosARobar = 8;
	}

	@Override
	public boolean aplicarObj(Jugador jugador, List<Jugador> listaJugadores) {
		int indice = listaJugadores.indexOf(jugador);
		if(jugador.getPuntos()< cantPuntosARobar)
			return false;
		jugador.restarPuntos(cantPuntosARobar);
		//PARA sumarle los puntos al siguiente jugador de la lista
		indice = (indice==0)?listaJugadores.size()-1:indice-1;
		listaJugadores.get(indice).sumarPuntos(cantPuntosARobar);
		return true;
	}

	@Override
	public void accion(Jugador jugador) {
		jugador.setObjEfectos(this);
		
	}

	public int getCantPuntosARobar() {
		return cantPuntosARobar;
	}
	
	
}
