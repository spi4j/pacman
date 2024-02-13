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
package fr.pacman.commons.ui;

/**
 * List of compatibles models for Pacman generators.
 * 
 * @author MinArm.
 */
public enum SafranGenerator_Enum
{
   ENTITY("entity"), SOA("soa"), CINEMATIC("cinematic"), REQUIREMENT("requirement");

   private String _value;

   SafranGenerator_Enum (String p_value)
   {
      _value = p_value;
   }

   public String get_value ()
   {
      return _value;
   }
}
