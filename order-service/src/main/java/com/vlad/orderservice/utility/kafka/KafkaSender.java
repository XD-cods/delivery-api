package com.vlad.orderservice.utility.kafka;

import com.vlad.orderservice.persistence.entity.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
@RequiredArgsConstructor
public class KafkaSender {

  private final KafkaTemplate<String, Order> kafkaTemplate;

  public void send(String topic, Order order) {
    log.info("Kafka send message");
    kafkaTemplate.send(topic, order);
  }

}
