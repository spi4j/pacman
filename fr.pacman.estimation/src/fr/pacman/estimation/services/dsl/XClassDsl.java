package fr.pacman.estimation.services.dsl;

import java.util.ArrayList;
import java.util.List;

import fr.pacman.estimation.services.complexite_pf.ComplexiteEntitePfGdi_Enum;

/**
 * Contient les informations de l'entité.
 * 
 * @author MINARM
 */
public class XClassDsl
{
   /** Le nom de l'entité (ex : "saisie personne") */
   private String _nom;

   /** La liste des attributs de l'entité */
   private final List<String> _lstAttribute = new ArrayList<String>();
   /** La liste des relations 1,N --> 1,N de l'entité */
   private final List<String> _lstRelation1N_1N = new ArrayList<String>();
   /** La liste des relations 1,1 --> 1,N de l'entité */
   private final List<String> _lstRelation11_1N = new ArrayList<String>();
   /** La liste des relations 0,1 --> 1de l'entité */
   private final List<String> _lstRelation01_1 = new ArrayList<String>();

   /**
    * Constructeur.
    * 
    * @param p_nom
    *           Le nom de l'entité (ex : "saisie personne").
    */
   public XClassDsl (final String p_nom)
   {
      set_nom(p_nom);
   }

   /**
    * Obtenir le nom de l'entité.
    * 
    * @return Le nom.
    */
   public String get_nom ()
   {
      return _nom;
   }

   /**
    * Affecter le nom de l'entité (ex : "saisie personne").
    * 
    * @param p_nom
    *           Le nom.
    */
   public void set_nom (final String p_nom)
   {
      _nom = p_nom;
   }

   /**
    * Ajouter un attribut à l'entité.
    * 
    * @param p_attributeName
    *           Le nom de l'attribut.
    */
   public void addAttribute (final String p_attributeName)
   {
      _lstAttribute.add(p_attributeName);
   }

   /**
    * Permet de savoir la complexité de l'entité.
    * 
    * @return La valeur de la complexité.
    */
   public ComplexiteEntitePfGdi_Enum getComplexite ()
   {
      ComplexiteEntitePfGdi_Enum v_return;
      // 1 "SLD"
      if (getNbSld() <= 1)
      {
         // Moins de 20 "DE"
         if (getNbDe() <= 19)
         {
            v_return = ComplexiteEntitePfGdi_Enum.faible;
         }
         // Entre 20 et 50 "DE"
         else if ((getNbDe() > 19) && (getNbDe() <= 50))
         {
            v_return = ComplexiteEntitePfGdi_Enum.faible;
         }
         // Plus de 50 "DE"
         else
         {
            v_return = ComplexiteEntitePfGdi_Enum.moyenne;
         }
      }
      // Entre 2 et 5 "SLD"
      else if ((getNbSld() >= 2) && (getNbSld() <= 5))
      {
         // Moins de 20 "DE"
         if (getNbDe() <= 19)
         {
            v_return = ComplexiteEntitePfGdi_Enum.faible;
         }
         // Entre 20 et 50 "DE"
         else if ((getNbDe() > 19) && (getNbDe() <= 50))
         {
            v_return = ComplexiteEntitePfGdi_Enum.moyenne;
         }
         // Plus de 50 "DE"
         else
         {
            v_return = ComplexiteEntitePfGdi_Enum.elevee;
         }
      }
      // 6 "SLD" et +
      else
      {
         // Moins de 20 "DE"
         if (getNbDe() <= 19)
         {
            v_return = ComplexiteEntitePfGdi_Enum.moyenne;
         }
         // Entre 20 et 50 "DE"
         else if ((getNbDe() > 19) && (getNbDe() <= 50))
         {
            v_return = ComplexiteEntitePfGdi_Enum.elevee;
         }
         // Plus de 50 "DE"
         else
         {
            v_return = ComplexiteEntitePfGdi_Enum.elevee;
         }
      }

      return v_return;
   }

   /**
    * Obtenir le nombre de SLD (= Sous-Groupe Logique).
    * <pre>
    *    = Le nombre de relation (1,n --> 1,n) + 2
    *    + Le nombre de relation (1,1-->1,n) + 1
    *    + Le nombre de relation (0,1-->1)
    * </pre>
    * @return La valeur
    */
   private int getNbSld ()
   {
      int v_return = 0;

      // Si au moins une relation 1,N --> 1,N
      if (_lstRelation1N_1N.isEmpty() == false)
      {
         v_return = v_return + (_lstRelation1N_1N.size() + 2);
      }

      // Si au moins une relation 1,1 --> 1,N
      if (_lstRelation11_1N.isEmpty() == false)
      {
         v_return = v_return + (_lstRelation11_1N.size() + 1);
      }

      // Si au moins une relation 0,1 --> 1
      if (_lstRelation01_1.isEmpty() == false)
      {
         v_return = v_return + _lstRelation01_1.size();
      }

      return v_return;
   }

   /**
    * Obtenir le nombre de DE (= 1 attribut d'une entité)
    * @return Le nombre de DE.
    */
   private int getNbDe ()
   {
      return _lstAttribute.size();
   }

   /**
    * Ajouter relation 1,N --> 1,N.
    * @param p_name Le nom de la relation.
    */
   public void addRelation_N_N (final String p_name)
   {
      _lstRelation1N_1N.add(p_name);
   }

   /**
    * Ajouter relation 1,1 --> 1,N.
    * @param p_name Le nom de la relation.
    */
   public void addRelation_1_N (final String p_name)
   {
      _lstRelation11_1N.add(p_name);
   }

   /**
    * Ajouter relation 0,1 --> 1.
    * @param p_name Le nom de la relation.
    */
   public void addRelation_0_1 (final String p_name)
   {
      _lstRelation01_1.add(p_name);
   }

   /**
    * La liste des relations 1,N --> 1,N.
    * @return La liste désirée.
    */
   public List<String> get_lstRelation1N_1N ()
   {
      return _lstRelation1N_1N;
   }

   /**
    * La liste des relations 1,1 --> 1,N.
    * @return La liste désirée.
    */
   public List<String> get_lstRelation11_1N ()
   {
      return _lstRelation11_1N;
   }

   /**
    * La liste des relations 0,1 --> 1.
    * @return La liste désirée.
    */
   public List<String> get_lstRelation01_1 ()
   {
      return _lstRelation01_1;
   }

   @Override
   public String toString ()
   {
      return "Entité " + _nom + " possède une complexité de " + getComplexite() + " - DE=" + getNbDe() + " - SLD=" + getNbSld();
   }

}
