name := "kafka-producer-consumer"

version := "0.1"

scalaVersion := "2.11.2"
val sparkVersion = "2.4.5"


libraryDependencies ++= Seq(
	"org.apache.spark" %% "spark-core" % sparkVersion,
	"org.apache.spark" %% "spark-streaming" % sparkVersion,
	"org.apache.spark" %% "spark-sql" % sparkVersion,
	"org.apache.spark" %% "spark-repl" % sparkVersion,
	"org.apache.spark" %% "spark-streaming-kafka" % "1.6.3",
	"com.fasterxml.jackson.core" % "jackson-databind" % "2.4.5",
	"com.typesafe" % "config" % "1.3.3"

)