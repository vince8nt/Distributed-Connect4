


name := "connect4"

version := "0.0.0"

scalaVersion := "2.13.7"
// scalaVersion := "2.3.1"

scalacOptions ++= Seq("-feature", "-deprecation", "-unchecked", "-Xlint", "-Ymacro-annotations")

// libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "latest.integration"
// libraryDependencies += "com.typesafe.akka" %% "akka-remote" % "latest.integration"
// libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % "latest.integration"

libraryDependencies +=  "io.github.scala-loci" %% "scala-loci-language" % "0.5.0" % "compile-internal"
libraryDependencies +=  "io.github.scala-loci" %% "scala-loci-language-runtime" % "0.5.0"
libraryDependencies +=  "io.github.scala-loci" %% "scala-loci-language-transmitter-rescala" % "0.5.0"
libraryDependencies +=  "io.github.scala-loci" %% "scala-loci-serializer-upickle" % "0.5.0"
libraryDependencies +=  "io.github.scala-loci" %% "scala-loci-communicator-tcp" % "0.5.0"


/*
dependencyOverrides +=  "io.github.scala-loci" %% "scala-loci-language_2.13" % "0.5.0" % "compile-internal"
dependencyOverrides +=  "io.github.scala-loci" %% "scala-loci-language-runtime_2.13" % "0.5.0"
dependencyOverrides +=  "io.github.scala-loci" %% "scala-loci-language-transmitter-rescala_2.13" % "0.5.0"
dependencyOverrides +=  "io.github.scala-loci" %% "scala-loci-serializer-upickle_2.13" % "0.5.0"
dependencyOverrides +=  "io.github.scala-loci" %% "scala-loci-communicator-tcp_2.13" % "0.5.0"
*/

/*
val scala3Version = "3.2.2"
lazy val root = project
  .in(file("."))
  .settings(
    name := "connect4",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,
    scalacOptions += "Ymacro-annotations", // had to remove leading "-", annoying

    // has to dependency override for these, annoying
    dependencyOverrides += "io.github.scala-loci" %% "scala-loci-language_3" % "0.5.0" % "compile-internal",
    dependencyOverrides += "io.github.scala-loci" %% "scala-loci-language-runtime_2.13" % "0.5.0",

    dependencyOverrides += "io.github.scala-loci" %% "scala-loci-transmitter-rescala_2.13" % "0.5.0"

  )
*/
