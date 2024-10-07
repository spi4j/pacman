package fr.application.types.enums;
// Start of user code for imports

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

// End of user code

/**
 * Enumeration TypeCompetence_Enum
 * @author safr@n
 */
public enum TypeCompetence_Enum
{

   /** TYPECOMP1 : . */
   TYPECOMP1("TYPECOMP1"),
   /** TYPECOMP2 : . */
   TYPECOMP2("TYPECOMP2"),
   /** TYPECOMP3 : . */
   TYPECOMP3("TYPECOMP3");


    /** La map de recuperation des enum par leur valeur */
    private static final Map <String, TypeCompetence_Enum> c_byValueMap = new LinkedHashMap<>();

	/** La valeur courante de l'enum sous forme de String. */
	private final String _value;

	

    /**
    *   Initialisation de c_byValueMap
    */
    static {
    	for ( TypeCompetence_Enum  v_enumValue :  TypeCompetence_Enum.values()  ) {
    		c_byValueMap.put(v_enumValue._value, v_enumValue );
    	}
    }

    /**
    * Contructeur par défaut
    */
	private TypeCompetence_Enum (final String p_literal) {

        _value = p_literal;
		

    // constructeur
    // Start of user code constructeur
	
    // End of user code
    }

   /**
    * Récupération de la valeur courante d'enum
    */
    public String getValue() {
		return _value;
	}

   /**
    * Récupération d'une enum par sa valeur
    */
    public static TypeCompetence_Enum getEnumByValue (String p_value) {
		return c_byValueMap.get(p_value);
	}



    // specifique
    // Start of user code specifique
	
    // End of user code
}
