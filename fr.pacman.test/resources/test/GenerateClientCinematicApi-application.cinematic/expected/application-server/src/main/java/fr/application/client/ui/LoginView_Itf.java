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

// End of user code

/**
 * Interface de vue.
 * @author safr@n
 */

// Start of user code annotation class
@UserView("Login")
// End of user code
public interface LoginView_Itf extends View_Itf
{

   
   // Start of user code txtLogin_ERREUR [ Widget sans nom]

   /**
    * @return txtLogin
    */
   @Field("txtLogin")
   	ERREUR [ Widget sans nom] get_txtLogin ();		
   // End of user code

   
   // Start of user code txtPassword_ERREUR [ Widget sans nom]

   /**
    * @return txtPassword
    */
   @Field("txtPassword")
   	ERREUR [ Widget sans nom] get_txtPassword ();		
   // End of user code

   
   // Start of user code btConnecter_ERREUR [ Widget sans nom]

   /**
    * @return btConnecter
    */
   @Field("btConnecter")
   	ERREUR [ Widget sans nom] get_btConnecter ();		
   // End of user code

   
   // Start of user code btAnnuler_ERREUR [ Widget sans nom]

   /**
    * @return btAnnuler
    */
   @Field("btAnnuler")
   	ERREUR [ Widget sans nom] get_btAnnuler ();		
   // End of user code

   
   // Start of user code LoginView_Itf

   // End of user code

}

