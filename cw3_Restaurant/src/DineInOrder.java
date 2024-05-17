import java.util.ArrayList;

public class DineInOrder extends Order {
    protected int tableNumber;
    protected int pax;

    public DineInOrder(ArrayList<Item> orderList, ArrayList<Set> setOrderList, int orderNumber, int tableNumber, int pax){
        setOrderList(orderList);
        setSetList(setOrderList);
        setOrderNumber(orderNumber);
        setTableNumber(tableNumber);
        setPax(pax);
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setPax(int pax) {
        this.pax = pax;
    }

    public int getPax() {
        return pax;
    }
}
