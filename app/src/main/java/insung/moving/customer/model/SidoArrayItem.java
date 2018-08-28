package insung.moving.customer.model;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018-07-06.
 */

public class SidoArrayItem {
    ArrayList<String> sidoItems;
    ArrayList<String> sidoCodes;

    public void setSidoItems(ArrayList<String> sidoItems) {
        this.sidoItems = sidoItems;
    }
    public ArrayList<String> getSidoItems() {
        return sidoItems;
    }


    public void setSidoCodes(ArrayList<String> sidoCodes) {
        this.sidoCodes = sidoCodes;
    }
    public ArrayList<String> getSidoCodes() {
        return sidoCodes;
    }
}
