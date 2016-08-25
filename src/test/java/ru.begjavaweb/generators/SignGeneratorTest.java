import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.util.HashMap;
import ru.begjavaweb.generators.SignGenerator;


public class SignGeneratorTest  {
	@Test
	public void testRandomNumberGenerator() {
		int minVal = 0;
		int maxVal = 5;
		SignGenerator sg = new SignGenerator();
		for (int i=0, j=100; i<j; i++ ) {
			int t = sg.getRandomNumberInRange(minVal, maxVal);
			assertTrue(t >= minVal && t<=maxVal);
		}
	}

	@Test
	public void testSignMap() {
		SignGenerator sg = new SignGenerator();
		HashMap<Integer, String> map = sg.getSignMap();
		assertEquals("rock", map.get(0));
		assertEquals("scissors", map.get(1));
		assertEquals("paper", map.get(2));

	}

	@Test
	public void testGeneratedSign() {
		SignGenerator sg = new SignGenerator();
		for (int i=0, j=100; i<j; i++ ) {
			String sign = sg.getGeneratedSign();
			System.out.println("sign = "+sign);
			assertTrue(sign == "rock" || sign == "scissors" || sign == "paper");
		}
	}
}