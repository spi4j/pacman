/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.persistence.impl_jdbc.annuaire;
// Start of user code for imports

import fr.application.annuaire.TypeCompetence_Enum;
import fr.application.persistence.api.annuaire.PaysEntity_Itf;
import fr.application.persistence.impl_jdbc.ApplicationAutoFieldsEntity_Abs;
import fr.spi4j.exception.Spi4jValidationException;
import fr.spi4j.persistence.entity.EntityUtil;
import java.util.List;

// End of user code

/**
 * Entité du type Pays.
 * @author safr@n
 */
public class PaysEntity extends ApplicationAutoFieldsEntity_Abs<Long> implements PaysEntity_Itf
{
   // CONSTANTES

   /**
    * SerialUid.
    */
   private static final long serialVersionUID = -1;

   
   // Start of user code Constantes PaysEntity

   // End of user code

   // ATTRIBUTS

   /** Id. */
   private Long _pays_id;

   /** nom. */
   private String _nom;

   /** capitale. */
   private String _capitale;


   /** personne. */
   private Long _personnePays_id;

   
   // Start of user code Attributs PaysEntity

   // End of user code

   // METHODES

   /**
    * Constructeur sans paramètre de l'entité 'Pays'. <br>
    * Attention à ne pas appeler ce constructeur directement, mais à bien passer par UserPersistence.
    */
   public PaysEntity ()
   {
      super ();
   }

   @Override
   public Long getId ()
   {
      return _pays_id;
   }

   @Override
   public void setId (final Long p_id)
   {
      _pays_id = p_id;
   }

   @Override
   public String get_nom ()
   {
      return _nom;
   }

   @Override
   public void set_nom (final String p_nom)
   {
      _nom = p_nom;
   }

   @Override
   public String get_capitale ()
   {
      return _capitale;
   }

   @Override
   public void set_capitale (final String p_capitale)
   {
      _capitale = p_capitale;
   }



   @Override
   public Long get_personnePays_id ()
   {
      return _personnePays_id;
   }

   @Override
   public void set_personnePays_id (final Long p_personnePays_id)
   {
      _personnePays_id = p_personnePays_id;
   }

   @Override
   public String toString ()
   {
      return super.toString () + " : " + _pays_id + ", " + _nom + ", " + _capitale + ", " + _personnePays_id;
   }

   @Override
   public void validate () throws Spi4jValidationException
   {
      List<String> v_champsInvalides = prepareValidate ();

      v_champsInvalides = EntityUtil.checkMandatoryField ("nom", _nom, v_champsInvalides);
      v_champsInvalides = EntityUtil.checkMandatoryField ("capitale", _capitale, v_champsInvalides);
      // si la liste des champs invalides n'est pas nulle, elle ne peut pas être vide à cet endroit
      if (v_champsInvalides != null)
      {
         throw new Spi4jValidationException (this, v_champsInvalides.toArray (new String[v_champsInvalides.size()]));
      }
   }

   
   // Start of user code Methodes PaysEntity

   // End of user code

}
