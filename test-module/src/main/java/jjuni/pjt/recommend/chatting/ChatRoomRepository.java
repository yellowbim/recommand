package jjuni.pjt.recommend.chatting;

import jjuni.pjt.recommend.vo.ChatRoomVO;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ChatRoomRepository {
    private Map<String, ChatRoomVO> chatRoomDTOMap;

    @PostConstruct
    private void init(){
        chatRoomDTOMap = new LinkedHashMap<>();
    }

    public List<ChatRoomVO> findAllRooms(){
        //채팅방 생성 순서 최근 순으로 반환
        List<ChatRoomVO> result = new ArrayList<>(chatRoomDTOMap.values());
        Collections.reverse(result);

        return result;
    }

    public ChatRoomVO findRoomById(String id){
        return chatRoomDTOMap.get(id);
    }

    public ChatRoomVO createChatRoomDTO(String name){
        ChatRoomVO room = ChatRoomVO.create(name);
        chatRoomDTOMap.put(room.getRoomId(), room);

        return room;
    }


}
