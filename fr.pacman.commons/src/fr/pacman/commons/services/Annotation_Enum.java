package fr.pacman.commons.services;

import org.obeonetwork.dsl.environment.ObeoDSMObject;

/**
 * Enumération des méta-données possibles dans les modèles.
 * @author Administrateur
 * 
 */
@SuppressWarnings("unchecked")
public enum Annotation_Enum
{
   /** Nom de l'objet en base de données */
   PHYSICAL_NAME("PHYSICAL_NAME", Model_Enum.DSL_ENTITY, true, org.obeonetwork.dsl.entity.Entity.class,
            org.obeonetwork.dsl.environment.Attribute.class, org.obeonetwork.dsl.environment.Reference.class),
   /** Nom court de l'objet (pour nom de la séquence ou de l'index sur la clé primaire) */
   PHYSICAL_SHORT_NAME("PHYSICAL_SHORT_NAME", Model_Enum.DSL_ENTITY, true, org.obeonetwork.dsl.entity.Entity.class),
   /** Taille de la colonne en base de données */
   PHYSICAL_SIZE("PHYSICAL_SIZE", Model_Enum.DSL_ENTITY, true, org.obeonetwork.dsl.environment.Attribute.class),
   /** Attribut servant à gérer la version du tuple */
   VERSION("VERSION", Model_Enum.DSL_ENTITY, false, org.obeonetwork.dsl.environment.Attribute.class),

   /** Données mises en cache */
   REFERENTIEL("REFERENTIEL", Model_Enum.DSL_SOA_DTO, false, org.obeonetwork.dsl.soa.System.class,
            org.obeonetwork.dsl.environment.Namespace.class),
   /** DTO lié à aucune entité, uniquement à des fins d'encapsulation d'informations entre le client et le serveur */
   NOT_PERSISTENT("NOT_PERSISTENT", Model_Enum.DSL_SOA_DTO, false, org.obeonetwork.dsl.soa.System.class,
            org.obeonetwork.dsl.environment.Namespace.class),
   /** Attribut pouvant être calculé au sein d'un DTO */
   COMPUTED("COMPUTED", Model_Enum.DSL_SOA_DTO, false, org.obeonetwork.dsl.environment.Attribute.class,
            org.obeonetwork.dsl.environment.Reference.class),
   /** DTO représentant l'utilisateur connecté (stocké en session) */
   USER("USER", Model_Enum.DSL_SOA_DTO, false),

   /** Permissions liées à l'opération */
   PERMISSIONS("PERMISSIONS", Model_Enum.DSL_SOA_SERVICE, true, org.obeonetwork.dsl.soa.Operation.class),
   /** Permissions liées à l'opération */
   PERMISSIONS_OR("PERMISSIONS_OR", Model_Enum.DSL_SOA_SERVICE, true, org.obeonetwork.dsl.soa.Operation.class),
   /** Permissions liées à l'opération */
   PERMISSIONS_AND("PERMISSIONS_AND", Model_Enum.DSL_SOA_SERVICE, true, org.obeonetwork.dsl.soa.Operation.class),
   /** Nombre de lignes dans la base de données */
   VOL_NB_ROWS("VOL_NB_ROWS", Model_Enum.DSL_ENTITY, true, org.obeonetwork.dsl.entity.Entity.class),
   /** Nombre min */
   VOL_MIN_OCCURS("VOL_MIN_OCCURS", Model_Enum.DSL_ENTITY, true, org.obeonetwork.dsl.environment.Reference.class),
   /** Nombre max */
   VOL_MAX_OCCURS("VOL_MAX_OCCURS", Model_Enum.DSL_ENTITY, true, org.obeonetwork.dsl.environment.Reference.class), 
   /** Authorise l'accès non authentifié à une ressource. */
   ACCEPT_UNAUTHENTIFIED("ACCEPT_UNAUTHENTIFIED", Model_Enum.DSL_SOA_SERVICE, false, org.obeonetwork.dsl.soa.Operation.class), 
   /** Nombre de tuples à renvoyer dans une pagination. */
   PAGE_SIZE("PAGE_SIZE", Model_Enum.DSL_SOA_SERVICE, true, org.obeonetwork.dsl.soa.Operation.class), 
   /** Indicateur d'opération dédiée à l'authorisation pour les resources REST. */
   AUTH_TOKEN("AUTH_TOKEN", Model_Enum.DSL_SOA_SERVICE, true, org.obeonetwork.dsl.soa.Operation.class),
   /** Demande de ne pas tenir compte du numéro de version dans les URI REST. */
   API_VERSION("API_VERSION", Model_Enum.DSL_SOA_SERVICE, true, org.obeonetwork.dsl.soa.Component.class), 
   /** Le nom de la librairie si un DTO appartient à une librairie externe. */
   LIBRARY_NAME("LIBRARY_NAME", Model_Enum.DSL_SOA_DTO, true, org.obeonetwork.dsl.environment.DTO.class);

