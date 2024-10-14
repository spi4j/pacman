/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.persistence.jdbc.main;
// Start of user code for imports

import fr.spi4j.exception.Spi4jValidationException;
import fr.spi4j.persistence.entity.EntityUtil;
import fr.test.persistence.api.main.UserEntity;
import fr.test.types.enums.StatusEnum;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Entité du type User.
 * @author safr@n
 */
public class UserEntityImpl implements UserEntity
{
   // CONSTANTES

   /**
    * SerialUid.
    */
   private static final long serialVersionUID = -1;

   // Constantes UserEntityImpl
   // Start of user code 75864387ecdfb13fc59a23e4407ec552

   // End of user code

   // ATTRIBUTS

   /** Id. */
   private Long user_id;

   /** name. */
   private String name;

   /** email. */
   private String email;

   /** password. */
   private String password;


   // Attributs UserEntityImpl
   // Start of user code ad04c475cad2667fe72044475db29ff9

   // End of user code

   // METHODES

   /**
    * Constructeur sans paramètre de l'entité 'User'. <br>
    * Attention à ne pas appeler ce constructeur directement, mais à bien passer par UserPersistence.
    */
   public UserEntityImpl ()
   {
      super ();
   }

   @Override
   public Long getId ()
   {
      return user_id;
   }

   @Override
   public void setId (final Long id)
   {
      this.user_id = id;
   }

   @Override
   public String getName ()
   {
      return name;
   }

   @Override
   public void setName (final String name)
   {
      this.name = name;
   }

   @Override
   public String getEmail ()
   {
      return email;
   }

   @Override
   public void setEmail (final String email)
   {
      this.email = email;
   }

   @Override
   public String getPassword ()
   {
      return password;
   }

   @Override
   public void setPassword (final String password)
   {
      this.password = password;
   }



   @Override
   public String toString ()
   {
      return super.toString () + " : " + user_id + ", " + name + ", " + email + ", " + password;
   }

   @Override
   public void validate () throws Spi4jValidationException
   {
      List<String> champsInvalides = null;
      champsInvalides = EntityUtil.checkMandatoryField ("name", name, champsInvalides);
      champsInvalides = EntityUtil.checkMandatoryField ("email", email, champsInvalides);
      champsInvalides = EntityUtil.checkMandatoryField ("password", password, champsInvalides);
      // si la liste des champs invalides n'est pas nulle, elle ne peut pas être vide à cet endroit
      if (champsInvalides != null)
      {
         throw new Spi4jValidationException (this, champsInvalides.toArray (new String[champsInvalides.size()]));
      }
   }

   // Methodes UserEntityImpl
   // Start of user code 8f1b983e35402557e34dd91326be90a6

   // End of user code

}
