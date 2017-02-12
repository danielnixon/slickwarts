import scalariform.formatter.preferences._

lazy val commonSettings = Seq(
  organization := "org.danielnixon",
  licenses := Seq("The Apache Software License, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt")),
  version := "0.2.0",
  publishMavenStyle := true,
  publishArtifact in Test := false,
  publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
  },
  homepage := Some(url("https://github.com/danielnixon/slickwarts")),
  pomExtra := {
    <scm>
      <url>git@github.com:danielnixon/slickwarts.git</url>
      <connection>scm:git:git@github.com:danielnixon/slickwarts.git</connection>
    </scm>
      <developers>
        <developer>
          <id>danielnixon</id>
          <name>Daniel Nixon</name>
          <url>https://danielnixon.org/</url>
        </developer>
      </developers>
  },
  coverageMinimum := 86,
  coverageFailOnMinimum := true,
  scalariformPreferences := scalariformPreferences.value
    .setPreference(DoubleIndentClassDeclaration, true)
    .setPreference(PlaceScaladocAsterisksBeneathSecondAsterisk, true),
  scalacOptions ++= Seq(
    "-deprecation",
    "-feature",
    "-unchecked",
    "-Xfatal-warnings",
    "-Ywarn-dead-code",
    "-Ywarn-inaccessible",
    "-Ywarn-value-discard",
    "-Ywarn-numeric-widen",
    "-Ywarn-nullary-override")
)

val coreName = "slickwarts"
val wartremoverVersion = "2.0.1"
val scalatestVersion = "3.0.1"

lazy val root = Project(
  id = "root",
  base = file("."),
  aggregate = Seq(core, sbtPlug)
).settings(commonSettings ++ Seq(
  publishArtifact := false,
  scalaVersion := "2.11.8"
): _*)

lazy val core = Project(
  id = "core",
  base = file("core")
).settings(commonSettings ++ Seq(
  name := coreName,
  scalaVersion := "2.11.8",
  libraryDependencies ++= Seq(
    "org.wartremover" %% "wartremover" % wartremoverVersion,
    "org.scalatest" %% "scalatest" % scalatestVersion % Test,
    "com.typesafe.slick" %% "slick" % "3.1.1" % Test
  ),
  dependencyOverrides ++= Set(
    "org.scalatest" %% "scalatest" % scalatestVersion
  ),
  scalacOptions ++= Seq("-Xlint:_", "-Ywarn-unused", "-Ywarn-unused-import")
): _*)

lazy val sbtPlug: Project = Project(
  id = "sbt-plugin",
  base = file("sbt-plugin")
).enablePlugins(
  BuildInfoPlugin
).disablePlugins(
  ScoverageSbtPlugin
).settings(commonSettings ++ Seq(
  buildInfoKeys := Seq[BuildInfoKey](version, organization, "artifactID" -> coreName),
  buildInfoPackage := s"${organization.value}.$coreName",
  sbtPlugin := true,
  name := s"sbt-$coreName",
  scalaVersion := "2.10.6",
  addSbtPlugin("org.wartremover" %% "sbt-wartremover" % wartremoverVersion),
  scalacOptions += "-Xlint"
): _*)

addCommandAlias("publishLocalCoverageOff", ";clean;coverageOff;compile;test;publishLocal")
addCommandAlias("publishSignedCoverageOff", ";clean;coverageOff;compile;test;publishSigned")