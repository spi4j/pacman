/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.ws.api.annuaire;
// Start of user code for imports

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.application.Binary;
import fr.application.ws.api.annuaire.PersonneXto;
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
 * Implémentation XTO pour le type 'Competence'.
 * @author safr@n
 */
@XmlRootElement(name = "CompetenceXto", namespace = "http://annuaire.application.fr/xto")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompetenceXto extends ApplicationAutoFieldsXto_Abs implements Xto_Itf<Long>, RsXto_Itf
{

   private static final long serialVersionUID = 1L;

   // ATTRIBUTS

   /** L'identifiant. */
   // for annotation_id
   // Start of user code for annotation_id
   @XmlElement(namespace = "", name = "id")
   @JsonProperty("id")
   private Long _id;
   // End of user code

   /** Le libellé de la compétence. */
   // for annotation_libelle
   // Start of user code for annotation_libelle
   @XmlElement(namespace = "", name = "libelle")
   @JsonProperty("libelle")
   // End of user code
   private String _libelle;

   /** . */
   // for annotation_typecompetence
   // Start of user code for annotation_typecompetence
   @XmlElement(namespace = "", name = "typecompetence")
   @JsonProperty("typecompetence")
   // End of user code
   private TypeCompetence_Enum _typecompetence;

   /** La liste de type 'Personne' associee a  l'instance de 'Competence' courante. */
   // for annotation_1__tab_dispose
   // Start of user code for annotation_1__tab_dispose
   @XmlElement(namespace = "", name = "dispose")
   @JsonProperty("dispose")
   // End of user code
   private List<PersonneXto> _tab_dispose;

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
    * Obtenir le libellé de la compétence.
    * @return Le libellé de la compétence.
    */
   public String get_libelle ()
   {
      return _libelle;
   }

   /**
    * Affecter le libellé de la compétence.
    * @param p_libelle
    *           (In)(*) Le libellé de la compétence.
    */
   public void set_libelle (final String p_libelle)
   {
      _libelle = p_libelle;
   }

   /**
    * Obtenir typecompetence.
    * @return typecompetence.
    */
   public TypeCompetence_Enum get_typecompetence ()
   {
      return _typecompetence;
   }

   /**
    * Affecter typecompetence.
    * @param p_typecompetence
    *           (In)(*) typecompetence.
    */
   public void set_typecompetence (final TypeCompetence_Enum p_typecompetence)
   {
      _typecompetence = p_typecompetence;
   }

   /**
    * Obtenir la liste de type 'Personne' associee à  l'instance de 'Competence' courante.
    * @return La liste desiree.
    */
   public List<PersonneXto> get_tab_dispose ()
   {
      return _tab_dispose;
   }

   /**
    * Affecter les Personne de competence.
    *           (In) Dispose.
    */
   public void setDispose (final List<PersonneXto> p_tab_personne)
   {
      _tab_dispose = p_tab_personne;
   }

   @Override
   public String toString ()
   {
      // toString
      // Start of user code toString

      return getClass().getName() + '['  + getId ()  + ", " + get_libelle()   + ", " + get_typecompetence()   + ']';

      // End of user code
   }
}
