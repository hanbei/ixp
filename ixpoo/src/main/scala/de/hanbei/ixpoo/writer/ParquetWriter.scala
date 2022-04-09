package de.hanbei.ixpoo.writer

import org.apache.spark.sql.{DataFrame, SaveMode}

class ParquetWriter(path: String) extends DataWriter {

  override def write(dataFrame: DataFrame): Unit = {
    dataFrame.write.mode(SaveMode.Overwrite).parquet(path)
  }

}
