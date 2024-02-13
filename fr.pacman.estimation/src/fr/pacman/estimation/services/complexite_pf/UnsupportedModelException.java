package fr.pacman.estimation.services.complexite_pf;

/**
 * Si une estimation n'est pas possible sur cette modélisation.
 * 
 * @author MINARM
 */
public class UnsupportedModelException extends Exception
{
   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   /** Le modèle non géré */
   private final Object _model;

   /**
    * Constructeur.
    * 
    * @param p_model
    *           Le modèle.
    */
   public UnsupportedModelException (final Object p_model)
   {
      super("Estimation non gérée sur cet objet du modèle : " + p_model);
      _model = p_model;
   }

   /**
    * Le modèle non géré.
    * 
    * @return Le modèle.
    */
   public Object get_model ()
   {
      return _model;
   }
}
