package com.neusoft.kafka;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;

import com.neusoft.common.utils.Topic;


public class MessageConsumer {

    public static void main(String[] args) {

        KafkaConsumer<String, String> consumer = KafkaUtil.getConsumer();
        Map<String, List<PartitionInfo>> map = consumer.listTopics();
        consumer.subscribe(Arrays.asList(Topic.CPC_MATCH));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1);
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
        }
    }

}
