/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.ws.api.annuaire;
// Start of user code for imports

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.application.Binary;
import fr.application.ws.api.annuaire.AdresseXto;
import fr.application.ws.api.annuaire.PaysXto;
import fr.application.ws.api.annuaire.PersonneXto;
import fr.application.ws.api.ref.GradeXto;
import fr.spi4j.ws.rs.RsXto_Itf;
import fr.spi4j.ws.xto.Xto_Itf;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Implémentation XTO pour le type 'Personne'.
 * @author safr@n
 */
@XmlRootElement(name = "PersonneXto", namespace = "http://annuaire.application.fr/xto")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonneXto extends ApplicationAutoFieldsXto_Abs implements Xto_Itf<Long>, RsXto_Itf
{

   private static final long serialVersionUID = 1L;

   // ATTRIBUTS

   /** L'identifiant. */
   
   // Start of user code for annotation_id
   @XmlElement(namespace = "", name = "id")
   @JsonProperty("id")
   private Long _id;
   // End of user code

   /** . */
   
   // Start of user code for annotation_nom
   @XmlElement(namespace = "", name = "nom")
   @JsonProperty("nom")
   // End of user code
   private String _nom;

   /** . */
   
   // Start of user code for annotation_prenom
   @XmlElement(namespace = "", name = "prenom")
   @JsonProperty("prenom")
   // End of user code
   private String _prenom;

   /** . */
   
   // Start of user code for annotation_civil
   @XmlElement(namespace = "", name = "civil")
   @JsonProperty("civil")
   // End of user code
   private Boolean _civil;

   /** . */
   
   // Start of user code for annotation_dateNaissance
   @XmlElement(namespace = "", name = "dateNaissance")
   @JsonProperty("dateNaissance")
   // End of user code
   private Date _dateNaissance;

   /** . */
   
   // Start of user code for annotation_salaire
   @XmlElement(namespace = "", name = "salaire")
   @JsonProperty("salaire")
   // End of user code
   private Double _salaire;

   /** La FK sur le Type 'Grade'. */
   
   // Start of user code for annotation_1_grade_id
   @XmlElement(namespace = "", name = "grade_id")
   @JsonProperty("grade_id")
   // End of user code
   private Long _grade_id;

   /** L'instance de 'Grade' associee a  l'instance de 'Personne' courante. */
   
   // Start of user code for annotation_grade
   @XmlElement(namespace = "", name = "grade")
   @JsonProperty("grade")
   // End of user code
   private GradeXto _grade;

   /** La FK sur le Type 'Personne'. */
   
   // Start of user code for annotation_1_marieAvec_id
   @XmlElement(namespace = "", name = "marieAvec_id")
   @JsonProperty("marieAvec_id")
   // End of user code
   private Long _marieAvec_id;

   /** L'instance de 'Personne' associee a  l'instance de 'Personne' courante. */
   
   // Start of user code for annotation_marieAvec
   @XmlElement(namespace = "", name = "marieAvec")
   @JsonProperty("marieAvec")
   // End of user code
   private PersonneXto _marieAvec;

   /** La liste de type 'Adresse' associee a  l'instance de 'Personne' courante. */
   
   // Start of user code for annotation__tab_adresses
   @XmlElement(namespace = "", name = "adresses")
   @JsonProperty("adresses")
   // End of user code
   private List<AdresseXto> _tab_adresses;

   /** La liste de type 'Personne' associee a  l'instance de 'Personne' courante. */
   
   // Start of user code for annotation__tab_parentDe
   @XmlElement(namespace = "", name = "parentDe")
   @JsonProperty("parentDe")
   // End of user code
   private List<PersonneXto> _tab_parentDe;

   /** La liste de type 'Pays' associee a  l'instance de 'Personne' courante. */
   
   // Start of user code for annotation__tab_pays
   @XmlElement(namespace = "", name = "pays")
   @JsonProperty("pays")
   // End of user code
   private List<PaysXto> _tab_pays;

   /** La FK sur le type 'Personne'. */
   
   // Start of user code for annotation_parentDe_id
   @XmlElement(namespace = "", name = "personneParentDe_id")
   @JsonProperty("personneParentDe_id")
   // End of user code
   private Long _personneParentDe_id;

   // METHODES

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
    * Obtenir l'instance de 'Grade' associee a  l'instance de 'Personne' courante.
    * @return L'instance desiree.
    */
   public GradeXto get_grade ()
   {
      return _grade;
   }

   /**
    * Affecter grade de personne.
    * @param p_grade
    *           (In) Grade.
    */
   public void set_grade (final GradeXto p_grade)
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
    * Obtenir l'instance de 'Personne' associee a  l'instance de 'Personne' courante.
    * @return L'instance desiree.
    */
   public PersonneXto get_marieAvec ()
   {
      return _marieAvec;
   }

   /**
    * Affecter marieAvec de personne.
    * @param p_marieAvec
    *           (In) MarieAvec.
    */
   public void set_marieAvec (final PersonneXto p_marieAvec)
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
    * Obtenir la liste de type 'Adresse' associee à  l'instance de 'Personne' courante.
    * @return La liste desiree.
    */
   public List<AdresseXto> get_tab_adresses ()
   {
      return _tab_adresses;
   }

   /**
    * Affecter les Adresse de personne.
    *           (In) Adresses.
    */
   public void setAdresses (final List<AdresseXto> p_tab_adresse)
   {
      _tab_adresses = p_tab_adresse;
   }

   /**
    * Obtenir la liste de type 'Personne' associee à  l'instance de 'Personne' courante.
    * @return La liste desiree.
    */
   public List<PersonneXto> get_tab_parentDe ()
   {
      return _tab_parentDe;
   }

   /**
    * Affecter les Personne de personne.
    *           (In)(*) ParentDe.
    */
   public void setParentDe (final List<PersonneXto> p_tab_personne)
   {
      _tab_parentDe = p_tab_personne;
   }

   /**
    * Obtenir la liste de type 'Pays' associee à  l'instance de 'Personne' courante.
    * @return La liste desiree.
    */
   public List<PaysXto> get_tab_pays ()
   {
      return _tab_pays;
   }

   /**
    * Affecter les Pays de personne.
    *           (In) Pays.
    */
   public void setPays (final List<PaysXto> p_tab_pays)
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

   @Override
   public String toString ()
   {
      
      // Start of user code toString

      return getClass().getName() + '['  + getId ()  + ", " + get_nom()   + ", " + get_prenom()   + ", " + get_civil()   + ", " + get_dateNaissance()   + ", " + get_salaire()   + ", " + get_grade_id () + ", " + get_marieAvec_id () + ']';

      // End of user code
   }
}
