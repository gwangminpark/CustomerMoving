package insung.moving.customer.model;

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
    private int phone;

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

    public int getMoving_type() {
        return moving_type;
    }

    public void setMoving_type(int moving_type) {
        this.moving_type = moving_type;
    }

    public int getMoving_typelist() {
        return moving_typelist;
    }

    public void setMoving_typelist(int moving_typelist) {
        this.moving_typelist = moving_typelist;
    }

    public String getMoving_date() {
        return moving_date;
    }

    public void setMoving_date(String moving_date) {
        this.moving_date = moving_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getStart_address1() {
        return start_address1;
    }

    public void setStart_address1(String start_address1) {
        this.start_address1 = start_address1;
    }

    public String getStart_address2() {
        return start_address2;
    }

    public void setStart_address2(String start_address2) {
        this.start_address2 = start_address2;
    }

    public String getStart_address3() {
        return start_address3;
    }

    public void setStart_address3(String start_address3) {
        this.start_address3 = start_address3;
    }

    public int getStart_height() {
        return start_height;
    }

    public void setStart_height(int start_height) {
        this.start_height = start_height;
    }

    public String getFinish_address1() {
        return finish_address1;
    }

    public void setFinish_address1(String finish_address1) {
        this.finish_address1 = finish_address1;
    }

    public String getFinish_address2() {
        return finish_address2;
    }

    public void setFinish_address2(String finish_address2) {
        this.finish_address2 = finish_address2;
    }

    public String getFinish_address3() {
        return finish_address3;
    }

    public void setFinish_address3(String finish_address3) {
        this.finish_address3 = finish_address3;
    }

    public int getFinish_height() {
        return finish_height;
    }

    public void setFinish_height(int finish_height) {
        this.finish_height = finish_height;
    }

    public String getMember_memo() {
        return member_memo;
    }

    public void setMember_memo(String member_memo) {
        this.member_memo = member_memo;
    }





}
