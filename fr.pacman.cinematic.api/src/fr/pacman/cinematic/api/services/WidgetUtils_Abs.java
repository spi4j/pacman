package fr.pacman.cinematic.api.services;

import org.obeonetwork.dsl.cinematic.toolkits.Toolkit;
import org.obeonetwork.dsl.cinematic.toolkits.Widget;

/**
 * Interface de gestion des widgets.
 * @author MINARM
 */
public abstract class WidgetUtils_Abs
{

   /**
    * Vérifie que le widget est valide.
    * @param p_widget
    *           le widget
    * @throws IncompatibleWidgetException
    *            widget incompatible pour la génération
    */
   protected void checkWidget (final Widget p_widget) throws IncompatibleWidgetException
   {
      // si le widget est null
      if (p_widget == null)
      {
         throw new IncompatibleWidgetException("Widget null");
      }

      // si le widget n'a pas de nom
      if (p_widget.getName() == null || p_widget.getName().trim().isEmpty())
      {
         throw new IncompatibleWidgetException("Widget sans nom");
      }

      final Toolkit v_toolkit = p_widget.getToolkit();
      // si le toolkit est null
      if (v_toolkit == null)
      {
         throw new IncompatibleWidgetException("Toolkit null pour widget " + p_widget.getName());
      }
   }

   /**
    * Cherche un widget dans un ensemble de widgets (par son nom)
    * @param <T>
    *           le type de widget recherché
    * @param p_widget
    *           le widget cherché
    * @param p_widgets
    *           l'ensemble de widgets dans lesquels chercher
    * @return l'énumération du widget trouvé
    * @throws IncompatibleWidgetException
    *            si le widget n'a pas été trouvé
    */
   protected <T extends ToolkitWidget_Itf> T findWidget (final Widget p_widget, final T[] p_widgets)
            throws IncompatibleWidgetException
   {
      // vérification du widget
      checkWidget(p_widget);

      // parcours des widgets disponibles
      for (final T v_widget : p_widgets)
      {
         if (p_widget.getName().equals(v_widget.getName()))
         {
            return v_widget;
         }
      }

      // construction du message d'erreur si le widget n'a pas été trouvé
      final String v_toolkitName;
      if (p_widgets == null || p_widgets.length == 0)
      {
         v_toolkitName = "";
      }
      else
      {
         v_toolkitName = p_widgets[0].getToolkitName();
      }
      throw new IncompatibleWidgetException("Widget inconnu dans le toolkit " + v_toolkitName + " ("
               + p_widget.getName() + ')');
   }

   /**
    * Retourne le nom simple de la classe à partir du nom complet
    * @param p_fullyQualifiedClassName le nom complet de la classe (avec surement un package)
    * @return le nom simple de la classe
    */
   protected String getClassNameFromFullyQualifiedName (final String p_fullyQualifiedClassName)
   {
      // si l'implémentation est sous forme de package
      if (p_fullyQualifiedClassName.contains(".") && p_fullyQualifiedClassName.charAt(p_fullyQualifiedClassName.length() - 1) != '.')
      {
         return p_fullyQualifiedClassName.substring(p_fullyQualifiedClassName.lastIndexOf('.') + 1);
      }
      else
      {
         return p_fullyQualifiedClassName;
      }
   }

   /**
    * Retourne le package de la classe à partir du nom complet
    * @param p_fullyQualifiedClassName le nom complet de la classe (avec surement un package)
    * @return le nom du package de la classe s'il est indiqué, une chaine vide sinon
    */
   protected String getPackageFromFullyQualifiedName (final String p_fullyQualifiedClassName)
   {
      // si l'implémentation est sous forme de package
      if (p_fullyQualifiedClassName.contains(".") && p_fullyQualifiedClassName.charAt(p_fullyQualifiedClassName.length() - 1) != '.')
      {
         return p_fullyQualifiedClassName.substring(0, p_fullyQualifiedClassName.lastIndexOf('.'));
      }
      else
      {
         return "";
      }
   }

}
