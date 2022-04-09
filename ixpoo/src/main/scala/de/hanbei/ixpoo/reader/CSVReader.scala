package de.hanbei.ixpoo.reader

import org.apache.spark.sql.DataFrame


class CSVReader(path: String, header: Boolean = true, inferSchema: Boolean = true) extends DataReader {

  override def read(): DataFrame = {
    spark.read.format("csv")
      .option("inferSchema", inferSchema)
      .option("header", header)
      .load(path)
  }


}
