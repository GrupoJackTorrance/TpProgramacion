
import org.junit.Assert;
import org.junit.Test;

public class DadoTest {

	@Test
	public void queSumaCorrectamenteLosValores() {
		Dado dado1 = new Dado();
		dado1.setRandomGenerator(new RandomGeneratorStub(4));

		Assert.assertEquals(4, dado1.tirar());
	}
}
