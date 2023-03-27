package jjuni.pjt.recommend.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
@EnableKafka
@RequiredArgsConstructor // 얘가 뭐하는 녀석인고?
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String payload) {
        System.out.println("sending payload =" + payload + " to topice= " + topic);
        this.kafkaTemplate.send(topic, payload);
    }

}
