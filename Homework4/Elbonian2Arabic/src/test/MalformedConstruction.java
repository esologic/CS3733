package src.test;

import src.converter.ElbonianArabicConverter;
import src.converter.MalformedNumberException;
import org.junit.Test;

@SuppressWarnings("unused")
public class MalformedConstruction {
	@Test(expected = MalformedNumberException.class)
	public void testMixElbonianArabic() throws MalformedNumberException {
		ElbonianArabicConverter eac = new ElbonianArabicConverter("9V");
	}
}
