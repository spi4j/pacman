/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.ws.api.ref;
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
 * Implémentation XTO pour le type 'Grade'.
 * @author safr@n
 */
@XmlRootElement(name = "GradeXto", namespace = "http://ref.application.fr/xto")
@XmlAccessorType(XmlAccessType.FIELD)
public class GradeXto extends ApplicationAutoFieldsXto_Abs implements Xto_Itf<Long>, RsXto_Itf
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

   /** Le libellé du grade. */
   // for annotation_libelle
   // Start of user code for annotation_libelle
   @XmlElement(namespace = "", name = "libelle")
   @JsonProperty("libelle")
   // End of user code
   private String _libelle;

   /** Le trigramme du grade. */
   // for annotation_trigramme
   // Start of user code for annotation_trigramme
   @XmlElement(namespace = "", name = "trigramme")
   @JsonProperty("trigramme")
   // End of user code
   private String _trigramme;

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
    * Obtenir le libellé du grade.
    * @return Le libellé du grade.
    */
   public String get_libelle ()
   {
      return _libelle;
   }

   /**
    * Affecter le libellé du grade.
    * @param p_libelle
    *           (In)(*) Le libellé du grade.
    */
   public void set_libelle (final String p_libelle)
   {
      _libelle = p_libelle;
   }

   /**
    * Obtenir le trigramme du grade.
    * @return Le trigramme du grade.
    */
   public String get_trigramme ()
   {
      return _trigramme;
   }

   /**
    * Affecter le trigramme du grade.
    * @param p_trigramme
    *           (In)(*) Le trigramme du grade.
    */
   public void set_trigramme (final String p_trigramme)
   {
      _trigramme = p_trigramme;
   }

   @Override
   public String toString ()
   {
      // toString
      // Start of user code toString

      return getClass().getName() + '['  + getId ()  + ", " + get_libelle()   + ", " + get_trigramme()   + ']';

      // End of user code
   }
}
