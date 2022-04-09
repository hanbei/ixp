package de.hanbei.ixpoo.transformer

import org.apache.spark.sql.DataFrame

trait DataTransformer {

  def transform(dataFrame: DataFrame): DataFrame

}
