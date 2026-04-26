package cabbooking.service;

import cabbooking.model.Driver;
import cabbooking.repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository){
        this.driverRepository = driverRepository;
    }

    public Driver saveDriver(Driver driver){
        return driverRepository.save(driver);
    }

    public List<Driver> getAllDrivers(){
        return driverRepository.findAll();
    }
}