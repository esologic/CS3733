package src.test;

import src.converter.ElbonianArabicConverter;
import src.converter.MalformedNumberException;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ AdditionTests.class, SubtractionTests.class, MalformedAddition.class,
		MalformedSubtraction.class, MalformedConstruction.class })
public class FeatureTestSuite {
	// the class remains empty,
	// used only as a holder for the above annotations
}