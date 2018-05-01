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

  props.put("bootstrap.servers", config.getString("BOOTSTRAP_SERVER"))
  props.put("key.deserializer", config.getString("DESERIALIZER"))
  props.put("value.deserializer", config.getString("VALUE_DESERIALIZER"))
  props.put("group.id", config.getString("GROUP_ID"))
  props.put("enable.auto.commit", "false")
  props.put("auto.offset.reset", config.getString("OFFSET"))

  val consumer = new KafkaConsumer[String, String](props)

  def readFromKafka(topic: String) {
    consumer.subscribe(Collections.singletonList(topic))
    while (true) {
      val records = consumer.poll(5000)
      for (record <- records.asScala) {
        log.info(s"received message- key: ${record.key} value: ${record.value} \n")
      }
    }
  }

}

object ConsumerMain extends App {
  val topic = ConfigFactory.load().getString("TOPIC")
  (new CustomConsumer).readFromKafka(topic)
}
