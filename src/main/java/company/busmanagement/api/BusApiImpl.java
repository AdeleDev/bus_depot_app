package company.busmanagement.api;

import company.busmanagement.busservice.api.BusesApi;
import company.busmanagement.busservice.model.AddBus201ResponseDto;
import company.busmanagement.busservice.model.BusDto;
import company.busmanagement.busservice.model.UpdateBus200ResponseDto;
import company.busmanagement.exception.BusAlreadyExistException;
import company.busmanagement.exception.BusNotExistException;
import company.busmanagement.service.BusMngmtServiceImpl;
import company.busmanagement.service.api.BusMngmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Bus management service
 *
 * <p>This is a sample API of Bus management service.
 */
@Component
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
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
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
    public ResponseEntity<List<BusDto>> findByPeopleAmount(@NotNull Long amount) {
        try {
            List<BusDto> buses = service.findByPeopleAmount(amount);
            if(!buses.isEmpty()) {
                return ResponseEntity.ok().body(buses);
            }
            else {
                return ResponseEntity.noContent().build();
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public ResponseEntity<List<BusDto>> findByTrip(@NotNull @Size(min = 1) String location) {
        try {
            List<BusDto> buses = service.findByTrip(location);
            if(!buses.isEmpty()) {
                return ResponseEntity.ok(buses);
            }
            else {
                return ResponseEntity.noContent().build();
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

