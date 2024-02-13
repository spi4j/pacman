package fr.pacman.validation.services;

import org.obeonetwork.dsl.entity.EntityFactory;
import org.obeonetwork.dsl.requirement.RequirementFactory;
import org.obeonetwork.dsl.soa.SoaFactory;
import org.obeonetwork.graal.GraalFactory;

/**
 * Les differents DSL possibles pour une regle.
 */
public enum Dsl_Enum
{
   /** DSL Requirement */
   DslRequirement(RequirementFactory.class.getPackage()),
   /** DSL GRAAL - diagramme de t�ches */
   DslGraalDiagrammeDeTaches(GraalFactory.class.getPackage()),
   /** DSL GRAAL - plan d'actions */
   DslGraalDiagrammeDePlanActions(GraalFactory.class.getPackage()),
   /** DSL GRAAL - diagramme des acteurs */
   DslGraalDiagrammeDesActeurs(GraalFactory.class.getPackage()),
   /** DSL Cinematic */
   // DslCinematic(CinematicFactory.class.getPackage()),
   /** DSL SOA - DTO */
   DslSoaDto(SoaFactory.class.getPackage()),
   /** DSL SOA - Services */
   DslSoaService(SoaFactory.class.getPackage()),
   /** DSL Matching */
   // DslMatching(MatchingFactory.class.getPackage()),
   /** DSL Entity */
   DslEntity(EntityFactory.class.getPackage());
   /** DSL UML diagramme de classes */
   // DslUmlDiagrammeDeClasses(UmlFactory.class.getPackage()),

   private Package _package;

   /**
    * Constructeur.
    * 
    * @param p_package
    *           le package associé au DSL
    */
   private Dsl_Enum (final Package p_package)
   {
      _package = p_package;
   }

   /**
    * @return le package associé à ce DSL
    */
   public Package getPackage ()
   {
      return _package;
   }

}
