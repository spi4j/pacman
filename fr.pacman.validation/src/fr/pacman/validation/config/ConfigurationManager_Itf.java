package fr.pacman.validation.config;

import java.io.File;
import java.io.IOException;
import java.util.List;

import fr.pacman.validation.services.DslValidationRule_Itf;

/**
 * Interface de gestion de configuration.
 * 
 * @author MINARM
 */
public interface ConfigurationManager_Itf
{

   /**
    * Initialisation de l'ensemble de règles à partir d'un fichier.
    * 
    * @param p_configFile
    *           le fichier à lire
    * @return l'ensemble des règles
    * @throws IOException
    *            erreur d'IO
    */
   List<? extends DslValidationRule_Itf> createRulesFromFile (final File p_configFile) throws IOException;

   /**
    * Ecriture des règles dans le fichier.
    * 
    * @param p_rules
    *           les règles à écrire
    * @param p_configFile
    *           le fichier à écrire ou modifier
    * @throws IOException
    *            erreur d'IO
    */
   void writeRulesInFile (final List<? extends DslValidationRule_Itf> p_rules, final File p_configFile) throws IOException;

}
