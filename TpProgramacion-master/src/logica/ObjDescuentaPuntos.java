package logica;

import java.util.List;

public class ObjDescuentaPuntos extends EfectoDarObjeto{

	private int puntosParaDescontar;
	
	public ObjDescuentaPuntos() {
		super(1, "Descontar Puntos");
		puntosParaDescontar = 5;
	}

	@Override
	public boolean aplicarObj(Jugador jugador, List<Jugador> listaJugadores) {
		if(jugador.getPuntos()< puntosParaDescontar)
			return false;
		jugador.restarPuntos(puntosParaDescontar);
		return true;
	}

	@Override
	public void accion(Jugador jugador) {
		jugador.setObjEfectos(this);
		
	}

	public int getPuntosParaDescontar() {
		return puntosParaDescontar;
	}

	
}
