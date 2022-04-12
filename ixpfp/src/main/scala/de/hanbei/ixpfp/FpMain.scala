package de.hanbei.ixpfp

import cats.Id
import cats.data.{Kleisli, Reader}
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

object FpMain {

  def read() = Reader[SparkSession, DataFrame] { spark => {
    println("Read")
    spark.read.format("csv")
      .option("inferSchema", true)
      .option("header", true)
      .load("data/iris.data")
  }
  }

  def transform1(dataFrame: DataFrame) = Reader[SparkSession, DataFrame] {
    spark => {
      println("Transform1")
      dataFrame.withColumn("sepal_width", col("sepal_width") * 2)
    }
  }

  def transform2(dataFrame: DataFrame) = Reader[SparkSession, DataFrame] {
    spark => {
      println("Transform2")
      dataFrame.printSchema()
      dataFrame.show()
      dataFrame
    }
  }

  def write(dataFrame: DataFrame): DataFrame = { //Reader[SparkSession, DataFrame] {
    println("Write")
    dataFrame.write.mode(SaveMode.Overwrite).parquet("data/output/iris-fp")
    dataFrame
  }


  def main(args: Array[String]): Unit = {
    val pipeline: Kleisli[Id, SparkSession, DataFrame] = for {
      df1 <- read()
      df2 <- transform1(df1)
      df3 <- transform2(df2)
    } yield {
      write(df3)
    }
    pipeline.run(sparkSession("test"))
  }


  private def sparkSession(appName: String): SparkSession =
    SparkSession.builder
      .appName(appName)
      .master("local")
      .getOrCreate()
}
