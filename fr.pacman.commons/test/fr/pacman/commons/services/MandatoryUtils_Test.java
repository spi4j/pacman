// Fichier : "MandatoryUtils_Test.java"
package fr.pacman.commons.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.eclipse.emf.cdo.CDOLock;
import org.eclipse.emf.cdo.CDOObjectHistory;
import org.eclipse.emf.cdo.CDOState;
import org.eclipse.emf.cdo.common.id.CDOID;
import org.eclipse.emf.cdo.common.lock.CDOLockState;
import org.eclipse.emf.cdo.common.revision.CDORevision;
import org.eclipse.emf.cdo.common.security.CDOPermission;
import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.view.CDOView;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.api.Test;
import org.obeonetwork.dsl.environment.Behaviour;
import org.obeonetwork.dsl.environment.BindingRegistry;
import org.obeonetwork.dsl.environment.BoundableElement;
import org.obeonetwork.dsl.environment.MetaDataContainer;
import org.obeonetwork.dsl.environment.MultiplicityKind;

/**
 * @author salle_formation
 * 
 */
public class MandatoryUtils_Test
{

   /**
    * Test 'Attribute' en multiplicité "1" : obligatoire.
    */
   @Test
   public void isMandatoryEntityAttribute_CN1 ()
   {
      // Définir une 'org.obeonetwork.dsl.entity.Attribute'
      final org.obeonetwork.dsl.environment.Attribute v_Attribute = new MyAttributeEntityImpl();
      // Définir une multiplicité "1"
      v_Attribute.setMultiplicity(MultiplicityKind.ONE_LITERAL);
      // Appeler la méthode testée
      final boolean v_obtenu = MandatoryUtils.isMandatoryEntity(v_Attribute);
      // Vérifier que l'attribut est obligatoire
      assertTrue(v_obtenu == true, "L'attribut devrait être obligatoire");
   }

   /**
    * Test 'Attribute' en multiplicité "1..*" : obligatoire.
    */
   @Test
   public void isMandatoryEntityAttribute_CN2 ()
   {
      // Définir une 'org.obeonetwork.dsl.entity.Attribute'
      final org.obeonetwork.dsl.environment.Attribute v_Attribute = new MyAttributeEntityImpl();
      // Définir une multiplicité "1..*"
      v_Attribute.setMultiplicity(MultiplicityKind.ONE_STAR_LITERAL);
      // Appeler la méthode testée
      final boolean v_obtenu = MandatoryUtils.isMandatoryEntity(v_Attribute);
      // Vérifier que l'attribut est obligatoire
      assertTrue(v_obtenu == true, "L'attribut devrait être obligatoire");
   }

   /**
    * Test 'Attribute' en multiplicité "0..1" : facultatif.
    */
   @Test
   public void isMandatoryEntityAttribute_CN3 ()
   {
      // Définir une 'org.obeonetwork.dsl.entity.Attribute'
      final org.obeonetwork.dsl.environment.Attribute v_Attribute = new MyAttributeEntityImpl();
      // Définir une multiplicité "0..1"
      v_Attribute.setMultiplicity(MultiplicityKind.ZERO_ONE_LITERAL);
      // Appeler la méthode testée
      final boolean v_obtenu = MandatoryUtils.isMandatoryEntity(v_Attribute);
      // Vérifier que l'attribut est facultatif
      assertTrue(v_obtenu == false, "L'attribut devrait être facultatif");
   }

   /**
    * Test 'Attribute' en multiplicité "0..*" : facultatif.
    */
   @Test
   public void isMandatoryEntityAttribute_CN4 ()
   {
      // Définir une 'org.obeonetwork.dsl.entity.Attribute'
      final org.obeonetwork.dsl.environment.Attribute v_Attribute = new MyAttributeEntityImpl();
      // Définir une multiplicité "0..*"
      v_Attribute.setMultiplicity(MultiplicityKind.ZERO_STAR_LITERAL);
      // Appeler la méthode testée
      final boolean v_obtenu = MandatoryUtils.isMandatoryEntity(v_Attribute);
      // Vérifier que l'attribut est facultatif
      assertTrue(v_obtenu == false, "L'attribut devrait être facultatif");
   }

   /**
    * Test 'Reference' en multiplicité "0..1" sans relation opposée : obligatoire.
    */
   @Test
   public void isMandatoryEntityReference_CN1 ()
   {
      // Définir une 'org.obeonetwork.dsl.entity.Reference'
      final org.obeonetwork.dsl.environment.Reference v_Reference = new MyReferenceEntityImpl();
      // Définir une multiplicité "1"
      v_Reference.setMultiplicity(MultiplicityKind.ONE_LITERAL);
      // Appeler la méthode testée
      final boolean v_obtenu = MandatoryUtils.isMandatoryEntity(v_Reference);
      // Vérifier que la référence est obligatoire
      assertTrue(v_obtenu == true, "La référence devrait être obligatoire");
   }

