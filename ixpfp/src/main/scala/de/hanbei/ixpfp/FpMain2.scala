package de.hanbei.ixpfp

import cats.data.Reader
import de.hanbei.ixpfp.pipeline.Pipeline
import de.hanbei.ixpfp.reader.CSVReader
import de.hanbei.ixpfp.writer.ParquetWriter
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.col

object FpMain2 {
  def main(args: Array[String]): Unit = {
    Pipeline("test-oo")
      .reader(new CSVReader(path = "data/iris.data", header = true, inferSchema = true))
      .transformer(doubleStuff)
      .transformer(printStuff)
      .writer(new ParquetWriter("data/output/iris-oo"))
      .run()
  }

  private def printStuff(dataFrame: DataFrame): Reader[SparkSession, DataFrame] = Reader[SparkSession, DataFrame] { spark =>
    dataFrame.printSchema()
    dataFrame.show()
    dataFrame
  }

  private def doubleStuff(dataFrame: DataFrame): Reader[SparkSession, DataFrame] = Reader[SparkSession, DataFrame] { spark =>
    dataFrame.withColumn("sepal_width", col("sepal_width") * 0)
  }
}
