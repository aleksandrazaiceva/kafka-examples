package kafka

import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object StringConsumer extends App {
	val sparkConfig = new SparkConf().setMaster("local[2]").setAppName("Spark Kafka Test")
	val streamingContext = new StreamingContext(sparkConfig, Seconds(5))

	val host = Configuration.get[String]("kafka.host")
	val port = Configuration.get[String]("kafka.host")

	val interval = 5
	val topics = Set("test")
	val kafkaParams = Map[String, String](
		"metadata.broker.list" -> s"${host}:${port}",
		"serializer.class" -> "kafka.serializer.StringEncoder"
	)

	val kafkaStream = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](streamingContext, kafkaParams, topics)

	kafkaStream.foreachRDD { rdd =>
		rdd.foreachPartition(records => {
			while (records.hasNext) {
				val message = records.next()
				println("Got message:" + message)
			}
		})
	}

	streamingContext.start()
	streamingContext.awaitTermination()
}