   /** Le nom de l'annotation */
   private String _AnotationName;

   /** Le type de modèle DSM sur laquelle on l'a trouve */
   // private Model_Enum[] _tab_AnotationModel;
   private Model_Enum _tab_AnotationModel;

   /** Le type de modèle DSM sur laquelle on l'a trouve */
   private Class<? extends ObeoDSMObject>[] _tab_AnotationTypesModel;

   /** le boolean qui indique si la métadonnées a un contenu */
   private boolean _contenu;

   /**
    * Constructeur avec le nom et les DSM de l'annotation.
    * @param p_AnotationName
    *           Le nom de l'annotation.
    * @param p_tab_AnotationModel
    *           Le (ou les) DSM sur lesquelles elle s'applique.
    * @param p_contenu
    *           'true' si l'annotation possède un contenu.
    * @param p_tab_AnotationTypeModel
    *           Le (ou les) DSL sur lesquelles elle s'applique.
    */
   private Annotation_Enum (final String p_AnotationName, final Model_Enum p_tab_AnotationModel,
            final boolean p_contenu, final Class<? extends ObeoDSMObject>... p_tab_AnotationTypeModel)
   {
      _AnotationName = p_AnotationName;
      _tab_AnotationModel = p_tab_AnotationModel;
      _tab_AnotationTypesModel = p_tab_AnotationTypeModel;
      _contenu = p_contenu;
   }

   /**
    * Obtenir le nom de l'annotation.
    * @return Le nom.
    */
   public String get_AnotationName ()
   {
      return _AnotationName;
   }

   /**
    * Boolean qui indique si la métadonnée a un contenu
    * @return boolean : _contenu.
    */
   public boolean hasBody ()
   {
      return _contenu;
   }

   /**
    * Le (ou les) DSM sur lesquelles elle s'applique.
    * @return Le tableau contenant les DSM potentiels.
    */
   public Model_Enum get_tab_AnotationModel ()
   {
      return _tab_AnotationModel;
   }

   /**
    * Le (ou les) types de DSM sur lesquelles elle s'applique.
    * @return Le tableau contenant les types de DSM potentiels.
    */
   public Class<? extends ObeoDSMObject>[] get_tab_AnotationTypesModel ()
   {
      return _tab_AnotationTypesModel;
   }

   /**
    * Retourne une annotation à partir de son titre
    * @param p_titre
    *           Le titre de l'annotation
    * @return Le tableau contenant les types de DSM potentiels.
    */
   public static Annotation_Enum findByName (final String p_titre)
   {
      Annotation_Enum v_a = null;
      for (Annotation_Enum v_ae : Annotation_Enum.values())
      {
         if (v_ae.get_AnotationName().equalsIgnoreCase(p_titre))
         {
            v_a = v_ae;
         }
      }
      return v_a;
   }

   /**
    * Retourne vrai si la classe en entrée dispose de la métadonnée en entrée
    * @param p_obj
    *           Le titre de la classe (ex : System, Entity ..)
    * @param p_enum
    *           Le titre de l'annotation
    * @return true si la classe en entrée est prise en charge par la métadonnée.
    */
   public static boolean findClass (final String p_obj, final String p_enum)
   {
      boolean v_ClassFound = false;
      Class<? extends ObeoDSMObject>[] v_c;
      final Annotation_Enum v_ae = findByName(p_enum);
      if (v_ae != null)
      {
         v_c = v_ae.get_tab_AnotationTypesModel();
         for (int v_i = 0; v_i < v_c.length; v_i++)
         {
            if (p_obj.equalsIgnoreCase(v_c[v_i].getSimpleName()))
            {
               v_ClassFound = true;
            }
         }
      }

      return v_ClassFound;

   }

}
