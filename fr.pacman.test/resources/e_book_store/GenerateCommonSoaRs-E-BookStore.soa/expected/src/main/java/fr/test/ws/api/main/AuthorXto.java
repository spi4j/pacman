/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.ws.api.main;
// Start of user code for imports

import fr.spi4j.ws.rs.RsXto_Itf;
import fr.spi4j.ws.xto.Xto_Itf;
import fr.test.Binary;
import fr.test.ws.api.main.BookXto;
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
 * Implémentation XTO pour le type 'Author'.
 * @author safr@n
 */
@XmlRootElement(name = "AuthorXto", namespace = "http://main.test.fr/xto")
@XmlAccessorType(XmlAccessType.FIELD)
public class AuthorXto implements Xto_Itf<Long>, RsXto_Itf
{

   private static final long serialVersionUID = 1L;

   // ATTRIBUTS

   /** L'identifiant. */
   // for annotation_id
   // Start of user code 4254d63f8960d40c586ab8ad348942ec
   @XmlElement(namespace = "", name = "id")
   
   private Long _id;
   // End of user code

   /** . */
   // for annotation_name
   // Start of user code 16a98109aebfaf1b73aacd8550bb5191
   @XmlElement(namespace = "", name = "name")
   
   // End of user code
   private String name;

   /** La liste de type 'Book' associee a  l'instance de 'Author' courante. */
   // for annotation_wrote
   // Start of user code e592f4931efbf5e8cad0653196707b49
   @XmlElement(namespace = "", name = "wrote")
   
   // End of user code
   private List<BookXto> wrote;

   // METHODES

   @Override
   public Long getId ()
   {
      return _id;
   }

   @Override
   public void setId (final Long id)
   {
      this._id = id;
   }

   /**
    * Obtenir name.
    * @return name.
    */
   public String getName ()
   {
      return name;
   }

   /**
    * Affecter name.
    * @param name
    *           (In)(*) name.
    */
   public void setName (final String name)
   {
      this.name = name;
   }

   /**
    * Obtenir la liste de type 'Book' associee à  l'instance de 'Author' courante.
    * @return La liste desiree.
    */
   public List<BookXto> getWrote ()
   {
      return wrote;
   }

   /**
    * Affecter les Book de author.
    *           (In) wrote.
    */
   public void setWrote (final List<BookXto> book)
   {
      this.wrote = book;
   }

   @Override
   public String toString ()
   {
      // toString
      // Start of user code c424b4614133c28bf2c3dee0d0b54f6d

      return getClass().getName() + '['  + getId ()  + ", " + getName()   + ']';

      // End of user code
   }
}
