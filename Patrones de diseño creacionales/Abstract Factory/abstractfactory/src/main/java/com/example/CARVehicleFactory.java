package com.example;

public class CARVehicleFactory extends VehicleFactory {

  public LuxuryCAR getLuxury() {
    return new LuxuryCAR("Luxury-Car");
  }
  public NonLuxuryCAR getNonLuxury() {
    return new NonLuxuryCAR("NonLuxury-Car");
  }
} // End of class


