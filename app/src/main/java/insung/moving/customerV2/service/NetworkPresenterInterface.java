package insung.moving.customerV2.service;

import java.util.ArrayList;

import insung.moving.customerV2.service.resultInterface.GetDorderForCustInterface;
import insung.moving.customerV2.service.resultInterface.GetMapAddrInterface;
import insung.moving.customerV2.service.resultInterface.GetVersionCustInterface;
import insung.moving.customerV2.service.resultInterface.InsertDorderForCustCInterface;
import insung.moving.customerV2.service.resultInterface.Pst_PingInterface;

/**
 * Created by Administrator on 2018-07-04.
 */

public interface NetworkPresenterInterface {
    void InsertDorderForCust(String movingtype_check, String movingday_data, String movingname_data, String movingphone_data, ArrayList<String> movingstart_data, ArrayList<String> movingfinish_data, InsertDorderForCustCInterface anInterface);

    void GetDorderForCust(String name, String phone, GetDorderForCustInterface anInterface);

    void GetMapAddr(SendPacket sendPacket, GetMapAddrInterface anInterface);

    void GetVersionCust(GetVersionCustInterface anInterface);

    void PST_PING(Pst_PingInterface anInterface); //핑
}
