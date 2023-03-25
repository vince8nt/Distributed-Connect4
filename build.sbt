scalacOptions += "-Ymacro-annotations"
libraryDependencies ++= Seq(
  "io.github.scala-loci" %% "scala-loci-language" % "0.5.0" % "compile-internal",
  "io.github.scala-loci" %% "scala-loci-language-runtime" % "0.5.0")

