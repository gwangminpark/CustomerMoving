package insung.moving.customer.service.resultInterface;

import insung.moving.customer.service.RecvPacket;

/**
 * Created by Administrator on 2018-07-04.
 */

public interface BaseInterface{
    void success(RecvPacket packet);
    void fail(String t);
}
