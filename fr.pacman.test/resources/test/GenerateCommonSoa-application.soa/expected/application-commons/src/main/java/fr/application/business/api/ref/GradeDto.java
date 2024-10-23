/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.business.api.ref;
// Start of user code for imports

import fr.application.business.ApplicationAutoFieldsDto_Abs;
import fr.spi4j.business.dto.DtoUtil;
import fr.spi4j.business.dto.Dto_Itf;
import fr.spi4j.exception.Spi4jValidationException;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * DTO 'Grade'.
 * @author safr@n
 */
public class GradeDto extends ApplicationAutoFieldsDto_Abs implements Dto_Itf<Long>
{
   /**
    * SerialUid.
    */
   private static final long serialVersionUID = -1;

   // ATTRIBUTS

   /** L'identifiant. */
   private Long _id;

   /** Le libellé du grade. */
   private String _libelle;

   /** Le trigramme du grade. */
   private String _trigramme;


   // METHODES

   /**
    * Constructeur sans paramètre du dto 'Grade'.
    */
   public GradeDto ()
   {
      super();
   }

   /**
    * Constructeur complet du dto 'Grade'.
    * @param p_id
    *           (In)(*) L'identifiant de grade.
    * @param p_libelle
    *           (In)(*) Le libellé du grade.
    * @param p_trigramme
    *           (In)(*) Le trigramme du grade.
    */
   public GradeDto (final Long p_id, final String p_libelle, final String p_trigramme)
   {
      super();
      reset_GradeDto(p_id, p_libelle, p_trigramme);
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
    * Obtenir le libellé du grade.
    * @return Le libellé du grade.
    */
   public String get_libelle ()
   {
      return _libelle;
   }

   /**
    * Affecter le libellé du grade.
    * @param p_libelle
    *           (In)(*) Le libellé du grade.
    */
   public void set_libelle (final String p_libelle)
   {
      _libelle = p_libelle;
   }

   /**
    * Obtenir le trigramme du grade.
    * @return Le trigramme du grade.
    */
   public String get_trigramme ()
   {
      return _trigramme;
   }

   /**
    * Affecter le trigramme du grade.
    * @param p_trigramme
    *           (In)(*) Le trigramme du grade.
    */
   public void set_trigramme (final String p_trigramme)
   {
      _trigramme = p_trigramme;
   }

   /**
    * Recycler le dto 'Grade'.
    * @param p_id
    *           (In)(*) L'identifiant de grade.
    * @param p_libelle
    *           (In)(*) Le libellé du grade.
    * @param p_trigramme
    *           (In)(*) Le trigramme du grade.
    */
   public void reset_GradeDto (final Long p_id, final String p_libelle, final String p_trigramme)
   {
      setId(p_id);
      set_libelle(p_libelle);
      set_trigramme(p_trigramme);
   }

   @Override
   public void validate () throws Spi4jValidationException
   {
      List<String> v_champsInvalides = null;
      v_champsInvalides = DtoUtil.checkMandatoryField("libelle", _libelle, v_champsInvalides);
      v_champsInvalides = DtoUtil.checkMandatoryField("trigramme", _trigramme, v_champsInvalides);
      // si la liste des champs invalides n'est pas nulle, elle ne peut pas être vide à cet endroit
      if (v_champsInvalides != null)
      {
         throw new Spi4jValidationException(this, v_champsInvalides.toArray(new String[v_champsInvalides.size()]));
      }
   }

   @Override
   public String toString ()
   {
      
      // Start of user code toString

      return getClass().getSimpleName() + '[' + _id + ", " + get_libelle() + ", " + get_trigramme()   + ']';

      // End of user code
   }

   
   // Start of user code Methodes

   // End of user code
}
