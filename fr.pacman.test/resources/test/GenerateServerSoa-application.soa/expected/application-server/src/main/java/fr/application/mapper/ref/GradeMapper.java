/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.mapper.ref;
// Start of user code for imports

import fr.application.business.api.ref.GradeDto;
import fr.application.ws.api.ref.GradeXto;
import fr.spi4j.business.dto.Dto_Itf;
import fr.spi4j.mapper.Mapper_Abs;
import fr.spi4j.mapper.Mapper_Itf;
import fr.spi4j.ws.xto.Xto_Itf;

// End of user code

/**
 * Implémentation pour le Mapper (= Dto <-> Xto) sur le type 'Grade'.
 * @author safr@n
 */
public class GradeMapper extends Mapper_Abs<GradeDto, GradeXto> implements GradeMapper_Itf
{

   @Override
   public GradeXto convertDtoToXto (final GradeDto p_dto)
   {
      final GradeXto v_xto = super.convertDtoToXto(p_dto);

      
      // Start of user code convertDtoToXto

      // End of user code

      return v_xto;
   }

   @Override
   public GradeDto convertXtoToDto (final GradeXto p_xto)
   {
      final GradeDto v_dto = super.convertXtoToDto(p_xto);

      
      // Start of user code convertXtoToDto

      // End of user code

      return v_dto;
   }

   @Override
   protected GradeDto getInstanceOfDto ()
   {
      return new GradeDto();
   }

   @Override
   protected GradeXto getInstanceOfXto ()
   {
      return new GradeXto();
   }

   @Override
   protected Mapper_Itf<? extends Dto_Itf<?>, ? extends Xto_Itf<?>> getSpecificMapper (final String p_fieldName)
   {
      return super.getSpecificMapper(p_fieldName);
   }

   
   // Start of user code GradeMapper

   // End of user code
}
