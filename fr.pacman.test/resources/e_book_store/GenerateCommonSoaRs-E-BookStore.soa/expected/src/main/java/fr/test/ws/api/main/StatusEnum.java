/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.ws.api.main;
// Start of user code for imports

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Date;

// End of user code

/**
* Enumération. 
* 
*/
public enum StatusEnum {

 	// StatusEnum
 	// Start of user code 20d5c29924f5b80247b43781741083d9

    /** pending. */
    PENDING("pending"),
   /** shipped. */
    SHIPPED("shipped");
	// End of user code	

	/**
	 * Valeur transmise pour l'énumeration.
	 */
	private String value;

	StatusEnum(final String value){
		this.value = value;
	}

	@JsonValue
	public String getValue(){
		return value;
	}
}

