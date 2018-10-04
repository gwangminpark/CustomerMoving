package insung.moving.customer.service;

import java.util.ArrayList;

import insung.moving.customer.service.resultInterface.GetDorderForCustInterface;
import insung.moving.customer.service.resultInterface.GetMapAddrInterface;
import insung.moving.customer.service.resultInterface.GetVersionCustInterface;
import insung.moving.customer.service.resultInterface.InsertDorderForCustCInterface;
import insung.moving.customer.service.resultInterface.Pst_PingInterface;

/**
 * Created by Administrator on 2018-07-04.
 */

public interface NetworkPresenterInterface {
 // 서버 통신에 사용 할 전체 메서드


 void InsertDorderForCust(String movingtype_check , String  movingday_data, String movingname_data, String movingphone_data, ArrayList<String> movingstart_data, ArrayList<String> movingfinish_data , InsertDorderForCustCInterface anInterface);
 //이사정보 등록
 void GetDorderForCust(String name,String phone, GetDorderForCustInterface anInterface);
 //이사정보 조회
 void GetMapAddr(SendPacket sendPacket, GetMapAddrInterface anInterface);
 //주소조회
 void GetVersionCust(GetVersionCustInterface anInterface);
 //버전 체크
 void PST_PING(Pst_PingInterface anInterface);

}
