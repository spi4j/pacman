/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
// CHECKSTYLE:OFF
package fr.test.requirement;
// CHECKSTYLE:ON

import fr.spi4j.requirement.Requirement_Itf;
import fr.spi4j.requirement.RequirementsUtil;

/**
 * Enumération des exigences.
 * @author safr@n
 */
public enum RequirementEnum implements Requirement_Itf
{
   /**
    * Req1-HomePageBeautiful : <br>
    * The homepage shall be beautiful<br>
    * Voir RequirementVersionTest#testVersion_REQ_R1()
    */
   REQ_R1("Req1-HomePageBeautiful", "2"),

   /**
    * Req2-ManageFavorite : <br>
    * The user shall be able to manage his favorites<br>
    * Voir RequirementVersionTest#testVersion_REQ_R2()
    */
   REQ_R2("Req2-ManageFavorite", "1"),

   /**
    * Req3-AtLeastOneSearchCriteriaMustBeSpecfied : <br>
    * At least one seach criteria must be entered by the user to perform a search<br>
    * Voir RequirementVersionTest#testVersion_REQ_R3()
    */
   REQ_R3("Req3-AtLeastOneSearchCriteriaMustBeSpecfied", "1");

   /** Id. */
   private final String id;

   /** Nom. */
   private final String name;

   /** La version de l'exigence dans le modèle Requirement */
   private final String versionModel;

   /** La version implémentée. */
   private String versionImplem;

   /**
    * Constructeur de l'énumeration de l'exigence.
    * @param name
    *           le nom.
    * @param versionModel
    *           La version de l'exigence dans la modélisation.
    */
   private RequirementEnum (final String name, final String versionModel)
   {
      this.id = toString().substring("REQ_".length());
      this.name = name;
      this.versionModel = versionModel;
   }

   @Override
   public String getId ()
   {
      return id;
   }

   @Override
   public String getName ()
   {
      return name;
   }

   @Override
   public void set_versionImplem ()
   {
      set_versionImplem(c_notImplemented);
   }

   @Override
   public String get_versionImplem ()
   {
      return versionImplem;
   }

   @Override
   public void set_versionImplem (final String versionImplem)
   {
      // Vérifier que la version implémentée est égale à celle de la modélisation
      RequirementsUtil.checkRequirementVersion(this, versionImplem);
      // Si version pas encore affectée
      this.versionImplem = versionImplem;
   }

   @Override
   public String get_versionModel ()
   {
      return versionModel;
   }
}
