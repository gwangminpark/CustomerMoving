package insung.moving.customerV2.model;

/**
 * Created by user on 2018-06-22.
 */

public class OrderData {
    private int moving_type;
    //(1:가정이사, 2:사무실이사, 3:원룸이사, 4:공장이사, 5:제주도이사, 6:해외이사)
    private int moving_typelist;
    //(1:포장이사, 2:일반이사, 3:보관이사, 5:고급포장이사)
    private String moving_date;
    private String name;

    //이사시작 정보
    private String start_address1;
    // 대구,부산,광주,
    private String start_address2;
    // 남구,서구,북구
    private String start_address3;
    //대명동, 등 동
    private int start_height;
    //층수

    //이사할 곳 정보
    private String finish_address1;
    // 대구,부산,광주,
    private String finish_address2;
    // 남구,서구,북구
    private String finish_address3;
    //대명동, 등 동
    private int finish_height;
    //층수
    private String member_memo;
    //고객메모

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
