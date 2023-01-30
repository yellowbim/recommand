package jjuni.pjt.recommand.connectDB;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SdkSmsSend {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "MSG_ID", nullable = false)
    private Integer msgId;   //고유 메시지 구분값 (자동증가 key)
    private String userId;   //회원 ID
    private Integer scheduleType;   //발송시점 구분 (즉시전송:0, 예약전송:1)
    private String subject;   //제목
    private String smsMsg;   //발송 SMS 메시지 (80byte)
    private String callbackUrl;   //전송할 CALLBACK URL
    private String nowDate;   //DB 입력 시간: YYYYMMDDHHMMSS
    private String sendDate;   //발송 희망 시간: YYYYMMDDHHMMSS
    private String callback;   //콜백번호(회신번호)
    private Integer destType;   //수신자 정보 저장 타입 (0:TEXT 고정)
    private Integer destCount;   //수신자 목록 개수 (Max: 100)
    private String destInfo;   //착신자 정보 이름과 번호 저장
    private String ktOfficeCode;   //KT 유통망 코드
    private String cdrId;   //과금 ID
    private String reserved1;   //여분필드 1 (임의로 사용가능)
    private String reserved2;   //여분필드 2 (임의로 사용가능)
    private String reserved3;   //여분필드 3 (임의로 사용가능)
    private String reserved4;   //여분필드 4 (임의로 사용가능)
    private String reserved5;   //여분필드 5 (임의로 사용가능)
    private String reserved6;   //여분필드 6 (임의로 사용가능)
    private String reserved7;   //여분필드 7 (임의로 사용가능)
    private String reserved8;   //여분필드 2 (임의로 사용가능)
    private String reserved9;   //여분필드 2 (임의로 사용가능)
    private Integer sendStatus;   //SEND 전송 처리 상태
    private Integer sendCount;   //서버로 전송 실패 시 재전송하는 회수
    private Integer sendResult;   //전송결과 서버 ACK 값
    private String sendProcTime;   //전송 처리 시간: YYYYMMDDHHMMSS
    private Integer stdId;   //타 규격 연동 시 사용



}
