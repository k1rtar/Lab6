package com.kirtar.lab_6.models;


import java.time.ZonedDateTime;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import com.fasterxml.jackson.annotation.JsonProperty;  
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;

public class Flat implements Comparable<Flat>,Serializable
{
    
    private Long id = ++IdFlat.lastId; 
    @JsonProperty("name")
    private String name; 
    @JsonProperty("Coordinates")
    private Coordinates coordinates; 
    LocalDateTime myLocalDateTime = LocalDateTime.of(2023, Month.MARCH, 1, 20, 55, 30);
    ZoneId vnZoneId = ZoneId.of("Europe/Moscow");
    private ZonedDateTime creationDate =  ZonedDateTime.of(myLocalDateTime, vnZoneId); 
    @JsonProperty("area")
    private double area; 
    @JsonProperty("numberOfRooms")
    private int numberOfRooms; 
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private long numberOfBathrooms; 
    @JsonProperty("view")
    private View view; 
    @JsonProperty("transport")
    private Transport transport; 
    @JsonProperty("house")
    private House house; 

    public Flat()
    {
        super();
    }
    public Flat(String name, Coordinates coordinates,
    double area, int numberOfRooms, long numberOfBathrooms, View view, Transport transport, House house,ZonedDateTime creationDate)
    {
        this.name = name;
        this.coordinates = coordinates;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.view = view;
        this.transport = transport;
        this.house = house;
        this.creationDate = creationDate;
    }

    public Flat(String name, Coordinates coordinates,
    double area, int numberOfRooms, long numberOfBathrooms, View view, Transport transport, House house)
    {
        this.name = name;
        this.coordinates = coordinates;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.view = view;
        this.transport = transport;
        this.house = house;
    }
    public Long getId() 
    {
        return id; 
    }
    public String getName()
    {
        return name;
    }
    public Coordinates getCoordinates() 
    {
        return coordinates;
    }
    public ZonedDateTime getCreationDate()
    {
        return creationDate;
    }

    public double getArea()
    {
        return area;
    }

    public int getNumberOfRooms()
    {
        return numberOfRooms;
    }

    public long getNumberOfBathrooms()
    {
        return numberOfBathrooms;
    }
    public View getView()
    {
        return view;
    }
    
    public Transport getTransport()
    {
        return transport;
    }

    public House getHouse()
    {
        return house;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setCoordinates(Coordinates coordinates) 
    {
        this.coordinates = coordinates;
    }
    public void setCreationDate(ZonedDateTime creationDate)
    {
        this.creationDate = creationDate;
    }

    public void setArea(double area)
    {
        this.area = area;
    }

    public void setNumberOfRooms(int numberOfRooms)
    {
        this.numberOfRooms = numberOfRooms;
    }

    public void setNumberOfBathrooms(long numberOfBathrooms)
    {
        this.numberOfBathrooms = numberOfBathrooms;
    }
    public void setView(View view)
    {
        this.view = view;
    }
    
    public void setTransport(Transport transport)
    {
        this.transport = transport;
    }

    public void setHouse(House house)
    {
        this.house = house;
    }

    @Override
    public int compareTo(Flat flat)
    {
        return id.compareTo(flat.getId());
    }

    @Override 
    public String toString()
    {
        return String.format("Flat(id=%d,name=%s,coordinates=%s,area=%f,numberOfRooms=%d,numberOfBathrooms=%d,view=%s,transport=%s,house=(%s),creationDate = %s)",
        id,name,coordinates.toString(),area,numberOfRooms,numberOfBathrooms,view,transport,house.toString(),creationDate);
    }


}
