/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
// CHECKSTYLE:OFF
package fr.application.requirement;
// CHECKSTYLE:ON

import fr.spi4j.requirement.Requirement_Itf;
import fr.spi4j.requirement.RequirementsUtil;

/**
 * Enumération des exigences.
 * @author safr@n
 */
public enum Requirement_Enum implements Requirement_Itf
{
   /**
    * dateNaissance : <br>
    * La date de naissance est antérieure à la date courante<br>
    * Voir RequirementVersion_Test#testVersion_REQ_FCT_PERS_01()
    */
   REQ_FCT_PERS_01("dateNaissance", "1"),

   /**
    * CritereDateDebutDateFin : <br>
    * La date de début est strictement inférieure à la date de fin<br>
    * Voir RequirementVersion_Test#testVersion_REQ_TEC_PERS_02()
    */
   REQ_TEC_PERS_02("CritereDateDebutDateFin", "1");

   /** Id. */
   private final String _id;

   /** Nom. */
   private final String _name;

   /** La version de l'exigence dans le modèle Requirement */
   private final String _versionModel;

   /** La version implémentée. */
   private String _versionImplem;

   /**
    * Constructeur de l'énumeration de l'exigence.
    * @param p_name
    *           le nom.
    * @param p_versionModel
    *           La version de l'exigence dans la modélisation.
    */
   private Requirement_Enum (final String p_name, final String p_versionModel)
   {
      _id = toString().substring("REQ_".length());
      _name = p_name;
      _versionModel = p_versionModel;
   }

   @Override
   public String getId ()
   {
      return _id;
   }

   @Override
   public String getName ()
   {
      return _name;
   }

   @Override
   public void set_versionImplem ()
   {
      set_versionImplem(c_notImplemented);
   }

   @Override
   public String get_versionImplem ()
   {
      return _versionImplem;
   }

   @Override
   public void set_versionImplem (final String p_versionImplem)
   {
      // Vérifier que la version implémentée est égale à celle de la modélisation
      RequirementsUtil.checkRequirementVersion(this, p_versionImplem);
      // Si version pas encore affectée
      _versionImplem = p_versionImplem;
   }

   @Override
   public String get_versionModel ()
   {
      return _versionModel;
   }
}
