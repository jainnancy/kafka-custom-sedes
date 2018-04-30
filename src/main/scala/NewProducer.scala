import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.log4j.Logger

object NewProducer {

  val log = Logger.getLogger(this.getClass)
    val props = new Properties()

    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "CustomSerializer")

  val producer = new KafkaProducer[String, Student](props)
  val student = new Student(1,"Nancy")

  val studentRecord = new ProducerRecord[String, Student]("StudentRecord", "key", student)

  producer.send(studentRecord)
  log.info(s"Student record has been sent")

  producer.close()
}
