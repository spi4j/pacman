package fr.pacman.estimation.services.dsl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.pacman.estimation.services.EstimationCharge_Sce;
import fr.pacman.estimation.services.complexite_pf.NotReliableException;

/**
 * Classe de tests associée à 'XModel'.
 * 
 * @author MINARM
 */
public class XModel_Test
{

   private static final EstimationCharge_Sce c_estimationChargeService = new EstimationCharge_Sce();

   /**
    * Test du cas nominal : le nombre de point de fonction est > à 300.
    * 
    * @throws NotReliableException
    *            Si le nombre de points de fonctions n'est fiable.
    */
   @Test
   public void testModel_CN () throws NotReliableException
   {
      // Obtenir le modèle
      final XModel v_XModel = initModel2();
      // Calculer la complexité globale
      final double v_obtenu1 = c_estimationChargeService.getComplexiteEnPfGdi(v_XModel);
      // Calculer la complexité globale statistique (= méthode rapide)
      final double v_obtenu2 = c_estimationChargeService.getComplexiteEnPfGdiRapide(v_XModel);
      // Vérifier la valeur obtenue
      assertTrue(v_obtenu1 > 347 && v_obtenu1 < 348, "La valeur v_obtenu1 est fausse : " + v_obtenu1);
      assertTrue(v_obtenu2 > 281 && v_obtenu2 < 282, "La valeur v_obtenu2 est fausse : " + v_obtenu2);
   }

   /**
    * Test du cas nominal sur AppWhite1.
    * 
    * @throws NotReliableException
    *            Si le nombre de points de fonctions n'est fiable.
    */
   @Test
   public void testModelAppWhite1_CE () throws NotReliableException
   {
      boolean v_errorGenerated = false;
      // Obtenir le modèle
      final XModel v_XModel = initModel_AppWhite1();
      double v_obtenu = 0;
      try
      {
         // Essayer de calculer la complexité
         c_estimationChargeService.getComplexiteEnPfGdi(v_XModel);
      }
      catch (NotReliableException v_e)
      {
         // Obtenir la valeur obtenue dans l'erreur
         v_obtenu = v_e.get_valeurPf();
         // Vérifier la valeur obtenue
         assertTrue(v_obtenu > 253 && v_obtenu < 254, "La valeur obtenue est fausse : " + v_obtenu);
         v_errorGenerated = true;
      }
      
      // Cette assertion permet d'utiliser Junit 5 avec Java 7 (pas de lambda).
      assertTrue(v_errorGenerated == true, "Une erreur NotReliableException aurait due être générée.");
   }

   /**
    * Test du cas d'exception : le nombre de points de fonctions n'est pas fiable (car < 300).
    * 
    * @throws NotReliableException
    *            Si le nombre de points de fonctions n'est fiable.
    */
   @Test
   public void testModel_CE () throws NotReliableException
   {
      boolean v_errorGenerated = false;
      double v_obtenu = 0;
      try
      {
         // Obtenir le modèle
         final XModel v_XModel = initModel1("Modèle No1 - pas fiable en PF car < 300");
         // Essayer de calculer la complexité
         c_estimationChargeService.getComplexiteEnPfGdi(v_XModel);
      }
      catch (NotReliableException v_e)
      {
         // Obtenir la valeur obtenue dans l'erreur
         v_obtenu = v_e.get_valeurPf();
         // Vérifier la valeur obtenue
         assertTrue(v_obtenu > 175 && v_obtenu < 176, "La valeur obtenue est fausse : " + v_obtenu);
         v_errorGenerated = true;
      }
      
      // Cette assertion permet d'utiliser Junit 5 avec Java 7 (pas de lambda).
      assertTrue(v_errorGenerated == true, "Une erreur NotReliableException aurait due être générée.");
   }

