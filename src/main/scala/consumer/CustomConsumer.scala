package consumer

import java.util.{Collections, Properties}

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.log4j.Logger

import scala.collection.JavaConverters._

object CustomConsumer {

  val log = Logger.getLogger(this.getClass)
  val props = new Properties()

  props.put("bootstrap.servers", "localhost:9092")
  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer", "serde.CustomDeserializer")
  props.put("group.id", "something")
  props.put("enable.auto.commit","false")
  props.put("auto.offset.reset","earliest")

  val consumer = new KafkaConsumer[String, String](props)

  consumer.subscribe(Collections.singletonList("topic"))
  readFromKafka()

  def readFromKafka() {
    while (true) {
      val records = consumer.poll(5000)
      for (record <- records.asScala) {
        log.info("received message")
        log.info(record.value)
      }
    }
  }

}
