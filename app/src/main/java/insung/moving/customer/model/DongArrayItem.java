package insung.moving.customer.model;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018-07-06.
 */

public class DongArrayItem {
    ArrayList<String> DongItems;
    ArrayList<String> DongCodes;

    public void setDongItems(ArrayList<String> DongItems) {
        this.DongItems = DongItems;
    }
    public ArrayList<String> getDongItems() {
        return DongItems;
    }


    public void setDongCodes(ArrayList<String> DongCodes) {
        this.DongCodes = DongCodes;
    }
    public ArrayList<String> getDongCodes() {
        return DongCodes;
    }
}
