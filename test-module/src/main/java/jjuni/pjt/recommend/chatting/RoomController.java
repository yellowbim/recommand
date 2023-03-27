package jjuni.pjt.recommend.chatting;

import jjuni.pjt.recommend.vo.ChatRoomVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
@Log4j2
public class RoomController {

    private final ChatRoomRepository repository;

    //채팅방 목록 조회
    @GetMapping(value = "/rooms")
    public ResponseEntity<?> rooms(){
        log.info("# All Chat Rooms");
        return new ResponseEntity<List<ChatRoomVO>>(repository.findAllRooms(), HttpStatus.OK);
    }

    //채팅방 개설
    @PostMapping(value = "/room")
    public ResponseEntity<ChatRoomVO> create(@RequestBody ChatRoomVO chatRoomVO){

        log.info("# Create Chat Room , name: " + chatRoomVO.getName());
        repository.createChatRoomDTO(chatRoomVO.getName());
        return new ResponseEntity<ChatRoomVO>(chatRoomVO, HttpStatus.OK);
    }

    //채팅방 조회
    @GetMapping("/room")
    public void getRoom(String roomId, Model model){

        log.info("# get Chat Room, roomID : " + roomId);
        repository.findRoomById(roomId);
    }

}
