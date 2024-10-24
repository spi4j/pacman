package fr.pacman.commons.convention;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import fr.pacman.commons.convention.norme.Norme_Abs;
import fr.pacman.commons.services.StringUtils;

/**
 * Définit les méthodes que l'on peut appeler pour notre convention de nommage
 * @author MINARM
 * 
 */
public final class NotationDefinition
{
   /** Le nom associé à 'normeCamelCase' */
   private static final String c_name_normeCamelCase = "CamelCase";

   /** Le nom associé à 'normeCamelCase' */
   private static final String c_name_normeCamelCaseIgnoreFirst = "CamelCaseIgnoreFirst";

   /** Le nom associé à 'normeLanguageC' */
   private static final String c_name_normeLanguageC = "LanguageC";

   /** Le nom associé à 'normeUpperFirst' */
   private static final String c_name_normeUpperFirst = "UpperFirst";

   /** Le nom associé à 'normeLowerFirst' */
   private static final String c_name_normeLowerFirst = "LowerFirst";

   /** Le nom associé à 'normeUpperAll' */
   private static final String c_name_normeUpperAll = "UpperAll";

   /** Le nom associé à 'normeLowerAll' */
   private static final String c_name_normeLowerAll = "LowerAll";
   
   /** Le nom associé à 'normeTrimLowerAll' */
   private static final String c_name_normeTrimLowerAll = "TrimLowerAll";

   /** La suite de noms */
   private final List<String> _tab_allNames = new ArrayList<String>();

   /**
    * Constructeur par défaut.
    * @param p_name
    *           la norme
    **/
   public NotationDefinition (final String p_name)
   {
      // Si on a au moins un nom (ex : "CamelCase")
      if (p_name != null && !p_name.trim().isEmpty())
      {
         String v_nameCour;
         final StringTokenizer v_st = new StringTokenizer(p_name, ".");
         // Parcourir les noms (ex : "CamelCase.LowerAll.upperFirst")
         while (v_st.hasMoreTokens())
         {
            // Obtenir le nom de notation courante
            v_nameCour = (String) v_st.nextElement();
            // Ajouter le nom à la liste
            _tab_allNames.add(v_nameCour);
         }
      }
   }

   /**
    * Permet d'obtenir une chaîne représentant tous les attributs du bean.
    * @return La chaîne représentant tous les attributs du bean.
    */
   @Override
   public String toString ()
   {
      boolean v_first = true;
      final StringBuilder v_strBuilder = new StringBuilder();
      for (final String v_name : _tab_allNames)
      {
         if (v_first)
         {
            v_first = false;
         }
         else
         {
            v_strBuilder.append('.');
         }
         v_strBuilder.append(v_name);
      }

      return v_strBuilder.toString();
   }

   /**
    * Demander la mise en norme "CamelCase" (ex : "une valeur" --> "uneValeur").
    * @return Une instance prenant en compte cette notation.
    */
   public static NotationDefinition normeCamelCase ()
   {
      return new NotationDefinition(c_name_normeCamelCase);
   }

   /**
    * Demander la mise en norme "CamelCaseIgnoreFirst" (ex : "une valeur" --> "uneValeur").
    * @return Une instance prenant en compte cette notation.
    */
   public static NotationDefinition normeCamelCaseIgnoreFirst ()
   {
      return new NotationDefinition(c_name_normeCamelCaseIgnoreFirst);
   }

   /**
    * Demander la mise en norme "langage C" (ex : "une valeur" --> "une_valeur").
    * @return Une instance prenant en compte cette notation.
    */
   public static NotationDefinition normeLanguageC ()
   {
      return new NotationDefinition(c_name_normeLanguageC);
   }

   /**
    * Demander la mise en norme "LowerAll" (ex : "uNe vaLeur" --> "une valeur").
    * @return Une instance prenant en compte cette notation.
    */
   public static NotationDefinition normeLowerAll ()
   {
      return new NotationDefinition(c_name_normeLowerAll);
   }
   
   /**
    * Demander la mise en norme "TrimLowerAll" (ex : "uNe vaLeur" --> "unevaleur").
    * @return Une instance prenant en compte cette notation.
    */
   public static NotationDefinition normeTrimLowerAll ()
   {
      return new NotationDefinition(c_name_normeTrimLowerAll);
   }


   /**
    * Demander la mise en norme "LowerFirst" (ex : "UnE Valeur" --> "unE Valeur").
    * @return Une instance prenant en compte cette notation.
    */
   public static NotationDefinition normeLowerFirst ()
   {
      return new NotationDefinition(c_name_normeLowerFirst);
   }

   /**
    * Demander la mise en norme "UpperAll" (ex : "une valeur" --> "UNE VALEUR").
    * @return Une instance prenant en compte cette notation.
    */
   public static NotationDefinition normeUpperAll ()
   {
      return new NotationDefinition(c_name_normeUpperAll);
   }

   /**
    * Demander la mise en norme "UpperFirst" (ex : "unE Valeur" --> "UnE Valeur").
    * @return Une instance prenant en compte cette notation.
    */
   public static NotationDefinition normeUpperFirst ()
   {
      return new NotationDefinition(c_name_normeUpperFirst);
   }

   /**
    * Permet d'obtenir la valeur résolue en tenant compte de la notation désirée.
    * @param p_value
    *           La valeur d'origine.
    * @return La valeur résolue.
    */
   public String applyNorme (final String p_value)
   {
      String v_return = StringUtils.normalize(p_value);
      // Parcourir les noms de notation demandés
      for (String v_nameNotation : _tab_allNames)
      {
         // Appliquer la norme demandée
         final Norme_Abs v_Norme = findNorme(v_nameNotation);
         // Appliquer la norme
         v_return = v_Norme.applyNorme(v_return);
      }

      return v_return;
   }

   /**
    * Appliquer la norme.
    * @param p_nameNotation
    *           Le nom de la norme à appliquer (ex : "CamelCase").
    * @return Le valeur normée.
    */
   private Norme_Abs findNorme (final String p_nameNotation)
   {
      final String v_prefixClassNorme = Norme_Abs.class.getPackage().getName() + ".Norme";
      try
      {
         // Instancier la norme désirée
         @SuppressWarnings("unchecked")
         final Class<Norme_Abs> v_normeClass = (Class<Norme_Abs>) Class.forName(v_prefixClassNorme + p_nameNotation);
         return (Norme_Abs) v_normeClass.getDeclaredConstructor().newInstance();
      }
      catch (final Exception v_err)
      {
         throw new IllegalArgumentException("Pas possible d'instancier la norme \"" + v_prefixClassNorme
                  + p_nameNotation, v_err);
      }
   }

   /**
    * Ajoute une norme à la suite de la norme courante
    * @param p_normeToAppend
    *           la norme à ajouter
    * @return la norme concaténée
    */
   public NotationDefinition appendNorme (final NotationDefinition p_normeToAppend)
   {
      _tab_allNames.addAll(p_normeToAppend._tab_allNames);
      return this;
   }

}
