/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.business;
// Start of user code for imports

import fr.spi4j.business.dto.Dto_Itf;
import fr.spi4j.type.XtopSup;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Classe abstraite contenant les attributs additionnels qui sont communs à tous les dtos.
 *
 * @author safr@n
 */
public abstract class ApplicationAutoFieldsDto_Abs {

	// CONSTANTES

   /**
	* SerialUid.
	*/
	private static final long serialVersionUID = -1;

	
	// Start of user code Constantes

	// End of user code

	// ATTRIBUTS
   	/** Xdmaj */
   	private Date _xdmaj;

   	/** Xtopsup */
   	private XtopSup _xtopsup;


	 
	// Start of user code Commons Attributs

	// End of user code

 	// METHODES

	public Date get_xdmaj ()
	{
		return _xdmaj;
	}

	public void set_xdmaj (final Date p_xdmaj)
	{
		_xdmaj = p_xdmaj;
	}

	public XtopSup get_xtopsup ()
	{
		return _xtopsup;
	}

	public void set_xtopsup (final XtopSup p_xtopsup)
	{
		_xtopsup = p_xtopsup;
	}

}
