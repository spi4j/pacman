package fr.pacman.validation.services;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation pour une méthode de véfication d'une exigence de modélisation.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DslValidationRule
{
   /**
    * L'exigence du DSM.
    */
   DslValidationRuleReferentiel_Enum value();
}
