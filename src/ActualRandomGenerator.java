
import java.util.Random;

public class ActualRandomGenerator extends RandomGenerator {

	@Override
	public int obtenerAleatorioMenorQue(int maximo) { // Mas de 6 no se puede tirar
		return new Random().nextInt(maximo);

	}
}