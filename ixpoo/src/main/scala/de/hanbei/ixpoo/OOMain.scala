package de.hanbei.ixpoo

import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.functions.col

object OOMain {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder
      .appName("test-me")
      .master("local")
      .getOrCreate()

    val dataFrame = spark.read.format("csv")
      .option("inferSchema", true)
      .option("header", true)
      .load("data/iris.data")

    dataFrame.withColumn("sepal_width", col("sepal_width") * 2)

    dataFrame.write.mode(SaveMode.Overwrite).parquet("data/output/iris-oo")

    dataFrame.printSchema()
    dataFrame.show()

    spark.close()
  }

}
