/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.persistence.impl_jdbc.annuaire;
// Start of user code for imports

import fr.application.annuaire.TypeCompetence_Enum;
import fr.application.persistence.api.annuaire.CompetenceEntity_Itf;
import fr.application.persistence.impl_jdbc.ApplicationAutoFieldsEntity_Abs;
import fr.spi4j.exception.Spi4jValidationException;
import fr.spi4j.persistence.entity.EntityUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// End of user code

/**
 * Entité du type Competence.
 * @author safr@n
 */
public class CompetenceEntity extends ApplicationAutoFieldsEntity_Abs<Long> implements CompetenceEntity_Itf
{
   // CONSTANTES

   /**
    * SerialUid.
    */
   private static final long serialVersionUID = -1;

   
   // Start of user code Constantes CompetenceEntity

   // End of user code

   // ATTRIBUTS

   /** Id. */
   private Long _competence_id;

   /** Le libellé de la compétence. */
   private String _libelle;

   /** typecompetence. */
   private TypeCompetence_Enum _typecompetence;


   
   // Start of user code Attributs CompetenceEntity

   // End of user code

   // METHODES

   /**
    * Constructeur sans paramètre de l'entité 'Competence'. <br>
    * Attention à ne pas appeler ce constructeur directement, mais à bien passer par UserPersistence.
    */
   public CompetenceEntity ()
   {
      super ();
   }

   @Override
   public Long getId ()
   {
      return _competence_id;
   }

   @Override
   public void setId (final Long p_id)
   {
      _competence_id = p_id;
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
   public TypeCompetence_Enum get_typecompetence ()
   {
      return _typecompetence;
   }

   @Override
   public void set_typecompetence (final TypeCompetence_Enum p_typecompetence)
   {
      _typecompetence = p_typecompetence;
   }



   @Override
   public String toString ()
   {
      return super.toString () + " : " + _competence_id + ", " + _libelle + ", " + _typecompetence;
   }

   @Override
   public void validate () throws Spi4jValidationException
   {
      List<String> v_champsInvalides = prepareValidate ();

      v_champsInvalides = EntityUtil.checkMandatoryField ("libelle", _libelle, v_champsInvalides);
      v_champsInvalides = EntityUtil.checkMandatoryField ("typecompetence", _typecompetence, v_champsInvalides);
      // si la liste des champs invalides n'est pas nulle, elle ne peut pas être vide à cet endroit
      if (v_champsInvalides != null)
      {
         throw new Spi4jValidationException (this, v_champsInvalides.toArray (new String[v_champsInvalides.size()]));
      }
   }

   
   // Start of user code Methodes CompetenceEntity

   // End of user code

}
