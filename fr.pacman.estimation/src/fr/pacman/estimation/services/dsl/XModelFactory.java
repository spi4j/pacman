package fr.pacman.estimation.services.dsl;

import fr.pacman.estimation.services.complexite_pf.UnsupportedModelException;

/**
 * Factory d'initialisation de {@link XModel} à partir d'un objet de modèle (dynamiquement, selon le type)
 * 
 * @author MINARM
 */
public final class XModelFactory
{

   /**
    * Constructeur privé.
    */
   private XModelFactory ()
   {
      super();
   }

   /**
    * Initialiser le 'XModel' à partir de l'entrée dans la modélisation (ex : "Block' de Entity).
    * 
    * @param p_root
    *           La racine du modèle.
    * @return le modèle initialisé
    * @throws UnsupportedModelException
    *            Conversion non applicable pour ce type d'objet
    */
   public static XModel getInstance (final Object p_root) throws UnsupportedModelException
   {
      XModel v_XModel;
      XPackage v_XPackage;
      // Si racine du DSL 'Entity'
      if (p_root instanceof org.obeonetwork.dsl.entity.Root)
      {
         final XModelConverterDslEntity v_XConverter = new XModelConverterDslEntity();
         v_XModel = new XModel("Pour estimation depuis DSL Entity depuis la racine");
         // Parcourir les packages
         for (org.obeonetwork.dsl.environment.Namespace v_Block : ((org.obeonetwork.dsl.entity.Root) p_root).getOwnedNamespaces())
         {
            // Block --> XPackage
            v_XPackage = v_XConverter.convertToXPackage(v_Block);
            // Ajouter le package dans le modèle
            v_XModel.addPackage(v_XPackage);
         }
      }
      // Si Namespace du DSL 'Entity'
      else if (p_root instanceof org.obeonetwork.dsl.environment.Namespace)
      {
         final XModelConverterDslEntity v_XConverter = new XModelConverterDslEntity();
         v_XModel = new XModel("Pour estimation depuis DSL Entity - depuis un Namespace");
         // Block --> XPackage
         v_XPackage = v_XConverter.convertToXPackage((org.obeonetwork.dsl.environment.Namespace) p_root);
         // Ajouter le package dans le modèle
         v_XModel.addPackage(v_XPackage);
      }
      // Si la racine du DSL 'SOA-DTO'
      //TODO delet this section there no DTORegistry
//      else if (p_root instanceof org.obeonetwork.dsl.soa.System)
//      {
//         // Appeler cette méthode sur le System revient au même que de l'appeler sur le Dto Registry contenu
//         v_XModel = getInstance(((org.obeonetwork.dsl.soa.System) p_root).getOwnedDtoRegistry());
//      }
      // Si la racine du DSL 'SOA-DTO'
//      else if (p_root instanceof org.obeonetwork.dsl.soa.DTORegistry)
//      {
//         final XModelConverterDslSoaDto v_XConverter = new XModelConverterDslSoaDto();
//         v_XModel = new XModel("Pour estimation depuis DSL SOA-DTO depuis le DTO Registry");
//         // Parcourir les packages
//         for (org.obeonetwork.dsl.soa.Category v_Category : ((org.obeonetwork.dsl.soa.DTORegistry) p_root).getOwnedCategories())
//         {
//            // Category --> XPackage
//            v_XPackage = v_XConverter.convertToXPackage(v_Category);
//            // Ajouter le package dans le modèle
//            v_XModel.addPackage(v_XPackage);
//         }
//      }
      // Si Namespace du DSL 'SOA-DTO'
      else if (p_root instanceof org.obeonetwork.dsl.environment.Namespace)
      {
         final XModelConverterDslSoaDto v_XConverter = new XModelConverterDslSoaDto();
         v_XModel = new XModel("Pour estimation depuis DSL SOA-DTO depuis une catégorie");
         // Category --> XPackage
         v_XPackage = v_XConverter.convertToXPackage((org.obeonetwork.dsl.environment.Namespace) p_root);
         // Ajouter le package dans le modèle
         v_XModel.addPackage(v_XPackage);
      }
      // Cas non géré
      else
      {
         throw new UnsupportedModelException(p_root);
      }

      return v_XModel;

   }

}
