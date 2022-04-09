package de.hanbei.ixpoo

import de.hanbei.ixpoo.pipeline._
import de.hanbei.ixpoo.reader.CSVReader
import de.hanbei.ixpoo.writer.ParquetWriter
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.col

object OOMain {

  def main(args: Array[String]): Unit = {
    Pipeline("test-oo")
      .reader(new CSVReader(path = "data/iris.data", header = true, inferSchema = true))
      .transform(doubleStuff)
      .transform(printStuff)
      .writer(new ParquetWriter("data/output/iris-oo"))
      .run()
  }

  private def printStuff(dataFrame: DataFrame): DataFrame = {
    dataFrame.printSchema()
    dataFrame.show()
    dataFrame
  }

  private def doubleStuff(dataFrame: DataFrame): DataFrame = {
    dataFrame.withColumn("sepal_width", col("sepal_width") * 0)
  }
}
