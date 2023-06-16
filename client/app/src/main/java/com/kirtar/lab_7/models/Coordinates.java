package com.kirtar.lab_7.models;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;  

public class Coordinates implements Serializable{
    @JsonProperty("x")
    private Float x; 
    @JsonProperty("y")
    private int y; 
    public Coordinates()
    {
        super();
    }
    public Coordinates(Float x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public Float getX()
    {
        return x;
    }
    public float getY()
    {
        return y;
    }
    public void setX(float x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    @Override 
    public String toString()
    {
    	return String.format("(x=%f,y=%d)",x,y);
    }   
    
}