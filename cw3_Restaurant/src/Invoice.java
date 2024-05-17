import java.text.SimpleDateFormat;
import java.util.Date;

public class Invoice {
    protected Order order; //Each Order is associated with only 1 Invoice, and vice versa
    protected double serviceTax; //is fixed(?)
    protected double discount; //can be null - is optional - not used for Sets

    public Invoice(){

    }

    public Invoice(Order order, double serviceTax, double discount){
        setOrder(order);
        setServiceTax(serviceTax);
        setDiscount(discount);
    }

    public void setOrder(Order order){
        this.order = order;
    }

    public Order getOrder(){
        return order;
    }

    public void setServiceTax(double serviceTax){
        this.serviceTax = serviceTax;
    }

    public double getServiceTax(){
        return serviceTax;
    }

    public void setDiscount(double discount){
        this.discount = discount;
    }

    public double getDiscount(){
        return discount;
    }

    //method to get price total of all items (Ala Carte + Set(s))
    public double orderPriceTotal(){
        double total = 0;
        for (Item item : order.getOrderList()){
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    //method to calculate all sets (no discount)
    public double setOrderPriceTotal(){
        double total = 0;
        for (Set set : order.getSetList()){
            total += set.calculateSetPrice();
        }
        return total;
    }

    //method to calculate set after discount
    public double calculateSetOrder(){
        double total = 0;
        for (Set set : order.getSetList()){
            total += set.calculateSetPrice() - ((set.getDiscount()/100) * set.calculateSetPrice());
        }
        return total;

    }

    public double calculateTax(double tax){
        return (tax/100) * orderPriceTotal();
    }

    //the final calculation after considering tax and discounts
    public double calculateInvoice(){
        double total;
        if (discount > 0){
            total = orderPriceTotal() + calculateTax(serviceTax) - (orderPriceTotal() * (discount/100));
        } else {
            total = orderPriceTotal() + calculateTax(serviceTax);
        }

        return total + calculateSetOrder();
    }

    public String getDate(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return format.format(date);
    }

    public String getTime(){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return format.format(date);
    }

    //method to print invoice (all items, their prices and quantities)
    //the total of all items
    //service tax
    //any discounts
    public void printInvoice(){
        System.out.printf("%n%-30s: %.2f", "Total for ALA CARTE", orderPriceTotal());
        System.out.printf("%n%-30s: %.2f", "Total for SET", setOrderPriceTotal());
        System.out.printf("%n%-30s: %.2f", "SET(s) after Discount", calculateSetOrder());
        System.out.printf("%n%-30s: %.2f%%", "Service Tax", serviceTax);
        System.out.printf("%n%-30s: %.2f%%", "Discount", discount);
        System.out.printf("%n%-30s: %.2f%n", "Final Invoice", calculateInvoice());
        System.out.printf("%n%s: %-10s%15s: %s", "Date", getDate(), "Time", getTime());
    }
}
