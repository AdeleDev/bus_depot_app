package company.busmanagement.api;

import company.busmanagement.busservice.api.BusesApi;
import company.busmanagement.busservice.model.AddBus201ResponseDto;
import company.busmanagement.busservice.model.BusDto;
import company.busmanagement.busservice.model.UpdateBus200ResponseDto;
import company.busmanagement.exception.BusAlreadyExistException;
import company.busmanagement.exception.BusNotExistException;
import company.busmanagement.exception.InvalidIdException;
import company.busmanagement.service.BusMngmtServiceImpl;
import company.busmanagement.service.api.BusMngmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Bus management service
 *
 * <p>This is a sample API of Bus management service.
 */
@Component
@RequestMapping("/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class BusApiImpl implements BusesApi {
    private BusMngmtService service;

    @Autowired
    public void setService(BusMngmtServiceImpl service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<AddBus201ResponseDto> addBus(@Valid BusDto bus) {
        try {
            service.addBus(bus);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (BusAlreadyExistException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        }

    }

    @Override
    public ResponseEntity<UpdateBus200ResponseDto> updateBus(@Valid BusDto bus) {
        try {
            service.updateBus(bus);
            return ResponseEntity.ok().build();
        } catch (BusNotExistException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (BusAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteBus(@NotNull Long busId) {
        try {
            service.deleteBus(busId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (BusNotExistException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<List<BusDto>> getAllBuses() {
        try {
            List<BusDto> buses = service.getAll();
            return ResponseEntity.status(HttpStatus.OK).body(buses);
        } catch (BusNotExistException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @Override
    public ResponseEntity<BusDto> getBusById(Long busId) {
        try {
            BusDto bus = service.getBusById(busId);
            return ResponseEntity.status(HttpStatus.OK).body(bus);
        } catch (BusNotExistException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (InvalidIdException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

