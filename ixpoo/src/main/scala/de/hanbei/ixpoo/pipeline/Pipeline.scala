package de.hanbei.ixpoo.pipeline

import de.hanbei.ixpoo.reader.DataReader
import de.hanbei.ixpoo.transformer.DataTransformer
import de.hanbei.ixpoo.writer.DataWriter
import org.apache.spark.sql.SparkSession

import scala.collection.mutable.ListBuffer

class Pipeline(name: String) {


  private var reader: Option[DataReader] = None
  private var writer: Option[DataWriter] = None
  private var transformer: ListBuffer[DataTransformer] = ListBuffer()

  private val spark = SparkSession.builder
    .appName(name)
    .master("local")
    .getOrCreate()

  def reader(reader: DataReader): Pipeline = {
    this.reader = Some(reader)
    this
  }

  def transform(transformer: DataTransformer): Pipeline = {
    this.transformer += transformer
    this
  }


  def writer(writer: DataWriter): Pipeline = {
    this.writer = Some(writer)
    this
  }


  def run(): Unit = {
    try {
      val maybeFrame = reader.map(r => r.read()).getOrElse(spark.emptyDataFrame)
      val transformedFrame = transformer.foldLeft(maybeFrame)((dataFrame, transformer) => transformer.transform(dataFrame))
      writer.foreach(writer => writer.write(transformedFrame))
    } catch {
      case e: Throwable => IXPException(e)
    }
    finally {
      spark.close()
    }
  }


}

object Pipeline {

  def apply(name: String): Pipeline = new Pipeline(name)

}
