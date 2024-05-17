public class Set {

    protected Item setMainCourse;
    protected Item setBeverage;
    protected Item setDessert;
    //protected MainCourse setMainCourse;
    //protected Beverage setBeverage;
    //protected Dessert setDessert; //is optional
    protected double discount;

    public Set(){

    }

    public Set(MainCourse setMainCourse, Beverage setBeverage){
        setSetMainCourse(setMainCourse);
        setSetBeverage(setBeverage);
        setDiscount(15);
    }

    public Set(MainCourse setMainCourse, Beverage setBeverage, Dessert setDessert){
        setSetMainCourse(setMainCourse);
        setSetBeverage(setBeverage);
        setSetDessert(setDessert);
        setDiscount(15);
    }

    public void setSetMainCourse(MainCourse setMainCourse) {
        this.setMainCourse = setMainCourse;
    }

    public Item getSetMainCourse() {
        return setMainCourse;
    }

    public void setSetBeverage(Beverage setBeverage) {
        this.setBeverage = setBeverage;
    }

    public Item getSetBeverage() {
        return setBeverage;
    }

    public void setSetDessert(Dessert setDessert) {
        this.setDessert = setDessert;
    }

    public Item getSetDessert() {
        return setDessert;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public double calculateSetPrice(){ //to calculate total price of Set (with or without Dessert)
        double total;
        if (this.setDessert == null){
            total = getSetMainCourse().getPrice() + getSetBeverage().getPrice();
        } else {
            total = getSetMainCourse().getPrice() + getSetBeverage().getPrice() + getSetDessert().getPrice();
        }
        return total;
    }
}
