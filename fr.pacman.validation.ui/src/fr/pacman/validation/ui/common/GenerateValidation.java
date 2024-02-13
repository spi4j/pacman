/*******************************************************************************
 * Copyright (c) 2008, 2010 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package fr.pacman.validation.ui.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;

import fr.pacman.commons.main.PacmanGenerator_Abs;
import fr.pacman.commons.ui.SafranGenerator_Abs;
import fr.pacman.commons.ui.SafranGenerator_Enum;
import fr.pacman.validation.JavaValidationGenerator;

/**
 * Main entry point of the 'Validation' generation module.
 */
public class GenerateValidation extends SafranGenerator_Abs<URI>
{

   /**
    * Constructeur.
    * 
    * @param p_modelURI
    *           le modèle
    */
   public GenerateValidation (final URI p_modelURI)
   {
      super(p_modelURI, Collections.emptyList());
   }

   @Override
   protected List<PacmanGenerator_Abs> getPacmanGenerators (final URI p_modelURI, final List<? extends Object> p_arguments) throws IOException
   {
      final List<PacmanGenerator_Abs> v_generators = new ArrayList<PacmanGenerator_Abs>();
      final JavaValidationGenerator v_validationGenerator = new JavaValidationGenerator(p_modelURI);
      v_validationGenerator.setConfigurationFileDirectoryPath(getModelPath());
      v_generators.add(v_validationGenerator);
      return v_generators;
   }

   @Override
   public List<String> getProjectsNamesToRefresh ()
   {
      return Collections.emptyList();
   }
   
   @Override
   public List<Boolean> getValuesOfIncompatibleProperties() {
      return null;
   }
   
   @Override
   public List<SafranGenerator_Enum> getValuesOfCompatibleModels() {
      return null;
   }
}
