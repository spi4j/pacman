package fr.pacman.estimation.services.complexite_pf;

/**
 * Les valeurs de complexités.
 * @author MINARM
 */
public enum ComplexiteEntitePfGdi_Enum
{
   faible("complexité entité faible", 7),
   moyenne("complexité entité moyenne", 10),
   elevee("complexité entité élevée", 15);

   private String _nom;
   private int _nbPf;
   /**
    * Constructeur.
    * @param p_nom Le nom de la complexité.
    * @param p_nbPf Le nombre de points de fonctions.
    */
   ComplexiteEntitePfGdi_Enum (final String p_nom, final int p_nbPf)
   {
      _nom = p_nom;
      _nbPf = p_nbPf;
   }

   /**
    * Le nom.
    * @return Le nom.
    */
   public String get_nom ()
   {
      return _nom;
   }

   /**
    * Obtenir le nom de points de fonctions.
    * @return Le nb de PF.
    */
   public int get_nbPf ()
   {
      return _nbPf;
   }

}
