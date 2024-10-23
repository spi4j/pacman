/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.persistence.impl_jdbc.annuaire;
// Start of user code for imports

import fr.application.annuaire.TypeCompetence_Enum;
import fr.application.persistence.api.annuaire.PersonneColumns_Enum;
import fr.application.persistence.api.annuaire.PersonneEntity_Itf;
import fr.application.persistence.impl_jdbc.ApplicationAutoFieldsEntity_Abs;
import fr.spi4j.exception.Spi4jValidationException;
import fr.spi4j.persistence.entity.EntityUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Entité du type Personne.
 * @author safr@n
 */
public class PersonneEntity extends ApplicationAutoFieldsEntity_Abs<Long> implements PersonneEntity_Itf
{
   // CONSTANTES

   /**
    * SerialUid.
    */
   private static final long serialVersionUID = -1;

   
   // Start of user code Constantes PersonneEntity

   // End of user code

   // ATTRIBUTS

   /** Id. */
   private Long _personne_id;

   /** nom. */
   private String _nom;

   /** prenom. */
   private String _prenom;

   /** civil. */
   private Boolean _civil;

   /** dateNaissance. */
   private Date _dateNaissance;

   /** salaire. */
   private Double _salaire;


   /** Grade. */
   private Long _grade_id;

   /** MarieAvec. */
   private Long _marieAvec_id;

   /** personne. */
   private Long _personneParentDe_id;

   
   // Start of user code Attributs PersonneEntity

   // End of user code

   // METHODES

   /**
    * Constructeur sans paramètre de l'entité 'Personne'. <br>
    * Attention à ne pas appeler ce constructeur directement, mais à bien passer par UserPersistence.
    */
   public PersonneEntity ()
   {
      super ();
   }

   @Override
   public Long getId ()
   {
      return _personne_id;
   }

   @Override
   public void setId (final Long p_id)
   {
      _personne_id = p_id;
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
   public String get_prenom ()
   {
      return _prenom;
   }

   @Override
   public void set_prenom (final String p_prenom)
   {
      _prenom = p_prenom;
   }

   @Override
   public Boolean get_civil ()
   {
      return _civil;
   }

   @Override
   public void set_civil (final Boolean p_civil)
   {
      _civil = p_civil;
   }

   @Override
   public Date get_dateNaissance ()
   {
      return _dateNaissance;
   }

   @Override
   public void set_dateNaissance (final Date p_dateNaissance)
   {
      _dateNaissance = p_dateNaissance;
   }

   @Override
   public Double get_salaire ()
   {
      return _salaire;
   }

   @Override
   public void set_salaire (final Double p_salaire)
   {
      _salaire = p_salaire;
   }



   @Override
   public Long get_Grade_id ()
   {
      return _grade_id;
   }

   @Override
   public void set_Grade_id (final Long p_grade_id)
   {
      _grade_id = p_grade_id;
   }

   @Override
   public Long get_MarieAvec_id ()
   {
      return _marieAvec_id;
   }

   @Override
   public void set_MarieAvec_id (final Long p_marieAvec_id)
   {
      _marieAvec_id = p_marieAvec_id;
   }

   @Override
   public Long get_personneParentDe_id ()
   {
      return _personneParentDe_id;
   }

   @Override
   public void set_personneParentDe_id (final Long p_personneParentDe_id)
   {
      _personneParentDe_id = p_personneParentDe_id;
   }

   @Override
   public String toString ()
   {
      return super.toString () + " : " + _personne_id + ", " + _nom + ", " + _prenom + ", " + _civil + ", " + _dateNaissance + ", " + _salaire + ", " + _grade_id + ", " + _marieAvec_id + ", " + _personneParentDe_id;
   }

   @Override
   public void validate () throws Spi4jValidationException
   {
      List<String> v_champsInvalides = prepareValidate ();

      v_champsInvalides = EntityUtil.checkMandatoryField ("nom", _nom, v_champsInvalides);
      v_champsInvalides = EntityUtil.checkFieldSize ("nom", _nom, PersonneColumns_Enum.nom.getSize(), v_champsInvalides);
      v_champsInvalides = EntityUtil.checkMandatoryField ("prenom", _prenom, v_champsInvalides);
      v_champsInvalides = EntityUtil.checkMandatoryField ("civil", _civil, v_champsInvalides);
      v_champsInvalides = EntityUtil.checkMandatoryField ("dateNaissance", _dateNaissance, v_champsInvalides);
      v_champsInvalides = EntityUtil.checkMandatoryField ("salaire", _salaire, v_champsInvalides);
      v_champsInvalides = EntityUtil.checkMandatoryField ("personneParentDe", _personneParentDe_id, v_champsInvalides);
      // si la liste des champs invalides n'est pas nulle, elle ne peut pas être vide à cet endroit
      if (v_champsInvalides != null)
      {
         throw new Spi4jValidationException (this, v_champsInvalides.toArray (new String[v_champsInvalides.size()]));
      }
   }

   
   // Start of user code Methodes PersonneEntity

   // End of user code

}
