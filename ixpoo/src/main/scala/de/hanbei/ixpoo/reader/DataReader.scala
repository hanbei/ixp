package de.hanbei.ixpoo.reader

import de.hanbei.ixpoo.pipeline.HasSparkSession
import org.apache.spark.sql.DataFrame

trait DataReader extends HasSparkSession {

  def read(): DataFrame

}
