package src.test;

import src.converter.ElbonianArabicConverter;
import src.converter.MalformedNumberException;
import org.junit.Test;

@SuppressWarnings("unused")
public class MalformedSubtraction {

	@Test(expected = MalformedNumberException.class)
	public void testMultipleUppercaseFollowingLowercase() throws MalformedNumberException {
		ElbonianArabicConverter eac = new ElbonianArabicConverter("MMmMM");
	}
}
