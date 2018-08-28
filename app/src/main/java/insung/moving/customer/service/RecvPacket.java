package insung.moving.customer.service;

import android.os.Parcel;
import android.os.Parcelable;

import insung.moving.customer.temp.PROTOCOL;


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
        dest.writeString(HEAD);
        dest.writeInt(PACKET_SIZE);
        dest.writeInt(TYPE);
        dest.writeInt(SUB_TYPE);
        dest.writeInt(ERROR);
        dest.writeInt(NROW);
        dest.writeInt(MSG_TYPE);
        dest.writeString(COMMAND);
        dest.writeString(INTENT);
    }

    public static final Creator<RecvPacket> CREATOR = new Creator<RecvPacket>() {
        public RecvPacket createFromParcel(Parcel in) {
            return new RecvPacket(in);
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

    public String GetRecvPacketMsg() {
        String sRtn = "메시지가 없습니다";

        switch (ERROR) {
            case PROTOCOL.PE_INVALID_CID:
                sRtn = "미등록 단말기 입니다";
                break;
            case PROTOCOL.PE_ORDER_NOT_FOUND:
                sRtn = "등록된 오더가 없습니다";
                break;
            case PROTOCOL.PE_ALREADY_ALLOCATED:
                sRtn = "다른 기사님이 먼저 배차했습니다";
                break;
            case PROTOCOL.PE_INVALID_ORDER:
                sRtn = "등록되지 않는 오더입니다";
                break;
            case PROTOCOL.PE_DEST_LIST_NOT_FOUND:
                sRtn = "등록된 목적지가 없습니다";
                break;
            case PROTOCOL.PE_OK:
                sRtn = "완료 처리하였습니다";
                break;
            case PROTOCOL.PE_ERROR:
                sRtn = "처리 실패하였습니다";
                break;
            case PROTOCOL.PE_NO_DATA:
                sRtn = "자료가 존재하지 않습니다";
                break;
            case PROTOCOL.PE_TIMEOUT_ERROR:
                sRtn = "시간초과로 처리 실패하였습니다";
                break;
            case PROTOCOL.PE_ORDER_ONLY_MAN:
                sRtn = "직권배차입니다";
                break;
            case PROTOCOL.PE_NO_CHARGE:
                sRtn = "금액문제로 인한 배차실패";
                break;
            case PROTOCOL.PE_NOT_COMPLETE_ORDER:
                sRtn = "완료하지 않는 오더가 있습니다";
                break;
            case PROTOCOL.PE_NO_LIMITS:
                sRtn = "배차제한 미설정으로 인한 배차실패";
                break;
            case PROTOCOL.PE_ATTENDANCE:
                sRtn = "출근 처리 되었습니다.";
                break;
            case PROTOCOL.PE_QUERY_ERROR:
                sRtn = "DB쿼리 에러입니다";
                break;
            case PROTOCOL.PE_DUPLICATE_DATE:
                sRtn = "이미 등록된 데이터 입니다";
                break;
            case PROTOCOL.PE_LOGIN_OK:
                sRtn = "인증성공 하였습니다";
                break;
            case PROTOCOL.PE_OK_HELP_ME:
                sRtn = "지원요청을 하였습니다";
                break;
            case PROTOCOL.PE_ALREADY_HELP_ME:
                sRtn = "이미 지원요청을 하였습니다";
                break;
            case PROTOCOL.PE_LOCATION_CHANGED:
                sRtn = "위치 변경되었습니다";
                break;
            case PROTOCOL.PE_INVALID_PROTOCOL:
                sRtn = "INVAILD_PROTOCOL";
                break;
            case PROTOCOL.PE_UNDEFINED_PROTOCOL:
                sRtn = "UNDEFINED_PROTOCOL";
                break;
            case PROTOCOL.PE_CONNECTION_CLOSE:
                sRtn = "접속이 종료됩니다";
                break;
            case PROTOCOL.PE_CONNECTION_CLOSE_GGGI:
                sRtn = "인성퀵 프로그램의 정품프로그램에 대한 불법복제 행위가 확인 되었으므로 접속이 제한 됩니다.\n정상적으로 접속하기 위해서는 현재의 불법프로그램을 삭제후 정상프로그램으로 재설치 하십시오.\n설치주소 : 02.283.co.kr";
                break;
            case PROTOCOL.PE_CLOSE:
                sRtn = "퇴근요청을 하였습니다";
                break;
            case PROTOCOL.PE_NO_ATTENDANCE:
                sRtn = "출근보고후 퇴근요청을 하세요";
                break;
            case PROTOCOL.PE_OK_CONNECTION:
                sRtn = "연계요쳥을 하였습니다";
                break;
            case PROTOCOL.PE_ALREADY_CONNECTION:
                sRtn = "이미 연계요쳥을 하였습니다";
                break;
            case PROTOCOL.PE_MY_LOAD:
                sRtn = "상태를 전송하였습니다";
                break;
            case PROTOCOL.PE_POSITION_SAVE:
                sRtn = "저장 되었습니다";
                break;
            case PROTOCOL.PE_NO_POSITION:
                sRtn = "저장 실패";
                break;
            case PROTOCOL.PE_NO_RIDER:
                sRtn = "라이더 정보가 없습니다";
                break;
            case PROTOCOL.PE_NO_CUSTOMER:
                sRtn = "고객 정보가 없습니다";
                break;
            case PROTOCOL.PE_FAIL_ALLOCATE:
                sRtn = "배차에 실패하였습니다";
                break;
            case PROTOCOL.PE_ATTENDANCE_TIME:
                sRtn = "출근 시간을 초과 하였습니다";
                break;
            case PROTOCOL.PE_PUNCHOUT_TIME:
                sRtn = "퇴근 시간을 지켜 주세요";
                break;
        }
        return sRtn;
    }
}
