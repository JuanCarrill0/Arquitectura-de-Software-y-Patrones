public class MotorcycleVehicleFactory extends VehicleFactory{

    @Override
    public Luxury getLuxury() {
        return new LuxuryMotorcycle("Luxury-Motorcycle");
    }

    @Override
    public NonLuxury getNonLuxury() {
        return new NonLuxuryMotorcycle("Non-Luxury-Motorcycle");
    }
    
}
