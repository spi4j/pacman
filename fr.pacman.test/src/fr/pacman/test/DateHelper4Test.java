package fr.pacman.test;

import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;

class DateHelper4Test {

	private static final String c_finPatternDate = ")]";

	private static final String c_debutPatternDate = "[DATE(";

	/**
	 * Constructeur prive.
	 */
	private DateHelper4Test() {
		super();
	}

	public static String update(String p_expectedText, String p_fileText) {

		int v_indexRechercheDate = 0;

		while (p_expectedText.indexOf(c_debutPatternDate, v_indexRechercheDate) >= 0) {

			v_indexRechercheDate = p_expectedText.indexOf(c_debutPatternDate, v_indexRechercheDate);
			final String v_datePattern = p_expectedText.substring(v_indexRechercheDate + c_debutPatternDate.length(),
					p_expectedText.indexOf(c_finPatternDate, v_indexRechercheDate));

			// Nombre de caracteres permettant de distinguer avant et apres la date.
			final int v_N = 5;

			// Recuperation de N caracteres avant la balise de dates.
			final String v_preDate = p_expectedText.substring(v_indexRechercheDate - v_N, v_indexRechercheDate);
			final int v_indexFinPattern = p_expectedText.indexOf(c_finPatternDate, v_indexRechercheDate)
					+ c_finPatternDate.length();
			final String v_postDate = p_expectedText.substring(v_indexFinPattern, v_indexFinPattern + v_N);

			final SimpleDateFormat v_sdf = new SimpleDateFormat(v_datePattern);
			final int v_indexDebutDateGeneree = p_fileText.indexOf(v_preDate, v_indexRechercheDate - 2 * v_N) + v_N;
			final int v_indexFinDateGeneree = p_fileText.indexOf(v_postDate, v_indexDebutDateGeneree);
			final String v_dateActual = p_fileText.substring(v_indexDebutDateGeneree, v_indexFinDateGeneree);
			try {
				v_sdf.parse(v_dateActual);
			} catch (final ParseException v_e) {
				fail(v_e.toString());
			}
			p_expectedText = p_expectedText.replace(c_debutPatternDate + v_datePattern + c_finPatternDate,
					v_dateActual);
		}
		return p_expectedText;
	}
}
