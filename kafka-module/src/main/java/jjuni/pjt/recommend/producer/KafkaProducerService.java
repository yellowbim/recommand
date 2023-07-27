package jjuni.pjt.recommend.producer;

import jjuni.pjt.recommend.data.JsonData;
import jjuni.pjt.recommend.data.JsonData1;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
@EnableKafka
@RequiredArgsConstructor // 얘가 뭐하는 녀석인고?
public class KafkaProducerService {
    @Value("${spring.kafka.my.push.topic.name}")
    String topic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateString;

    @Autowired
    private KafkaTemplate<String, JsonData1> kafkaTemplateJson;

    public void sendMessage(String data) {
        System.out.println("String Send = " + data);
        this.kafkaTemplateString.send(topic, data);
    }

    public void sendJson(JsonData1 data) {
        this.kafkaTemplateJson.send(topic, data);
    }



}
