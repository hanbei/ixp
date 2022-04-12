package de.hanbei.ixpfp.transformer

import cats.data.Reader
import org.apache.spark.sql.{DataFrame, SparkSession}

trait DataTransformer {

  def transform(dataFrame: DataFrame): Reader[SparkSession, DataFrame]

}