   /**
    * Test 'Reference' en multiplicité "1..*" sans relation opposée : obligatoire.
    */
   @Test
   public void isMandatoryEntityReference_CN2 ()
   {
      // Définir une 'org.obeonetwork.dsl.entity.Reference'
      final org.obeonetwork.dsl.environment.Reference v_Reference = new MyReferenceEntityImpl();
      // Définir une multiplicité "1"
      v_Reference.setMultiplicity(MultiplicityKind.ONE_STAR_LITERAL);
      // Appeler la méthode testée
      final boolean v_obtenu = MandatoryUtils.isMandatoryEntity(v_Reference);
      // Vérifier que la référence est obligatoire
      assertTrue(v_obtenu == true, "La référence devrait être obligatoire");
   }
   
   /**
    * Test 'Reference' en multiplicité "0..*" sans relation opposée : obligatoire. Exemple : Cas : Homme ---0..*-> Enfant Enfant connaîtra toujours son père.
    */
   @Test
   public void isMandatoryEntityReference_CN3 ()
   {
      // Définir une 'org.obeonetwork.dsl.entity.Attribute'
      final org.obeonetwork.dsl.environment.Reference v_Reference = new MyReferenceEntityImpl();
      // Définir une multiplicité "1"
      v_Reference.setMultiplicity(MultiplicityKind.ZERO_STAR_LITERAL);
      // Appeler la méthode testée
      final boolean v_obtenu = MandatoryUtils.isMandatoryEntity(v_Reference);
      // Vérifier que la référence est obligatoire
      assertTrue(v_obtenu == true, "La référence devrait être obligatoire");
   }

   /**
    * Test de cas non prévu.
    */
   @Test
   public void isMandatoryEntity_CE ()
   {
      boolean v_errorGenerated = false;
      // Définir une Property non gérée par la méthode 'isMandatoryEntity'
      final org.obeonetwork.dsl.environment.Property v_Reference = new PropertyBad();
      // Définir une multiplicité "1"
      v_Reference.setMultiplicity(MultiplicityKind.ZERO_STAR_LITERAL);

      try
      {
         // Appeler la méthode testée
         MandatoryUtils.isMandatoryEntity(v_Reference);
      }
      catch (final IllegalArgumentException v_err)
      {
         assertTrue(v_err.getMessage().startsWith("Cas non prévu pour p_property=") == true,
                  "Le message d'erreur n'est pas conforme");
         v_errorGenerated = true;
      }
      
      // Cette assertion permet d'utiliser Junit 5 avec Java 7 (pas de lambda).
      assertTrue(v_errorGenerated == true, "Une erreur ErrorGenerationException aurait due être générée.");
   }

   /**
    * Test 'Reference' en multiplicité "0..*" avec relation opposée obligatoire : obligatoire.
    */
   @Test
   public void isMandatoryEntityReference_CN4 ()
   {
      // Définir une 'org.obeonetwork.dsl.entity.Attribute'
      final org.obeonetwork.dsl.environment.Reference v_Reference = new MyReferenceEntityImpl();
      // Définir une multiplicité "1"
      v_Reference.setMultiplicity(MultiplicityKind.ZERO_STAR_LITERAL);
      // Définir une relation opposée
      final org.obeonetwork.dsl.environment.Reference v_ReferenceOpposite = new MyReferenceEntityImpl();
      // Définir une multiplicité "1"
      v_ReferenceOpposite.setMultiplicity(MultiplicityKind.ONE_LITERAL);
      v_Reference.setOppositeOf(v_ReferenceOpposite);
      // Appeler la méthode testée
      final boolean v_obtenu = MandatoryUtils.isMandatoryEntity(v_Reference);
      // Vérifier que la référence est obligatoire
      assertTrue(v_obtenu == true, "La référence devrait être obligatoire");
   }

   /**
    * Test 'Reference' en multiplicité "0..*" avec relation opposée facultative : facultatif.
    */
   @Test
   public void isMandatoryEntityReference_CN5 ()
   {
      // Définir une 'org.obeonetwork.dsl.entity.Attribute'
      final org.obeonetwork.dsl.environment.Reference v_Reference = new MyReferenceEntityImpl();
      // Définir une multiplicité "1"
      v_Reference.setMultiplicity(MultiplicityKind.ZERO_STAR_LITERAL);
      // Définir une relation opposée
      final org.obeonetwork.dsl.environment.Reference v_ReferenceOpposite = new MyReferenceEntityImpl();
      // Définir une multiplicité "0..1"
      v_ReferenceOpposite.setMultiplicity(MultiplicityKind.ZERO_ONE_LITERAL);
      v_Reference.setOppositeOf(v_ReferenceOpposite);
      // Appeler la méthode testée
      final boolean v_obtenu = MandatoryUtils.isMandatoryEntity(v_Reference);
      // Vérifier que la référence est facultative
      assertTrue(v_obtenu == false, "La référence devrait être facultative");
   }

