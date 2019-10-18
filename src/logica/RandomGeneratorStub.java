package logica;
public class RandomGeneratorStub extends RandomGenerator {

	private int valor;

	public RandomGeneratorStub(int valorFijo) { // Se utilizan para el Test
		this.valor = valorFijo - 1;
	}

	@Override
	public int obtenerAleatorioMenorQue(int maximo) {
		return this.valor;
	}
}