package producer

import java.util.Properties

import com.typesafe.config.ConfigFactory
import models.Student
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.log4j.Logger

class CustomProducer {

  val log = Logger.getLogger(this.getClass)
  val props = new Properties()
<<<<<<< HEAD

  props.put("bootstrap.servers", "localhost:9092")
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "serde.CustomSerializer")

  val producer = new KafkaProducer[String, Student](props)
  val student = new Student(1, "Nancy")
  writeToKafka(student)
=======
  val config = ConfigFactory.load()

  props.put("bootstrap.servers", config.getString("BOOTSTRAP_SERVER"))
  props.put("key.serializer", config.getString("SERIALIZER"))
  props.put("value.serializer", config.getString("VALUE_SERIALIZER"))

  val producer = new KafkaProducer[String, Student](props)
>>>>>>> f18ed88872b11bc8f03510391d5344f8c3053f73

  def writeToKafka(topic: String, student: Student) {
    val studentRecord = new ProducerRecord[String, Student](topic, "key", student)
    producer.send(studentRecord)
    log.info(s"Student record has been sent")
  }

  producer.close()
}

object ProducerMain extends App {
  val topicName = "demo-topic"
  val student = new Student(1, "Nancy")
  (new CustomProducer).writeToKafka(topicName, student)
}