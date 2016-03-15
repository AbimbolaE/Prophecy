name := "prophecy"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.6" % "test" excludeAll(
    ExclusionRule("org.scala-lang.modules", "scala-xml_2.11"),
    ExclusionRule("org.scala-lang", "scala-reflect")
  )
)

coverageMinimum := 100

coverageFailOnMinimum := true
