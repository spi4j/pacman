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
@UserView("ListePersonnes")
// End of user code
public interface ListePersonnesView_Itf extends View_Itf
{

   
   // Start of user code cbGrade_ERREUR [ Widget sans nom]

   /**
    * @return cbGrade
    */
   @Field("cbGrade")
   	ERREUR [ Widget sans nom] get_cbGrade ();		
   // End of user code

   
   // Start of user code table_ERREUR [ Widget sans nom]

   /**
    * @return table
    */
   @Field("table")
   	ERREUR [ Widget sans nom] get_table ();		
   // End of user code

   
   // Start of user code btActualiser_ERREUR [ Widget sans nom]

   /**
    * @return btActualiser
    */
   @Field("btActualiser")
   	ERREUR [ Widget sans nom] get_btActualiser ();		
   // End of user code

   
   // Start of user code btCreer_ERREUR [ Widget sans nom]

   /**
    * @return btCreer
    */
   @Field("btCreer")
   	ERREUR [ Widget sans nom] get_btCreer ();		
   // End of user code

   
   // Start of user code btModifier_ERREUR [ Widget sans nom]

   /**
    * @return btModifier
    */
   @Field("btModifier")
   	ERREUR [ Widget sans nom] get_btModifier ();		
   // End of user code

   
   // Start of user code btVisualiser_ERREUR [ Widget sans nom]

   /**
    * @return btVisualiser
    */
   @Field("btVisualiser")
   	ERREUR [ Widget sans nom] get_btVisualiser ();		
   // End of user code

   
   // Start of user code btSupprimer_ERREUR [ Widget sans nom]

   /**
    * @return btSupprimer
    */
   @Field("btSupprimer")
   	ERREUR [ Widget sans nom] get_btSupprimer ();		
   // End of user code

   
   // Start of user code ListePersonnesView_Itf

   // End of user code

}

