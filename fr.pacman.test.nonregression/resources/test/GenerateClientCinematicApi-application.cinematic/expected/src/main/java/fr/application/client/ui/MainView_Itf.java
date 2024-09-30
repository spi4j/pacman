/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.client.ui;
// Start of user code for imports

import ERREUR [ Widget sans nom].ERREUR [ Widget sans nom];
import fr.spi4j.ui.graal.Field;
import fr.spi4j.ui.graal.UserView;
import fr.spi4j.ui.mvp.View_Itf;
import java.util.Date;

// End of user code

/**
 * Interface de vue.
 * @author safr@n
 */
// annotation class
// Start of user code annotation class
@UserView("Main")
// End of user code
public interface MainView_Itf extends View_Itf
{

   // btConnexion_ERREUR [ Widget sans nom]
   // Start of user code btConnexion_ERREUR [ Widget sans nom]

   /**
    * @return btConnexion
    */
   @Field("btConnexion")
   	ERREUR [ Widget sans nom] get_btConnexion ();		
   // End of user code

   // btDeconnexion_ERREUR [ Widget sans nom]
   // Start of user code btDeconnexion_ERREUR [ Widget sans nom]

   /**
    * @return btDeconnexion
    */
   @Field("btDeconnexion")
   	ERREUR [ Widget sans nom] get_btDeconnexion ();		
   // End of user code

   // MainView_Itf
   // Start of user code MainView_Itf

   // End of user code

}

