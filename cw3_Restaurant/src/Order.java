import java.util.ArrayList;

abstract class Order {
    protected int orderNumber;
    protected ArrayList<Item> orderList;
    protected ArrayList<Set> setList; //can be null - need to handle null in Invoice

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderList(ArrayList<Item> orderList) {
        this.orderList = orderList;
    }

    public ArrayList<Item> getOrderList() {
        return orderList;
    }

    public void setSetList(ArrayList<Set> setList) {
        this.setList = setList;
    }

    public ArrayList<Set> getSetList() {
        return setList;
    }
}
