public class Beverage extends Item{
    protected String size; //S, M, etc
    protected String temperature; //Hot/Cold
    protected int sugarLevel; //0, 1, 2, 3

    public Beverage(String name, double price, int quantity, String size, String temperature, int sugarLevel){
        super(name, price, quantity);
        setSize(size);
        setTemperature(temperature);
        setSugarLevel(sugarLevel);
    }

    public void setSize(String size){
        this.size = size;
    }

    public String getSize(){
        return size;
    }

    public void setTemperature(String temperature){
        this.temperature = temperature;
    }

    public String getTemperature(){return temperature;}

    public void setSugarLevel(int sugarLevel){
        this.sugarLevel = sugarLevel;
    }

    public int getSugarLevel(){return sugarLevel;}

    @Override
    public int preparationTime() {
        return 5 * getQuantity(); //will take 5 minutes
    }
}
