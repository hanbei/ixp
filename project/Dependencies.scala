import sbt._

object Dependencies {

  lazy val sparkVersion = "3.2.1"
  val spark = "org.apache.spark" %% "spark-core" % sparkVersion
  val sparkSql = "org.apache.spark" %% "spark-sql" % sparkVersion
  val cats = "org.typelevel" %% "cats-core" % "2.7.0"
  val catsEffects = "org.typelevel" %% "cats-effect" % "3.3.9"

  val commonDependencies = Seq(
    spark,
    sparkSql
  )

  val ooDependencies = Seq()

  val fpDependencies = Seq(
    cats,
    catsEffects,
  )

}
