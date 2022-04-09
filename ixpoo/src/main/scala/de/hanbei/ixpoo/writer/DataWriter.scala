package de.hanbei.ixpoo.writer

import org.apache.spark.sql.DataFrame

trait DataWriter {

  def write(dataFrame: DataFrame): Unit

}
