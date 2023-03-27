package jjuni.pjt.recommend.producer;

import jjuni.pjt.recommend.data.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping("/producerTest")
    public JsonData sendMessage(@RequestBody JsonData data) {
        Object obj = data.getMessage();

        kafkaProducerService.sendMessage(data.getTopic(), data.getMessage());
        return data;
    }

}
