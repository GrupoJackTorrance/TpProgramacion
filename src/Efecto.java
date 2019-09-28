
public class Efecto {
	int id;
	String tipo;

	public Efecto(String tipo) {
		this.tipo = tipo;
	}

	public void dar1pto(Jugador jugador) {
		jugador.sumarPuntos(1);
	}

	public void neutro(Jugador jugador) {
		return;
	}

	public void restarPuntos(Jugador jugador) {
		jugador.restarPuntos(1);

	}

	public String getTipo() {
		return this.tipo;
	}

	public void darObjeto(Jugador jugador) {
		jugador.setObjEfectos(1);
	}
}
