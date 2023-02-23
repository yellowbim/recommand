package jjuni.pjt.recommend.health;

import jjuni.pjt.recommend.common.model.TestVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class health {

    @ResponseBody
    @RequestMapping("/health")
    public ResponseEntity main(){
        TestVO testVO = new TestVO();
        testVO.setTest("ggggg");
        System.out.println(testVO);
        System.out.println("gd");
        return new ResponseEntity<>("health", HttpStatus.OK);
    }

    @RequestMapping("/test")
    public ResponseEntity<Map> test() {
        Map<String,String> map = new HashMap<>();
        map.put("test", "하이하이");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

//    @KafkaListener(topics = "testTopic11", groupId = "BBB")
//    public void consume(String message) throws IOException {
//        System.out.println("name = " + message);
//        System.out.println("consume message = " + message);
//    }

}
