name := "chart-viewer"

version := "1.0"

scalaVersion := "2.11.8"

updateOptions := updateOptions.value.withCachedResolution(true)

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  "Twitter Maven" at "https://maven.twttr.com"
)

libraryDependencies ++= Seq(
  "io.getquill" %% "quill-jdbc" % "1.0.0",
  "com.twitter" %% "finatra-http" % "2.5.0",
  "org.postgresql" % "postgresql" % "9.4.1208",
  "org.scala-lang.modules" %% "scala-async" % "0.9.5",
  "ch.qos.logback" % "logback-classic" % "1.1.7",
)

enablePlugins(JavaAppPackaging)
