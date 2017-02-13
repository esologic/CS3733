package src.test;

import src.converter.ElbonianArabicConverter;
import src.converter.MalformedNumberException;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SubtractionTests {

	@Test
	public void testSubtractionMMMmMcCxX() throws MalformedNumberException {
		ElbonianArabicConverter eac = new ElbonianArabicConverter("MMMmMcCxX");

		assertEquals("Conversion error: Elbonian lowercase letters", 3999, eac.toArabic());
	}
}
