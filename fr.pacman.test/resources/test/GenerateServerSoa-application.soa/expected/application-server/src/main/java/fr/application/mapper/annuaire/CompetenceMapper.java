/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.mapper.annuaire;
// Start of user code for imports

import fr.application.business.api.annuaire.CompetenceDto;
import fr.application.mapper.ApplicationUserMapper;
import fr.application.ws.api.annuaire.CompetenceXto;
import fr.spi4j.business.dto.Dto_Itf;
import fr.spi4j.mapper.Mapper_Abs;
import fr.spi4j.mapper.Mapper_Itf;
import fr.spi4j.ws.xto.Xto_Itf;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Implémentation pour le Mapper (= Dto <-> Xto) sur le type 'Competence'.
 * @author safr@n
 */
public class CompetenceMapper extends Mapper_Abs<CompetenceDto, CompetenceXto> implements CompetenceMapper_Itf
{

   @Override
   public CompetenceXto convertDtoToXto (final CompetenceDto p_dto)
   {
      final CompetenceXto v_xto = super.convertDtoToXto(p_dto);

      // convertDtoToXto
      // Start of user code convertDtoToXto

      // End of user code

      return v_xto;
   }

   @Override
   public CompetenceDto convertXtoToDto (final CompetenceXto p_xto)
   {
      final CompetenceDto v_dto = super.convertXtoToDto(p_xto);

      // convertXtoToDto
      // Start of user code convertXtoToDto

      // End of user code

      return v_dto;
   }

   @Override
   protected CompetenceDto getInstanceOfDto ()
   {
      return new CompetenceDto();
   }

   @Override
   protected CompetenceXto getInstanceOfXto ()
   {
      return new CompetenceXto();
   }

   @Override
   protected Mapper_Itf<? extends Dto_Itf<?>, ? extends Xto_Itf<?>> getSpecificMapper (final String p_fieldName)
   {
      if ("_tab_dispose".equals(p_fieldName))
      {
         return ApplicationUserMapper.getPersonneMapper();
      }
      return super.getSpecificMapper(p_fieldName);
   }

   // CompetenceMapper
   // Start of user code CompetenceMapper

   // End of user code
}
