package jjuni.pjt.recommend.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SocketVO {
    private String userName;

    // 메시지의 내용을 저장하기 위한 변수
    private String content;

}
