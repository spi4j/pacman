/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.business.api.annuaire;
// Start of user code for imports

import fr.application.business.ApplicationAutoFieldsDto_Abs;
import fr.application.business.ApplicationUserBusiness;
import fr.application.business.api.ref.GradeDto;
import fr.spi4j.business.dto.DtoUtil;
import fr.spi4j.business.dto.Dto_Itf;
import fr.spi4j.exception.Spi4jValidationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * DTO 'Personne'.
 * @author safr@n
 */
public class PersonneDto extends ApplicationAutoFieldsDto_Abs implements Dto_Itf<Long>
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
   private String _prenom;

   /** . */
   private Boolean _civil;

   /** . */
   private Date _dateNaissance;

   /** . */
   private Double _salaire;

   /** La FK sur le Type 'Grade'. */
   private Long _grade_id;

   /** L'instance de 'Invalid' associee à  l'instance de 'Personne' courante. */
   private GradeDto _grade;

   /** La FK sur le Type 'Personne'. */
   private Long _marieAvec_id;

   /** L'instance de 'Invalid' associee à  l'instance de 'Personne' courante. */
   private PersonneDto _marieAvec;

   /** La FK sur le type 'Personne'. */
   private Long _personneParentDe_id;

   /** La liste de type 'Adresse' associee a  l'instance de 'Personne' courante. */
   private List<AdresseDto> _tab_adresses;

   /** La liste de type 'Personne' associee a  l'instance de 'Personne' courante. */
   private List<PersonneDto> _tab_parentDe;

   /** La liste de type 'Pays' associee a  l'instance de 'Personne' courante. */
   private List<PaysDto> _tab_pays;


   // METHODES

   /**
    * Constructeur sans paramètre du dto 'Personne'.
    */
   public PersonneDto ()
   {
      super();
   }

   /**
    * Constructeur complet du dto 'Personne'.
    * @param p_id
    *           (In)(*) L'identifiant de personne.
    * @param p_nom
    *           (In)(*) nom.
    * @param p_prenom
    *           (In)(*) prenom.
    * @param p_civil
    *           (In)(*) civil.
    * @param p_dateNaissance
    *           (In)(*) dateNaissance.
    * @param p_salaire
    *           (In)(*) salaire.
    * @param p_grade_id
    *           (In) Grade.
    * @param p_marieAvec_id
    *           (In) MarieAvec.
    * @param p_personneParentDe_id
    *           (In)(*) personne
    */
   public PersonneDto (final Long p_id, final String p_nom, final String p_prenom, final Boolean p_civil, final Date p_dateNaissance, final Double p_salaire, final Long p_grade_id, final Long p_marieAvec_id, final Long p_personneParentDe_id)
   {
      super();
      reset_PersonneDto(p_id, p_nom, p_prenom, p_civil, p_dateNaissance, p_salaire, p_grade_id, p_marieAvec_id, p_personneParentDe_id);
   }

   /**
    * Constructeur complet du dto 'Personne'.
    * @param p_id
    *           (In)(*) L'identifiant de personne.
    * @param p_nom
    *           (In)(*) nom.
    * @param p_prenom
    *           (In)(*) prenom.
    * @param p_civil
    *           (In)(*) civil.
    * @param p_dateNaissance
    *           (In)(*) dateNaissance.
    * @param p_salaire
    *           (In)(*) salaire.
    * @param p_grade
    *           (In) Grade.
    * @param p_marieAvec
    *           (In) MarieAvec.
    * @param p_personneParentDe
    *           (In)(*) personne
    */
   public PersonneDto (final Long p_id, final String p_nom, final String p_prenom, final Boolean p_civil, final Date p_dateNaissance, final Double p_salaire, final GradeDto p_grade, final PersonneDto p_marieAvec, final PersonneDto p_personneParentDe)
   {
      super();
      reset_PersonneDto(p_id, p_nom, p_prenom, p_civil, p_dateNaissance, p_salaire, p_grade, p_marieAvec, p_personneParentDe);
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
    * Obtenir prenom.
    * @return prenom.
    */
   public String get_prenom ()
   {
      return _prenom;
   }

   /**
    * Affecter prenom.
    * @param p_prenom
    *           (In)(*) prenom.
    */
   public void set_prenom (final String p_prenom)
   {
      _prenom = p_prenom;
   }

   /**
    * Obtenir civil.
    * @return civil.
    */
   public Boolean get_civil ()
   {
      return _civil;
   }

   /**
    * Affecter civil.
    * @param p_civil
    *           (In)(*) civil.
    */
   public void set_civil (final Boolean p_civil)
   {
      _civil = p_civil;
   }

   /**
    * Obtenir dateNaissance.
    * @return dateNaissance.
    */
   public Date get_dateNaissance ()
   {
      return _dateNaissance;
   }

   /**
    * Affecter dateNaissance.
    * @param p_dateNaissance
    *           (In)(*) dateNaissance.
    */
   public void set_dateNaissance (final Date p_dateNaissance)
   {
      _dateNaissance = p_dateNaissance;
   }

   /**
    * Obtenir salaire.
    * @return salaire.
    */
   public Double get_salaire ()
   {
      return _salaire;
   }

   /**
    * Affecter salaire.
    * @param p_salaire
    *           (In)(*) salaire.
    */
   public void set_salaire (final Double p_salaire)
   {
      _salaire = p_salaire;
   }

   /**
    * Obtenir Grade.
    * @return Grade.
    */
   public Long get_grade_id ()
   {
      if (_grade != null)
      {
          _grade_id = _grade.getId();
      }
      return _grade_id;
   }

   /**
    * Affecter Grade.
    * @param p_grade_id
    *           (In) Grade.
    */
   public void set_grade_id (final Long p_grade_id)
   {
      _grade_id = p_grade_id;
      _grade = null;
   }

   /**
    * Obtenir Grade.
    * @return Grade.
    */
   public GradeDto get_grade ()
   {
      if (_grade_id != null && _grade == null)
      {
         _grade = ApplicationUserBusiness.getGradeService().findById(_grade_id);
      }
      return _grade;
   }

   /**
    * Affecter Grade.
    * @param p_grade
    *           (In) Grade.
    */
   public void set_grade (final GradeDto p_grade)
   {
      if (p_grade == null)
      {
         set_grade_id(null);
      }
      else
      {
         set_grade_id(p_grade.getId());
      }
      _grade = p_grade;
   }

   /**
    * Obtenir MarieAvec.
    * @return MarieAvec.
    */
   public Long get_marieAvec_id ()
   {
      if (_marieAvec != null)
      {
          _marieAvec_id = _marieAvec.getId();
      }
      return _marieAvec_id;
   }

   /**
    * Affecter MarieAvec.
    * @param p_marieAvec_id
    *           (In) MarieAvec.
    */
   public void set_marieAvec_id (final Long p_marieAvec_id)
   {
      _marieAvec_id = p_marieAvec_id;
      _marieAvec = null;
   }

   /**
    * Obtenir MarieAvec.
    * @return MarieAvec.
    */
   public PersonneDto get_marieAvec ()
   {
      if (_marieAvec_id != null && _marieAvec == null)
      {
         _marieAvec = ApplicationUserBusiness.getPersonneService().findById(_marieAvec_id);
      }
      return _marieAvec;
   }

   /**
    * Affecter MarieAvec.
    * @param p_marieAvec
    *           (In) MarieAvec.
    */
   public void set_marieAvec (final PersonneDto p_marieAvec)
   {
      if (p_marieAvec == null)
      {
         set_marieAvec_id(null);
      }
      else
      {
         set_marieAvec_id(p_marieAvec.getId());
      }
      _marieAvec = p_marieAvec;
   }

   /**
    * Obtenir la liste de 'Adresse' : Adresses.
    * @return Adresses.
    */
   public List<AdresseDto> get_tab_adresses ()
   {
      if (_id != null && _tab_adresses == null)
      {
         _tab_adresses = ApplicationUserBusiness.getAdresseService().findListAdressesByPersonne(_id);
      }
      return _tab_adresses;
   }

   /**
    * Affecter la liste de 'Adresse' : Adresses.
    *           (In) Adresses.
    */
   public void setAdresses (final List<AdresseDto> p_tab_adresse)
   {
      _tab_adresses = p_tab_adresse;
   }

   /**
    * Obtenir la liste de 'Personne' : ParentDe.
    * @return ParentDe.
    */
   public List<PersonneDto> get_tab_parentDe ()
   {
      if (_id != null && _tab_parentDe == null)
      {
         _tab_parentDe = ApplicationUserBusiness.getPersonneService().findListParentDeByPersonne(_id);
      }
      return _tab_parentDe;
   }

   /**
    * Affecter la liste de 'Personne' : ParentDe.
    *           (In)(*) ParentDe.
    */
   public void setParentDe (final List<PersonneDto> p_tab_personne)
   {
      _tab_parentDe = p_tab_personne;
   }

   /**
    * Obtenir la liste de 'Pays' : Pays.
    * @return Pays.
    */
   public List<PaysDto> get_tab_pays ()
   {
      if (_id != null && _tab_pays == null)
      {
         _tab_pays = ApplicationUserBusiness.getPaysService().findListPaysByPersonne(_id);
      }
      return _tab_pays;
   }

   /**
    * Affecter la liste de 'Pays' : Pays.
    *           (In) Pays.
    */
   public void setPays (final List<PaysDto> p_tab_pays)
   {
      _tab_pays = p_tab_pays;
   }

   /**
    * Obtenir personneParentDe de personne.
    * @return personne
    */
   public Long get_personneParentDe_id ()
   {
      return _personneParentDe_id;
   }

   /**
    * Affecter personneParentDe de personne.
    * @param p_personneParentDe_id
    *           (In)(*) personne
    */
   public void set_personneParentDe_id (final Long p_personneParentDe_id)
   {
      _personneParentDe_id = p_personneParentDe_id;
   }

   /**
    * Recycler le dto 'Personne'.
    * @param p_id
    *           (In)(*) L'identifiant de personne.
    * @param p_nom
    *           (In)(*) nom.
    * @param p_prenom
    *           (In)(*) prenom.
    * @param p_civil
    *           (In)(*) civil.
    * @param p_dateNaissance
    *           (In)(*) dateNaissance.
    * @param p_salaire
    *           (In)(*) salaire.
    * @param p_grade_id
    *           (In) Grade.
    * @param p_marieAvec_id
    *           (In) MarieAvec.
    * @param p_personneParentDe_id
    *           (In)(*) personne
    */
   public void reset_PersonneDto (final Long p_id, final String p_nom, final String p_prenom, final Boolean p_civil, final Date p_dateNaissance, final Double p_salaire, final Long p_grade_id, final Long p_marieAvec_id, final Long p_personneParentDe_id)
   {
      setId(p_id);
      set_nom(p_nom);
      set_prenom(p_prenom);
      set_civil(p_civil);
      set_dateNaissance(p_dateNaissance);
      set_salaire(p_salaire);
      set_grade_id(p_grade_id);
      set_marieAvec_id(p_marieAvec_id);
      _tab_adresses = null;
      _tab_parentDe = null;
      _tab_pays = null;
      set_personneParentDe_id(p_personneParentDe_id);
   }

   /**
    * Recycler le dto 'Personne'.
    * @param p_id
    *           (In)(*) L'identifiant de personne.
    * @param p_nom
    *           (In)(*) nom.
    * @param p_prenom
    *           (In)(*) prenom.
    * @param p_civil
    *           (In)(*) civil.
    * @param p_dateNaissance
    *           (In)(*) dateNaissance.
    * @param p_salaire
    *           (In)(*) salaire.
    * @param p_grade
    *           (In) Grade.
    * @param p_marieAvec
    *           (In) MarieAvec.
    * @param p_personneParentDe
    *           (In)(*) personne
    */
   public void reset_PersonneDto (final Long p_id, final String p_nom, final String p_prenom, final Boolean p_civil, final Date p_dateNaissance, final Double p_salaire, final GradeDto p_grade, final PersonneDto p_marieAvec, final PersonneDto p_personneParentDe)
   {
      setId(p_id);
      set_nom(p_nom);
      set_prenom(p_prenom);
      set_civil(p_civil);
      set_dateNaissance(p_dateNaissance);
      set_salaire(p_salaire);
      set_grade(p_grade);
      set_marieAvec(p_marieAvec);
      _tab_adresses = null;
      _tab_parentDe = null;
      _tab_pays = null;
      if (p_personneParentDe == null)
      {
         set_personneParentDe_id(null);
      }
      else
      {
         set_personneParentDe_id(p_personneParentDe.getId());
      }
   }

   @Override
   public void validate () throws Spi4jValidationException
   {
      List<String> v_champsInvalides = null;
      v_champsInvalides = DtoUtil.checkMandatoryField("nom", _nom, v_champsInvalides);
      v_champsInvalides = DtoUtil.checkFieldSize("nom", _nom, PersonneAttributes_Enum.nom.getSize(), v_champsInvalides);
      v_champsInvalides = DtoUtil.checkMandatoryField("prenom", _prenom, v_champsInvalides);
      v_champsInvalides = DtoUtil.checkMandatoryField("civil", _civil, v_champsInvalides);
      v_champsInvalides = DtoUtil.checkMandatoryField("dateNaissance", _dateNaissance, v_champsInvalides);
      v_champsInvalides = DtoUtil.checkMandatoryField("salaire", _salaire, v_champsInvalides);
      v_champsInvalides = DtoUtil.checkMandatoryField("personneParentDe", _personneParentDe_id, v_champsInvalides);
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

      return getClass().getSimpleName() + '[' + _id + ", " + get_nom() + ", " + get_prenom() + ", " + get_civil() + ", " + get_dateNaissance() + ", " + get_salaire()   + ", " + get_grade_id ()  + ", " + get_marieAvec_id ()  + ", " + get_personneParentDe_id ()  + ']';

      // End of user code
   }

}