   /**
    * Initialiser le modele No1 pour les tests (nbPf < 300).
    * 
    * @param p_nomModel
    *           Le nom du modèle.
    * @return Le modèle de test.
    */
   private XModel initModel1 (final String p_nomModel)
   {
      final XModel v_return = new XModel(p_nomModel);

      // Nouveau package : appwhite1.client-swing
      final XPackage v_package1 = new XPackage("appwhite1.client-swing");
      v_return.addPackage(v_package1);

      // Nouvelle entité : appwhite1.client-swing.SaisiePersonne
      final XClassDsl v_XClassDsl1 = new XClassDsl("saisie personne");
      // Ajouter l'entité au package
      v_package1.addClassDsl(v_XClassDsl1);
      v_XClassDsl1.addAttribute("le nom");
      v_XClassDsl1.addAttribute("le prénom");
      // Ajouter une relation 1,N --> 1,N
      v_XClassDsl1.addRelation_N_N("relation1");
      // Ajouter une relation 1,1 --> 1,N
      v_XClassDsl1.addRelation_1_N("relation2");
      // Ajouter une relation 0,1 --> 1
      v_XClassDsl1.addRelation_0_1("relation3");

      // Nouveau package : appwhite1.client-gwt
      final XPackage v_package2 = new XPackage("appwhite1.client-gwt");
      v_return.addPackage(v_package2);
      // Nouvelle entité : appwhite1.client-gwt.SaisiePersonne
      final XClassDsl v_XClassDsl2 = new XClassDsl("saisie personne");
      v_package2.addClassDsl(v_XClassDsl2);
      v_XClassDsl2.addAttribute("le nom");
      v_XClassDsl2.addAttribute("le prénom");

      // Nouveau package : appwhite1.server
      final XPackage v_package3 = new XPackage("appwhite1.server");
      v_return.addPackage(v_package3);
      // Nouvelle entité
      final XClassDsl v_XClassDsl3 = new XClassDsl("personne");
      // Ajouter l'entité au package
      v_package3.addClassDsl(v_XClassDsl3);
      v_XClassDsl3.addAttribute("nom");
      v_XClassDsl3.addAttribute("prénom");

      // Nouvelle entité de taille moyenne (> 19)
      final XClassDsl v_XClassDslMoyenne = new XClassDsl("entité moyenne A");
      // Ajouter l'entité au package
      v_package3.addClassDsl(v_XClassDslMoyenne);
      // Ajouter 22 attributs
      for (int v_i = 0; v_i < 22; v_i++)
      {
         v_XClassDslMoyenne.addAttribute("attributA " + v_i);
      }

      // Nouvelle entité de taille élévée (> 50)
      final XClassDsl v_XClassDslElevee = new XClassDsl("entité élevée B");
      // Ajouter l'entité au package
      v_package3.addClassDsl(v_XClassDslElevee);
      // Ajouter 20 attributs
      for (int v_i = 0; v_i < 60; v_i++)
      {
         v_XClassDslElevee.addAttribute("attributB " + v_i);
      }

      return v_return;
   }

   /**
    * Initialiser le modèle No2 pour les tests (nbPf > 300).
    * 
    * @return Le modèle de test.
    */
   private XModel initModel2 ()
   {
      final XModel v_return = initModel1("Modèle No2");

      // Nouveau package : appwhite1.common
      final XPackage v_package4 = new XPackage("appwhite1.common");
      v_return.addPackage(v_package4);

      // Nouveau package : appwhite1.common.subpackage1
      final XPackage v_package4_1 = new XPackage("appwhite1.common.subpackage1");
      v_package4.addSubPackage(v_package4_1);
      // Nouvelle entité de taille élévée (> 50)
      final XClassDsl v_XClassDslElevee1_1 = new XClassDsl("entité élevée CC");
      // Ajouter l'entité au package
      v_package4_1.addClassDsl(v_XClassDslElevee1_1);
      // Ajouter 20 attributs
      for (int v_i = 0; v_i < 60; v_i++)
      {
         v_XClassDslElevee1_1.addAttribute("attributCC " + v_i);
      }

      // Nouveau package : appwhite1.common.subpackage2
      final XPackage v_package4_2 = new XPackage("appwhite1.common.subpackage2");
      v_package4.addSubPackage(v_package4_2);

      // Nouvelle entité de taille élévée (> 50)
      final XClassDsl v_XClassDslElevee1 = new XClassDsl("entité élevée C");
      // Ajouter l'entité au package
      v_package4.addClassDsl(v_XClassDslElevee1);
      // Ajouter 20 attributs
      for (int v_i = 0; v_i < 60; v_i++)
      {
         v_XClassDslElevee1.addAttribute("attributC " + v_i);
      }

      // Nouvelle entité de taille élévée (> 50)
      final XClassDsl v_XClassDslElevee2 = new XClassDsl("entité élevée D");
      // Ajouter l'entité au package
      v_package4.addClassDsl(v_XClassDslElevee2);
      // Ajouter 20 attributs
      for (int v_i = 0; v_i < 60; v_i++)
      {
         v_XClassDslElevee2.addAttribute("attributD " + v_i);
      }

      // Nouvelle entité de taille élévée (> 50)
      final XClassDsl v_XClassDslElevee3 = new XClassDsl("entité élevée E");
      // Ajouter l'entité au package
      v_package4.addClassDsl(v_XClassDslElevee3);
      // Ajouter 20 attributs
      for (int v_i = 0; v_i < 60; v_i++)
      {
         v_XClassDslElevee3.addAttribute("attributE " + v_i);
      }

      return v_return;
   }

