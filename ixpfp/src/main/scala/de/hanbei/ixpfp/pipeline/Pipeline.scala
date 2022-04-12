package de.hanbei.ixpfp.pipeline

import cats.data.Reader
import de.hanbei.ixpfp.reader.DataReader
import de.hanbei.ixpfp.transformer.DataTransformer
import de.hanbei.ixpfp.writer.DataWriter
import org.apache.spark.sql.{DataFrame, SparkSession}

class Pipeline(name: String, reader: DataReader, transformer: List[DataTransformer], writer: DataWriter) {

  def reader(dataReader: DataReader): Pipeline = new Pipeline(name, dataReader, transformer, writer)

  def writer(dataWriter: DataWriter): Pipeline = new Pipeline(name, reader, transformer, dataWriter)

  def transformer(dataTransformer: DataTransformer): Pipeline = new Pipeline(name, reader, dataTransformer :: transformer, writer)

  def run(): Unit = {
    val pipeline = for {
      df <- this.reader.read()
      df <- Reader[SparkSession, DataFrame] { spark => transformer.foldLeft(df) { (df, t) => t.transform(df).run(spark) } }
    } yield {
      writer.write(df)
    }
    pipeline.run(sparkSession("test"))
  }

  private def sparkSession(appName: String): SparkSession = {
    SparkSession.builder
      .appName(appName)
      .master("local")
      .getOrCreate()
  }
}

object Pipeline {
  def apply(name: String) = new Pipeline(name, null, List(), null)
}
