package fr.pacman.test;

enum FileType4Test_Enum {

	JAVA(".java", ".java.txt"), XML(".xml", ".xml.txt"), SQL(".sql", ".sql.txt"), XHTML(".xhtml", "xhtml.txt");

	// Extension pour le fichier de reference.
	public final String _ext_exp;

	// Extension pour le fichier genere.
	public final String _ext_gen;

	FileType4Test_Enum(final String p_extJavaGenerated, final String p_extJavaExpected) {

		_ext_gen = p_extJavaGenerated;

		_ext_exp = p_extJavaExpected;
	}

	public String get_expectedFileExtension() {
		return _ext_exp;
	}

	public String get_generatedFileExtension() {
		return _ext_gen;
	}
}
