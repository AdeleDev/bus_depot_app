package company.busmanagement;


import company.busmanagement.busservice.model.BusDto;
import company.busmanagement.busservice.model.DriverDto;
import company.busmanagement.entity.BusEntity;
import company.busmanagement.exception.BusAlreadyExistException;
import company.busmanagement.repository.BusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class BusApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BusApplication.class, args);
        LOGGER.debug("Start application");
    }
}