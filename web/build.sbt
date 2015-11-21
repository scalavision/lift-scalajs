scalaJSStage in Global := FastOptStage

skip in packageJSDependencies := false

jsDependencies += RuntimeDOM

jsDependencies += "org.webjars" % "react" % "0.13.3" / "react-with-addons.js" commonJSName "React"

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.8.0"

libraryDependencies += "be.doeraene" %%% "scalajs-jquery" % "0.8.0"

libraryDependencies += "com.github.japgolly.scalajs-react" %%% "core" % "0.9.0"

libraryDependencies += "com.github.japgolly.scalajs-react" %%% "extra" % "0.9.0"

libraryDependencies += "com.github.japgolly.scalajs-react" %%% "ext-scalaz71" % "0.9.0"

libraryDependencies += "com.github.japgolly.scalajs-react" %%% "ext-monocle" % "0.9.0"

libraryDependencies += "com.github.japgolly.scalacss" %%% "ext-react" % "0.3.0"

libraryDependencies += "com.lihaoyi" %%% "utest" % "0.3.1" % "test"

libraryDependencies += "com.lihaoyi" %%% "scalatags" % "0.5.2"

libraryDependencies += "com.lihaoyi" %%% "scalarx" % "0.2.8"

//libraryDependencies += "com.github.benhutchison" %%% "prickle" % "1.1.9"

libraryDependencies += "com.lihaoyi" %%% "upickle" % "0.3.6"

//libraryDependencies += "org.webjars" %%% "log4javascript" % "1.4.13" / "js/log4javascript_uncompressed.js"

testFrameworks += new TestFramework("utest.runner.Framework")

//libraryDependencies ++= Def.setting(Seq(
//  
//)).value
