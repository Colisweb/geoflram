organization := "com.guizmaii"

name := "in_memory_geofla"

version := "0.1"

scalaVersion := "2.12.7"

scalafmtOnCompile := true
scalafmtCheck := true
scalafmtSbtCheck := true

libraryDependencies += "org.locationtech.jts" % "jts-core" % "1.16.0"
libraryDependencies += "org.geolatte" % "geolatte-geom" % "1.4.0"

scalacOptions := scalacOptions.value.filter(_ != "-Xfatal-warnings")
