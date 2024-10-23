/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.client.ui.annuaire;
// Start of user code for imports

import ERREUR [ Widget sans nom].ERREUR [ Widget sans nom];
import fr.spi4j.ui.graal.Field;
import fr.spi4j.ui.graal.UserView;
import fr.spi4j.ui.mvp.View_Itf;

// End of user code

/**
 * Interface de vue.
 * @author safr@n
 */

// Start of user code annotation class
@UserView("DetailPersonne")
// End of user code
public interface DetailPersonneView_Itf extends View_Itf
{

   
   // Start of user code btAnnuler_ERREUR [ Widget sans nom]

   /**
    * @return btAnnuler
    */
   @Field("btAnnuler")
   	ERREUR [ Widget sans nom] get_btAnnuler ();		
   // End of user code

   
   // Start of user code btEnregistrer_ERREUR [ Widget sans nom]

   /**
    * @return btEnregistrer
    */
   @Field("btEnregistrer")
   	ERREUR [ Widget sans nom] get_btEnregistrer ();		
   // End of user code

   
   // Start of user code DetailPersonneView_Itf

   // End of user code

}

