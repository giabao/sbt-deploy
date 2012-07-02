sbtPlugin := true

organization := "fi.reaktor"

name := "sbt-deploy"

version := "0.4.0"

scalacOptions := Seq("-deprecation", "-unchecked")

publishTo := Some(Resolver.file("Github Pages", file("../sbt-deploy-gh-pages/maven/")))
