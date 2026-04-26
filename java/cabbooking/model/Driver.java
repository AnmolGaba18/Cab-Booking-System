package cabbooking.model;

import jakarta.persistence.*;

@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double latitude;
    private double longitude;
    private boolean available;

    public Long getId(){ return id; }

    public String getName(){ return name; }
    public void setName(String name){ this.name=name; }

    public double getLatitude(){ return latitude; }
    public void setLatitude(double latitude){ this.latitude=latitude; }

    public double getLongitude(){ return longitude; }
    public void setLongitude(double longitude){ this.longitude=longitude; }

    public boolean isAvailable(){ return available; }
    public void setAvailable(boolean available){ this.available=available; }
}