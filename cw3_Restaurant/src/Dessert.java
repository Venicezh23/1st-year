public class Dessert extends Item{
    protected boolean serveAfterMeal;

    public Dessert(String name, double price, int quantity, boolean serveAfterMeal){
        super(name, price, quantity);
        setServeAfterMeal(serveAfterMeal);
    }

    public void setServeAfterMeal(boolean serveAfterMeal){
        this.serveAfterMeal = serveAfterMeal;
    }

    public boolean getServeAfterMeal(){return serveAfterMeal;}

    @Override
    public int preparationTime() {
        //System.out.println(getName() + " will take 5 minutes to prepare."); //will take 5 mins
        return 5 * getQuantity();
    }
}