   /**
    * Test 'Attribute' en multiplicité "1" .
    */
   @Test
   public void isMandatorySoaAttribute_CN1 ()
   {
      // Définir une 'org.obeonetwork.dsl.environment.Attribute'
      final org.obeonetwork.dsl.environment.Attribute v_Attribute = new MyAttributeSoaImpl();
      // Définir une multiplicité "1"
      v_Attribute.setMultiplicity(MultiplicityKind.ONE_LITERAL);
      // Appeler la méthode testée
      final boolean v_obtenu = MandatoryUtils.isMandatorySoa(v_Attribute);
      // Vérifier que l'attribut est obligatoire
      assertTrue(v_obtenu == true, "L'attribut devrait être obligatoire");
   }

   /**
    * Test 'Attribute' en multiplicité "1..*" .
    */
   @Test
   public void isMandatorySoaAttribute_CN2 ()
   {
      // Définir une 'org.obeonetwork.dsl.environment.Attribute'
      final org.obeonetwork.dsl.environment.Attribute v_Attribute = new MyAttributeSoaImpl();
      // Définir une multiplicité "1..*"
      v_Attribute.setMultiplicity(MultiplicityKind.ONE_STAR_LITERAL);
      // Appeler la méthode testée
      final boolean v_obtenu = MandatoryUtils.isMandatorySoa(v_Attribute);
      // Vérifier que l'attribut est obligatoire
      assertTrue(v_obtenu == true, "L'attribut devrait être obligatoire");
   }

   /**
    * Test 'Attribute' en multiplicité "0..1".
    */
   @Test
   public void isMandatorySoaAttribute_CN3 ()
   {
      // Définir une 'org.obeonetwork.dsl.environment.Attribute'
      final org.obeonetwork.dsl.environment.Attribute v_Attribute = new MyAttributeSoaImpl();
      // Définir une multiplicité "0..1"
      v_Attribute.setMultiplicity(MultiplicityKind.ZERO_ONE_LITERAL);
      // Appeler la méthode testée
      final boolean v_obtenu = MandatoryUtils.isMandatorySoa(v_Attribute);
      // Vérifier que l'attribut est obligatoire
      assertTrue(v_obtenu == false, "L'attribut devrait être falcultatif");
   }

   /**
    * Test 'Attribute' en multiplicité "0..*".
    */
   @Test
   public void isMandatorySoaAttribute_CN4 ()
   {
      // Définir une 'org.obeonetwork.dsl.environment.Attribute'
      final org.obeonetwork.dsl.environment.Attribute v_Attribute = new MyAttributeSoaImpl();
      // Définir une multiplicité "0..*"
      v_Attribute.setMultiplicity(MultiplicityKind.ZERO_STAR_LITERAL);
      // Appeler la méthode testée
      final boolean v_obtenu = MandatoryUtils.isMandatorySoa(v_Attribute);
      // Vérifier que l'attribut est obligatoire
      assertTrue(v_obtenu == false, "L'attribut devrait être falcultatif");
   }

}

/**
 * Implémention d'attribut d'Entité
 * @author salle_formation
 */
class MyAttributeEntityImpl extends org.obeonetwork.dsl.environment.impl.AttributeImpl
{

}

/**
 * Implémention de référence d'Entité
 * @author salle_formation
 */
class MyReferenceEntityImpl extends org.obeonetwork.dsl.environment.impl.ReferenceImpl
{

}

/**
 * Implémention d'attribut SOA
 * @author salle_formation
 */
class MyAttributeSoaImpl extends org.obeonetwork.dsl.environment.impl.AttributeImpl
{

}

/**
 * Propriété non gérée par la méthode.
 */
class PropertyBad implements org.obeonetwork.dsl.environment.Property
{

   @Override
   public EList<Behaviour> getBehaviours ()
   {
      // RAS
      return null;
   }

   @Override
   public EList<BindingRegistry> getBindingRegistries ()
   {
      // RAS
      return null;
   }

   @Override
   public String getDescription ()
   {
      // RAS
      return null;
   }

   @Override
   public EList<String> getKeywords ()
   {
      // RAS
      return null;
   }

   @Override
   public MetaDataContainer getMetadatas ()
   {
      // RAS
      return null;
   }

   @Override
   public void setDescription (final String p_arg0)
   {
      // RAS
   }

   @Override
   public void setMetadatas (final MetaDataContainer p_arg0)
   {
      // RAS
   }

   @Override
   public EClass eClass ()
   {
      // RAS
      return null;
   }

   @Override
   public Resource eResource ()
   {
      // RAS
      return null;
   }

