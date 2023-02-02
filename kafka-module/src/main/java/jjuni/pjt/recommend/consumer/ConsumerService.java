package jjuni.pjt.recommend.consumer;

import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@EnableKafka // 이 옵션을 넣어야 사용가능함
public class ConsumerService {

    @KafkaListener(topics = "testTopic11", groupId = "BBB")
    public void consume(String message) throws IOException {
        System.out.println("consume message = " + message);
    }

}
