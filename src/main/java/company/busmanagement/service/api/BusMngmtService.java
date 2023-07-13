package company.busmanagement.service.api;

import company.busmanagement.busservice.model.BusDto;
import company.busmanagement.exception.BusAlreadyExistException;
import company.busmanagement.exception.BusNotExistException;
import company.busmanagement.exception.InvalidIdException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional

public interface BusMngmtService {

    BusDto addBus(BusDto bus) throws BusAlreadyExistException;

    BusDto updateBus(BusDto bus) throws BusNotExistException, BusAlreadyExistException;

    void deleteBus(Long busId) throws BusNotExistException;

    BusDto getBusById(Long busId) throws BusNotExistException, InvalidIdException;

    List<BusDto> getAll() throws BusNotExistException;
}
