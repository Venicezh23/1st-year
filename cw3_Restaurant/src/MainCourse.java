import java.util.HashMap;

public class MainCourse extends Item{

    public MainCourse(String name, double price, int quantity){
        super(name, price, quantity);
    }

    @Override
    public int preparationTime() {
        //main courses take around 15 to 20 minutes
        //System.out.println(getName() + " will take 20 minutes to prepare.");
        return 20 * getQuantity();
    }
}
