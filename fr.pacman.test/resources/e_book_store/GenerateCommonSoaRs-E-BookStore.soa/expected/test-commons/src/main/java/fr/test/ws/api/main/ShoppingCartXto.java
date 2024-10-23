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
import fr.test.ws.api.main.ShoppingCartLineXto;
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
 * Implémentation XTO pour le type 'ShoppingCart'.
 * @author safr@n
 */
@XmlRootElement(name = "ShoppingCartXto", namespace = "http://main.test.fr/xto")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShoppingCartXto implements Xto_Itf<Long>, RsXto_Itf
{

   private static final long serialVersionUID = 1L;

   // ATTRIBUTS

   /** L'identifiant. */
   
   // Start of user code 4254d63f8960d40c586ab8ad348942ec
   @XmlElement(namespace = "", name = "id")
   
   private Long _id;
   // End of user code

   /** . */
   
   // Start of user code 3817614d5dbf15fea76e50b2841bdead
   @XmlElement(namespace = "", name = "status")
   
   // End of user code
   private StatusEnum status;

   /** La liste de type 'ShoppingCartLine' associee a  l'instance de 'ShoppingCart' courante. */
   
   // Start of user code 8c563fc31b65ae7d251a510108791e53
   @XmlElement(namespace = "", name = "lines")
   
   // End of user code
   private List<ShoppingCartLineXto> lines;

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
    * Obtenir status.
    * @return status.
    */
   public StatusEnum getStatus ()
   {
      return status;
   }

   /**
    * Affecter status.
    * @param status
    *           (In)(*) status.
    */
   public void setStatus (final StatusEnum status)
   {
      this.status = status;
   }

   /**
    * Obtenir la liste de type 'ShoppingCartLine' associee à  l'instance de 'ShoppingCart' courante.
    * @return La liste desiree.
    */
   public List<ShoppingCartLineXto> getLines ()
   {
      return lines;
   }

   /**
    * Affecter les ShoppingCartLine de shoppingCart.
    *           (In) lines.
    */
   public void setLines (final List<ShoppingCartLineXto> shoppingCartLine)
   {
      this.lines = shoppingCartLine;
   }

   @Override
   public String toString ()
   {
      
      // Start of user code c424b4614133c28bf2c3dee0d0b54f6d

      return getClass().getName() + '['  + getId ()  + ", " + getStatus()   + ']';

      // End of user code
   }
}
