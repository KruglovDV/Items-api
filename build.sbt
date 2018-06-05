name := """items"""
organization := "home"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.4"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
libraryDependencies += "com.typesafe.play" %% "play-slick" % "3.0.0"
libraryDependencies += "com.h2database" % "h2" % "1.4.196"
libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.2.2",
  "org.slf4j" % "slf4j-nop" % "1.6.4"
)
libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.23"
