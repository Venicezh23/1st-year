abstract class Item implements Preparation {
    protected int quantity; //quantity set by customer
    protected double price; //each item has their own fixed price - how to implement?
    protected String name; //name of food item

    public Item(){
        //empty constructor
    }

    public Item(String name, double price, int quantity){
        setName(name);
        setPrice(price);
        setQuantity(quantity);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int getQuantity(){
        return quantity;
    }
}
