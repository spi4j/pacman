/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.persistence.impl_jdbc;

import fr.application.persistence.api.ApplicationAutoFieldsEntity_Itf;
import fr.spi4j.type.XtopSup;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe abstraite contenant les attributs additionnels qui sont communs à toutes les entités.
 * @author safr@n
 * @param <TypeId>
 *           Le type générique de la clé primaire.
 */
public abstract class ApplicationAutoFieldsEntity_Abs<TypeId> implements ApplicationAutoFieldsEntity_Itf<TypeId>
{
   // CONSTANTES

   /**
    * SerialUid.
    */
   private static final long serialVersionUID = -1;

   // Constantes
   // Start of user code Constantes

   // End of user code

   // ATTRIBUTS

   /** Xdmaj */
   private Date _xdmaj;

   /** Xtopsup */
   private XtopSup _xtopsup;

   // Attributs
   // Start of user code Attributs

   // End of user code

   // METHODES

   @Override
   public Date get_xdmaj ()
   {
      return _xdmaj;
   }

   @Override
   public void set_xdmaj (final Date p_xdmaj)
   {
      _xdmaj = p_xdmaj;
   }

   @Override
   public XtopSup get_xtopsup ()
   {
      return _xtopsup;
   }

   @Override
   public void set_xtopsup (final XtopSup p_xtopsup)
   {
      _xtopsup = p_xtopsup;
   }

   /**
    * Vérifie la validité des champs additionnels.
    * @return La liste des champs invalides (null si aucun champ n'est invalide)
    */
   protected List<String> prepareValidate ()
   {
      // valide les attributs
      List<String> v_champsInvalides = null;
      if (_xdmaj == null)
      {
         v_champsInvalides = new ArrayList<>();
         v_champsInvalides.add("XDMAJ");
      }
      if (_xtopsup == null)
      {
         if (v_champsInvalides == null)
         {
            v_champsInvalides = new ArrayList<>();
         }
         v_champsInvalides.add("XTOPSUP");
      }
      return v_champsInvalides;
   }
}
