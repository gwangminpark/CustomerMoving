package insung.moving.customer.service;

import android.os.Parcel;
import android.os.Parcelable;

import insung.moving.customer.app.MyApplication;
import insung.moving.customer.temp.DATA;
import insung.moving.customer.util.Util;


public class SendPacket implements Parcelable {
    private String HEAD;            // 헤더 "SISD"
    private int PACKET_SIZE;    // 패킷 사이즈
    public int TYPE;            // 서비스구분
    public int SUB_TYPE;        // 작업 구분
    private int LON;            // 경도
    private int LAT;            // 위도
    private int MSG_TYPE;        // 메세지
    private int DONG_CODE;        // 위치 동코드

    private String COMMAND;
    private byte pszData[] = new byte[32767];

    public SendPacket() {
        HEAD = "SISD";
        PACKET_SIZE = 0;
        TYPE = 0;
        SUB_TYPE = 0;
        LON = 0;
        LAT = 0;
        DONG_CODE = 0;
        COMMAND = "";
    }

    public SendPacket(Parcel in) {
        HEAD = in.readString();
        PACKET_SIZE = in.readInt();
        TYPE = in.readInt();
        SUB_TYPE = in.readInt();
        LON = in.readInt();
        LAT = in.readInt();
        DONG_CODE = in.readInt();
        COMMAND = in.readString();
        in.readByteArray(pszData);
    }

    public void AddType(int Type, int SubType) {
        TYPE = Type;
        SUB_TYPE = SubType;
    }
    public void AddMessageType(int messageType) {
        MSG_TYPE = messageType;
    }

    public void MemCpy(String Data) {
        COMMAND += Data;
    }

    public void AddString(String Data) {
        COMMAND += Data + MyApplication.DELIMITER;
    }

    public void AddInt(int Data) {
        COMMAND += Data + MyApplication.DELIMITER;
    }

    public void AddRowDelimiter() {
        COMMAND += MyApplication.ROW_DELIMITER;
    }

    public void Commit() {
        //한글 포맷으로 인해 바이트화 시킬때 getBytes로 바이트화시키기 때문에
        //COMMAND.length()를 이요해서 문자열길이를 패킷사이즈로 주게되면 바이트 길이 오류가 나게된다.
        try {
            byte[] bName = COMMAND.getBytes("ksc5601");
            COMMAND = new String(bName, 0, bName.length, "ksc5601");
            PACKET_SIZE = bName.length + 28;
        } catch (Exception e) {
        }

        LON = DATA.nLon;
        LAT = DATA.nLat;

        Util.StringToByte(pszData, 0, HEAD);
        Util.IntToByte(pszData, 4, Util.htonl(PACKET_SIZE));
        Util.IntToByte(pszData, 8, Util.htonl(TYPE));
        Util.IntToByte(pszData, 12, Util.htonl(SUB_TYPE));
        Util.IntToByte(pszData, 16, Util.htonl(LON));
        Util.IntToByte(pszData, 20, Util.htonl(LAT));
        Util.IntToByte(pszData, 24, Util.htonl(DONG_CODE));
        Util.StringToByte(pszData, 28, COMMAND);
    }

    public void Commit(byte[] imageString) {
        //한글 포맷으로 인해 바이트화 시킬때 getBytes로 바이트화시키기 때문에
        //COMMAND.length()를 이요해서 문자열길이를 패킷사이즈로 주게되면 바이트 길이 오류가 나게된다.
        PACKET_SIZE = imageString.length + 28;

        LON = DATA.nLon;
        LAT = DATA.nLat;

        Util.StringToByte(pszData, 0, HEAD);
        Util.IntToByte(pszData, 4, Util.htonl(PACKET_SIZE));
        Util.IntToByte(pszData, 8, Util.htonl(TYPE));
        Util.IntToByte(pszData, 12, Util.htonl(SUB_TYPE));
        Util.IntToByte(pszData, 16, Util.htonl(LON));
        Util.IntToByte(pszData, 20, Util.htonl(LAT));
        Util.IntToByte(pszData, 24, Util.htonl(DONG_CODE));
        System.arraycopy(imageString, 0, pszData, 28, imageString.length);
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeString(HEAD);
        dest.writeInt(PACKET_SIZE);
        dest.writeInt(TYPE);
        dest.writeInt(SUB_TYPE);
        dest.writeInt(LON);
        dest.writeInt(LAT);
        dest.writeInt(DONG_CODE);
        dest.writeString(COMMAND);
        dest.writeByteArray(pszData);
    }

    public static final Creator<SendPacket> CREATOR = new Creator<SendPacket>() {
        public SendPacket createFromParcel(Parcel in) {
            return new SendPacket(in);
        }

        public SendPacket[] newArray(int size) {
            return new SendPacket[size];
        }
    };

    public int GetPacketSize() {
        return PACKET_SIZE;
    }

    public byte[] GetData() {
        return pszData;
    }

    public int GetMessageType() {
        return MSG_TYPE;
    }


}

