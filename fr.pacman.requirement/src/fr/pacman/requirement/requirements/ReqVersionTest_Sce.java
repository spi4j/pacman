package fr.pacman.requirement.requirements;

import org.obeonetwork.dsl.requirement.Requirement;

import fr.pacman.commons.convention.project.ProjectProperties;
import fr.pacman.commons.errorgen.ErrorGeneration;
import fr.pacman.commons.properties.PacmanPropertiesManager;

/**
 * Services Acceleo associé à "reqVersionTest.mtl".
 */
public class ReqVersionTest_Sce
{
   /**
    * Obtenir la version à mettre dans le "set_versionImplem()" en fonction de la valeur de la propriété.
    * @param p_Requirement Le modèle courant invoquant le service.
    * @return La version à mettre dans le "set_versionImplem()".
    */
   public String getTestRequirementVersionningInitial_invoke (final Requirement p_Requirement)
   {
      String v_return;

      // Obtenir la version de l'exigence dans le modèle au moment de la génération
      final int v_versionModel = p_Requirement.getVersion();
      // Obtenir l'identifiant de la propriété
      final String v_idParam_test_requirement_versionning_initial = ProjectProperties.c_idParam_test_requirement_versionning_initial;
      // Obtenir la valeur de la propriété
      final String v_valParam_test_requirement_versionning_initial = PacmanPropertiesManager.get_property(v_idParam_test_requirement_versionning_initial);

      // Si le nom de la propriété est incorrecte
      if (ProjectProperties.c_idParam_test_requirement_versionning_initial.equals(v_idParam_test_requirement_versionning_initial) == false)
      {
         v_return = ErrorGeneration.printMessageFmt("Problème avec la propriété \"" + v_idParam_test_requirement_versionning_initial + "\" le nom attendu est \"" + ProjectProperties.c_idParam_test_requirement_versionning_initial + "\"", "Vérifier votre fichier Properties");
      }
      // Si génération avec : set_versionImplem()
      else if (ProjectProperties.c_valParam_test_requirement_versionning_initial_none.equals(v_valParam_test_requirement_versionning_initial) == true)
      {
         // Retourner aucun version ==> exigence non implémentée
         v_return = "";
      }
      // Si génération avec : set_versionImplem(<versionModel>)
      else if (ProjectProperties.c_valParam_test_requirement_versionning_initial_current.equals(v_valParam_test_requirement_versionning_initial) == true)
      {
         // Retourner la version de l'exigence définie dans le modèle
         v_return = "\"" + v_versionModel + "\"";
      }
      // Erreur application
      else
      {
         v_return = ErrorGeneration.printMessageFmt("Problème avec la propriété \"" + v_idParam_test_requirement_versionning_initial + "\" : valeur (= " + v_valParam_test_requirement_versionning_initial + ") non gérée", "Vérifier votre fichier Properties (en mettant une valeur gérée) ou demander à l'administrateur PacMan d'ajouter cette nouvelle valeur (= " + v_valParam_test_requirement_versionning_initial + ") à gérer pour cette propriété (= " + v_idParam_test_requirement_versionning_initial + ")");
      }

      return v_return;
   }
}
