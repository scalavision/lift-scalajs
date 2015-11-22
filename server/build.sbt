name := "Lift-ScalaJs-Server"

scalaVersion := "2.11.7"

resolvers ++= Seq("snapshots"     at "https://oss.sonatype.org/content/repositories/snapshots",
                  "staging"       at "https://oss.sonatype.org/content/repositories/staging",
                  "releases"      at "https://oss.sonatype.org/content/repositories/releases"
                 )

scalacOptions ++= Seq("-deprecation", "-unchecked")

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

val rogueField      = "com.foursquare" %% "rogue-field"         % "2.5.0" intransitive()
val rogueCore       = "com.foursquare" %% "rogue-core"          % "2.5.1" intransitive()
val rogueLift       = "com.foursquare" %% "rogue-lift"          % "2.5.1" intransitive()
val rogueIndex      = "com.foursquare" %% "rogue-index"         % "2.5.1" intransitive()

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

val jetty = "9.2.9.v20150224"

enablePlugins(JettyPlugin)

libraryDependencies ++= {
  val liftVersion = "3.0-M6"
  val rogueVersion = "2.5.1"
  Seq(
    rogueField,
    rogueCore,
    rogueLift,
    rogueIndex,
    "net.liftweb"       %% "lift-json"               % liftVersion % "compile",
    "net.liftweb"       %% "lift-mongodb-record"     % liftVersion,
    "net.liftmodules"   %% "lift-jquery-module_3.0"  % "2.9",
    "net.liftmodules"   %% "extras_3-0"              % "0.5-SNAPSHOT",
    "org.eclipse.jetty" % "jetty-server"             % jetty,
    "org.eclipse.jetty" % "jetty-webapp"             % jetty        % "container,test", // For Jetty Config
    "org.eclipse.jetty" % "jetty-plus"               % jetty        % "container,test", // For Jetty Config
//    "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container,test" artifacts Artifact("javax.servlet", "jar", "jar"),
    "javax.servlet"     % "javax.servlet-api"        % "3.0.1"      % "provided",
    "ch.qos.logback"    % "logback-classic"          % "1.0.6",
    "org.specs2"        %% "specs2-core"             % "3.6.5"      % "test"
  )
}
