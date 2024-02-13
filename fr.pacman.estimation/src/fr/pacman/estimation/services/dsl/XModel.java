package fr.pacman.estimation.services.dsl;

import java.util.ArrayList;
import java.util.List;

/**
 * Contient les informations sur la modélisation.
 * 
 * @author MINARM
 */
public class XModel
{
   /** Le nom du modèle */
   private String _nom;

   /** La liste des packages */
   private final List<XPackage> _lstXPackage = new ArrayList<XPackage>();

   /**
    * Constructeur.
    * 
    * @param p_nom
    *           Le nom du modèle.
    */
   public XModel (final String p_nom)
   {
      set_nom(p_nom);
   }

   /**
    * Obtenir le nom du modèle.
    * 
    * @return Le nom.
    */
   public String get_nom ()
   {
      return _nom;
   }

   /**
    * Affecter le nom du modèle.
    * 
    * @param p_nom
    *           Le nom.
    */
   public void set_nom (final String p_nom)
   {
      _nom = p_nom;
   }

   /**
    * Obtenir la liste des packages
    * @return la liste des packages
    */
   public List<XPackage> get_lstXPackage ()
   {
      return _lstXPackage;
   }

   /**
    * Ajouter un package au modèle.
    * 
    * @param p_XPackage
    *           L'instance du package.
    */
   public void addPackage (final XPackage p_XPackage)
   {
      _lstXPackage.add(p_XPackage);
   }

   @Override
   public String toString ()
   {
      String v_return = _nom + " : ";

      XPackage v_XPackage;
      XClassDsl v_XClassDsl;
      // Parcourir les packages
      for (int v_i = 0; v_i < _lstXPackage.size(); v_i++)
      {
         // Obtenir le package courant
         v_XPackage = _lstXPackage.get(v_i);
         v_return = v_return + "\n   " + v_XPackage.get_nom();
         // Parcourir les classes
         for (int v_j = 0; v_j < v_XPackage.get_lstClassDslInPackage().size(); v_j++)
         {
            // Obtenir l'entité courante du package
            v_XClassDsl = v_XPackage.get_lstClassDslInPackage().get(v_j);

            v_return = v_return + "\n      " + v_XClassDsl.get_nom();
         }
      }
      return v_return;
   }
}
