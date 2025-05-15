public class LuxuryMotorcycle implements Luxury{
    private String name;
    
    public LuxuryMotorcycle(String mName){
        name = mName;
    }

    public String getLuxuryName() {
        return name;
    }

    public String getLuxuryFeatures() {
        return "Luxury Motorcycle Features ";
    }
    
}
