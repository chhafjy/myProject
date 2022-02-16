package com.example.mainmodule.controller;

import com.chh.utilmodule.redis.RedisUtils;
import com.example.mainmodule.kafka.producer.ProducerTestOne;
import com.example.mainmodule.test.beans.BeanOne;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 *
 *
 * @author chenhaohao
 * @version 1.0
 * @date 2021/11/19 14:47
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestController {

//    @Resource
    private final RedisUtils redisUtils;

    private final ProducerTestOne producerTestOne;

    @GetMapping(value = "/p/test/one")
    public void TestOne(){
        BeanOne one = new BeanOne("张三", 20);
        redisUtils.set("one", "1234");
        redisUtils.set("two", one, 1, TimeUnit.MINUTES);
        BeanOne two = new BeanOne();
        two.setAge(40);
        two.setName("后视镜爱国");
        redisUtils.set("three", two);
    }

    @GetMapping(value = {"/p/kafka/send"})
    public void kafkaSend() {
        producerTestOne.send("天灰雨蒙情深深");
    }

    @GetMapping(value = {"/p/kafka/sendV2"})
    public void kafkaSendV2() {
        BeanOne one = new BeanOne();
        one.setName("张三");
        one.setAge(20);
        producerTestOne.sendV2(one);
    }
}
