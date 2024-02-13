package fr.pacman.estimation.services.dsl;

import java.util.ArrayList;
import java.util.List;

/**
 * Contient les informations concernant un package.
 * @author MINARM
 */
public class XPackage
{
   /** Le nom du package en absolu */
   private String _nom;
   /** La liste d'entité du package */
   private final List<XClassDsl> _lstClassDslInPackage = new ArrayList<XClassDsl>();
   /** La liste des sous package */
   private final List<XPackage> _lstSubXPackage = new ArrayList<XPackage>();

   /**
    * Constructeur.
    * @param p_nom Le nom du package en absolu (ex : "appwhite1.client-swing").
    */
   public XPackage (final String p_nom)
   {
      set_nom(p_nom);
   }

   /**
    * Obtenir le nom du package en absolu.
    * @return Le nom.
    */
   public String get_nom ()
   {
      return _nom;
   }

   /**
    * Affecter le nom du package en absolu.
    * @param p_nom Le nom.
    */
   public void set_nom (final String p_nom)
   {
      this._nom = p_nom;
   }

   /**
    * Ajouter une entité au package.
    * @param p_XClassDsl L'entité à ajouter.
    */
   public void addClassDsl (final XClassDsl p_XClassDsl)
   {
      // Ajouter l'entité dans le package
      _lstClassDslInPackage.add(p_XClassDsl);
   }

   /**
    * Obtenir la liste des entités du package.
    * @return La liste désirée.
    */
   public List<XClassDsl> get_lstClassDslInPackage ()
   {
      return _lstClassDslInPackage;
   }

   /**
    * Ajouter le sous package
    * @param p_subXPackage Le sous package.
    */
   public void addSubPackage (final XPackage p_subXPackage)
   {
      // Si OK
      if (p_subXPackage != null)
      {
         _lstSubXPackage.add(p_subXPackage);
      }
   }

   /**
    * Obtenir la liste des sous packages.
    * @return La liste désirée.
    */
   public List<XPackage> get_lstSubXPackage ()
   {
      return _lstSubXPackage;
   }

   /**
    * Obtenir le nombre d'entités récursivement.
    * @return La valeur.
    */
   public int getNbXClassDsl ()
   {
      int v_return = 0;

      // Le nombre d'entités du package
      v_return = _lstClassDslInPackage.size();

      // Parcourir les sous package du package courant
      for (XPackage v_XPackage : _lstSubXPackage)
      {
         v_return = v_return + v_XPackage.getNbXClassDsl();
      }

      return v_return;
   }
}
