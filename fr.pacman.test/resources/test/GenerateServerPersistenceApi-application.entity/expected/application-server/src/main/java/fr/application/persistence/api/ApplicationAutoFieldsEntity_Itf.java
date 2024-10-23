/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.persistence.api;

import fr.spi4j.persistence.entity.Entity_Itf;
import fr.spi4j.type.XtopSup;

/**
 * Interface d'entité persistante pour les champs automatiques.
 * @author safr@n
 * @param <TypeId>
 *           Le type générique de la clé primaire.
 */
public interface ApplicationAutoFieldsEntity_Itf<TypeId> extends Entity_Itf<TypeId>
{
   /**
    * Obtenir XDMAJ.
    * @return XDMAJ
    */
   public Date get_xdmaj ();

   /**
    * Affecter XDMAJ.
    * @param p_xdmaj
    *           XDMAJ
    */
   public void set_xdmaj (final Date p_xdmaj);

   /**
    * Obtenir XTOPSUP.
    * @return XTOPSUP
    */
   public XtopSup get_xtopsup ();

   /**
    * Affecter XTOPSUP.
    * @param p_xtopsup
    *           XTOPSUP
    */
   public void set_xtopsup (final XtopSup p_xtopsup);

}
