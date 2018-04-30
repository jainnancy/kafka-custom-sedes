name := "kafka-custom-sedes"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies  +=  "org.apache.kafka" % "kafka-clients" % "0.11.0.0"

libraryDependencies += "log4j" % "log4j" % "1.2.17"

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"

libraryDependencies += "org.slf4j" % "slf4j-log4j12" % "1.8.0-beta1" % Test