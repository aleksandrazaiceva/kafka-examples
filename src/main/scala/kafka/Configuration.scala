package kafka

import com.typesafe.config.{Config, ConfigFactory}

import scala.reflect.ClassTag

object Configuration {
	val conf: Config = ConfigFactory.load()

	def get[T: ClassTag](name: String): T = {
		conf.getAnyRef(name).asInstanceOf[T]
	}
}


