package insung.moving.customer.model;

import java.util.ArrayList;

/**
 * Created by user on 2018-07-11.
 */

public class MyorderItem extends ArrayList<String> {

    public String ORDER_DATE;
    public String MOVE_SERVICE_GBN_TEXT;
    public String MOVE_DATE;
    public String START_ADDRESS;
    public String FINISH_ADDRESS;

    public String getORDER_DATE() {
        return ORDER_DATE;
    }

    public void setORDER_DATE(String ORDER_DATE) {
        this.ORDER_DATE = ORDER_DATE;
    }

    public String getMOVE_SERVICE_GBN_TEXT() {
        return MOVE_SERVICE_GBN_TEXT;
    }

    public void setMOVE_SERVICE_GBN_TEXT(String MOVE_SERVICE_GBN_TEXT) {
        this.MOVE_SERVICE_GBN_TEXT = MOVE_SERVICE_GBN_TEXT;
    }

    public String getMOVE_DATE() {
        return MOVE_DATE;
    }

    public void setMOVE_DATE(String MOVE_DATE) {
        this.MOVE_DATE = MOVE_DATE;
    }

    public String getSTART_ADDRESS() {
        return START_ADDRESS;
    }

    public void setSTART_ADDRESS(String START_ADDRESS) {
        this.START_ADDRESS = START_ADDRESS;
    }

    public String getFINISH_ADDRESS() {
        return FINISH_ADDRESS;
    }

    public void setFINISH_ADDRESS(String FINISH_ADDRESS) {
        this.FINISH_ADDRESS = FINISH_ADDRESS;
    }

}
