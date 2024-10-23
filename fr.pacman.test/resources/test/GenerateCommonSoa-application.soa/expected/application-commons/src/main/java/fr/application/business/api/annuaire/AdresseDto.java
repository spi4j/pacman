/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.business.api.annuaire;
// Start of user code for imports

import fr.application.business.ApplicationAutoFieldsDto_Abs;
import fr.spi4j.business.dto.DtoUtil;
import fr.spi4j.business.dto.Dto_Itf;
import fr.spi4j.exception.Spi4jValidationException;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * DTO 'Adresse'.
 * @author safr@n
 */
public class AdresseDto extends ApplicationAutoFieldsDto_Abs implements Dto_Itf<Long>
{
   /**
    * SerialUid.
    */
   private static final long serialVersionUID = -1;

   // ATTRIBUTS

   /** L'identifiant. */
   private Long _id;

   /** . */
   private String _rue;

   /** . */
   private String _ville;

   /** . */
   private String _codePostal;

   /** La FK sur le type 'Personne'. */
   private Long _personneAdresses_id;


   // METHODES

   /**
    * Constructeur sans paramètre du dto 'Adresse'.
    */
   public AdresseDto ()
   {
      super();
   }

   /**
    * Constructeur complet du dto 'Adresse'.
    * @param p_id
    *           (In)(*) L'identifiant de adresse.
    * @param p_rue
    *           (In)(*) rue.
    * @param p_ville
    *           (In)(*) ville.
    * @param p_codePostal
    *           (In)(*) codePostal.
    * @param p_personneAdresses_id
    *           (In) personne
    */
   public AdresseDto (final Long p_id, final String p_rue, final String p_ville, final String p_codePostal, final Long p_personneAdresses_id)
   {
      super();
      reset_AdresseDto(p_id, p_rue, p_ville, p_codePostal, p_personneAdresses_id);
   }

   /**
    * Constructeur complet du dto 'Adresse'.
    * @param p_id
    *           (In)(*) L'identifiant de adresse.
    * @param p_rue
    *           (In)(*) rue.
    * @param p_ville
    *           (In)(*) ville.
    * @param p_codePostal
    *           (In)(*) codePostal.
    * @param p_personneAdresses
    *           (In) personne
    */
   public AdresseDto (final Long p_id, final String p_rue, final String p_ville, final String p_codePostal, final PersonneDto p_personneAdresses)
   {
      super();
      reset_AdresseDto(p_id, p_rue, p_ville, p_codePostal, p_personneAdresses);
   }

   @Override
   public Long getId ()
   {
      return _id;
   }

   @Override
   public void setId (final Long p_id)
   {
      _id = p_id;
   }

   /**
    * Obtenir rue.
    * @return rue.
    */
   public String get_rue ()
   {
      return _rue;
   }

   /**
    * Affecter rue.
    * @param p_rue
    *           (In)(*) rue.
    */
   public void set_rue (final String p_rue)
   {
      _rue = p_rue;
   }

   /**
    * Obtenir ville.
    * @return ville.
    */
   public String get_ville ()
   {
      return _ville;
   }

   /**
    * Affecter ville.
    * @param p_ville
    *           (In)(*) ville.
    */
   public void set_ville (final String p_ville)
   {
      _ville = p_ville;
   }

   /**
    * Obtenir codePostal.
    * @return codePostal.
    */
   public String get_codePostal ()
   {
      return _codePostal;
   }

   /**
    * Affecter codePostal.
    * @param p_codePostal
    *           (In)(*) codePostal.
    */
   public void set_codePostal (final String p_codePostal)
   {
      _codePostal = p_codePostal;
   }

   /**
    * Obtenir personneAdresses de adresse.
    * @return personne
    */
   public Long get_personneAdresses_id ()
   {
      return _personneAdresses_id;
   }

   /**
    * Affecter personneAdresses de adresse.
    * @param p_personneAdresses_id
    *           (In) personne
    */
   public void set_personneAdresses_id (final Long p_personneAdresses_id)
   {
      _personneAdresses_id = p_personneAdresses_id;
   }

   /**
    * Recycler le dto 'Adresse'.
    * @param p_id
    *           (In)(*) L'identifiant de adresse.
    * @param p_rue
    *           (In)(*) rue.
    * @param p_ville
    *           (In)(*) ville.
    * @param p_codePostal
    *           (In)(*) codePostal.
    * @param p_personneAdresses_id
    *           (In) personne
    */
   public void reset_AdresseDto (final Long p_id, final String p_rue, final String p_ville, final String p_codePostal, final Long p_personneAdresses_id)
   {
      setId(p_id);
      set_rue(p_rue);
      set_ville(p_ville);
      set_codePostal(p_codePostal);
      set_personneAdresses_id(p_personneAdresses_id);
   }

   /**
    * Recycler le dto 'Adresse'.
    * @param p_id
    *           (In)(*) L'identifiant de adresse.
    * @param p_rue
    *           (In)(*) rue.
    * @param p_ville
    *           (In)(*) ville.
    * @param p_codePostal
    *           (In)(*) codePostal.
    * @param p_personneAdresses
    *           (In) personne
    */
   public void reset_AdresseDto (final Long p_id, final String p_rue, final String p_ville, final String p_codePostal, final PersonneDto p_personneAdresses)
   {
      setId(p_id);
      set_rue(p_rue);
      set_ville(p_ville);
      set_codePostal(p_codePostal);
      if (p_personneAdresses == null)
      {
         set_personneAdresses_id(null);
      }
      else
      {
         set_personneAdresses_id(p_personneAdresses.getId());
      }
   }

   @Override
   public void validate () throws Spi4jValidationException
   {
      List<String> v_champsInvalides = null;
      v_champsInvalides = DtoUtil.checkMandatoryField("rue", _rue, v_champsInvalides);
      v_champsInvalides = DtoUtil.checkMandatoryField("ville", _ville, v_champsInvalides);
      v_champsInvalides = DtoUtil.checkMandatoryField("codePostal", _codePostal, v_champsInvalides);
      v_champsInvalides = DtoUtil.checkFieldSize("codePostal", _codePostal, AdresseAttributes_Enum.codePostal.getSize(), v_champsInvalides);
      // si la liste des champs invalides n'est pas nulle, elle ne peut pas être vide à cet endroit
      if (v_champsInvalides != null)
      {
         throw new Spi4jValidationException(this, v_champsInvalides.toArray(new String[v_champsInvalides.size()]));
      }
   }

   @Override
   public String toString ()
   {
      
      // Start of user code toString

      return getClass().getSimpleName() + '[' + _id + ", " + get_rue() + ", " + get_ville() + ", " + get_codePostal()   + ", " + get_personneAdresses_id ()  + ']';

      // End of user code
   }

   
   // Start of user code Methodes

   // End of user code
}
