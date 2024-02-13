package fr.pacman.commons.services;

import org.eclipse.emf.ecore.EObject;
import org.obeonetwork.dsl.environment.Annotation;
import org.obeonetwork.dsl.environment.MetaData;
import org.obeonetwork.dsl.environment.MetaDataContainer;
import org.obeonetwork.dsl.environment.ObeoDSMObject;

/**
 * Classe utilitaire pour récupérer des annotations dans le modèle.
 * 
 * @author MINARM
 */
public final class AnnotationUtils
{

   /**
    * Constructeur privé.
    */
   private AnnotationUtils ()
   {
      // RAS
   }

   /**
    * Récupère le corps d'une annotation, en remontant récursivement dans le modèle à partir d'un objet p_o.
    * 
    * @param p_o
    *           l'objet source de la recherche
    * @param p_annotation
    *           le titre de l'annotation recherchée
    * @return le corps de l'annotation trouvée, null sinon
    */
   public static String getAnnotationBodyRecursively (final ObeoDSMObject p_o, final String p_annotation)
   {
      return getAnnotationBody(p_o, p_annotation, true);
   }

   /**
    * Récupère le corps d'une annotation, attachée à un objet p_o.
    * 
    * @param p_o
    *           l'objet contenant l'annotation
    * @param p_annotation
    *           le titre de l'annotation recherchée
    * @return le corps de l'annotation trouvée, null sinon
    */
   public static String getAnnotationBody (final ObeoDSMObject p_o, final String p_annotation)
   {
      return getAnnotationBody(p_o, p_annotation, false);
   }

   /**
    * Récupère le corps d'une annotation, récursivement ou non.
    * 
    * @param p_o
    *           l'objet source de la recherche
    * @param p_annotation
    *           le titre de l'annotation recherchée
    * @param p_recursive
    *           true si la recherche doit être récursive, false si la recherche doit se faire seulement sur l'objet p_o
    * @return le corps de l'annotation trouvée, null sinon
    */
   public static String getAnnotationBody (final ObeoDSMObject p_o, final String p_annotation, final boolean p_recursive)
   {
      if (p_o == null)
      {
         return null;
      }
      final MetaDataContainer v_metadataContainer = p_o.getMetadatas();
      final String v_body = getAnnotationBody(v_metadataContainer, p_annotation);
      if (v_body != null)
      {
         return v_body;
      }
      if (p_recursive)
      {
         final EObject v_containerObject = p_o.eContainer();
         if (v_containerObject instanceof ObeoDSMObject)
         {
            return getAnnotationBody((ObeoDSMObject) v_containerObject, p_annotation, p_recursive);
         }
      }
      return null;
   }

   /**
    * Récupère le corps d'une annotation dans un MetadataContainer.
    * 
    * @param p_metadataContainer
    *           le container de métadonnées
    * @param p_annotation
    *           l'annotation recherchée
    * @return le corps de l'annotation trouvée, null sinon
    */
   public static String getAnnotationBody (final MetaDataContainer p_metadataContainer, final String p_annotation)
   {
      if (p_metadataContainer != null)
      {
         for (final MetaData v_m : p_metadataContainer.getMetadatas())
         {
            if (v_m instanceof Annotation)
            {
               final Annotation v_anno = (Annotation) v_m;
               if (v_anno != null && v_anno.getTitle() != null
                        && v_anno.getTitle().trim().equalsIgnoreCase(p_annotation.trim())
                        && v_anno.getBody() != null
                        && !v_anno.getBody().trim().isEmpty())
               {
                  return v_anno.getBody();
               }
            }
         }
      }
      return null;
   }

   /**
    * Vérifie si une annotation existe
    * 
    * @param p_o
    *           l'objet annoté
    * @param p_annotation
    *           l'annotation recherchée
    * @return true si l'annotation existe, false sinon
    */
   public static boolean annotationExistsRecursively (final ObeoDSMObject p_o, final String p_annotation)
   {
      return annotationExists(p_o, p_annotation, true);
   }

