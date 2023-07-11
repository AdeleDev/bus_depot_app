package company.busmanagement.mappers;

import company.busmanagement.busservice.model.BusDto;
import company.busmanagement.entity.BusEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BusFieldsMapper {

    BusFieldsMapper BUS_FIELDS_MAPPER = Mappers.getMapper(BusFieldsMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "number", target = "number")
    @Mapping(source = "color", target = "color")
    @Mapping(source = "peopleAmount", target = "peopleAmount")
    @Mapping(source = "maintenanceDate", target = "maintenanceDate")
    @Mapping(source = "driversInfo", target = "driversInfo")
    @Mapping(source = "trip", target = "trip")
    @Mapping(target = "createdTimestamp", ignore = true)
    @Mapping(target = "lastModifiedTimestamp", ignore = true)
    BusEntity dtoToEntity(BusDto busDto);

    @InheritInverseConfiguration
    BusDto entityToDto(BusEntity bus);

}