organization := "com.guizmaii"

name := "in_memory_geofla"

version := "0.1"

scalaVersion := "2.12.7"

scalafmtOnCompile := true
scalafmtCheck := true
scalafmtSbtCheck := true

libraryDependencies += "org.locationtech.jts" % "jts-core" % "1.16.0"
libraryDependencies += "io.estatico" %% "newtype" % "0.4.2"
libraryDependencies += "io.chrisdavenport" %% "selection" % "0.1.0"
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)

scalacOptions := scalacOptions.value.filter(_ != "-Xfatal-warnings")
