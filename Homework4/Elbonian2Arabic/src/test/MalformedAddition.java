package src.test;

import src.converter.ElbonianArabicConverter;
import src.converter.MalformedNumberException;
import org.junit.Test;

@SuppressWarnings("unused")
public class MalformedAddition {

	@Test(expected = MalformedNumberException.class)
	public void testNonDescending() throws MalformedNumberException {
		ElbonianArabicConverter eac = new ElbonianArabicConverter("CM");
	}
}
