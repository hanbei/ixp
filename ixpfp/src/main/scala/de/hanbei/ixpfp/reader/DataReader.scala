package de.hanbei.ixpfp.reader

import cats.data.Reader
import org.apache.spark.sql.{DataFrame, SparkSession}

trait DataReader {

  def read(): Reader[SparkSession, DataFrame]

}
