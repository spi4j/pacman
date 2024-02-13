package fr.pacman.estimation.services;

import org.eclipse.emf.ecore.EObject;

import fr.pacman.estimation.services.complexite_pf.NotReliableException;
import fr.pacman.estimation.services.complexite_pf.UnsupportedModelException;
import fr.pacman.estimation.services.dsl.XClassDsl;
import fr.pacman.estimation.services.dsl.XModel;
import fr.pacman.estimation.services.dsl.XModelFactory;
import fr.pacman.estimation.services.dsl.XPackage;

/**
 * Services Acceleo associé à "EstimationCharge.mtl".
 */
public class EstimationCharge_Sce
{
   /**
    * Obtenir les indicateurs calculés depuis la racine du modèle fourni (ex : un Namespace)
    * 
    * @param p_rootModel
    *           La racine du modèle.
    * @param p_Node
    *           le point d'entrée pour l'estimation
    * @param <TypeRacine>
    *           le type de point d'entrée
    * @return La version à mettre dans le "set_versionImplem()".
    * @throws NotReliableException
    *            résultat non fiable (pf < 300)
    * @throws UnsupportedModelException
    *            algorithme non applicable pour ce modèle
    */
   public <TypeRacine> IndicatorsEstimation<TypeRacine> getIndicatorsEstimation_invoke (final EObject p_rootModel, final TypeRacine p_Node) throws NotReliableException, UnsupportedModelException
   {
      final IndicatorsEstimation<TypeRacine> v_return = new IndicatorsEstimation<TypeRacine>();
      v_return.set_rootModel(p_Node);
      // System.out.println("DEBUG - p_rootModel = " + p_rootModel);
      final XModel v_XModel = XModelFactory.getInstance(p_rootModel);

      // Remplir 'v_return' à partir des indicateurs calculés
      v_return.set_complexiteEnPfGdi(getComplexiteEnPfGdi(v_XModel));
      v_return.set_complexiteEnPfGdiRapide(getComplexiteEnPfGdiRapide(v_XModel));

      return v_return;
   }

   /**
    * Obtenir la complexité en points de fonctions.
    * 
    * @param p_XModel
    *           le modèle en entrée
    * @return Le nb de PF du modèle.
    * @throws NotReliableException
    *            Si le nombre de points de fonctions n'est fiable.
    */
   public Double getComplexiteEnPfGdi (final XModel p_XModel) throws NotReliableException
   {
      Double v_return;
      // Le nb de points de fonctions du GDI (= Groupe de Données Internes = entités d'un MCD)
      int v_nbPfBrutsGdi = 0;

      // Parcourir les packages
      for (XPackage v_XPackage : p_XModel.get_lstXPackage())
      {
         v_nbPfBrutsGdi = v_nbPfBrutsGdi + getComplexiteEnPfGdi(v_XPackage);
      }

      // v_nbPfBruts représente 23,3% de l'ensemble de l'application
      v_return = v_nbPfBrutsGdi * (100 / 23.3);

      // Si la valeur n'est pas fiable
      if (v_return < 300)
      {
         throw new NotReliableException(v_return);
      }

      return v_return;
   }

   /**
    * Obtenir la complexité en points de fonctions (méthode statistiques = rapide). = nombre d'entités * 7,3
    * 
    * @param p_XModel
    *           le modèle en entrée
    * @return Le nb de PF du modèle.
    */
   public Double getComplexiteEnPfGdiRapide (final XModel p_XModel)
   {
      Double v_return;
      // Le nb de points de fonctions du GDI (= Groupe de Données Internes = entités d'un MCD)
      int v_nbPfBrutsGdi = 0;

      // Parcourir les packages
      for (XPackage v_XPackage : p_XModel.get_lstXPackage())
      {
         v_nbPfBrutsGdi = v_nbPfBrutsGdi + v_XPackage.getNbXClassDsl();
      }

      // Calculer le PF brut
      v_return = v_nbPfBrutsGdi * 7.3;
      // v_nbPfBruts représente 23,3% de l'ensemble de l'application
      v_return = v_return * (100 / 23.3);

      return v_return;
   }

   /**
    * Calculer la complexité du package.
    * 
    * @param p_XPackage
    *           Le package à analyser.
    * @return Le nombre de points de fonctions bruts.
    */
   private int getComplexiteEnPfGdi (final XPackage p_XPackage)
   {
      // Le nb de points de fonctions du GDI (= Groupe de Données Internes = entités d'un MCD)
      int v_nbPfBrutsGdi = 0;

      // Parcourir les classes du package
      for (final XClassDsl v_XClassDsl : p_XPackage.get_lstClassDslInPackage())
      {
         // Calculer la complexité
         v_nbPfBrutsGdi = v_nbPfBrutsGdi + v_XClassDsl.getComplexite().get_nbPf();
      }

      // Parcourir les sous packages (éventuels)
      for (XPackage v_subXPackage : p_XPackage.get_lstSubXPackage())
      {
         // Calculer récursivement pour le sous-package courant
         v_nbPfBrutsGdi = v_nbPfBrutsGdi + getComplexiteEnPfGdi(v_subXPackage);
      }

      return v_nbPfBrutsGdi;
   }

}
