package producer

import java.util.Properties

import Models.Student
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.log4j.Logger

object CustomProducer {

  val log = Logger.getLogger(this.getClass)
  val props = new Properties()

  props.put("bootstrap.servers", "localhost:9092")
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "serde.CustomSerializer")

  val producer = new KafkaProducer[String, Student](props)
  val student = new Student(1, "Nancy")
  writeToKafka(student)

  def writeToKafka(student: Student) {
    val studentRecord = new ProducerRecord[String, Student]("StudentRecord", "key", student)
    producer.send(studentRecord)
    log.info(s"Models.Student record has been sent")
  }

  producer.close()
}
