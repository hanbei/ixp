package de.hanbei.ixpfp.reader

import cats.data.Reader
import org.apache.spark.sql.{DataFrame, SparkSession}

class CSVReader(path: String, header: Boolean = true, inferSchema: Boolean = true) extends DataReader {

  override def read(): Reader[SparkSession, DataFrame] = Reader[SparkSession, DataFrame] { spark =>
    spark.read.format("csv")
      .option("inferSchema", inferSchema)
      .option("header", header)
      .load(path)
  }
}
