package fr.pacman.validation.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.dom.DOMElement;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import fr.pacman.validation.services.DslValidationRuleReferentiel_Enum;
import fr.pacman.validation.services.DslValidationRule_Itf;
import fr.pacman.validation.services.rules.DslValidationRuleBean;
import fr.pacman.validation.services.rules.ValidationLevel_Enum;

/**
 * Gestion de la configuration au format XML.
 * 
 * @author MINARM
 */
public class XmlConfigurationManager implements ConfigurationManager_Itf
{

   private static final String c_rootElementTag = "rules";

   private static final String c_ruleElementTag = "rule";

   private static final String c_idRuleAttributeName = "id";

   private static final String c_libelleRuleElementTag = "text";

   private static final String c_levelRuleElementTag = "level";

   private static final String c_activeRuleElementTag = "active";

   private static final String c_paramsRuleElementTag = "params";

   private static final String c_paramRuleElementTag = "param";

   @Override
   public List<DslValidationRule_Itf> createRulesFromFile (final File p_configFile) throws IOException
   {
      // initialisation des règles à partir du référentiel de l'énumération
      final List<DslValidationRule_Itf> v_rules = new ArrayList<DslValidationRule_Itf>(Arrays.asList(DslValidationRuleReferentiel_Enum.values()));
      final SAXReader v_reader = new SAXReader();
      try
      {
         final Document v_document = v_reader.read(p_configFile);
         final Element v_root = v_document.getRootElement();
         @SuppressWarnings("unchecked")
         final List<Element> v_rulesElements = v_root.elements(c_ruleElementTag);
         for (final Element v_ruleElement : v_rulesElements)
         {
            // lecture d'une règle
            final String v_id = v_ruleElement.attributeValue(c_idRuleAttributeName);
            final String v_libelle = v_ruleElement.elementText(c_libelleRuleElementTag);
            final String v_levelStr = v_ruleElement.elementText(c_levelRuleElementTag);
            final String v_activeStr = v_ruleElement.elementText(c_activeRuleElementTag);
            final Element v_paramsElement = v_ruleElement.element(c_paramsRuleElementTag);
            final Object[] v_params;
            if (v_paramsElement != null)
            {
               @SuppressWarnings("unchecked")
               final List<Element> v_paramElements = v_paramsElement.elements(c_paramRuleElementTag);
               v_params = new Object[v_paramElements.size()];
               int v_i = 0;
               for (final Element v_paramElement : v_paramElements)
               {
                  v_params[v_i++] = v_paramElement.getText();
               }
            }
            else
            {
               v_params = new Object[0];
            }

            // Recherche de la règle existante avec cet identifiant
            final DslValidationRule_Itf v_initialRule = findRule(v_id);
            final ValidationLevel_Enum v_level = ValidationLevel_Enum.findLevel(v_levelStr);
            final boolean v_active = Boolean.parseBoolean(v_activeStr);

            final DslValidationRule_Itf v_newRule = new DslValidationRuleBean(v_id, v_libelle, v_params, v_initialRule.get_ClassImplem(), v_initialRule.get_RuleDsl_Enum(), v_level, v_active);
            // on remplace la règle
            v_rules.remove(v_initialRule);
            v_rules.add(v_newRule);
         }
      }
      catch (DocumentException v_e)
      {
         throw new IOException("Impossible de lire le fichier de configuration de la validation", v_e);
      }
      return v_rules;
   }

   @Override
   public void writeRulesInFile (final List<? extends DslValidationRule_Itf> p_rules, final File p_configFile) throws IOException
   {
      final Document v_document = DocumentFactory.getInstance().createDocument();
      final Element v_rootElement = new DOMElement(c_rootElementTag);
      v_document.setRootElement(v_rootElement);

      for (final DslValidationRule_Itf v_rule : p_rules)
      {
         final Element v_ruleElement = new DOMElement(c_ruleElementTag);
         v_ruleElement.addAttribute(c_idRuleAttributeName, v_rule.get_id());
         v_ruleElement.addElement(c_libelleRuleElementTag).setText(v_rule.get_libRule());
         v_ruleElement.addElement(c_levelRuleElementTag).setText(v_rule.get_RuleLevel().getTitle());
         v_ruleElement.addElement(c_activeRuleElementTag).setText(Boolean.toString(v_rule.is_activateRule()));

         final Element v_paramsElement = new DOMElement(c_paramsRuleElementTag);
         if (v_rule.get_tab_paramRule() != null)
         {
            for (final Object v_param : v_rule.get_tab_paramRule())
            {
               v_paramsElement.addElement(c_paramRuleElementTag).setText(v_param.toString());
            }

            v_ruleElement.add(v_paramsElement);
         }

         v_rootElement.add(v_ruleElement);
      }
      final OutputFormat v_format = OutputFormat.createPrettyPrint();
      final FileOutputStream v_fos = new FileOutputStream(p_configFile);
      final XMLWriter v_xmlWriter = new XMLWriter(v_fos, v_format);
      v_xmlWriter.write(v_document);
      v_fos.close();
   }

   /**
    * Cherche une règle dans le référentiel des règles de validation.
    * 
    * @param p_id
    *           l'identifiant de la règle recherchée
    * @return la règle trouvée ou lance une exception si la règle n'existe pas
    */
   private DslValidationRule_Itf findRule (final String p_id)
   {
      for (DslValidationRule_Itf v_rule : DslValidationRuleReferentiel_Enum.values())
      {
         if (v_rule.get_id().equalsIgnoreCase(p_id))
         {
            return v_rule;
         }
      }
      throw new IllegalArgumentException("Règle inconnue dans le référentiel de règles : " + p_id);
   }
}
