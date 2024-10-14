/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.persistence.impl_jdbc.annuaire;
// Start of user code for imports

import fr.application.persistence.api.annuaire.AdresseColumns_Enum;
import fr.application.persistence.api.annuaire.AdresseEntity_Itf;
import fr.application.persistence.impl_jdbc.ApplicationAutoFieldsEntity_Abs;
import fr.application.types.enums.TypeCompetence_Enum;
import fr.spi4j.exception.Spi4jValidationException;
import fr.spi4j.persistence.entity.EntityUtil;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Entité du type Adresse.
 * @author safr@n
 */
public class AdresseEntity extends ApplicationAutoFieldsEntity_Abs<Long> implements AdresseEntity_Itf
{
   // CONSTANTES

   /**
    * SerialUid.
    */
   private static final long serialVersionUID = -1;

   // Constantes AdresseEntity
   // Start of user code Constantes AdresseEntity

   // End of user code

   // ATTRIBUTS

   /** Id. */
   private Long _adresse_id;

   /** rue. */
   private String _rue;

   /** ville. */
   private String _ville;

   /** codePostal. */
   private String _codePostal;


   /** personne. */
   private Long _personneAdresses_id;

   // Attributs AdresseEntity
   // Start of user code Attributs AdresseEntity

   // End of user code

   // METHODES

   /**
    * Constructeur sans paramètre de l'entité 'Adresse'. <br>
    * Attention à ne pas appeler ce constructeur directement, mais à bien passer par UserPersistence.
    */
   public AdresseEntity ()
   {
      super ();
   }

   @Override
   public Long getId ()
   {
      return _adresse_id;
   }

   @Override
   public void setId (final Long p_id)
   {
      _adresse_id = p_id;
   }

   @Override
   public String get_rue ()
   {
      return _rue;
   }

   @Override
   public void set_rue (final String p_rue)
   {
      _rue = p_rue;
   }

   @Override
   public String get_ville ()
   {
      return _ville;
   }

   @Override
   public void set_ville (final String p_ville)
   {
      _ville = p_ville;
   }

   @Override
   public String get_codePostal ()
   {
      return _codePostal;
   }

   @Override
   public void set_codePostal (final String p_codePostal)
   {
      _codePostal = p_codePostal;
   }



   @Override
   public Long get_personneAdresses_id ()
   {
      return _personneAdresses_id;
   }

   @Override
   public void set_personneAdresses_id (final Long p_personneAdresses_id)
   {
      _personneAdresses_id = p_personneAdresses_id;
   }

   @Override
   public String toString ()
   {
      return super.toString () + " : " + _adresse_id + ", " + _rue + ", " + _ville + ", " + _codePostal + ", " + _personneAdresses_id;
   }

   @Override
   public void validate () throws Spi4jValidationException
   {
      List<String> v_champsInvalides = prepareValidate ();

      v_champsInvalides = EntityUtil.checkMandatoryField ("rue", _rue, v_champsInvalides);
      v_champsInvalides = EntityUtil.checkMandatoryField ("ville", _ville, v_champsInvalides);
      v_champsInvalides = EntityUtil.checkMandatoryField ("codePostal", _codePostal, v_champsInvalides);
      v_champsInvalides = EntityUtil.checkFieldSize ("codePostal", _codePostal, AdresseColumns_Enum.codePostal.getSize(), v_champsInvalides);
      // si la liste des champs invalides n'est pas nulle, elle ne peut pas être vide à cet endroit
      if (v_champsInvalides != null)
      {
         throw new Spi4jValidationException (this, v_champsInvalides.toArray (new String[v_champsInvalides.size()]));
      }
   }

   // Methodes AdresseEntity
   // Start of user code Methodes AdresseEntity

   // End of user code

}
