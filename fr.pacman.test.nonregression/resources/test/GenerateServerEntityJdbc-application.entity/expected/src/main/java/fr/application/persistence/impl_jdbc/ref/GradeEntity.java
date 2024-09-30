/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.persistence.impl_jdbc.ref;
// Start of user code for imports

import fr.application.persistence.api.ref.GradeEntity_Itf;
import fr.application.persistence.impl_jdbc.ApplicationAutoFieldsEntity_Abs;
import fr.application.types.enums.TypeCompetence_Enum;
import fr.spi4j.exception.Spi4jValidationException;
import fr.spi4j.persistence.entity.EntityUtil;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Entité du type Grade.
 * @author safr@n
 */
public class GradeEntity extends ApplicationAutoFieldsEntity_Abs<Long> implements GradeEntity_Itf
{
   // CONSTANTES

   /**
    * SerialUid.
    */
   private static final long serialVersionUID = -1;

   // Constantes GradeEntity
   // Start of user code Constantes GradeEntity

   // End of user code

   // ATTRIBUTS

   /** Id. */
   private Long _grade_id;

   /** Le libellé du grade. */
   private String _libelle;

   /** Le trigramme du grade. */
   private String _trigramme;


   // Attributs GradeEntity
   // Start of user code Attributs GradeEntity

   // End of user code

   // METHODES

   /**
    * Constructeur sans paramètre de l'entité 'Grade'. <br>
    * Attention à ne pas appeler ce constructeur directement, mais à bien passer par UserPersistence.
    */
   public GradeEntity ()
   {
      super ();
   }

   @Override
   public Long getId ()
   {
      return _grade_id;
   }

   @Override
   public void setId (final Long p_id)
   {
      _grade_id = p_id;
   }

   @Override
   public String get_libelle ()
   {
      return _libelle;
   }

   @Override
   public void set_libelle (final String p_libelle)
   {
      _libelle = p_libelle;
   }

   @Override
   public String get_trigramme ()
   {
      return _trigramme;
   }

   @Override
   public void set_trigramme (final String p_trigramme)
   {
      _trigramme = p_trigramme;
   }



   @Override
   public String toString ()
   {
      return super.toString () + " : " + _grade_id + ", " + _libelle + ", " + _trigramme;
   }

   @Override
   public void validate () throws Spi4jValidationException
   {
      List<String> v_champsInvalides = prepareValidate ();

      v_champsInvalides = EntityUtil.checkMandatoryField ("libelle", _libelle, v_champsInvalides);
      v_champsInvalides = EntityUtil.checkMandatoryField ("trigramme", _trigramme, v_champsInvalides);
      // si la liste des champs invalides n'est pas nulle, elle ne peut pas être vide à cet endroit
      if (v_champsInvalides != null)
      {
         throw new Spi4jValidationException (this, v_champsInvalides.toArray (new String[v_champsInvalides.size()]));
      }
   }

   // Methodes GradeEntity
   // Start of user code Methodes GradeEntity

   // End of user code

}
