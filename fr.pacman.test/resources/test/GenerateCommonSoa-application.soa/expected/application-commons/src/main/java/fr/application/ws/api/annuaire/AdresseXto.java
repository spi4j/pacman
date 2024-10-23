/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.ws.api.annuaire;
// Start of user code for imports

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.application.Binary;
import fr.spi4j.ws.rs.RsXto_Itf;
import fr.spi4j.ws.xto.Xto_Itf;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Implémentation XTO pour le type 'Adresse'.
 * @author safr@n
 */
@XmlRootElement(name = "AdresseXto", namespace = "http://annuaire.application.fr/xto")
@XmlAccessorType(XmlAccessType.FIELD)
public class AdresseXto extends ApplicationAutoFieldsXto_Abs implements Xto_Itf<Long>, RsXto_Itf
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
   
   // Start of user code for annotation_rue
   @XmlElement(namespace = "", name = "rue")
   @JsonProperty("rue")
   // End of user code
   private String _rue;

   /** . */
   
   // Start of user code for annotation_ville
   @XmlElement(namespace = "", name = "ville")
   @JsonProperty("ville")
   // End of user code
   private String _ville;

   /** . */
   
   // Start of user code for annotation_codePostal
   @XmlElement(namespace = "", name = "codePostal")
   @JsonProperty("codePostal")
   // End of user code
   private String _codePostal;

   /** La FK sur le type 'Personne'. */
   
   // Start of user code for annotation_adresses_id
   @XmlElement(namespace = "", name = "personneAdresses_id")
   @JsonProperty("personneAdresses_id")
   // End of user code
   private Long _personneAdresses_id;

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

   @Override
   public String toString ()
   {
      
      // Start of user code toString

      return getClass().getName() + '['  + getId ()  + ", " + get_rue()   + ", " + get_ville()   + ", " + get_codePostal()   + ']';

      // End of user code
   }
}
