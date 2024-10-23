package fr.pacman.commons.convention;

import fr.pacman.commons.convention.rule.AttributeRule;
import fr.pacman.commons.convention.rule.ClassRule;
import fr.pacman.commons.convention.rule.MethodRule;
import fr.pacman.commons.convention.rule.PackageRule;
import fr.pacman.commons.convention.rule.PageRule;
import fr.pacman.commons.convention.rule.ParameterRule;
import fr.pacman.commons.convention.rule.VariableRule;

/**
 * Classe Point d'entre des queries acceleo qui concernent l'application des conventions de nommage du projet.
 * 
 * @author MINARM
 */
public final class ApplyNorme
{

   /**
    * Constructeur prive.
    */
   private ApplyNorme ()
   {
      super();
   }

   /**
    * Applique la norme pour le package de persistence. Exemple : annuaire -> persistence Attention exception, ici on retourne juste le nom du package on ne peut pas definir le chemin complet a l'avance.
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return la valeur normee.
    */
   public static String norme_packagePersistence (final String p_value)
   {
      return PackageRule.applyNorme_packagePersistence(p_value);
   }

public static String norme_packagePersistenceJavaService(final String p_value){return norme_packagePersistence(p_value);}

   /**
    * Recupere le package d'implementation jdbc. Exemple : annuaire -> impl_jdbc Attention exception, ici on retourne juste le nom du package.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return la valeur normee.
    */
   public static String norme_packageImplemJdbc (final String p_value)
   {
      return PackageRule.applyNorme_packageImplemJdbc(p_value);
   }

public static String norme_packageImplemJdbcJavaService(final String p_value){return norme_packageImplemJdbc(p_value);}

   /**
    * Recupere pour le package d'implementation server. Exemple : annuaire -> impl_server Attention exception, ici on retourne juste le nom du package.
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return la valeur normee.
    */
   public static String norme_packageImplemServer (final String p_value)
   {
      return PackageRule.applyNorme_packageImplemServer(p_value);
   }

public static String norme_packageImplemServerJavaService(final String p_value){return norme_packageImplemServer(p_value);}

   /**
    * Recupere le package de matching. Exemple : annuaire -> matching Attention exception, ici on retourne juste le nom du package
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return la valeur normee.
    */
   public static String norme_packageMatching (final String p_value)
   {
      return PackageRule.applyNorme_packageMatching(p_value);
   }

public static String norme_packageMatchingJavaService(final String p_value){return norme_packageMatching(p_value);}

   /**
    * Recupere le package d'api. Exemple : annuaire -> api Attention exception, ici on retourne juste le nom du package.
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return la valeur normee.
    */
   public static String norme_packageApi (final String p_value)
   {
      return PackageRule.applyNorme_packageApi(p_value);
   }

public static String norme_packageApiJavaService(final String p_value){return norme_packageApi(p_value);}

   /**
    * Recupere le package de business. Exemple : annuaire -> business Attention exception, ici on retourne juste le nom du package.
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return la valeur normee.
    */
   public static String norme_packageBusiness (final String p_value)
   {
      return PackageRule.applyNorme_packageBusiness(p_value);
   }

public static String norme_packageBusinessJavaService(final String p_value){return norme_packageBusiness(p_value);}

   /**
    * Recupere le package de Requirement. Exemple : annuaire -> Requirement Attention exception, ici on retourne juste le nom du package.
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return la valeur normee.
    */
   public static String norme_packageRequirement (final String p_value)
   {
      return PackageRule.applyNorme_packageRequirement(p_value);
   }

public static String norme_packageRequirementJavaService(final String p_value){return norme_packageRequirement(p_value);}

