package fr.pacman.estimation.services.complexite_pf;

/**
 * Si une estimation n'est pas fiable.
 * @author MINARM
 */
public class NotReliableException
   extends Exception
{
   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   /** La valeur en points de fonctions */
   private final double _valeurPf;

   /**
    * Constructeur.
    * @param p_valeurPf La valeur de points de fonctions.
    */
   public NotReliableException (final double p_valeurPf)
   {
      super("La valeur obtenue n'est pas fiable car inférieure à 300 : " + p_valeurPf);
      _valeurPf = p_valeurPf;
   }

   /**
    * La valeur en points de fonctions.
    * @return La valeur spécifiée.
    */
   public double get_valeurPf ()
   {
      return _valeurPf;
   }

}
