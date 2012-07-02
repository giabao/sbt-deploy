A simple-build-tool 0.11.x plugin for deploying a project
=========================================================

Overview
--------

The sbt-deploy plugin consists of a set of plugins that provide reusable tasks and methods for defining deploy sequences. A deploy sequence is a task that consists of one or more tasks that deploys a distribution to a execution environment. Starting and stopping the service, database migrations, etc. may be part of the deployment process as deploy sequence may consist of user defined tasks.

Installation and usage
----------------------

Create a file, `project/plugins.sbt`, for plugin library dependencies with the following lines:

```scala
  resolvers += "sbt-deploy-repo" at "http://jackywyz.github.com/sbt-deploy/maven"

  addSbtPlugin("fi.reaktor" %% "sbt-deploy" % "0.4.0")
```

Then, start using the plugin by amending your settings in the build as follows. Note that `deploySettings` defines a task <code>deploy</code>, which is a sequence of tasks that shall be run in the specified order constrolled by <code>dependsOn</code>.

```scala
import sbt._
import sbt.deploy.DeployDistPlugin

object MyProjectBuild extends Build {
  lazy val deploySettings =
    secureConnectivitySettings ++
    packageDistSettings ++
    deployDistSettings ++ Seq(
      user := prodEnvUser,
      host := prodEnvHost,
      instDirParent := prodEnvInstDirParent,
    ) ++ Seq(
      deployDist <<= deployDist dependsOn (deployInitScript),
      deploy <<= Seq(deployDist).dependOn
    )
  lazy val MyProject = Project(
    id = "my-project",
    base = file("."),
    settings = Defaults.defaultSettings ++ deploySettings
  )
  lazy val prodEnvUser = "user"
  lazy val prodEnvHostname = "localhost"
  lazy val prodEnvInstDirParent = new File("/opt/my-project")
}

```