   /**
    * Applique la convention de nommage d'un attributeDefault.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_attributeDefault (final String p_value)
   {
      return AttributeRule.applyNorme_attributeDefault(p_value);
   }

public static String norme_attributeDefaultJavaService(final String p_value){return norme_attributeDefault(p_value);}

   /**
    * Applique la convention de nommage d'un attributeEnum.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_attributeEnum (final String p_value)
   {
      return AttributeRule.applyNorme_attributeEnum(p_value);
   }

public static String norme_attributeEnumJavaService(final String p_value){return norme_attributeEnum(p_value);}

   /**
    * Applique la convention de nommage d'un attributeMultiple.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_attributeMultiple (final String p_value)
   {
      return AttributeRule.applyNorme_attributeMultiple(p_value);
   }

public static String norme_attributeMultipleJavaService(final String p_value){return norme_attributeMultiple(p_value);}

   /**
    * Applique la convention de nommage d'un attributeFinal.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_attributeFinal (final String p_value)
   {
      return AttributeRule.applyNorme_attributeFinal(p_value);
   }

public static String norme_attributeFinalJavaService(final String p_value){return norme_attributeFinal(p_value);}

   /**
    * Applique la convention de nommage d'un attributeFinalStatic.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_attributeFinalStatic (final String p_value)
   {
      return AttributeRule.applyNorme_attributeFinalStatic(p_value);
   }

public static String norme_attributeFinalStaticJavaService(final String p_value){return norme_attributeFinalStatic(p_value);}

   /**
    * Applique la convention de nommage d'un attributeStatic.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_attributeStatic (final String p_value)
   {
      return AttributeRule.applyNorme_attributeStatic(p_value);
   }

public static String norme_attributeStaticJavaService(final String p_value){return norme_attributeStatic(p_value);}

   /**
    * Applique la convention de nommage d'un classAbstract.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_classAbstract (final String p_value)
   {
      return ClassRule.applyNorme_classAbstract(p_value);
   }

public static String norme_classAbstractJavaService(final String p_value){return norme_classAbstract(p_value);}

   /**
    * Applique la convention de nommage d'un classDefault.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_classDefault (final String p_value)
   {
      return ClassRule.applyNorme_classDefault(p_value);
   }

public static String norme_classDefaultJavaService(final String p_value){return norme_classDefault(p_value);}

   /**
    * Applique la convention de nommage d'un classDefault.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_pageDefault (final String p_value)
   {
      return PageRule.applyNorme_pageDefault(p_value);
   }

public static String norme_pageDefaultJavaService(final String p_value){return norme_pageDefault(p_value);}

   /**
    * Applique la convention de nommage d'un classAnnotation (i.e. annotation Java).
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_classAnnotation (final String p_value)
   {
      return ClassRule.applyNorme_classAnnotation(p_value);
   }

public static String norme_classAnnotationJavaService(final String p_value){return norme_classAnnotation(p_value);}

   /**
    * Applique la convention de nommage d'un classEnum.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_classEnum (final String p_value)
   {
      return ClassRule.applyNorme_classEnum(p_value);
   }

public static String norme_classEnumJavaService(final String p_value){return norme_classEnum(p_value);}

   /**
    * Applique la convention de nommage d'un classImplem.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_classImplem (final String p_value)
   {
      return ClassRule.applyNorme_classImplem(p_value);
   }

public static String norme_classImplemJavaService(final String p_value){return norme_classImplem(p_value);}

   /**
    * Applique la convention de nommage d'un classInterface.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_classInterface (final String p_value)
   {
      return ClassRule.applyNorme_classInterface(p_value);
   }

public static String norme_classInterfaceJavaService(final String p_value){return norme_classInterface(p_value);}

   /**
    * Applique la convention de nommage d'un classTest.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_classTest (final String p_value)
   {
      return ClassRule.applyNorme_classTest(p_value);
   }

public static String norme_classTestJavaService(final String p_value){return norme_classTest(p_value);}

   /**
    * Applique la convention de nommage d'un methodDefault.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_methodDefault (final String p_value)
   {
      return MethodRule.applyNorme_methodDefault(p_value);
   }

public static String norme_methodDefaultJavaService(final String p_value){return norme_methodDefault(p_value);}

   /**
    * Applique la convention de nommage d'un methodMultiple.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_methodMultiple (final String p_value)
   {
      return MethodRule.applyNorme_methodMultiple(p_value);
   }

public static String norme_methodMultipleJavaService(final String p_value){return norme_methodMultiple(p_value);}

   /**
    * Applique la convention de nommage d'un methodGet.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_methodGet (final String p_value)
   {
      return MethodRule.applyNorme_methodGet(p_value);
   }

public static String norme_methodGetJavaService(final String p_value){return norme_methodGet(p_value);}

   /**
    * Applique la convention de nommage d'un methodReset.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_methodReset (final String p_value)
   {
      return MethodRule.applyNorme_methodReset(p_value);
   }

public static String norme_methodResetJavaService(final String p_value){return norme_methodReset(p_value);}

   /**
    * Applique la convention de nommage d'un methodSet.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_methodSet (final String p_value)
   {
      return MethodRule.applyNorme_methodSet(p_value);
   }

public static String norme_methodSetJavaService(final String p_value){return norme_methodSet(p_value);}

   /**
    * Applique la convention de nommage d'un parameterMethodClassicIn.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_parameterMethodClassicIn (final String p_value)
   {
      return ParameterRule.applyNorme_parameterMethodClassicIn(p_value);
   }

public static String norme_parameterMethodClassicInJavaService(final String p_value){return norme_parameterMethodClassicIn(p_value);}

   /**
    * Applique la convention de nommage d'un parameterMethodMultiple.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_parameterMethodMultiple (final String p_value)
   {
      return ParameterRule.applyNorme_parameterMethodMultiple(p_value);
   }

public static String norme_parameterMethodMultipleJavaService(final String p_value){return norme_parameterMethodMultiple(p_value);}

   /**
    * Applique la convention de nommage d'un parameterMethodMultipleMap.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_parameterMethodMultipleMap (final String p_value)
   {
      return ParameterRule.applyNorme_parameterMethodMultipleMap(p_value);
   }

public static String norme_parameterMethodMultipleMapJavaService(final String p_value){return norme_parameterMethodMultipleMap(p_value);}

   /**
    * Applique la convention de nommage d'un parameterMethodObjectIn.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_parameterMethodObjectIn (final String p_value)
   {
      return ParameterRule.applyNorme_parameterMethodObjectIn(p_value);
   }

public static String norme_parameterMethodObjectInJavaService(final String p_value){return norme_parameterMethodObjectIn(p_value);}

   /**
    * Applique la convention de nommage d'un parameterMethodClassicInOut.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_parameterMethodClassicInOut (final String p_value)
   {
      return ParameterRule.applyNorme_parameterMethodClassicInOut(p_value);
   }

public static String norme_parameterMethodClassicInOutJavaService(final String p_value){return norme_parameterMethodClassicInOut(p_value);}

   /**
    * Applique la convention de nommage d'un parameterMethodObjectInOut.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_parameterMethodObjectInOut (final String p_value)
   {
      return ParameterRule.applyNorme_parameterMethodObjectInOut(p_value);
   }

public static String norme_parameterMethodObjectInOutJavaService(final String p_value){return norme_parameterMethodObjectInOut(p_value);}

   /**
    * Applique la convention de nommage d'un parameterMethodClassicOut.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_parameterMethodClassicOut (final String p_value)
   {
      return ParameterRule.applyNorme_parameterMethodClassicOut(p_value);
   }

public static String norme_parameterMethodClassicOutJavaService(final String p_value){return norme_parameterMethodClassicOut(p_value);}

   /**
    * Applique la convention de nommage d'un parameterMethodObjectOut.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_parameterMethodObjectOut (final String p_value)
   {
      return ParameterRule.applyNorme_parameterMethodObjectOut(p_value);
   }

public static String norme_parameterMethodObjectOutJavaService(final String p_value){return norme_parameterMethodObjectOut(p_value);}

   /**
    * Applique la convention de nommage d'un parameterMethodReset.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_parameterMethodReset (final String p_value)
   {
      return ParameterRule.applyNorme_parameterMethodReset(p_value);
   }

public static String norme_parameterMethodResetJavaService(final String p_value){return norme_parameterMethodReset(p_value);}

   /**
    * Applique la convention de nommage d'une variableDefault.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_variableDefault (final String p_value)
   {
      return VariableRule.applyNorme_variableDefault(p_value);
   }

public static String norme_variableDefaultJavaService(final String p_value){return norme_variableDefault(p_value);}

   /**
    * Applique la convention de nommage d'une variableMultipleDefault.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_variableMultipleDefault (final String p_value)
   {
      return VariableRule.applyNorme_variableMultipleDefault(p_value);
   }

public static String norme_variableMultipleDefaultJavaService(final String p_value){return norme_variableMultipleDefault(p_value);}

   /**
    * Applique la convention de nommage d'une variableMultipleDefault.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_variableMultipleMap (final String p_value)
   {
      return VariableRule.applyNorme_variableMultipleMap(p_value);
   }

public static String norme_variableMultipleMapJavaService(final String p_value){return norme_variableMultipleMap(p_value);}

   /**
    * Applique la convention de nommage d'une variableFinal.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_variableFinal (final String p_value)
   {
      return VariableRule.applyNorme_variableFinal(p_value);
   }

public static String norme_variableFinalJavaService(final String p_value){return norme_variableFinal(p_value);}

   /**
    * Applique la convention de nommage d'une variableObjectDefault.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_variableObjectDefault (final String p_value)
   {
      return VariableRule.applyNorme_variableObjectDefault(p_value);
   }

public static String norme_variableObjectDefaultJavaService(final String p_value){return norme_variableObjectDefault(p_value);}

   /**
    * Applique la convention de nommage d'une variableObjectFinal.
    * 
    * @param p_value
    *           La valeur du model a normer.
    * 
    * @return La valeur normee.
    */
   public static String norme_variableObjectFinal (final String p_value)
   {
      return VariableRule.applyNorme_variableObjectFinal(p_value);
   }

public static String norme_variableObjectFinalJavaService(final String p_value){return norme_variableObjectFinal(p_value);}
}
