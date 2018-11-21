import sbt.Keys.{homepage, libraryDependencies, licenses}

ThisBuild / organization := "com.colisweb"
ThisBuild / scalaVersion := "2.12.7"
ThisBuild / scalafmtOnCompile := true
ThisBuild / scalafmtCheck := true
ThisBuild / scalafmtSbtCheck := true

val testKitLibs = Seq(
  "org.scalacheck" %% "scalacheck" % "1.14.0",
  "org.scalactic"  %% "scalactic"  % "3.0.5",
  "org.scalatest"  %% "scalatest"  % "3.0.5",
).map(_ % Test)

lazy val root = Project(id = "geoflram", base = file("."))
  .settings(moduleName := "root")
  .settings(noPublishSettings: _*)
  .aggregate(core, jruby)
  .dependsOn(core, jruby)

lazy val core =
  project
    .settings(moduleName := "geoflram")
    .settings(
      addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full),
      libraryDependencies ++= Seq(
        "org.locationtech.jts" % "jts-core" % "1.16.0"
      ) ++ testKitLibs
    )

lazy val jruby =
  project
    .settings(moduleName := "geoflram-jruby")
    .settings(libraryDependencies ++= testKitLibs)
    .dependsOn(core)

lazy val benchmarks =
  project
    .enablePlugins(JmhPlugin)
    .settings(noPublishSettings: _*)
    .dependsOn(core)

/**
  * Copied from Cats
  */
def noPublishSettings = Seq(
  publish := {},
  publishLocal := {},
  publishArtifact := false
)

inThisBuild(
  List(
    credentials += Credentials(Path.userHome / ".bintray" / ".credentials"),
    licenses := Seq("MIT" -> url("http://opensource.org/licenses/MIT")),
    homepage := Some(url("https://github.com/colisweb/sbt-datadog")),
    bintrayOrganization := Some("colisweb"),
    bintrayReleaseOnPublish := true,
    publishMavenStyle := true,
    pomExtra := (
      <url>https://github.com/Colisweb/geoflram</url>
      <scm>
        <url>git@github.com:Colisweb/geoflram.git</url>
        <connection>scm:git:git@github.com:Colisweb/geoflram.git</connection>
      </scm>
      <developers>
        <developer>
          <id>guizmaii</id>
          <name>Jules Ivanic</name>
          <url>https://www.colisweb.com</url>
        </developer>
      </developers>
    )
  )
)

//// Aliases

/**
  * Copied from kantan.csv
  */
addCommandAlias("runBenchs", "benchmarks/jmh:run -i 10 -wi 10 -f 2 -t 1")

/**
  * Example of JMH tasks that generate flamegraphs.
  *
  * http://malaw.ski/2017/12/10/automatic-flamegraph-generation-from-jmh-benchmarks-using-sbt-jmh-extras-plain-java-too/
  */
addCommandAlias(
  "flame93",
  "benchmarks/jmh:run -f1 -wi 10 -i 20 PackerBenchmarkWithRealData -prof jmh.extras.Async:flameGraphOpts=--minwidth,2;verbose=true")
