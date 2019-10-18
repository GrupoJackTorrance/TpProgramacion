package logica;
public class EfectoDarObjeto  extends Efecto{

	@Override
	public void accion(Jugador jugador) {
		if(jugador.getObjEfectos()==0)
		jugador.setObjEfectos(1);		
	}

}
