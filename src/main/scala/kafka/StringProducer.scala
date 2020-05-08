package kafka

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

import scala.util.Random

object StringProducer extends App {
	val host = Configuration.get[String]("kafka.host")
	val port = Configuration.get[String]("kafka.host")

	assert(host != null && port != null)

	val props = new Properties()
	props.put("bootstrap.servers", s"$host:$port")
	props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
	props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

	val producer = new KafkaProducer[String, String](props)
	val topic = Configuration.get[String]("producer.topic")

	try {
		while (true) {
			val record = new ProducerRecord[String, String](topic, "key", Random.nextString(10))
			producer.send(record)
			println(s"Sending record (key=${record.key()} value=${record.value()}) to $topic")
			Thread.sleep(5000)
		}
	} catch {
		case e: Exception => e.printStackTrace()
	} finally {
		producer.close()
	}
}
