/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.business.api.annuaire;
// Start of user code for imports

import fr.application.business.ApplicationAutoFieldsDto_Abs;
import fr.application.business.ApplicationUserBusiness;
import fr.application.types.enums.TypeCompetence_Enum;
import fr.spi4j.business.dto.DtoUtil;
import fr.spi4j.business.dto.Dto_Itf;
import fr.spi4j.exception.Spi4jValidationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * DTO 'Competence'.
 * @author safr@n
 */
public class CompetenceDto extends ApplicationAutoFieldsDto_Abs implements Dto_Itf<Long>
{
   /**
    * SerialUid.
    */
   private static final long serialVersionUID = -1;

   // ATTRIBUTS

   /** L'identifiant. */
   private Long _id;

   /** Le libellé de la compétence. */
   private String _libelle;

   /** . */
   private TypeCompetence_Enum _typecompetence;

   /** La liste de type 'Personne' associee a  l'instance de 'Competence' courante. */
   private List<PersonneDto> _tab_dispose;

   // METHODES

   /**
    * Constructeur sans paramètre du dto 'Competence'.
    */
   public CompetenceDto ()
   {
      super();
   }

   /**
    * Constructeur complet du dto 'Competence'.
    * @param p_id
    *           (In)(*) L'identifiant de competence.
    * @param p_libelle
    *           (In)(*) Le libellé de la compétence.
    * @param p_typecompetence
    *           (In)(*) typecompetence.
    */
   public CompetenceDto (final Long p_id, final String p_libelle, final TypeCompetence_Enum p_typecompetence)
   {
      super();
      reset_CompetenceDto(p_id, p_libelle, p_typecompetence);
   }

   @Override
   public Long getId ()
   {
      return _id;
   }

   @Override
   public void setId (final Long p_id)
   {
      _id = p_id;
   }

   /**
    * Obtenir le libellé de la compétence.
    * @return Le libellé de la compétence.
    */
   public String get_libelle ()
   {
      return _libelle;
   }

   /**
    * Affecter le libellé de la compétence.
    * @param p_libelle
    *           (In)(*) Le libellé de la compétence.
    */
   public void set_libelle (final String p_libelle)
   {
      _libelle = p_libelle;
   }

   /**
    * Obtenir typecompetence.
    * @return typecompetence.
    */
   public TypeCompetence_Enum get_typecompetence ()
   {
      return _typecompetence;
   }

   /**
    * Affecter typecompetence.
    * @param p_typecompetence
    *           (In)(*) typecompetence.
    */
   public void set_typecompetence (final TypeCompetence_Enum p_typecompetence)
   {
      _typecompetence = p_typecompetence;
   }

   /**
    * Obtenir la liste de 'Personne' : Dispose.
    * @return Dispose.             
    */
   public List<PersonneDto> get_tab_dispose ()
   {
      if (_id != null && _tab_dispose == null)
      {
         _tab_dispose = ApplicationUserBusiness.getCompetenceService().findListDisposeByCompetence(_id);
      }
      return _tab_dispose;
   }

   /**
    * Affecter la liste de 'Personne' : Dispose.
    *           (In) Dispose.
    */
   public void setDispose (final List<PersonneDto> p_tab_personne)
   {
      _tab_dispose = p_tab_personne;
   }

   /**
    * Recycler le dto 'Competence'.
    * @param p_id
    *           (In)(*) L'identifiant de competence.
    * @param p_libelle
    *           (In)(*) Le libellé de la compétence.
    * @param p_typecompetence
    *           (In)(*) typecompetence.
    */
   public void reset_CompetenceDto (final Long p_id, final String p_libelle, final TypeCompetence_Enum p_typecompetence)
   {
      setId(p_id);
      set_libelle(p_libelle);
      set_typecompetence(p_typecompetence);
      _tab_dispose = null;
   }

   @Override
   public void validate () throws Spi4jValidationException
   {
      List<String> v_champsInvalides = null;
      v_champsInvalides = DtoUtil.checkMandatoryField("libelle", _libelle, v_champsInvalides);
      v_champsInvalides = DtoUtil.checkMandatoryField("typecompetence", _typecompetence, v_champsInvalides);
      // si la liste des champs invalides n'est pas nulle, elle ne peut pas être vide à cet endroit
      if (v_champsInvalides != null)
      {
         throw new Spi4jValidationException(this, v_champsInvalides.toArray(new String[v_champsInvalides.size()]));
      }
   }

   @Override
   public String toString ()
   {
      // toString
      // Start of user code toString

      return getClass().getSimpleName() + '[' + _id + ", " + get_libelle() + ", " + get_typecompetence()   + ']';

      // End of user code
   }

}
