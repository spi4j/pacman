package fr.pacman.commons.services;

import java.io.File;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;

import fr.pacman.commons.errorgen.ErrorGeneration;

/**
 * Classe utilitaire pour travailler sur les chaines de caractères.
 * 
 * @author MINARM
 */
public final class StringUtils {
	private static final String c_PLAIN_ASCII = "AaEeIiOoUu" // grave
			+ "AaEeIiOoUuYy" // acute
			+ "AaEeIiOoUuYy" // circumflex
			+ "AaOoNn" // tilde
			+ "AaEeIiOoUuYy" // umlaut
			+ "Aa" // ring
			+ "Cc" // cedilla
			+ "OoUu" // double acute
			+ "___"; // hyphen

	private static final String c_UNICODE = "\u00C0\u00E0\u00C8\u00E8\u00CC\u00EC\u00D2\u00F2\u00D9\u00F9"
			+ "\u00C1\u00E1\u00C9\u00E9\u00CD\u00ED\u00D3\u00F3\u00DA\u00FA\u00DD\u00FD"
			+ "\u00C2\u00E2\u00CA\u00EA\u00CE\u00EE\u00D4\u00F4\u00DB\u00FB\u0176\u0177"
			+ "\u00C3\u00E3\u00D5\u00F5\u00D1\u00F1"
			+ "\u00C4\u00E4\u00CB\u00EB\u00CF\u00EF\u00D6\u00F6\u00DC\u00FC\u0178\u00FF" + "\u00C5\u00E5"
			+ "\u00C7\u00E7" + "\u0150\u0151\u0170\u0171" + "\u002D\u2212\u2012";

	private static final String c_RESERVED_KEYWORDS = "|abstract|assert|boolean|break|byte|case|catch|char|class"
			+ "|continue|default|do|double|else|enum|extends|false|final|finally|float|for|if|implements|import|instanceof"
			+ "|int|interface|long|native|new|null|package|private|protected|public|return|short|static|strictfp|super|switch"
			+ "|synchronized|this|throw|throws|transient|try|true|void|volatile|while";

	private static Pattern _pt = Pattern.compile("[^a-zA-Z0-9\s_]");
	private static boolean _deleteSpecialChars = true;

	/**
	 * Constructeur privé.
	 */
	private StringUtils() {
		// RAS
	}

	/**
	 * Supprime les caractères spéciaux et les remplace par leurs équivalents.
	 * 
	 * @param p_s la chaine de caractère à normaliser
	 * @return la chaine normalisée
	 */
	public static String normalize(final String p_s) {
		if (p_s == null) {
			return null;
		}
		final StringBuilder v_sb = new StringBuilder();
		final int v_n = p_s.length();
		for (int v_i = 0; v_i < v_n; v_i++) {
			final char v_c = p_s.charAt(v_i);
			final int v_pos = c_UNICODE.indexOf(v_c);
			if (v_pos > -1) {
				v_sb.append(c_PLAIN_ASCII.charAt(v_pos));
			} else {
				v_sb.append(v_c);
			}
		}
		return _deleteSpecialChars ? deleteSpecialChars(p_s.toString()) : p_s.toString();
	}

	/**
	 * Retourne la notation CamelCase d'une chaine de caractères.
	 * 
	 * @param p_s la chaine à écrire
	 * @return la notation CamelCase de p_s
	 */
	public static String sanitize(final String p_s) {
		if (p_s == null) {
			return null;
		}
		final StringBuilder v_sb = new StringBuilder();
		final String[] v_splits = p_s.split("\\s");
		for (final String v_split : v_splits) {
			if (v_split.length() > 0) {
				v_sb.append(v_split.substring(0, 1).toUpperCase());
			}
			if (v_split.length() > 1) {
				v_sb.append(v_split.substring(1));
			}
		}
		return v_sb.toString();
	}

	/**
	 * Ici on va plus loion que la méthode precedente normalize qui ne s'occupe que
	 * des caracteres accentuees, on supprime tous les caracteres speciaux qui n'ont
	 * rien a voir avec le Java ou le sql.
	 * 
	 * @param p_s la chaine à écrire
	 * @return la chaine delestes des caracteres speciaux.
	 */
	public static String deleteSpecialChars(String p_s) {
		Matcher match = _pt.matcher(p_s);
		while (match.find()) {
			String s = match.group();
			p_s = p_s.replaceAll("\\" + s, "");
		}
		return p_s;
	}

