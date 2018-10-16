package insung.moving.customerV2.model;

/**
 * Created by user on 2018-06-27.
 */

public class StartAddressItem {
    public String dongName;
    public String sidoName;
    public String gunguName;
    public String heightName;
    public String dongCode;
    public String sidoCode;
    public String gunguCode;
    public String heightCode;
    public String StartAddressName;

    public StartAddressItem() {
        this.sidoName = "";
        this.gunguName = "";
        this.dongName = "";
        this.heightName="";
        this.sidoCode = "";
        this.gunguCode = "";
        this.dongCode = "";
        this.heightCode="";
    }

    public String getSidoName() {
        return sidoName;
    }

    public void setSidoName(String sidoName) {
        this.sidoName = sidoName;
    }

    public String getGunguName() {
        return gunguName;
    }

    public void setGunguName(String gunguName) {
        this.gunguName = gunguName;
    }

    public String getDongName() {
        return dongName;
    }

    public void setDongName(String dongName) {
        this.dongName = dongName;
    }

    public String getDongCode() {
        return dongCode;
    }

    public void setDongCode(String dongCode) {
        this.dongCode = dongCode;
    }

    public String getSidoCode() {
        return sidoCode;
    }

    public void setSidoCode(String sidoCode) {
        this.sidoCode = sidoCode;
    }

    public String getGunguCode() {
        return gunguCode;
    }

    public void setGunguCode(String gunguCode) {
        this.gunguCode = gunguCode;
    }
//    public StartAddressItem(String name){
//        this.name = name;
//    }

    public String getHeightName() {
        return heightName;
    }

    public void setHeightName(String heightName) {
        this.heightName = heightName;
    }

    public String getHeightCode() {
        return heightCode;
    }

    public void setHeightCode(String heightCode) {
        this.heightCode = heightCode;
    }

    public String getStartAddressName(){
        if(!getHeightName().equals("")){
            return getSidoName() +" - "+getGunguName()+" - "+getDongName()+"-"+getHeightName();
        }
        else if(!getDongName().equals("")){
            return getSidoName() +" - "+getGunguName()+" - "+getDongName();
        }else if(!getGunguName().equals("")){
            return getSidoName() +" - "+getGunguName();
        }else{
            return getSidoName();
        }

    }
    public void setStartAddressName(String startAddressName) {
        StartAddressName = startAddressName;
    }


}

