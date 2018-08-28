package insung.moving.customer.service.resultInterface;

import java.util.ArrayList;

import insung.moving.customer.model.OrderlistData;

/**
 * Created by Administrator on 2018-07-04.
 */

public interface GetDorderForCustInterface extends BaseInterface {
    void success(ArrayList<String> orderlistData);
}
