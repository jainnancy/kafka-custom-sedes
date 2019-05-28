package com.knoldus.serde

import java.io.{ByteArrayOutputStream, ObjectOutputStream}
import java.util
import org.apache.log4j.Logger

import com.knoldus.models.Student
import org.apache.kafka.common.serialization.Serializer

class CustomSerializer[T] extends Serializer[T] {

  val log = Logger.getLogger(this.getClass)

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {
  }

  override def serialize(topic: String, data: T): Array[Byte] = {
    log.info("Calling custom serializer")
    try {
      val byteOutputStream = new ByteArrayOutputStream()
      val objectSerialized = new ObjectOutputStream(byteOutputStream)
      objectSerialized.writeObject(data)
      byteOutputStream.toByteArray
    }
    catch {
      case ex: Exception => throw new Exception(ex.getMessage)
    }
  }

  override def close(): Unit = {
  }

}
