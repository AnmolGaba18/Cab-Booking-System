package cabbooking.service;

import cabbooking.model.Driver;
import cabbooking.model.Ride;
import cabbooking.model.User;
import cabbooking.repository.DriverRepository;
import cabbooking.repository.RideRepository;
import cabbooking.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {

    private final DriverRepository driverRepo;
    private final RideRepository rideRepo;
    private final UserRepository userRepo;

    public RideService(DriverRepository d,
                       RideRepository r,
                       UserRepository u){

        driverRepo=d;
        rideRepo=r;
        userRepo=u;
    }

    public Driver findNearestDriver(double lat, double lng) {

        List<Driver> drivers = driverRepo.findByAvailableTrue();

        if (drivers.isEmpty()) return null;

        Driver nearest = null;
        double minDistance = Double.MAX_VALUE;

        for (Driver d : drivers) {

            double distance = haversine(
                    lat,
                    lng,
                    d.getLatitude(),
                    d.getLongitude()
            );

            if (distance < minDistance) {
                minDistance = distance;
                nearest = d;
            }
        }

        return nearest;
    }

    public Ride requestRide(Long userId,
                            double lat,
                            double lng){

        Driver driver=findNearestDriver(lat,lng);

        User user=userRepo.findById(userId).get();

        user.setLatitude(lat);
        user.setLongitude(lng);

        userRepo.save(user);

        double distance=haversine(lat,lng,
                driver.getLatitude(),
                driver.getLongitude());

        double fare=40+(distance*12);

        double eta=(distance/30)*60;

        Ride ride=new Ride();

        ride.setUserId(userId);
        ride.setDriverId(driver.getId());
        ride.setPickupLatitude(lat);
        ride.setPickupLongitude(lng);
        ride.setStatus("ASSIGNED");
        ride.setFare(fare);
        ride.setEta(eta);

        return rideRepo.save(ride);
    }

    private double haversine(double lat1,double lon1,
                             double lat2,double lon2){

        final int R=6371;

        double dLat=Math.toRadians(lat2-lat1);
        double dLon=Math.toRadians(lon2-lon1);

        double a=Math.sin(dLat/2)*Math.sin(dLat/2)
                +Math.cos(Math.toRadians(lat1))
                *Math.cos(Math.toRadians(lat2))
                *Math.sin(dLon/2)*Math.sin(dLon/2);

        double c=2*Math.atan2(Math.sqrt(a),Math.sqrt(1-a));

        return R*c;
    }
}