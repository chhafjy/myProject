package com.chh.kafka.consumer;

import com.chh.kafka.topics.TopicOne;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 *
 *
 * @author chenhaohao
 * @version 1.0
 * @date 2021/12/1 9:30
 */
@Component
@Slf4j
public class TestOneConsumer {

    @KafkaListener(topics = TopicOne.TOPIC_TEST_ONE,groupId = TopicOne.TEST_ONE_GROUP1)
    public void topic_test1(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional<?> message = Optional.ofNullable(record.value());
        if(message.isPresent()){
            Object msg =  message.get();
            log.info("topic_test1 消费了： Topic:" + topic + ",Message:" + msg);
            ack.acknowledge();
        }
    }

    @KafkaListener(topics = TopicOne.TOPIC_TEST_TWO,groupId = TopicOne.TEST_ONE_GROUP2)
    public void topic_test2(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional<?> message = Optional.ofNullable(record.value());
        if(message.isPresent()){
            Object msg = message.get();
            log.info("topic_test2 消费了： Topic:" + topic + ",Message:" + msg);
            ack.acknowledge();
        }
    }
}
