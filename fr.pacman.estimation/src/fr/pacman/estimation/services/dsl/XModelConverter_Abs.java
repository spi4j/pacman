package fr.pacman.estimation.services.dsl;

import java.util.List;

import org.obeonetwork.dsl.environment.MultiplicityKind;

/**
 * L'abstraction permettant la convertion vers le modèle générique indépendant du DSL manipulant les concepts suivants : - Package - Entité (ou classe) - Relations
 * 
 * @param <TypeDslPackage>
 *           type de package
 * @param <TypeClassDsl>
 *           type de classe
 * @param <TypeDslAttribute>
 *           type d'attribut
 * @param <TypeDslReference>
 *           type de relation
 * @author MINARM
 */
abstract public class XModelConverter_Abs<TypeDslPackage, TypeClassDsl, TypeDslAttribute, TypeDslReference>
{

   /**
    * Convertir un type "TypeDslPackage" d'un DSL spécifique de la modélisation en 'XPackage'.
    * 
    * @param p_Package
    *           Le type "Package" d'un DSL spécifique.
    * @return L'instance désirée.
    */
   public XPackage convertToXPackage (final TypeDslPackage p_Package)
   {
      final XPackage v_return = new XPackage(getPacakgeName(p_Package));

      // Ajouter la liste des classes du package
      addEntitiesOfPackage(v_return, getPackageClasses(p_Package));

      // Ajouter les sous packages
      addSubPackagesOfPackage(v_return, getPackageSubPackages(p_Package));

      return v_return;

   }

   /**
    * Ajouter la liste des classes du package.
    * 
    * @param p_XPackage
    *           Le package.
    * @param p_lst_Class
    *           La liste des classes.
    */
   protected void addEntitiesOfPackage (final XPackage p_XPackage, final List<TypeClassDsl> p_lst_Class)
   {
      XClassDsl v_XClassDsl;
      // Parcourir les 'DTO' du package
      for (TypeClassDsl v_Class : p_lst_Class)
      {
         // ClassDsl --> XClassDsl
         v_XClassDsl = convertToXClassDsl(v_Class);
         // Ajouter l'entité au package
         p_XPackage.addClassDsl(v_XClassDsl);
      }
   }

   /**
    * Convertir un type "TypeDslClassDsl" d'un DSL spécifique de la modélisation en 'XClassDsl'.
    * 
    * @param p_ClassDsl
    *           Le type "ClassDsl" d'un DSL spécifique.
    * @return L'instance désirée.
    */
   protected XClassDsl convertToXClassDsl (final TypeClassDsl p_ClassDsl)
   {

      final XClassDsl v_return = new XClassDsl(getClassDslName(p_ClassDsl));

      // Ajouter la liste des attributs de la classe
      addAttributesOfClass(v_return, getClassDslAttributes(p_ClassDsl));

      // Ajouter la liste des attributs de la classe
      addReferencesOfClass(v_return, getClassDslReferences(p_ClassDsl));

      return v_return;
   }

   /**
    * Ajouter la liste des sous packages du package.
    * 
    * @param p_XPackage
    *           Le package.
    * @param p_lst_subPackage
    *           La liste des sous packages.
    */
   protected void addSubPackagesOfPackage (final XPackage p_XPackage, final List<TypeDslPackage> p_lst_subPackage)
   {
      XPackage v_subXPackage;
      // Parcourir les sous packages (éventuels) du package
      for (TypeDslPackage v_subCategory : p_lst_subPackage)
      {
         // Convertion récursive : Category --> XPackage
         v_subXPackage = convertToXPackage(v_subCategory);
         // Ajouter le sous package
         p_XPackage.addSubPackage(v_subXPackage);
      }
   }

   /**
    * Ajouter la liste des attributs de la classe.
    * 
    * @param p_XClassDsl
    *           la classe
    * @param p_lst_attribute
    *           les attributs
    */
   protected void addAttributesOfClass (final XClassDsl p_XClassDsl, final List<TypeDslAttribute> p_lst_attribute)
   {
      for (final TypeDslAttribute v_attribute : p_lst_attribute)
      {
         p_XClassDsl.addAttribute(getAttributeName(v_attribute));
      }
   }

