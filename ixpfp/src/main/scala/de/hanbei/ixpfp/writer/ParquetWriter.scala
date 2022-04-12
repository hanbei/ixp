package de.hanbei.ixpfp.writer

import org.apache.spark.sql.{DataFrame, SaveMode}

class ParquetWriter(path: String) extends DataWriter {

  override def write(dataFrame: DataFrame): DataFrame = {
    dataFrame.write.mode(SaveMode.Overwrite).parquet(path)
    dataFrame
  }
}
