package company.busmanagement.service;

import company.busmanagement.busservice.model.BusDto;
import company.busmanagement.entity.BusEntity;
import company.busmanagement.exception.BusAlreadyExistException;
import company.busmanagement.exception.BusNotExistException;
import company.busmanagement.exception.InvalidIdException;
import company.busmanagement.repository.BusRepository;
import company.busmanagement.service.api.BusMngmtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static company.busmanagement.mappers.BusFieldsMapper.BUS_FIELDS_MAPPER;


@Component
@Transactional
public class BusMngmtServiceImpl implements BusMngmtService {
    private final static Logger LOGGER = LoggerFactory.getLogger(BusMngmtService.class);

    private BusRepository busRepository;

    private static final String INVALID_YEAR_MESSAGE = "Years before 2010 are not supported";


    @Override
    public BusDto addBus(BusDto bus) throws BusAlreadyExistException {
        LOGGER.info("Got create request for bus with registration number" + bus.getNumber());

        List<BusEntity> busesEntities = busRepository.findByNumber(bus.getNumber());
        if (bus.getMaintenanceDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() < 2010) {
            throw new ValidationException(INVALID_YEAR_MESSAGE);
        }
        if (!busesEntities.isEmpty()) {
            throw new BusAlreadyExistException(bus.getNumber());
        }
        BusEntity busEntity = BUS_FIELDS_MAPPER.dtoToEntity(bus);
        busEntity.setCreatedTimestamp(OffsetDateTime.now());
        busEntity.setLastModifiedTimestamp(OffsetDateTime.now());
        busEntity = busRepository.save(busEntity);
        bus = BUS_FIELDS_MAPPER.entityToDto(busEntity);

        LOGGER.info("Bus with registration number " + bus.getNumber() + " was created");

        return bus;
    }

    @Override
    public BusDto updateBus(BusDto bus) throws BusNotExistException {
        LOGGER.info("Got update request for bus with registration number" + bus.getNumber());
        Long busId = bus.getId();

        if (busRepository.findById(busId).isEmpty()) {
            throw new BusNotExistException();
        }
        if (bus.getMaintenanceDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() < 2010) {
            throw new ValidationException(INVALID_YEAR_MESSAGE);
        }
        BusEntity busEntity = BUS_FIELDS_MAPPER.dtoToEntity(bus);
        busEntity.setLastModifiedTimestamp(OffsetDateTime.now());
        busEntity = busRepository.save(busEntity);
        bus = BUS_FIELDS_MAPPER.entityToDto(busEntity);

        LOGGER.info("Bus with id  = " + busId + " and registration number" + bus.getNumber() + " was updated");

        return bus;
    }

    @Override
    public void deleteBus(Long busId) throws BusNotExistException {
        LOGGER.info("Got delete request for bus with id = " + busId);
        if (busRepository.findById(busId).isEmpty()) {
            throw new BusNotExistException();
        }
        BusEntity busEntity = busRepository.findById(busId).get();
        busRepository.delete(busEntity);

        LOGGER.info("Bus with id  = " + busId + " and registration number" + busEntity.getNumber() + " was deleted");

    }

    @Override
    public BusDto getBusById(Long busId) throws BusNotExistException, InvalidIdException {
        LOGGER.info("Got get request for bus for bus with id = " + busId);

        if (busId <= 0) {
            throw new InvalidIdException();
        }
        if (busRepository.findById(busId).isEmpty()) {
            throw new BusNotExistException();
        }

        BusEntity busEntity = busRepository.findById(busId).get();
        return BUS_FIELDS_MAPPER.entityToDto(busEntity);
    }

    @Override
    public List<BusDto> findByPeopleAmount(Long amount) {
        List<BusEntity> busesEntities = busRepository.findByPeopleAmount(amount);
        List<BusDto> busesDto = new ArrayList<>();
        if (!busesEntities.isEmpty()) {
            LOGGER.info("Found " + busesEntities.size() + " buses with amount of passengers = " + amount);
            for (BusEntity bus : busesEntities) {
                LOGGER.debug("Bus with id = " + bus.getId() + " and number = " + bus.getNumber());
                busesDto.add(BUS_FIELDS_MAPPER.entityToDto(bus));
            }
        } else {
            LOGGER.info("No buses were found by set amount of passengers");
        }
        return busesDto;
    }


    @Override
    public List<BusDto> findByTrip(String location) {
        List<BusEntity> busesEntities = busRepository.findByTripContains(location);
        List<BusDto> busesDto = new ArrayList<>();
        if (!busesEntities.isEmpty()) {
            LOGGER.info("Found " + busesEntities.size() + " buses that passes location = " + location);
            for (BusEntity bus : busesEntities) {
                LOGGER.debug("Bus with id = " + bus.getId() + " and number = " + bus.getNumber());
                busesDto.add(BUS_FIELDS_MAPPER.entityToDto(bus));
            }
        } else {
            LOGGER.info("No buses were found to follow chosen location");
        }
        return busesDto;
    }


}
