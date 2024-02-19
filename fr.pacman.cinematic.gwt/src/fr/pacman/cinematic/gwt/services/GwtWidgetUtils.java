package fr.pacman.cinematic.gwt.services;

import org.obeonetwork.dsl.cinematic.toolkits.Toolkit;
import org.obeonetwork.dsl.cinematic.toolkits.Widget;

import fr.pacman.cinematic.api.services.IncompatibleWidgetException;
import fr.pacman.cinematic.api.services.StandardToolkitWidget_Itf;
import fr.pacman.cinematic.api.services.WidgetUtils_Abs;

/**
 * Gestion des widgets GWT
 * @author MINARM
 */
public class GwtWidgetUtils extends WidgetUtils_Abs
{

   /**
    * Retourne la classe d'implémentation du widget ou une chaine d'erreur si le widget est inconnu.
    * @param p_widget
    *           le widget
    * @return la classe d'implémentation du widget ou une chaine d'erreur si le widget est inconnu
    */
   public String getImplementationClass (final Widget p_widget)
   {
      try
      {
         // vérification du widget
         checkWidget(p_widget);

         final GwtToolkitWidget_Itf v_widget = getWidget(p_widget);
         if (v_widget != null)
         {
            return v_widget.getImplementationClass();
         }
         // Le widget positionné dans le modèle n'est pas dans un toolkit connu
         // il faut donc utiliser les données que le widget contient (widget défini par l'équipe projet)
         else
         {
            final String v_widgetImplementation = p_widget.getImplementation();
            // vérification que le widget possède bien une implémentation
            if (v_widgetImplementation == null || v_widgetImplementation.trim().isEmpty())
            {
               throw new IncompatibleWidgetException("Implémentation non définie pour le widget " + p_widget.getName() + " dans le toolkit " + p_widget.getToolkit().getName());
            }
            return getClassNameFromFullyQualifiedName(v_widgetImplementation);
         }
      }
      catch (final IncompatibleWidgetException v_e)
      {
         return "ERREUR [ " + v_e.getMessage() + ']';
      }
   }

public String getImplementationClassJavaService(final Widget p_widget){return getImplementationClass(p_widget);}

   /**
    * Retourne la classe d'implémentation du widget ou une chaine d'erreur si le widget est inconnu.
    * @param p_widget
    *           le widget
    * @return la classe d'implémentation du widget ou une chaine d'erreur si le widget est inconnu
    */
   public String getImplementationClassNameForImport (final Widget p_widget)
   {
      String v_interfaceName = getImplementationClass(p_widget);
      if (v_interfaceName.endsWith(">")) {
         v_interfaceName = v_interfaceName.substring(0, v_interfaceName.indexOf('<'));
      }
      return v_interfaceName;
   }

public String getImplementationClassNameForImportJavaService(final Widget p_widget){return getImplementationClassNameForImport(p_widget);}

   /**
    * Retourne le package de la classe d'implémentation du widget ou une chaine d'erreur si le widget est inconnu.
    * @param p_widget
    *           le widget
    * @return le package de la classe d'implémentation du widget ou une chaine d'erreur si le widget est inconnu
    */
   public String getImplementationPackage (final Widget p_widget)
   {
      try
      {
         // vérification du widget
         checkWidget(p_widget);

         final GwtToolkitWidget_Itf v_widget = getWidget(p_widget);
         if (v_widget != null)
         {
            return v_widget.getImplementationPackage();
         }
         // Le widget positionné dans le modèle n'est pas dans un toolkit connu
         // il faut donc utiliser les données que le widget contient (widget défini par l'équipe projet)
         else
         {
            final String v_widgetImplementation = p_widget.getImplementation();
            // vérification que le widget possède bien une implémentation
            if (v_widgetImplementation == null || v_widgetImplementation.trim().isEmpty())
            {
               throw new IncompatibleWidgetException("Implémentation non définie pour le widget " + p_widget.getName() + " dans le toolkit " + p_widget.getToolkit().getName());
            }
            return getPackageFromFullyQualifiedName(v_widgetImplementation);
         }
      }
      catch (final IncompatibleWidgetException v_e)
      {
         return "ERREUR [ " + v_e.getMessage() + ']';
      }
   }

public String getImplementationPackageJavaService(final Widget p_widget){return getImplementationPackage(p_widget);}

   /**
    * Récupère une énumération correspondant au widget
    * @param p_widget
    *           le widget
    * @return l'énumération correspondant au widget
    * @throws IncompatibleWidgetException
    *            si le widget n'est pas compatible (null, sans nom, sans toolkit, non géré dans son toolkit)
    */
   private GwtToolkitWidget_Itf getWidget (final Widget p_widget) throws IncompatibleWidgetException
   {
      final Toolkit v_toolkit = p_widget.getToolkit();

      // utilisation du toolkit standard
      if (StandardToolkitWidget_Itf.c_toolkitName.equals(v_toolkit.getName()))
      {
         // on récupère l'énumération des informations liées à ce widget
         return findWidget(p_widget, StandardGwtToolkit_Enum.values());
      }
      // utilisation du toolkit GWT
      else if (GwtToolkit_Enum.c_toolkitName.equals(v_toolkit.getName()))
      {
         // on récupère l'énumération des informations liées à ce widget
         return findWidget(p_widget, GwtToolkit_Enum.values());
      }
      else
      {
         return null;
      }
   }

}
