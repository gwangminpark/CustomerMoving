package insung.moving.customerV2.service;

import android.os.Parcel;
import android.os.Parcelable;


public class RecvPacket implements Parcelable {
    public String HEAD;            // 헤더 "SISD"
    public int PACKET_SIZE;    // 패킷 사이즈
    public int TYPE;            // 서비스구분
    public int SUB_TYPE;        // 작업 구분
    public int ERROR;            // 경도
    public int NROW;            // 위도
    public int MSG_TYPE;        // 위치 동코드

    public String COMMAND;
    private String INTENT;

    public RecvPacket() {
        HEAD = "SISD";
        PACKET_SIZE = 0;
        TYPE = 0;
        SUB_TYPE = 0;
        ERROR = 0;
        NROW = 0;
        MSG_TYPE = 0;
        COMMAND = "";
        INTENT = "";
    }

    public RecvPacket(Parcel in) {
        HEAD = in.readString();
        PACKET_SIZE = in.readInt();
        TYPE = in.readInt();
        SUB_TYPE = in.readInt();
        ERROR = in.readInt();
        NROW = in.readInt();
        MSG_TYPE = in.readInt();
        COMMAND = in.readString();
        INTENT = in.readString();
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeString( HEAD );
        dest.writeInt( PACKET_SIZE );
        dest.writeInt( TYPE );
        dest.writeInt( SUB_TYPE );
        dest.writeInt( ERROR );
        dest.writeInt( NROW );
        dest.writeInt( MSG_TYPE );
        dest.writeString( COMMAND );
        dest.writeString( INTENT );
    }

    public static final Creator<RecvPacket> CREATOR = new Creator<RecvPacket>() {
        public RecvPacket createFromParcel(Parcel in) {
            return new RecvPacket( in );
        }

        public RecvPacket[] newArray(int size) {
            return new RecvPacket[size];
        }
    };

    public void SetIntentString(String intent) {
        INTENT = intent;
    }

    public String GetIntentString() {
        return INTENT;
    }


}
