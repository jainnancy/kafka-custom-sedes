package serde

import java.io.{ByteArrayOutputStream, ObjectOutputStream}
import java.util

import models.Student
import org.apache.kafka.common.serialization.Serializer

class CustomSerializer extends Serializer[Student] {

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {
  }

  override def serialize(topic: String, data: Student): Array[Byte] = {

    if (data == null)
      System.out.print("Now data to serialize")

    val byteOutputStream = new ByteArrayOutputStream()
    val objectSerialized = new ObjectOutputStream(byteOutputStream)
    objectSerialized.writeObject(data)
    byteOutputStream.toByteArray
  }

  override def close(): Unit = {
  }
}
