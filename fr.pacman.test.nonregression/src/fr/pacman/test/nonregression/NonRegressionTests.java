package fr.pacman.test.nonregression;

import fr.pacman.commons.main.PacmanGenerator_Abs;

import java.io.File;
import java.util.Collection;

import org.junit.runners.Parameterized.Parameters;

public class NonRegressionTests extends AbstractNonRegressionTests {

	public NonRegressionTests(Class<? extends PacmanGenerator_Abs> cls, File modelFolder, File model) {
		super(cls, modelFolder, model);
	}

	@Parameters(name = "{0}-{1}")
	public static Collection<Object[]> retrieveTests() {
		return retrieveTests("resources");
	}

}
