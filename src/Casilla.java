import java.awt.Color;

public class Casilla {


	private Color color; // color diferenciara el efecto? supongo que nos va a servir para la parte
							// gráfica

	private Efecto efecto;
	private boolean esUnion;

	public Casilla(Color color, Efecto efecto, boolean esUnion) {

		this.color = color;
		this.efecto = efecto;
		this.esUnion = esUnion;
	}

	public void aplicarEfecto(Jugador jugador) {
	efecto.accion(jugador);
	}

	public Color getColor() {
		return color;
	}

	public boolean getEsUnion() {
		return esUnion;
	}

}
