package insung.moving.customerV2.service.resultInterface;

import insung.moving.customerV2.service.RecvPacket;

/**
 * Created by Administrator on 2018-07-04.
 */

public interface BaseInterface{
    void success(RecvPacket packet);
    void fail(String t);
}
