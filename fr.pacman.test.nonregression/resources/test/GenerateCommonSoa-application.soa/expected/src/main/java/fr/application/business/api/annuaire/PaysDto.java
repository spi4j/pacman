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
 * DTO 'Pays'.
 * @author safr@n
 */
public class PaysDto extends ApplicationAutoFieldsDto_Abs implements Dto_Itf<Long>
{
   /**
    * SerialUid.
    */
   private static final long serialVersionUID = -1;

   // ATTRIBUTS

   /** L'identifiant. */
   private Long _id;

   /** . */
   private String _nom;

   /** . */
   private String _capitale;

   /** La FK sur le type 'Personne'. */
   private Long _personnePays_id;


   // METHODES

   /**
    * Constructeur sans paramètre du dto 'Pays'.
    */
   public PaysDto ()
   {
      super();
   }

   /**
    * Constructeur complet du dto 'Pays'.
    * @param p_id
    *           (In)(*) L'identifiant de pays.
    * @param p_nom
    *           (In)(*) nom.
    * @param p_capitale
    *           (In)(*) capitale.
    * @param p_personnePays_id
    *           (In) personne
    */
   public PaysDto (final Long p_id, final String p_nom, final String p_capitale, final Long p_personnePays_id)
   {
      super();
      reset_PaysDto(p_id, p_nom, p_capitale, p_personnePays_id);
   }

   /**
    * Constructeur complet du dto 'Pays'.
    * @param p_id
    *           (In)(*) L'identifiant de pays.
    * @param p_nom
    *           (In)(*) nom.
    * @param p_capitale
    *           (In)(*) capitale.
    * @param p_personnePays
    *           (In) personne
    */
   public PaysDto (final Long p_id, final String p_nom, final String p_capitale, final PersonneDto p_personnePays)
   {
      super();
      reset_PaysDto(p_id, p_nom, p_capitale, p_personnePays);
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
    * Obtenir nom.
    * @return nom.
    */
   public String get_nom ()
   {
      return _nom;
   }

   /**
    * Affecter nom.
    * @param p_nom
    *           (In)(*) nom.
    */
   public void set_nom (final String p_nom)
   {
      _nom = p_nom;
   }

   /**
    * Obtenir capitale.
    * @return capitale.
    */
   public String get_capitale ()
   {
      return _capitale;
   }

   /**
    * Affecter capitale.
    * @param p_capitale
    *           (In)(*) capitale.
    */
   public void set_capitale (final String p_capitale)
   {
      _capitale = p_capitale;
   }

   /**
    * Obtenir personnePays de pays.
    * @return personne
    */
   public Long get_personnePays_id ()
   {
      return _personnePays_id;
   }

   /**
    * Affecter personnePays de pays.
    * @param p_personnePays_id
    *           (In) personne
    */
   public void set_personnePays_id (final Long p_personnePays_id)
   {
      _personnePays_id = p_personnePays_id;
   }

   /**
    * Recycler le dto 'Pays'.
    * @param p_id
    *           (In)(*) L'identifiant de pays.
    * @param p_nom
    *           (In)(*) nom.
    * @param p_capitale
    *           (In)(*) capitale.
    * @param p_personnePays_id
    *           (In) personne
    */
   public void reset_PaysDto (final Long p_id, final String p_nom, final String p_capitale, final Long p_personnePays_id)
   {
      setId(p_id);
      set_nom(p_nom);
      set_capitale(p_capitale);
      set_personnePays_id(p_personnePays_id);
   }

   /**
    * Recycler le dto 'Pays'.
    * @param p_id
    *           (In)(*) L'identifiant de pays.
    * @param p_nom
    *           (In)(*) nom.
    * @param p_capitale
    *           (In)(*) capitale.
    * @param p_personnePays
    *           (In) personne
    */
   public void reset_PaysDto (final Long p_id, final String p_nom, final String p_capitale, final PersonneDto p_personnePays)
   {
      setId(p_id);
      set_nom(p_nom);
      set_capitale(p_capitale);
      if (p_personnePays == null)
      {
         set_personnePays_id(null);
      }
      else
      {
         set_personnePays_id(p_personnePays.getId());
      }
   }

   @Override
   public void validate () throws Spi4jValidationException
   {
      List<String> v_champsInvalides = null;
      v_champsInvalides = DtoUtil.checkMandatoryField("nom", _nom, v_champsInvalides);
      v_champsInvalides = DtoUtil.checkMandatoryField("capitale", _capitale, v_champsInvalides);
      // si la liste des champs invalides n'est pas nulle, elle ne peut pas être vide à cet endroit
      if (v_champsInvalides != null)
      {
         throw new Spi4jValidationException(this, v_champsInvalides.toArray(new String[v_champsInvalides.size()]));
      }
   }

   @Override
   public String toString ()
   {
      // toString
      // Start of user code toString

      return getClass().getSimpleName() + '[' + _id + ", " + get_nom() + ", " + get_capitale()   + ", " + get_personnePays_id ()  + ']';

      // End of user code
   }

}
