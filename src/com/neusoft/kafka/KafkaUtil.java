package com.neusoft.kafka;

import java.util.Properties;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

import com.neusoft.common.utils.PropertiesLoader;  
  
public class KafkaUtil {  
	
    private volatile static KafkaProducer<String, String> kp = null;  
    private volatile static KafkaConsumer<String, String> kc = null;
    private volatile static PropertiesConfiguration pc;
    
    public static KafkaProducer<String, String> getProducer() {
        if (null == kp) {
        	synchronized (KafkaUtil.class) {
        		if (null == kp){
        			pc = PropertiesLoader.load(); 
                    Properties props = new Properties();
                    props.put("bootstrap.servers", pc.getString("kafka.bootstrap.servers")); 
                    props.put("acks", pc.getString("kafka.acks")); 
                    props.put("retries", pc.getInt("kafka.retries"));
                    props.put("batch.size", pc.getInt("kafka.batch.size"));  
                    props.put("key.serializer", pc.getString("kafka.producer.key.serializer"));  
                    props.put("value.serializer", pc.getString("kafka.producer.value.serializer"));  
                    kp = new KafkaProducer<String, String>(props);
        		}
        	}
        }
        return kp;
    }
      
    public static KafkaConsumer<String, String> getConsumer() {    
        if(null == kc) {
        	synchronized (KafkaUtil.class) {
        		if (null == kc){
        			pc = PropertiesLoader.load();
                    Properties props = new Properties();
                    props.put("bootstrap.servers", "10.4.120.112:19092,10.4.120.113:19092,10.4.120.120:19092");
                    props.put("group.id", pc.getString("kafka.group.id"));
                    props.put("auto.offset.reset", pc.getString("kafka.auto.offset.reset"));  //auto.offset.reset这里设置为earliest，是为了consumer能够从头开始读取内容即offset=0开始。默认是最新的offset开始读取（即当前最大的offset开始）
                    props.put("enable.auto.commit", pc.getString("kafka.enable.auto.commit"));    
                    props.put("auto.commit.interval.ms", pc.getString("kafka.auto.commit.interval.ms"));    
                    props.put("session.timeout.ms", pc.getString("kafka.session.timeout.ms"));
                    props.put("key.deserializer", pc.getString("kafka.consumer.key.serializer"));    
                    props.put("value.deserializer", pc.getString("kafka.consumer.value.serializer"));    
                    kc = new KafkaConsumer<String, String>(props); 
        		}
        	}
        }    
        return kc;
    }
}  
