name := "Lift-ScalaJs-Example"

val commonSettings = Seq(
  version := "0.1-SNAPSHOT",
  organization := "com.obx",
  scalaVersion := "2.11.7",
  EclipseKeys.withSource := true,
  EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource,
  resolvers ++= Seq(
    "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
    "Java.net Maven2 Repository"     at "http://download.java.net/maven/2/",
    "Sonatype scala-tools repo"      at "https://oss.sonatype.org/content/groups/scala-tools/",
    "Sonatype scala-tools releases"  at "https://oss.sonatype.org/content/repositories/releases",
    "Sonatype scala-tools snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
  ),
  scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8"),
  scalacOptions in Test ++= Seq("-Yrangepos"),
  libraryDependencies ++= Seq(
    "org.specs2" %% "specs2-core" % "3.6" % "test", 
    "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
  )
)

lazy val model = crossProject.crossType(CrossType.Pure).in(file("model"))
  .settings(commonSettings: _*) 
  .settings(name := "Lift-ScalaJs-Model")

lazy val modelJvm = model.jvm
lazy val modelJS = model.js

lazy val api = project.in(file("api"))
  .settings(commonSettings: _*)
  .settings(name := "Lift-ScalaJs-Api")

lazy val server = project.in(file("server"))
  .settings(commonSettings: _*)
  .settings(name := "Lift-ScalaJs-Server")
  .enablePlugins(JettyPlugin)
  .dependsOn(modelJvm, api)

lazy val web = project.in(file("web"))
  .settings(commonSettings: _*)
  .settings(name := "Lift-ScalaJs-WebClient")
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(modelJS, api)

EclipseKeys.useProjectId := true
