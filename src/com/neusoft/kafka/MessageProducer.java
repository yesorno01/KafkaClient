package com.neusoft.kafka;

import java.util.Random;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.neusoft.common.utils.Topic;


public class MessageProducer extends Thread {
	private static Random random = new Random();
	public static void main(String[] args) throws InterruptedException {
		Producer<String, String> producer = KafkaUtil.getProducer();
		int i = 0;
		while (true) {
			ProducerRecord<String, String> record = new ProducerRecord<String, String>(Topic.CPC_MATCH, String.valueOf(i), getRandomString());
//			producer.send(record);
			producer.send(record, new Callback() {
				public void onCompletion(RecordMetadata metadata, Exception e) {
					if (e != null){
					    e.printStackTrace();
					}
					System.out.println("message send to partition " + metadata.partition() + ", offset: " + metadata.offset());
				}
			});
			i++;
			//Thread.sleep(1000);
		}
	}
	public static String getRandomString(){
		String[] sentences = new String[] {
				"jikexueyuan is a good school",
				"And if the golden sun",
				"four score and seven years ago",
				"storm hadoop spark hbase",
				"blogchong is a good man",
				"Would make my whole world bright",
				"blogchong is a good website",
				"storm would have to be with you",
				"Pipe to subprocess seems to be broken No output read",
				" You make me feel so happy",
				"For the moon never beams without bringing me dreams Of the beautiful Annalbel Lee",
				"Who love jikexueyuan and blogchong",
				"blogchong.com is Magic sites",
				"Ko blogchong swayed my leaves and flowers in the sun",
				"You love blogchong.com", "Now I may wither into the truth",
				"That the wind came out of the cloud",
				"at backtype storm utils ShellProcess",
				"Of those who were older than we" };

		String sentence = sentences[random.nextInt(sentences.length)];
		return sentence;
	}
}
