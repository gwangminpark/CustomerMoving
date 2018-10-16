package insung.moving.customerV2.model;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018-07-06.
 */

public class DongArrayItem {
    private ArrayList<String> DongItems;
    private ArrayList<String> DongCodes;

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
