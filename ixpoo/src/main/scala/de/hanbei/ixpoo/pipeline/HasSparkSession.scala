package de.hanbei.ixpoo.pipeline

import org.apache.spark.SparkException
import org.apache.spark.sql.SparkSession

trait HasSparkSession {

  val spark: SparkSession = SparkSession.getActiveSession match {
    case Some(ss) => ss
    case _ => throw new SparkException("No active Spark session")
  }
}
