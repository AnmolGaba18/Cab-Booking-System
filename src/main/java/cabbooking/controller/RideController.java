package cabbooking.controller;

import cabbooking.model.Driver;
import cabbooking.model.Ride;
import cabbooking.repository.RideRepository;
import cabbooking.service.RideService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ride")
public class RideController {

    private final RideService service;
    private final RideRepository repo;

    public RideController(RideService s,
                          RideRepository r){

        service=s;
        repo=r;
    }

    @GetMapping("/nearest-driver")
    public Driver nearestDriver(
            @RequestParam double lat,
            @RequestParam double lng){

        return service.findNearestDriver(lat,lng);
    }

    @PostMapping("/request")
    public Ride requestRide(
            @RequestParam Long userId,
            @RequestParam double lat,
            @RequestParam double lng){

        return service.requestRide(userId,lat,lng);
    }

    @GetMapping("/all")
    public List<Ride> getAllRides(){
        return repo.findAll();
    }
}