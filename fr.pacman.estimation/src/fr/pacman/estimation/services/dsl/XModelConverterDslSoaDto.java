package fr.pacman.estimation.services.dsl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.obeonetwork.dsl.environment.Attribute;
import org.obeonetwork.dsl.environment.DTO;
import org.obeonetwork.dsl.environment.MultiplicityKind;
import org.obeonetwork.dsl.environment.Namespace;
import org.obeonetwork.dsl.environment.Reference;
import org.obeonetwork.dsl.environment.Type;

/**
 * Implémentation pour le DSL "org.obeonetwork.dsl.soa" permettant la convertion vers le modèle générique indépendant du DSL manipulant les concepts suivants : - Package - Entité (ou classe) - Relations
 * 
 * @author MINARM
 */
public class XModelConverterDslSoaDto extends XModelConverter_Abs<org.obeonetwork.dsl.environment.Namespace, org.obeonetwork.dsl.environment.DTO, org.obeonetwork.dsl.environment.Attribute, org.obeonetwork.dsl.environment.Reference>
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
   protected List<Namespace> getPackageSubPackages (final Namespace p_Package)
   {
      return p_Package.getOwnedNamespaces();
   }

   @Override
   protected List<DTO> getPackageClasses (final Namespace p_Package)
   {
      final EList<Type> v_lst_Types = p_Package.getTypes();
      final List<DTO> v_lst_Dto = new ArrayList<DTO>(v_lst_Types.size());
      // Parcourir les types
      for (final Type v_Type : v_lst_Types)
      {
         if (v_Type instanceof DTO)
         {
            v_lst_Dto.add((DTO) v_Type);
         }
      }
      return v_lst_Dto;
   }

   @Override
   protected String getClassDslName (final DTO p_ClassDsl)
   {
      return p_ClassDsl.getName();
   }

   @Override
   protected List<Attribute> getClassDslAttributes (final DTO p_ClassDsl)
   {
      return p_ClassDsl.getAttributes();
   }

   @Override
   protected List<Reference> getClassDslReferences (final DTO p_ClassDsl)
   {
      return p_ClassDsl.getOwnedReferences();
   }

}
