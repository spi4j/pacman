package fr.test.main;
// Start of user code for imports

import java.util.LinkedHashMap;
import java.util.Map;

// End of user code

/**
 * Enumeration StatusEnum
 * @author safr@n
 */
public enum StatusEnum
{

   /** pending : . */
   PENDING("pending"),
   /** shipped : . */
   SHIPPED("shipped");


    /** La map de recuperation des enum par leur valeur */
    private static final Map <String, StatusEnum> BYVALUEMAP = new LinkedHashMap<>();

	/** La valeur courante de l'enum sous forme de String. */
	private final String value;

	

    /**
    *   Initialisation de BYVALUEMAP
    */
    static {
    	for ( StatusEnum  enumValue :  StatusEnum.values()  ) {
    		BYVALUEMAP.put(enumValue.value, enumValue );
    	}
    }

    /**
    * Contructeur par défaut
    */
	private StatusEnum (final String literal) {

        this.value = literal;
		

    
    // Start of user code 2e3e969fd52202f4f94ef7456a361c15
	
    // End of user code
    }

   /**
    * Récupération de la valeur courante d'enum
    */
    public String getValue() {
		return this.value;
	}

   /**
    * Récupération d'une enum par sa valeur
    */
    public static StatusEnum getEnumByValue (String value) {
		return BYVALUEMAP.get(value);
	}



    
    // Start of user code ce66da52bccc0b0a4afb6570f0378b2c
	
    // End of user code
}
