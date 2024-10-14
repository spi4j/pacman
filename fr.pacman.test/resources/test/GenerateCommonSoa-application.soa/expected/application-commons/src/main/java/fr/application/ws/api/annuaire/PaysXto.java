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
 * Implémentation XTO pour le type 'Pays'.
 * @author safr@n
 */
@XmlRootElement(name = "PaysXto", namespace = "http://annuaire.application.fr/xto")
@XmlAccessorType(XmlAccessType.FIELD)
public class PaysXto extends ApplicationAutoFieldsXto_Abs implements Xto_Itf<Long>, RsXto_Itf
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

   /** . */
   // for annotation_nom
   // Start of user code for annotation_nom
   @XmlElement(namespace = "", name = "nom")
   @JsonProperty("nom")
   // End of user code
   private String _nom;

   /** . */
   // for annotation_capitale
   // Start of user code for annotation_capitale
   @XmlElement(namespace = "", name = "capitale")
   @JsonProperty("capitale")
   // End of user code
   private String _capitale;

   /** La FK sur le type 'Personne'. */
   // for annotation_pays_id
   // Start of user code for annotation_pays_id
   @XmlElement(namespace = "", name = "personnePays_id")
   @JsonProperty("personnePays_id")
   // End of user code
   private Long _personnePays_id;

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

   @Override
   public String toString ()
   {
      // toString
      // Start of user code toString

      return getClass().getName() + '['  + getId ()  + ", " + get_nom()   + ", " + get_capitale()   + ']';

      // End of user code
   }
}
