package fr.pacman.cinematic.api.services;

import org.obeonetwork.dsl.cinematic.toolkits.Toolkit;
import org.obeonetwork.dsl.cinematic.toolkits.Widget;

/**
 * Gestion des widgets GWT
 * @author MINARM
 */
public class InterfaceWidgetUtils extends WidgetUtils_Abs
{

   /**
    * Retourne le nom de l'interface du widget ou une chaine d'erreur si le widget est inconnu.
    * @param p_widget
    *           le widget
    * @return le nom de l'interface du widget ou une chaine d'erreur si le widget est inconnu
    */
   public String getInterfaceName (final Widget p_widget)
   {
      try
      {
         // vérification du widget
         checkWidget(p_widget);

         final InterfaceToolkitWidget_Itf v_widget = getWidget(p_widget);
         if (v_widget != null)
         {
            return v_widget.getInterfaceName();
         }
         // Le widget positionné dans le modèle n'est pas dans un toolkit connu
         // il faut donc utiliser les données que le widget contient (widget défini par l'équipe projet)
         else
         {
            // TODO Obeo rajoutera peut être un attribut "interface"
            final String v_widgetInterface = p_widget.getImplementation();
            // vérification que le widget possède bien une implémentation
            if (v_widgetInterface == null || v_widgetInterface.trim().isEmpty())
            {
               throw new IncompatibleWidgetException("Interface non définie pour le widget " + p_widget.getName());
            }
            return getClassNameFromFullyQualifiedName(v_widgetInterface);
         }
      }
      catch (final IncompatibleWidgetException v_e)
      {
         return "ERREUR [ " + v_e.getMessage() + ']';
      }
   }

public String getInterfaceNameJavaService(final Widget p_widget){return getInterfaceName(p_widget);}

   /**
    * Retourne le nom de l'interface du widget ou une chaine d'erreur si le widget est inconnu.
    * @param p_widget
    *           le widget
    * @return le nom de l'interface du widget ou une chaine d'erreur si le widget est inconnu
    */
   public String getInterfaceNameForImport (final Widget p_widget)
   {
      String v_interfaceName = getInterfaceName(p_widget);
      if (v_interfaceName.endsWith(">")) {
         v_interfaceName = v_interfaceName.substring(0, v_interfaceName.indexOf('<'));
      }
      return v_interfaceName;
   }

public String getInterfaceNameForImportJavaService(final Widget p_widget){return getInterfaceNameForImport(p_widget);}

   /**
    * Retourne le nom de l'interface du widget ou une chaine d'erreur si le widget est inconnu.
    * @param p_widget
    *           le widget
    * @return le nom de l'interface du widget ou une chaine d'erreur si le widget est inconnu
    */
   public String getInterfacePackage (final Widget p_widget)
   {
      try
      {
         // vérification du widget
         checkWidget(p_widget);

         final InterfaceToolkitWidget_Itf v_widget = getWidget(p_widget);
         if (v_widget != null)
         {
            return v_widget.getInterfacePackage();
         }
         // Le widget positionné dans le modèle n'est pas dans un toolkit connu
         // il faut donc utiliser les données que le widget contient (widget défini par l'équipe projet)
         else
         {
            // TODO Obeo rajoutera peut être un attribut "interface"
            final String v_widgetInterface = p_widget.getImplementation();
            // vérification que le widget possède bien une implémentation
            if (v_widgetInterface == null || v_widgetInterface.trim().isEmpty())
            {
               throw new IncompatibleWidgetException("Interface non définie pour le widget " + p_widget.getName());
            }
            return getPackageFromFullyQualifiedName(v_widgetInterface);
         }
      }
      catch (final IncompatibleWidgetException v_e)
      {
         return "ERREUR [ " + v_e.getMessage() + ']';
      }
   }

public String getInterfacePackageJavaService(final Widget p_widget){return getInterfacePackage(p_widget);}
   /**
    * Récupère une énumération correspondant au widget
    * @param p_widget
    *           le widget
    * @return l'énumération correspondant au widget
    * @throws IncompatibleWidgetException
    *            si le widget n'est pas compatible (null, sans nom, sans toolkit, non géré dans son toolkit)
    */
   private InterfaceToolkitWidget_Itf getWidget (final Widget p_widget) throws IncompatibleWidgetException
   {
      final Toolkit v_toolkit = p_widget.getToolkit();

      // utilisation du toolkit standard
      if (StandardToolkitWidget_Itf.c_toolkitName.equals(v_toolkit.getName()))
      {
         // on récupère l'énumération des informations liées à ce widget
         return findWidget(p_widget, StandardInterfaceToolkit_Enum.values());
      }
      else
      {
         return null;
      }
   }

}
