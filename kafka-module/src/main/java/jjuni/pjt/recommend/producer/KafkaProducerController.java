package jjuni.pjt.recommend.producer;

import jjuni.pjt.recommend.data.JsonData;
import jjuni.pjt.recommend.data.JsonData1;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
public class KafkaProducerController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    /**
     * test data
     * {
     *     "topic":"testTopic11",
     *     "message":"안녕하세요"
     * }
     */
    @ResponseBody
    @RequestMapping("/stringProducer")
    public String sendStringMessage(@RequestBody String data) {
        log.info("들어왔다~ : " + data);
        kafkaProducerService.sendMessage(data);
        return data;
    }

    @ResponseBody
    @RequestMapping("/jsonProducer")
    public ResponseEntity<String> sendJsonMessage(JsonData1 data) {
        String message;

        try{
            kafkaProducerService.sendJson(data);
            message = "성공적으로 전달!";
            return new ResponseEntity<String>(message, HttpStatus.OK);
        } catch (Exception e) {
            message = "에러...";
            log.error(e.getMessage());
            return new ResponseEntity<String>(message, HttpStatus.OK);
        }
    }
}
