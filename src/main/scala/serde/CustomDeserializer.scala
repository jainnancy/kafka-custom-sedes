package serde

import java.io.{ByteArrayInputStream, ObjectInputStream}
import java.util

import models.Student
import org.apache.kafka.common.serialization.Deserializer

class CustomDeserializer extends Deserializer[Student] {

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {
  }

  override def close(): Unit = {
  }

  override def deserialize(topic: String, data: Array[Byte]): Student = {
    if (data == null)
      System.out.println("Null received at deserialize")
    val byteInputStream = new ByteArrayInputStream(data)
    val inputObject = new ObjectInputStream(byteInputStream)
    val objectDeserialized = inputObject.readObject().asInstanceOf[Student]
    objectDeserialized
  }
}
