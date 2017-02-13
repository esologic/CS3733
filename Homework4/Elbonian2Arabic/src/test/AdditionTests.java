package src.test;

import static org.junit.Assert.assertEquals;

import src.converter.ElbonianArabicConverter;
import src.converter.MalformedNumberException;
import org.junit.Test;

public class AdditionTests {

	@Test
	public void testAdditionMMMDCCYXJIII() throws MalformedNumberException {
		ElbonianArabicConverter eac = new ElbonianArabicConverter("MMMDCCYXJIII");
		assertEquals("Conversion error: Elbonian addition of letters", 3768, eac.toArabic());
	}
}
