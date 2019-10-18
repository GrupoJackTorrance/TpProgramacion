package logica;
public class Dado {

	private RandomGenerator numero;

	public Dado() {
		this.numero = new ActualRandomGenerator();
	}

	public void setRandomGenerator(RandomGeneratorStub randomGeneratorStub) {
		this.numero = randomGeneratorStub;

	}

	public int tirar() {
		return this.numero.obtenerAleatorioMenorQue(6) + 1; // El rango del dado es de 1 a 6
	}
}