	/**
	 * Ecrit une ligne au format /* ABCDEF \*\/
	 * 
	 * @param p_str  la chaine à écrire à la place de ABCDEF
	 * @param p_size la taille de la ligne à générer
	 * @return la ligne générée
	 */
	public static String genSlashedLine(final String p_str, final Integer p_size) {
		final StringBuilder v_sb = new StringBuilder("/* ");
		if (p_str != null) {
			v_sb.append(p_str);
		}
		while (v_sb.length() < p_size) {
			v_sb.append(' ');
		}
		v_sb.append(" */");
		return v_sb.toString();
	}

	/**
	 * Retourne la date du jour au format p_format.
	 * 
	 * @param p_format le format attendu de la date
	 * @return la date du jour formatée avec p_format
	 */
	public static String date(final String p_format) {
		final SimpleDateFormat v_formatter = new SimpleDateFormat(p_format);
		return v_formatter.format(new Date());
	}

	/**
	 * Echappe les caractères spéciaux d'une chaine, tels que les guillemets et les
	 * retours chariots.
	 * 
	 * @param p_str la chaine à échapper
	 * @return la chaine échappée
	 */
	public static String escape(final String p_str) {
		if (p_str == null) {
			return "";
		}
		return p_str.replaceAll("\"", "\\\\\"").replaceAll("\r\n", "\\\\n").replaceAll("\n", "\\\\n").replaceAll("\t",
				"   ");
	}

	/**
	 * Inverse une chaine de caractères, selon un séparateur :
	 * reverse("abc.def.ghi", "\\.") --> "ghi.def.abc"
	 * 
	 * @param p_chaine            la chaine originale
	 * @param p_patternSeparation le pattern de séparation
	 * @param p_nouveauSeparateur la nouvelle chaine de séparation
	 * @return la chaine inversée
	 */
	public static String reverse(final String p_chaine, final String p_patternSeparation,
			final String p_nouveauSeparateur) {
		final String[] v_split = p_chaine.split(p_patternSeparation);
		final StringBuilder v_builder = new StringBuilder(p_chaine.length());
		for (int v_i = v_split.length - 1; v_i >= 0; v_i--) {
			v_builder.append(v_split[v_i]).append(p_nouveauSeparateur);
		}
		if (v_builder.length() >= p_nouveauSeparateur.length()) {
			v_builder.setLength(v_builder.length() - p_nouveauSeparateur.length());
		}
		return v_builder.toString();
	}

	/**
	 * Retourne la liste des valeurs.
	 * 
	 * @param p_property  la propriété
	 * @param p_separator le séparateur entre les propriétés
	 * @return la liste valeurs de la propriété
	 */
	public static List<String> getPropertyAsList(final Object p_property, final String p_separator) {
		final List<String> v_props = new ArrayList<String>();
		if (p_property instanceof String) {
			final String[] v_properties = ((String) p_property).trim().split(p_separator);
			for (final String v_prop : v_properties) {
				v_props.add(v_prop.trim());
			}
		}
		return v_props;
	}

	/**
	 * Retourne le URI du parent du modèle d'un objet
	 * 
	 * @param p_o l'objet du modèle
	 * @return l'URI du parent
	 */
	// public static String getModuleFolderFromEObject (final EObject p_o)
	// {
	// try
	// {
	// if (p_o == null)
	// {
	// return "[ERROR] Impossible de trouver le modèle contenant un objet null";
	// }
	// final Resource v_resource = p_o.eResource();
	// if (v_resource == null)
	// {
	// return "[ERROR] Impossible de trouver le modèle pour l'objet suivant : " +
	// p_o.toString();
	// }
	// final URI v_uri = v_resource.getURI();
	// if (v_uri == null)
	// {
	// return "[ERROR] Impossible de calculer l'URI de la ressource suivante : " +
	// v_resource.toString();
	// }
	// return getModuleFolderFromUri(v_uri);
	// }
	// catch (Throwable v_t)
	// {
	// return "[ERROR] " + v_t.toString() + "\n\nOBJECT = " + p_o + "\nRESOURCE = "
	// + p_o.eResource() + "\nURI = "
	// + p_o.eResource().getURI();
	// }
	// }

	/**
	 * Permet d'obtenir répertoire du modèle à partir d'une instance d'EObject.
	 * 
	 * @param p_EObject L'EObject à prendre en comtpe.
	 * @return La chaîne de caractères représentant le répertoire du modèle.
	 */
	public static String getModuleFolderFromEObject(final EObject p_EObject) {
		return getModuleFolderFromUri(getUriFromEObject(p_EObject));
	}

