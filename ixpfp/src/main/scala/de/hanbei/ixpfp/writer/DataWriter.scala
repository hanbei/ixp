package de.hanbei.ixpfp.writer

import org.apache.spark.sql.DataFrame

trait DataWriter {

  def write(dataFrame: DataFrame): DataFrame

}
