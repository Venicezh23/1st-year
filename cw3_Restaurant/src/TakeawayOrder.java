import java.util.ArrayList;

public class TakeawayOrder extends Order{

    protected boolean cutlery;

    public TakeawayOrder(ArrayList<Item> orderList, ArrayList<Set> setOrderList, int takeawayOrderNumber, boolean cutlery){
        setOrderList(orderList);
        setSetList(setOrderList);
        setOrderNumber(takeawayOrderNumber);
        setCutlery(cutlery);
    }

    public void setCutlery(boolean cutlery){
        this.cutlery = cutlery;
    }

    public boolean isCutlery() {
        return cutlery;
    }
}
