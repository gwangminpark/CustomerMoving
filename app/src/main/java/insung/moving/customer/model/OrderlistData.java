package insung.moving.customer.model;

import insung.moving.customer.service.SendPacket;

/**
 * Created by user on 2018-07-11.
 */

public class OrderlistData extends SendPacket {


    public String order_day;
    public  String moving_type;
    public  String moving_day;
    public   String start_adderss;
    public  String finish_address;
    public String getOrder_day() {
        return order_day;
    }

    public String getMoving_type() {
        return moving_type;
    }

    public String getMoving_day() {
        return moving_day;
    }

    public String getStart_adderss() {
        return start_adderss;
    }

    public String getFinish_address() {
        return finish_address;
    }
    public OrderlistData(String order_day, String moving_type,String moving_day,String start_adderss,String finish_address ) {
        this.order_day = order_day;
        this.moving_type = moving_type;
        this.moving_day= moving_day;
        this.start_adderss=start_adderss;
        this.finish_address=finish_address;
    }
    public OrderlistData(){
        order_day="";
        moving_type="";
        moving_day="";
        start_adderss="";
        finish_address="";
    }
}
