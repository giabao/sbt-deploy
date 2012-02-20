sbtPlugin := true

organization := "fi.reaktor"

name := "sbt-deploy"

version := "0.3.3-SNAPSHOT"

scalacOptions := Seq("-deprecation", "-unchecked")

resolvers += "sbt-plugin-releases" at "http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/"

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.7.2")

publishTo := Some(Resolver.file("Github Pages", file("../sbt-deploy-gh-pages/maven/")))
