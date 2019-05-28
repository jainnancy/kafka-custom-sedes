package com.knoldus.producer

import java.util.Properties

import com.knoldus.models.{Student, Teacher}
import com.knoldus.ApplicationConfig._
import com.typesafe.config.ConfigFactory
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.log4j.Logger

class CustomProducer {

  val log = Logger.getLogger(this.getClass)
  val props = new Properties()
  val config = ConfigFactory.load()

  props.put("bootstrap.servers", bootstrapServer)
  props.put("key.serializer", keySerializer)
  props.put("value.serializer", valueSerializer)

  val producer = new KafkaProducer[String, Student](props)
  val teacherProducer = new KafkaProducer[String, Teacher](props)
  val stringProducer = new KafkaProducer[String, String](props)

  /**
    * This method will write data to given topic.
    * @param topic String
    */
  def writeToKafka(topic: String) {
    for (i <- 1 to 10)
      producer.send(new ProducerRecord[String, Student](topic, i.toString, Student(i, s"name-$i")))
    log.info(s"student Record has been written to kafka.\n")

    for (i <- 1 to 10)
      teacherProducer.send(new ProducerRecord[String, Teacher](topic, i.toString, Teacher(i, s"Teacher-$i", "Commerce")))
    log.info(s"Teachers Records has been written to kafka.\n")

    stringProducer.send(new ProducerRecord[String, String](topic, "hk", "hellooooo"))

    producer.close()
  }

}

object ProducerMain extends App {
  val topic = topicName

  (new CustomProducer).writeToKafka(topic)
}
