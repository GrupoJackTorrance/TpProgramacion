
public class Casilla {

	private String color; // color diferenciara el efecto? supongo que nos va a servir para la parte
							// gráfica
	private Efecto efecto;
	private boolean esUnion;

	public Casilla(String color, Efecto efecto, boolean esUnion) {

		this.color = color;
		this.efecto = efecto;
		this.esUnion = esUnion;
	}

	public void aplicarEfecto(Jugador jugador) {
		String tipo = efecto.getTipo();
		if (tipo.equals("sumarPuntos")) {
			efecto.dar1pto(jugador);
		} else if (tipo.equals("restarPuntos")) {
			efecto.restarPuntos(jugador);
		} else if (tipo.equals("objetoPoder"))
			efecto.darObjeto(jugador);
		else
			efecto.neutro(jugador);
	}

	public String getColor() {
		return color;
	}

	public boolean getEsUnion() {
		return esUnion;
	}

}
