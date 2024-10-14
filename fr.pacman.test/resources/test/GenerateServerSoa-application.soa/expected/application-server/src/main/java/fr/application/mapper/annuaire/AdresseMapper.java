/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.mapper.annuaire;
// Start of user code for imports

import fr.application.business.api.annuaire.AdresseDto;
import fr.application.ws.api.annuaire.AdresseXto;
import fr.spi4j.business.dto.Dto_Itf;
import fr.spi4j.mapper.Mapper_Abs;
import fr.spi4j.mapper.Mapper_Itf;
import fr.spi4j.ws.xto.Xto_Itf;
import java.util.Date;

// End of user code

/**
 * Implémentation pour le Mapper (= Dto <-> Xto) sur le type 'Adresse'.
 * @author safr@n
 */
public class AdresseMapper extends Mapper_Abs<AdresseDto, AdresseXto> implements AdresseMapper_Itf
{

   @Override
   public AdresseXto convertDtoToXto (final AdresseDto p_dto)
   {
      final AdresseXto v_xto = super.convertDtoToXto(p_dto);

      // convertDtoToXto
      // Start of user code convertDtoToXto

      // End of user code

      return v_xto;
   }

   @Override
   public AdresseDto convertXtoToDto (final AdresseXto p_xto)
   {
      final AdresseDto v_dto = super.convertXtoToDto(p_xto);

      // convertXtoToDto
      // Start of user code convertXtoToDto

      // End of user code

      return v_dto;
   }

   @Override
   protected AdresseDto getInstanceOfDto ()
   {
      return new AdresseDto();
   }

   @Override
   protected AdresseXto getInstanceOfXto ()
   {
      return new AdresseXto();
   }

   @Override
   protected Mapper_Itf<? extends Dto_Itf<?>, ? extends Xto_Itf<?>> getSpecificMapper (final String p_fieldName)
   {
      return super.getSpecificMapper(p_fieldName);
   }

   // AdresseMapper
   // Start of user code AdresseMapper

   // End of user code
}
