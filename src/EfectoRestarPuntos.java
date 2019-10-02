
public class EfectoRestarPuntos extends Efecto {
	int cantidad;
	public EfectoRestarPuntos(int cantidad) {
		this.cantidad=cantidad;
	}
	@Override
	public void accion(Jugador jugador) {
		if(jugador.getPuntos()-this.cantidad>0)
		jugador.setPuntos(jugador.getPuntos()-this.cantidad);
		else
			jugador.setPuntos(0);
		
	}

}
