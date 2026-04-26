package cabbooking.controller;

import cabbooking.model.Driver;
import cabbooking.repository.DriverRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverController {

    private final DriverRepository repo;

    public DriverController(DriverRepository repo) {
        this.repo = repo;
    }

    // ADD DRIVER

    @PostMapping("/add")
    public Driver addDriver(@RequestBody Driver driver) {
        driver.setAvailable(true);
        return repo.save(driver);
    }

    // GET ALL DRIVERS

    @GetMapping("/all")
    public List<Driver> getDrivers() {
        return repo.findAll();
    }

    // TOGGLE ONLINE / OFFLINE

    @PutMapping("/status")
    public Driver updateAvailability(
            @RequestParam Long id,
            @RequestParam boolean available
    ) {

        Driver driver = repo.findById(id).orElseThrow();

        driver.setAvailable(available);

        return repo.save(driver);
    }
}