   /**
    * Initialiser le modèle No1 pour les tests (nbPf < 300).
    * 
    * @return Le modèle de test.
    */
   private XModel initModel_AppWhite1 ()
   {
      final XModel v_return = new XModel("Modèle AppWhite1");

      // Nouveau package : appwhite1.annuaire
      final XPackage v_package_annuaire = new XPackage("appwhite1.annuaire");
      v_return.addPackage(v_package_annuaire);
      // Entité Personne
      final XClassDsl v_XClassDslPersonne = new XClassDsl("Personne");
      // Ajouter l'entité au package
      v_package_annuaire.addClassDsl(v_XClassDslPersonne);
      // Définir les attributs
      v_XClassDslPersonne.addAttribute("nom");
      v_XClassDslPersonne.addAttribute("prénom");
      v_XClassDslPersonne.addAttribute("civil");
      v_XClassDslPersonne.addAttribute("dateNaissance");
      v_XClassDslPersonne.addAttribute("salaire");
      v_XClassDslPersonne.addAttribute("version");
      // Définir les relations
      v_XClassDslPersonne.addRelation_1_N("propriétaire");
      v_XClassDslPersonne.addRelation_1_N("personne_compétence");
      v_XClassDslPersonne.addRelation_N_N("personnes");
      v_XClassDslPersonne.addRelation_0_1("grade");

      // Entité Adresse
      final XClassDsl v_XClassDslAdresse = new XClassDsl("Adresse");
      // Ajouter l'entité au package
      v_package_annuaire.addClassDsl(v_XClassDslAdresse);
      // Définir les attributs
      v_XClassDslAdresse.addAttribute("rue");
      v_XClassDslAdresse.addAttribute("code postal");
      v_XClassDslAdresse.addAttribute("ville");
      v_XClassDslAdresse.addAttribute("id");
      // Définir les relations
      v_XClassDslAdresse.addRelation_1_N("adresses");

      // Entité Competence
      final XClassDsl v_XClassDslCompetence = new XClassDsl("Competence");
      // Ajouter l'entité au package
      v_package_annuaire.addClassDsl(v_XClassDslCompetence);
      v_XClassDslCompetence.addAttribute("libellé");
      // Définir les relations
      v_XClassDslCompetence.addRelation_N_N("compétences");

      // Entité Personne_Competence
      final XClassDsl v_XClassDslPersonne_Competence = new XClassDsl("Personne_Competence");
      // Ajouter l'entité au package
      v_package_annuaire.addClassDsl(v_XClassDslPersonne_Competence);
      // Définir les relations
      v_XClassDslPersonne_Competence.addRelation_1_N("jointure_personne");
      v_XClassDslPersonne_Competence.addRelation_1_N("jointure_compétencePerso");

      // Entité CompetencePerso
      final XClassDsl v_XClassDslCompetencePerso = new XClassDsl("CompetencePerso");
      // Ajouter l'entité au package
      v_package_annuaire.addClassDsl(v_XClassDslCompetencePerso);
      // Définir les relations
      v_XClassDslCompetencePerso.addRelation_1_N("jointure_competencePerso");
      v_XClassDslCompetencePerso.addAttribute("libellé");

      // Nouveau package : secu
      final XPackage v_package_secu = new XPackage("secu");
      v_return.addPackage(v_package_secu);
      // Entité Permission
      final XClassDsl v_XClassDslPermission = new XClassDsl("Permission");
      // Ajouter l'entité au package
      v_package_secu.addClassDsl(v_XClassDslPermission);
      v_XClassDslPermission.addAttribute("nomPermission");
      // Définir les relations
      v_XClassDslPermission.addRelation_N_N("role");

      // Entité Role
      final XClassDsl v_XClassDslRole = new XClassDsl("Role");
      // Ajouter l'entité au package
      v_package_secu.addClassDsl(v_XClassDslRole);
      v_XClassDslRole.addAttribute("nomRole");
      // Définir les relations
      v_XClassDslRole.addRelation_N_N("permission");
      v_XClassDslRole.addRelation_N_N("utilisateur");

      // Entité Utilisateur
      final XClassDsl v_XClassDslUtilisateur = new XClassDsl("Utilisateur");
      // Ajouter l'entité au package
      v_package_secu.addClassDsl(v_XClassDslUtilisateur);
      v_XClassDslUtilisateur.addAttribute("identifiant");
      v_XClassDslUtilisateur.addAttribute("motDePasse");
      v_XClassDslUtilisateur.addAttribute("nom");
      v_XClassDslUtilisateur.addAttribute("prenom");
      v_XClassDslUtilisateur.addAttribute("email");
      v_XClassDslUtilisateur.addAttribute("motDePasseHashe");
      // Définir les relations
      v_XClassDslUtilisateur.addRelation_N_N("rolesUtilisateur");

      // // Nouveau package : referentiel
      // final XPackage v_package_referentiel = new XPackage("referentiel");
      // v_return.addPackage(v_package_referentiel);
      // // Entité Grade
      // final XClassDsl v_XClassDslGrade = new XClassDsl("Grade");
      // // Ajouter l'entité au package
      // v_package_referentiel.addClassDsl(v_XClassDslGrade);
      // // Définir les attributs
      // v_XClassDslGrade.addAttribute("libellé");
      // v_XClassDslGrade.addAttribute("trigramme");
      // // Définir les relations
      // v_XClassDslGrade.addRelation_0_1("possède");

      return v_return;
   }
}
