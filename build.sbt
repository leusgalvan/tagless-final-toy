scalaVersion := "2.13.1"

organization := "com.example"

lazy val `free-monad-toy` = (project in file("."))
  .settings(name := "Free Monad Toy")

libraryDependencies += "org.typelevel" %% "cats-core" % "2.1.0"
libraryDependencies += "org.typelevel" %% "cats-free" % "2.1.0"
libraryDependencies += "org.typelevel" %% "cats-effect" % "2.1.0"
libraryDependencies += "org.http4s" %% "http4s-blaze-server" % "0.21.1"
libraryDependencies += "org.http4s" %% "http4s-blaze-client" % "0.21.1"
libraryDependencies += "org.http4s" %% "http4s-circe" % "0.21.1"
libraryDependencies += "org.http4s" %% "http4s-dsl" % "0.21.1"
libraryDependencies += "io.circe" %% "circe-generic" % "0.13.0"
libraryDependencies += "org.specs2" %% "specs2-core" % "4.8.3" % Test
libraryDependencies += "org.specs2" %% "specs2-mock" % "4.8.3" % Test
