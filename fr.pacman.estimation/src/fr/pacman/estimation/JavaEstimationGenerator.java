package fr.pacman.estimation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

import fr.pacman.commons.main.PacmanGeneratorOld_Abs;
import fr.pacman.estimation.services.EstimationCharge_Sce;
import fr.pacman.estimation.services.IndicatorsEstimation;
import fr.pacman.estimation.services.complexite_pf.NotReliableException;
import fr.pacman.estimation.services.complexite_pf.UnsupportedModelException;

/**
 * Lanceur de "estimation projet" (pour chargement du modèle).
 * 
 * @author MINARM
 */
public class JavaEstimationGenerator extends PacmanGeneratorOld_Abs
{
   /**
    * This allows clients to instantiates a generator with all required information.
    * 
    * @param p_uri
    *           We'll iterate over the content of this element to find Objects matching the first parameter of the template we need to call.
    * @throws IOException
    *            This can be thrown in two scenarios : the module cannot be found, or it cannot be loaded.
    */
   public JavaEstimationGenerator (final URI p_uri) throws IOException
   {
      super(p_uri, null, Collections.emptyList());
   }

   /**
    * This allows clients to instantiates a generator with all required information.
    * 
    * @param p_model
    *           We'll iterate over the content of this element to find Objects matching the first parameter of the template we need to call.
    * @throws IOException
    *            This can be thrown in two scenarios : the module cannot be found, or it cannot be loaded.
    */
   public JavaEstimationGenerator (final EObject p_model) throws IOException
   {
      super(p_model, null, Collections.emptyList());
   }

   /**
    * @param p_Uri
    *           uri du model
    * @throws IOException
    *            RAS
    */
   public void setModelURI (final String p_Uri) throws IOException
   {
      initialize(URI.createFileURI(p_Uri), new File("gen/"), new ArrayList<String>());
   }

   @Override
   protected String getModuleFileName ()
   {
      return "empty";
   }

   @Override
   protected String[] getModuleTemplates ()
   {
      return null;
   }

   @Override
   public String getProjectName ()
   {
      return "fr.pacman.estimation";
   }

   @Override
   public boolean getSwitchQueryCache ()
   {
      return Boolean.TRUE;
   }

   @Override
   public void doGenerate (final Monitor p_monitor) throws IOException
   {
      throw new UnsupportedOperationException("Cette méthode ne devrait pas être appelée. Il faut appeler \"computeIndicatorsEstimation\"");
   }

   /**
    * Calcule les indicateurs d'estimation.
    * 
    * @param p_Node
    *           le point d'entrée pour l'estimation
    * @param <TypeRacine>
    *           le type de point d'entrée
    * @return les indicateurs d'estimation.
    * @throws IOException
    *            erreur de lecture du modèle
    * @throws NotReliableException
    *            Estimation pas fiable
    * @throws UnsupportedModelException
    *            Estimation pas applicable sur le modèle
    */
   public <TypeRacine> IndicatorsEstimation<TypeRacine> computeIndicatorsEstimation (final TypeRacine p_Node) throws IOException, NotReliableException, UnsupportedModelException
   {
      final EObject v_root = model;

      // Lecture des règles à vérifier
      // final Properties v_properties = new Properties();
      // for (final String v_propertiesFilename : getProperties())
      // {
      // loadProperties(v_properties, v_propertiesFilename);
      // }
      final EstimationCharge_Sce v_service = new EstimationCharge_Sce();
      final IndicatorsEstimation<TypeRacine> v_indicators = v_service.getIndicatorsEstimation_invoke(v_root, p_Node);
      return v_indicators;
   }
}
