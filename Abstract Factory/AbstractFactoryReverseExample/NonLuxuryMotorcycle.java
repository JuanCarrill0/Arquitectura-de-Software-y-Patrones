public class NonLuxuryMotorcycle implements NonLuxury{
    private String name;
    
    public NonLuxuryMotorcycle(String mName){
        name = mName;
    }

    public String getNLName() {
        return name;
    }

    public String getNLFeatures() {
        return "Non-Luxury Motorcycle Features ";
    }
}
