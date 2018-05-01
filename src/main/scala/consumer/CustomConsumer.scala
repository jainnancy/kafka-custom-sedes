package consumer

import java.util.{Collections, Properties}

import com.typesafe.config.ConfigFactory
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.log4j.Logger

import scala.collection.JavaConverters._

class CustomConsumer {

  val log = Logger.getLogger(this.getClass)
  val props = new Properties()
  val config = ConfigFactory.load()

<<<<<<< HEAD
  props.put("bootstrap.servers", "localhost:9092")
  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer", "serde.CustomDeserializer")
  props.put("group.id", "something")
  props.put("enable.auto.commit", "false")
  props.put("auto.offset.reset", "earliest")
=======
  props.put("bootstrap.servers", config.getString("BOOTSTRAP_SERVER"))
  props.put("key.deserializer", config.getString("DESERIALIZER"))
  props.put("value.deserializer", config.getString("VALUE_DESERIALIZER"))
  props.put("group.id", config.getString("GROUP_ID"))
  props.put("enable.auto.commit", "false")
  props.put("auto.offset.reset", config.getString("OFFSET"))
>>>>>>> f18ed88872b11bc8f03510391d5344f8c3053f73

  val consumer = new KafkaConsumer[String, String](props)

  def readFromKafka(topic: String) {
    consumer.subscribe(Collections.singletonList(topic))
    while (true) {
      val records = consumer.poll(5000)
      for (record <- records.asScala) {
        log.info("received message")
        log.info(record.value)
      }
    }
  }

}

object ConsumerMain extends App {
  val topicName = "demo-topic"
  (new CustomConsumer).readFromKafka(topicName)
}
