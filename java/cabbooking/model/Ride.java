package cabbooking.model;

import jakarta.persistence.*;

@Entity
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long driverId;

    private double pickupLatitude;
    private double pickupLongitude;

    private String status;

    private double fare;
    private double eta;

    public Long getId(){ return id; }

    public Long getUserId(){ return userId; }
    public void setUserId(Long userId){ this.userId=userId; }

    public Long getDriverId(){ return driverId; }
    public void setDriverId(Long driverId){ this.driverId=driverId; }

    public double getPickupLatitude(){ return pickupLatitude; }
    public void setPickupLatitude(double pickupLatitude){ this.pickupLatitude=pickupLatitude; }

    public double getPickupLongitude(){ return pickupLongitude; }
    public void setPickupLongitude(double pickupLongitude){ this.pickupLongitude=pickupLongitude; }

    public String getStatus(){ return status; }
    public void setStatus(String status){ this.status=status; }

    public double getFare(){ return fare; }
    public void setFare(double fare){ this.fare=fare; }

    public double getEta(){ return eta; }
    public void setEta(double eta){ this.eta=eta; }
}