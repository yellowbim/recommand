package jjuni.pjt.recommend.config.kafka;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@Configuration
public class KafkaProducerConfig {

//    @Value("${spring.kafka.consumer.bootstrap-servers}")
//    private String bootstrapServer;

    // producer 셋팅
    // Factory에서 Producer를 생산할 때 필요한 설정들을 Map형태로, 전달받아 Producer를 생산합니다.
//    @Bean
//    public ProducerFactory<String, String> producerFactory() {
//        Map<String, Object> configProps = new HashMap<>();
//        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
//        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        return new DefaultKafkaProducerFactory<>(configProps);
//    }
//
//    // 이벤트 생산을 편리하게 할 수 있도록 도와주는 Template입니다
//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }

}