   @Override
   public EObject eContainer ()
   {
      // RAS
      return null;
   }

   @Override
   public EStructuralFeature eContainingFeature ()
   {
      // RAS
      return null;
   }

   @Override
   public EReference eContainmentFeature ()
   {
      // RAS
      return null;
   }

   @Override
   public EList<EObject> eContents ()
   {
      // RAS
      return null;
   }

   @Override
   public TreeIterator<EObject> eAllContents ()
   {
      // RAS
      return null;
   }

   @Override
   public boolean eIsProxy ()
   {
      // RAS
      return false;
   }

   @Override
   public EList<EObject> eCrossReferences ()
   {
      // RAS
      return null;
   }

   @Override
   public Object eGet (final EStructuralFeature p_feature)
   {
      // RAS
      return null;
   }

   @Override
   public Object eGet (final EStructuralFeature p_feature, final boolean p_resolve)
   {
      // RAS
      return null;
   }

   @Override
   public void eSet (final EStructuralFeature p_feature, final Object p_newValue)
   {
      // RAS
   }

   @Override
   public boolean eIsSet (final EStructuralFeature p_feature)
   {
      // RAS
      return false;
   }

   @Override
   public void eUnset (final EStructuralFeature p_feature)
   {
      // RAS
   }

   @Override
   public Object eInvoke (final EOperation p_operation, final EList<?> p_arguments) throws InvocationTargetException
   {
      // RAS
      return null;
   }

   @Override
   public EList<Adapter> eAdapters ()
   {
      // RAS
      return null;
   }

   @Override
   public boolean eDeliver ()
   {
      // RAS
      return false;
   }

   @Override
   public void eSetDeliver (final boolean p_deliver)
   {
      // RAS
   }

   @Override
   public void eNotify (final Notification p_notification)
   {
      // RAS
   }

   @Override
   public boolean isPathValid (final BoundableElement p_arg0, final String p_arg1)
   {
      // RAS
      return false;
   }

   @Override
   public MultiplicityKind getMultiplicity ()
   {
      // RAS
      return null;
   }

   @Override
   public String getName ()
   {
      // RAS
      return null;
   }

   // @Override
   // public boolean isIsPrimaryKey ()
   // {
   // // RAS
   // return false;
   // }
   //
   // @Override
   // public void setIsPrimaryKey (final boolean p_arg0)
   // {
   // // RAS
   // }

   @Override
   public void setMultiplicity (final MultiplicityKind p_arg0)
   {
      // RAS
   }

   @Override
   public void setName (final String p_arg0)
   {
      // RAS
   }

   @Override
   public boolean cdoConflict ()
   {
      // RAS
      return false;
   }

   @Override
   public CDOResource cdoDirectResource ()
   {
      // RAS
      return null;
   }

   @Override
   public CDOID cdoID ()
   {
      // RAS
      return null;
   }

   @Override
   public boolean cdoInvalid ()
   {
      // RAS
      return false;
   }

   @Override
   public void cdoPrefetch (final int p_arg0)
   {
      // RAS

   }

   @Override
   public CDOLock cdoReadLock ()
   {
      // RAS
      return null;
   }

   @Override
   public void cdoReload ()
   {
      // RAS

   }

   @Override
   public CDOResource cdoResource ()
   {
      // RAS
      return null;
   }

   @Override
   public CDORevision cdoRevision ()
   {
      // RAS
      return null;
   }

   @Override
   public CDOState cdoState ()
   {
      // RAS
      return null;
   }

   @Override
   public CDOView cdoView ()
   {
      // RAS
      return null;
   }

   @Override
   public CDOLock cdoWriteLock ()
   {
      // RAS
      return null;
   }

   @Override
   public Date getCreatedOn ()
   {
      // RAS
      return null;
   }

   @Override
   public Date getModifiedOn ()
   {
      // RAS
      return null;
   }

   @Override
   public int getVersion ()
   {
      // RAS
      return 0;
   }

   @Override
   public void setCreatedOn (final Date p_arg0)
   {
      // RAS
   }

   @Override
   public void setModifiedOn (final Date p_arg0)
   {
      // RAS
   }

   @Override
   public void setVersion (final int p_arg0)
   {
      // RAS
   }

   @Override
   public CDOObjectHistory cdoHistory ()
   {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public CDOLockState cdoLockState ()
   {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public CDOPermission cdoPermission ()
   {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public CDORevision cdoRevision (final boolean p_arg0)
   {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public CDOLock cdoWriteOption ()
   {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public boolean isIsIdentifier ()
   {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public void setIsIdentifier (final boolean p_arg0)
   {
      // TODO Auto-generated method stub

   }

   @Override
   public String getTechnicalid ()
   {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void setTechnicalid (final String p_arg0)
   {
      // TODO Auto-generated method stub

   }

}
