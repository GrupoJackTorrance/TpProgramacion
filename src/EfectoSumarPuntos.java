
public class EfectoSumarPuntos extends Efecto{
	int cantidad;
	public EfectoSumarPuntos(int cantidad) {
		this.cantidad=cantidad;
	
	}
	@Override
	public void accion(Jugador jugador) {
		jugador.setPuntos(jugador.getPuntos()+this.cantidad);
	}

}
