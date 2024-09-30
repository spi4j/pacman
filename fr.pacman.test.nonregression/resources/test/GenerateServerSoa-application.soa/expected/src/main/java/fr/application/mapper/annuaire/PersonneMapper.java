/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.mapper.annuaire;
// Start of user code for imports

import fr.application.business.api.annuaire.PersonneDto;
import fr.application.mapper.ApplicationUserMapper;
import fr.application.ws.api.annuaire.PersonneXto;
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
 * Implémentation pour le Mapper (= Dto <-> Xto) sur le type 'Personne'.
 * @author safr@n
 */
public class PersonneMapper extends Mapper_Abs<PersonneDto, PersonneXto> implements PersonneMapper_Itf
{

   @Override
   public PersonneXto convertDtoToXto (final PersonneDto p_dto)
   {
      final PersonneXto v_xto = super.convertDtoToXto(p_dto);

      // convertDtoToXto
      // Start of user code convertDtoToXto

      // End of user code

      return v_xto;
   }

   @Override
   public PersonneDto convertXtoToDto (final PersonneXto p_xto)
   {
      final PersonneDto v_dto = super.convertXtoToDto(p_xto);

      // convertXtoToDto
      // Start of user code convertXtoToDto

      // End of user code

      return v_dto;
   }

   @Override
   protected PersonneDto getInstanceOfDto ()
   {
      return new PersonneDto();
   }

   @Override
   protected PersonneXto getInstanceOfXto ()
   {
      return new PersonneXto();
   }

   @Override
   protected Mapper_Itf<? extends Dto_Itf<?>, ? extends Xto_Itf<?>> getSpecificMapper (final String p_fieldName)
   {
      if ("_grade".equals(p_fieldName))
      {
         return ApplicationUserMapper.getGradeMapper();
      }
      if ("_marieAvec".equals(p_fieldName))
      {
         return ApplicationUserMapper.getPersonneMapper();
      }
      if ("_tab_adresses".equals(p_fieldName))
      {
         return ApplicationUserMapper.getAdresseMapper();
      }
      if ("_tab_parentDe".equals(p_fieldName))
      {
         return ApplicationUserMapper.getPersonneMapper();
      }
      if ("_tab_pays".equals(p_fieldName))
      {
         return ApplicationUserMapper.getPaysMapper();
      }
      return super.getSpecificMapper(p_fieldName);
   }

   // PersonneMapper
   // Start of user code PersonneMapper

   // End of user code
}
