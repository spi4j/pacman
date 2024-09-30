package fr.pacman.validation.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Classe de test abstraite pour tester la validation des modèles
 * @author MINARM
 */
public class ModelValidationTester_Abs
{

   /**
    * Vérification des règles d'un DSL.
    * 
    * @param p_modelUrl
    *           le modèle en entrée
    * @param p_dsl
    *           le DSL à tester
    * @param p_expected
    *           le nombre de problèmes attendus
    * @throws Throwable
    *            erreur lors de la validation
    */
   protected void validate (final String p_modelUrl, final Dsl_Enum p_dsl, final int p_expected) throws Throwable
   {
//      ValidationService.getProblems().clear();
//      final JavaValidationGenerator v_generator = new JavaValidationGenerator(ModelLoader4Test.initializeModelContents(URI.createURI(p_modelUrl)));
//      v_generator.filterDsl(p_dsl);
//      v_generator.doGenerate(BasicMonitor.toMonitor(new NullProgressMonitor()));
//      // System.out.println(ValidationService.getProblems());
//      assertEquals(p_expected, ValidationService.getProblems().size(), "Mauvais nombre de problèmes attendus pour les règles du DSL " + p_dsl);
   }

   /**
    * Vérification d'une règle.
    * 
    * @param p_modelUrl
    *           le modèle en entrée
    * @param p_rule
    *           la règle à tester
    * @param p_expected
    *           le nombre de problèmes attendus
    * @throws Throwable
    *            erreur lors de la validation
    */
   protected void validate (final String p_modelUrl, final DslValidationRule_Itf p_rule, final int p_expected) throws Throwable
   {
//      ValidationService.getProblems().clear();
//      final JavaValidationGenerator v_generator = new JavaValidationGenerator(ModelLoader4Test.initializeModelContents(URI.createURI(p_modelUrl)));
//      v_generator.filterDsl(p_rule.get_RuleDsl_Enum());
//      v_generator.filterRule(p_rule);
//      v_generator.doGenerate(BasicMonitor.toMonitor(new NullProgressMonitor()));
//      // System.out.println(ValidationService.getProblems());
//      assertEquals(p_expected, ValidationService.getProblems().size(), "Mauvais nombre de problèmes attendus pour la règle " + p_rule.get_id());
   }

   /**
    * Verification d'une execution par rapport à un fichier de reference
    * @param p_modelUrl
    *           le modèle en entrée
    * @param p_dsl
    *           la règle à tester
    * @param p_fileName
    *           le fichier contenant les erreurs attendues
    * @throws Throwable
    *            erreur lors de la validation
    */
   protected void validate (final String p_modelUrl, final Dsl_Enum p_dsl, final String p_fileName) throws Throwable
   {
//      // FileInputStream v_fis = new FileInputStream(new File("p_fileName"));
//      ValidationService.getProblems().clear();
//      final JavaValidationGenerator v_generator = new JavaValidationGenerator(ModelLoader4Test.initializeModelContents(URI.createURI(p_modelUrl)));
//      v_generator.filterDsl(p_dsl);
//      v_generator.doGenerate(BasicMonitor.toMonitor(new NullProgressMonitor()));
//      final String v_expected = getFileBody(p_fileName);
//      String v_result = "";
//      for (DslValidationRuleNokBean v_rule : ValidationService.getProblems())
//      {
//         v_result += v_rule.get_messageNok() + "\r\n";
//      }
//      assertEquals(v_expected, v_result);
   }

   /**
    * Récupérer le corps du fichier attendu
    * @param p_Path
    *           chemin du fichier
    * @return corps du fichier
    * @throws IOException
    *            RAS
    */
   private String getFileBody (final String p_Path) throws IOException
   {

      InputStream v_Is = null;
      final StringBuilder v_Str = new StringBuilder();
      try
      {
         v_Is = new FileInputStream(p_Path);
         int v_Read;
         final byte[] v_Buffer = new byte[2000];
         while (v_Is.available() > 0)
         {
            v_Read = v_Is.read(v_Buffer);
            v_Str.append(new String(v_Buffer, 0, v_Read, "UTF-8"));
         }
      }
      finally
      {
         if (v_Is != null)
         {
            v_Is.close();
         }
      }
      return v_Str.toString();
   }
}
