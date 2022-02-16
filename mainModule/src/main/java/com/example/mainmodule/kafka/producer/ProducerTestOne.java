package com.example.mainmodule.kafka.producer;

import com.alibaba.fastjson.JSONObject;
import com.chh.kafka.topics.TopicOne;
import com.example.mainmodule.test.beans.BeanOne;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;

/**
 *
 *
 * @author chenhaohao
 * @version 1.0
 * @date 2021/12/1 10:12
 */
@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProducerTestOne {

    private final KafkaTemplate<String,Object> kafkaTemplate;

    public void send(Object obj) {
        String jsonString = JSONObject.toJSONString(obj);
        log.info("准备发送消息为：{}", jsonString);
        ListenableFuture<SendResult<String, Object>> send = kafkaTemplate.send(TopicOne.TOPIC_TEST_ONE, obj);
        send.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                //发送失败的处理
                log.info(TopicOne.TOPIC_TEST_ONE + " - 生产者 发送消息失败：" + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                //成功的处理
                log.info(TopicOne.TOPIC_TEST_ONE + " - 生产者 发送消息成功：" + result.getProducerRecord().value());
            }
        });
    }

    public Boolean sendV2(BeanOne one) {
        log.info("准备发动信息: {}",one);

        ListenableFuture<SendResult<String, Object>> send = kafkaTemplate.send(TopicOne.TOPIC_TEST_TWO, one.toString());
        send.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                //发送失败的处理
                log.info(TopicOne.TOPIC_TEST_ONE + " - 生产者 发送消息失败：" + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                //成功的处理
                log.info(TopicOne.TOPIC_TEST_ONE + " - 生产者 发送消息成功：" + result.getProducerRecord().value());
            }
        });
        return true;
    }
}
