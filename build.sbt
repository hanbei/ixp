ThisBuild / organization := "de.hanbei"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.12.15"

import Dependencies._

lazy val ixpoo = (project in file("ixpoo"))
  .settings(
    name := "ixpoo",
    //idePackagePrefix := Some("de.hanbei")
    libraryDependencies ++= commonDependencies ++ ooDependencies
  )

lazy val ixpfp = (project in file("ixpfp"))
  .settings(
    name := "ixpfp",
    //idePackagePrefix := Some("de.hanbei")
    libraryDependencies ++= commonDependencies ++ fpDependencies
  )

lazy val root = (project in file("."))
  .aggregate(ixpoo, ixpfp)
  .settings(
    name := "ixp",
    //idePackagePrefix := Some("de.hanbei")
    libraryDependencies ++= commonDependencies
  )