   /**
    * Ajouter la liste des relations de la classe.
    * 
    * @param p_Class
    *           la classe
    * @param p_lst_reference
    *           les relations
    */
   protected void addReferencesOfClass (final XClassDsl p_Class, final List<TypeDslReference> p_lst_reference)
   {
      // Parcourir les relations
      for (TypeDslReference v_Reference : p_lst_reference)
      {
         // Si relation 0..1 ou 1..1
         if ((getReferenceMultiplicity(v_Reference) == MultiplicityKind.ZERO_ONE_LITERAL) || (getReferenceMultiplicity(v_Reference) == MultiplicityKind.ONE_LITERAL))
         {
            p_Class.addRelation_0_1(getReferenceName(v_Reference));
         }
         // Si relation 0..N ou 1..N
         else if ((getReferenceMultiplicity(v_Reference) == MultiplicityKind.ZERO_STAR_LITERAL) || (getReferenceMultiplicity(v_Reference) == MultiplicityKind.ONE_STAR_LITERAL))
         {
            // Obtenir la relation inverse (potentielle)
            final TypeDslReference v_ReferenceOpposite = getReferenceOpposite(v_Reference);
            // Si pas de relation inverse
            if (v_ReferenceOpposite == null)
            {
               p_Class.addRelation_1_N(getReferenceName(v_Reference));
            }
            // Si relation inverse
            else
            {
               // Si relation inverse en 0..1 ou 1..1
               if ((getReferenceMultiplicity(v_ReferenceOpposite) == MultiplicityKind.ONE_LITERAL) || (getReferenceMultiplicity(v_ReferenceOpposite) == MultiplicityKind.ZERO_ONE_LITERAL))
               {
                  p_Class.addRelation_1_N(getReferenceName(v_Reference));
               }
               // Si relation inverse en 0..N ou 1..N
               else if ((getReferenceMultiplicity(v_ReferenceOpposite) == MultiplicityKind.ZERO_STAR_LITERAL) || (getReferenceMultiplicity(v_ReferenceOpposite) == MultiplicityKind.ONE_STAR_LITERAL))
               {
                  p_Class.addRelation_N_N(getReferenceName(v_Reference));
               }
               else
               {
                  throw new IllegalArgumentException("Cas non prévu pour la relation inverse v_ReferenceOpposite="
                           + getReferenceName(v_ReferenceOpposite)
                           + " avec p_DTO.getName()="
                           + p_Class.get_nom()
                           + " et getReferenceName(v_Reference)="
                           + getReferenceName(v_Reference));
               }
            }
         }
         // Cas non prévu
         else
         {
            throw new IllegalArgumentException("Cas non prévu pour la relation v_Reference=" + getReferenceName(v_Reference) + "avec p_ClassDsl.getName()=" + p_Class.get_nom());
         }
      }
   }

   /**
    * Obtenir le nom d'un package
    * 
    * @param p_Package
    *           le package
    * @return le nom d'un package
    */
   abstract protected String getPacakgeName (TypeDslPackage p_Package);

   /**
    * Obtenir les sous packages d'un package
    * 
    * @param p_Package
    *           le package
    * @return les sous packages d'un package
    */
   abstract protected List<TypeDslPackage> getPackageSubPackages (TypeDslPackage p_Package);

   /**
    * Obtenir les classes d'un package
    * 
    * @param p_Package
    *           le package
    * @return les classes d'un package
    */
   abstract protected List<TypeClassDsl> getPackageClasses (TypeDslPackage p_Package);

   /**
    * Obtenir le nom d'une classe du modèle
    * 
    * @param p_ClassDsl
    *           la classe du modèle
    * @return le nom d'une classe du modèle
    */
   abstract protected String getClassDslName (TypeClassDsl p_ClassDsl);

   /**
    * Obtenir les attributs d'une classe du modèle
    * 
    * @param p_ClassDsl
    *           la classe du modèle
    * @return les attributs d'une classe du modèle
    */
   abstract protected List<TypeDslAttribute> getClassDslAttributes (TypeClassDsl p_ClassDsl);

   /**
    * Obtenir les relations d'une classe du modèle
    * 
    * @param p_ClassDsl
    *           la classe du modèle
    * @return les relations d'une classe du modèle
    */
   abstract protected List<TypeDslReference> getClassDslReferences (TypeClassDsl p_ClassDsl);

   /**
    * Convertir un attribut en chaine de caractères représentant son nom
    * 
    * @param p_Attribute
    *           l'attribut
    * @return le nom de l'attribut
    */
   abstract protected String getAttributeName (final TypeDslAttribute p_Attribute);

   /**
    * Convertir une relation en chaine de caractères représentant son nom
    * 
    * @param p_Reference
    *           la relation
    * @return le nom de la relation
    */
   abstract protected String getReferenceName (final TypeDslReference p_Reference);

   /**
    * Obtenir la multiplicité d'une relation (voir {@link MultiplicityKind})
    * 
    * @param p_Reference
    *           la relation
    * @return la multiplicité de la relation
    * @see MultiplicityKind
    */
   abstract protected MultiplicityKind getReferenceMultiplicity (final TypeDslReference p_Reference);

   /**
    * Obtenir la relation opposée à une relation (ou null s'il n'existe pas de relation opposée).
    * 
    * @param p_Reference
    *           la relation
    * @return la relation opposée
    */
   abstract protected TypeDslReference getReferenceOpposite (final TypeDslReference p_Reference);
}