	/**
	 * Permet d'obtenir une instance d'URI à partir d''une instance d'EObject.
	 * 
	 * @param p_EObject L'EObject à prendre en comtpe.
	 * @return L'URI désirée.
	 */
	public static URI getUriFromEObject(final EObject p_EObject) {
		try {
			if (p_EObject == null) {
				ErrorGeneration.printMessageFmt("[ERROR] Impossible de trouver le modèle contenant un objet null", "?");
				return null;
			}
			final Resource v_resource = p_EObject.eResource();
			if (v_resource == null) {
				String v_objectString = null;
				try {
					final Method v_getNameMethod = p_EObject.getClass().getMethod("getName");
					v_objectString = p_EObject.getClass().getSimpleName() + "#" + v_getNameMethod.invoke(p_EObject);
				} catch (Exception v_e) {
					v_objectString = p_EObject.toString();
				}
				ErrorGeneration.printMessageFmt(
						"[ERROR] Impossible de trouver le modèle pour l'objet suivant : " + v_objectString, "?");
				return null;
			}
			final URI v_uri = v_resource.getURI();
			if (v_uri == null) {
				ErrorGeneration.printMessageFmt(
						"[ERROR] Impossible de calculer l'URI de la ressource suivante : " + v_resource.toString(),
						"?");
				return null;
			}
			// On a une ressource CDO
			if ("cdo".equalsIgnoreCase(v_uri.scheme())) {
				// Récupération de l'AIRD
				final Session v_session = SessionManager.INSTANCE.getSession((EObject) p_EObject);
				if (v_session == null) {
					ErrorGeneration.printMessageFmt("[ERROR] Impossible de récupérer la session CDO", "?");
					ErrorGeneration.doIfThrowErrorGenerationException();
					return null;
				}
				final Resource v_aird = v_session.getSessionResource();
				return v_aird.getURI();
			}
			return v_uri;
		} catch (Throwable v_t) {
			ErrorGeneration.printMessageFmt(v_t, "?");
			ErrorGeneration.doIfThrowErrorGenerationException();
			return null;
		}
	}

	/**
	 * Permet d'obtenir répertoire du modèle à partir d'une instance d'URI.
	 * 
	 * @param p_uri L'URI à prendre en comtpe.
	 * @return La chaîne de caractères représentant le répertoire du modèle.
	 */
	public static String getModuleFolderFromUri(final URI p_uri) {
		if (p_uri == null) {
			return null;
		}
		final String v_fileString = p_uri.toFileString();
		if (v_fileString != null) {
			// mode développement
			return v_fileString.substring(0, v_fileString.length() - p_uri.lastSegment().length() - 1);
		} else {
			// mode production (plugins embarqués)
			final IPath v_path = new Path(p_uri.toPlatformString(true));

			final IWorkspace v_wksp = ResourcesPlugin.getWorkspace();
			final IWorkspaceRoot v_root = v_wksp.getRoot();
			final IResource v_modelFile = v_root.getFile(v_path);

			return new File(v_modelFile.getLocation().toString()).getParent();
		}
	}

	/**
	 * 
	 * @param p_str
	 * @return
	 */
	public static String createFixedUserCodeId(final String p_str) {
		try {
			MessageDigest v_messageDigest = MessageDigest.getInstance("MD5");
			v_messageDigest.update(p_str.getBytes());
			byte[] messageDigestMD5 = v_messageDigest.digest();
			StringBuffer v_stringBuffer = new StringBuffer();
			for (byte v_bytes : messageDigestMD5) {
				v_stringBuffer.append(String.format("%02x", v_bytes & 0xff));
			}
			return v_stringBuffer.toString();
		} catch (NoSuchAlgorithmException exception) {
			return "";
		}
	}

	/**
	 * Ajoute "_1" au nom de la variable si cette dernière est un mot clé de Java.
	 * Cette méthode est utilisé lors de la fabrication du client pour la librairie
	 * REST. Comme le fournisseur de ressources peut utiliser un autre langage, il
	 * existe des cas ou ces noms de variable ne conviennent pas dans le cadre de la
	 * génération Java.
	 * 
	 * @param p_varName
	 * @return
	 */
	public static String sanitizeJavaKeywords(final String p_varName) {
		if (c_RESERVED_KEYWORDS.indexOf("|" + p_varName.toLowerCase() + "|") > 0) {
			return p_varName + "_1";
		}
		return sanitize(p_varName);
	}
}
