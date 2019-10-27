package logica;

import java.util.List;

public class ObjSinEfecto extends EfectoDarObjeto{

	
	public ObjSinEfecto() {
		super(0, "");
		
	}

	@Override
	public boolean aplicarObj(Jugador jugador, List<Jugador> listaJugadores) {
		
		return true;
	}
	
	@Override
	public void accion(Jugador jugador) {
		jugador.setObjEfectos(this);
		
	}
	
}
