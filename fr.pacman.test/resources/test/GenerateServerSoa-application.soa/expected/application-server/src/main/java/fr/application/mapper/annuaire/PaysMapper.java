/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.mapper.annuaire;
// Start of user code for imports

import fr.application.business.api.annuaire.PaysDto;
import fr.application.ws.api.annuaire.PaysXto;
import fr.spi4j.business.dto.Dto_Itf;
import fr.spi4j.mapper.Mapper_Abs;
import fr.spi4j.mapper.Mapper_Itf;
import fr.spi4j.ws.xto.Xto_Itf;

// End of user code

/**
 * Implémentation pour le Mapper (= Dto <-> Xto) sur le type 'Pays'.
 * @author safr@n
 */
public class PaysMapper extends Mapper_Abs<PaysDto, PaysXto> implements PaysMapper_Itf
{

   @Override
   public PaysXto convertDtoToXto (final PaysDto p_dto)
   {
      final PaysXto v_xto = super.convertDtoToXto(p_dto);

      
      // Start of user code convertDtoToXto

      // End of user code

      return v_xto;
   }

   @Override
   public PaysDto convertXtoToDto (final PaysXto p_xto)
   {
      final PaysDto v_dto = super.convertXtoToDto(p_xto);

      
      // Start of user code convertXtoToDto

      // End of user code

      return v_dto;
   }

   @Override
   protected PaysDto getInstanceOfDto ()
   {
      return new PaysDto();
   }

   @Override
   protected PaysXto getInstanceOfXto ()
   {
      return new PaysXto();
   }

   @Override
   protected Mapper_Itf<? extends Dto_Itf<?>, ? extends Xto_Itf<?>> getSpecificMapper (final String p_fieldName)
   {
      return super.getSpecificMapper(p_fieldName);
   }

   
   // Start of user code PaysMapper

   // End of user code
}
