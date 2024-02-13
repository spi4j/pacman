package fr.pacman.estimation.services.dsl;

import java.util.ArrayList;
import java.util.List;

import org.obeonetwork.dsl.entity.Entity;
import org.obeonetwork.dsl.environment.Attribute;
import org.obeonetwork.dsl.environment.MultiplicityKind;
import org.obeonetwork.dsl.environment.Namespace;
import org.obeonetwork.dsl.environment.Reference;
import org.obeonetwork.dsl.environment.Type;

/**
 * Implémentation pour le DSL "org.obeonetwork.dsl.entity" permettant la convertion vers le modèle générique indépendant du DSL manipulant les concepts suivants : - Package - Entité (ou classe) - Relations
 * 
 * @author MINARM
 */
public class XModelConverterDslEntity extends XModelConverter_Abs<org.obeonetwork.dsl.environment.Namespace, org.obeonetwork.dsl.entity.Entity, org.obeonetwork.dsl.environment.Attribute, org.obeonetwork.dsl.environment.Reference>
{

   @Override
   protected String getAttributeName (final Attribute p_Attribute)
   {
      return p_Attribute.getName();
   }

   @Override
   protected String getReferenceName (final Reference p_Reference)
   {
      return p_Reference.getName();
   }

   @Override
   protected MultiplicityKind getReferenceMultiplicity (final Reference p_Reference)
   {
      return p_Reference.getMultiplicity();
   }

   @Override
   protected Reference getReferenceOpposite (final Reference p_Reference)
   {
      return p_Reference.getOppositeOf();
   }

   @Override
   protected String getPacakgeName (final Namespace p_Package)
   {
      return p_Package.getName();
   }

  

   @Override
   protected String getClassDslName (final Entity p_ClassDsl)
   {
      return p_ClassDsl.getName();
   }

   @Override
   protected List<Attribute> getClassDslAttributes (final Entity p_ClassDsl)
   {
      return p_ClassDsl.getAttributes();
   }

   @Override
   protected List<Reference> getClassDslReferences (final Entity p_ClassDsl)
   {
      return p_ClassDsl.getOwnedReferences();
   }

   @Override
   protected List<Namespace> getPackageSubPackages (Namespace p_Package)
   {
      
      return p_Package.getOwnedNamespaces();
   }

   @Override
   protected List<Entity> getPackageClasses (Namespace p_Package)
   {
      List<Entity> v_entities=new ArrayList<Entity>();
     List<Type> v_types = p_Package.getTypes();
      for (Type v_type : v_types)
      {
         if(v_type instanceof Entity){
            v_entities.add((Entity)v_type);
         }
      }
      return v_entities;
   }
}