   /**
    * Vérifie si une annotation existe
    * 
    * @param p_o
    *           l'objet annoté
    * @param p_annotation
    *           l'annotation recherchée
    * @param p_recursive
    *           true pour chercher récursivement parmi les parents
    * @return true si l'annotation existe, false sinon
    */
   public static boolean annotationExists (final ObeoDSMObject p_o, final String p_annotation, final boolean p_recursive)
   {
      if (p_o == null)
      {
         return false;
      }
      final MetaDataContainer v_metadataContainer = p_o.getMetadatas();
      if (v_metadataContainer != null)
      {
         for (final MetaData v_m : v_metadataContainer.getMetadatas())
         {
            if (v_m instanceof Annotation)
            {
               final Annotation v_anno = (Annotation) v_m;
               if (v_anno != null && v_anno.getTitle() != null
                        && v_anno.getTitle().trim().equalsIgnoreCase(p_annotation.trim()))
               {
                  return true;
               }
            }
         }
      }
      if (p_recursive)
      {
         final EObject v_containerObject = p_o.eContainer();
         if (v_containerObject instanceof ObeoDSMObject)
         {
            return annotationExists((ObeoDSMObject) v_containerObject, p_annotation, p_recursive);
         }
      }
      return false;
   }

   /**
    * Vérifie si une annotation existe
    * 
    * @param p_o
    *           l'objet annoté
    * @param p_Annotation_Enum
    *           l'annotation recherchée
    * @param p_recursive
    *           true pour chercher récursivement parmi les parents
    * @return null si l'annotation n'existe pas, et le nom de cette dernière sinon.
    */
   public static String getAnnotationBody (final ObeoDSMObject p_o, final Annotation_Enum p_Annotation_Enum,
            final boolean p_recursive)
   {
      final String v_annotationName = p_Annotation_Enum.get_AnotationName();
      return getAnnotationBody(p_o, v_annotationName, p_recursive);
   }

   /**
    * Vérifie si une annotation existe
    * 
    * @param p_o
    *           l'objet annoté
    * @param p_recursive
    *           true pour chercher récursivement parmi les parents
    * @return null si l'annotation n'existe pas, et le nom de cette dernière sinon.
    */
   public static String getAnnotation_PHYSICAL_SHORT_NAME (final ObeoDSMObject p_o, final boolean p_recursive)
   {
      return getAnnotationBody(p_o, Annotation_Enum.PHYSICAL_SHORT_NAME, p_recursive);
   }

   /**
    * Vérifie si une annotation existe
    * 
    * @param p_o
    *           l'objet annoté
    * @param p_recursive
    *           true pour chercher récursivement parmi les parents
    * @return null si l'annotation n'existe pas, et le nom de cette dernière sinon.
    */
   public static Integer getAnnotation_VOL_NB_ROWS (final ObeoDSMObject p_o, final boolean p_recursive)
   {
      return parseInteger(getAnnotationBody(p_o, Annotation_Enum.VOL_NB_ROWS, p_recursive));
   }

   /**
    * Vérifie si une annotation existe
    * 
    * @param p_o
    *           l'objet annoté
    * @param p_recursive
    *           true pour chercher récursivement parmi les parents
    * @return null si l'annotation n'existe pas, et le nom de cette dernière sinon.
    */
   public static Integer getAnnotation_VOL_MIN_OCCURS (final ObeoDSMObject p_o, final boolean p_recursive)
   {
      return parseInteger(getAnnotationBody(p_o, Annotation_Enum.VOL_MIN_OCCURS, p_recursive));
   }

   /**
    * Vérifie si une annotation existe
    * 
    * @param p_o
    *           l'objet annoté
    * @param p_recursive
    *           true pour chercher récursivement parmi les parents
    * @return null si l'annotation n'existe pas, et le nom de cette dernière sinon.
    */
   public static Integer getAnnotation_VOL_MAX_OCCURS (final ObeoDSMObject p_o, final boolean p_recursive)
   {
      return parseInteger(getAnnotationBody(p_o, Annotation_Enum.VOL_MAX_OCCURS, p_recursive));
   }

   /**
    * Parse le contenu d'une annotation pour le convertir en entier.
    * @param p_value
    *           la valeur de la métadonnée
    * @return la valeur entière de la métadonnée ou -1 si la valeur est nulle ou n'est pas un entier
    */
   private static Integer parseInteger (final String p_value)
   {
      if (p_value == null)
      {
         return -1;
      }
      try
      {
         return Integer.parseInt(p_value);
      }
      catch (final NumberFormatException v_e)
      {
         return -1;
      }
   }

   /**
    * Vérifie si une annotation existe
    * 
    * @param p_o
    *           l'objet annoté
    * @param p_recursive
    *           true pour chercher récursivement parmi les parents
    * @return null si l'annotation n'existe pas, et le nom de cette dernière sinon.
    */
   public static String getAnnotation_REFERENTIEL (final ObeoDSMObject p_o, final boolean p_recursive)
   {
      return getAnnotationBody(p_o, Annotation_Enum.REFERENTIEL, p_recursive);
   }
}
