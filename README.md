A simple-build-tool 0.11.x plugin for deploying a project
=========================================================

Overview
--------

The sbt-deploy plugin consists of a set of plugins that provide reusable tasks and methods for defining deploy sequences. A deploy sequence is a task that consists of one or more tasks that deploys a distribution to a execution environment. Starting and stopping the service, database migrations, etc. may be part of the deployment process as deploy sequence may consist of user defined tasks.

Installation and usage
----------------------

Create a file, `project/plugins/build.sbt`, for plugin library dependencies with the following lines:

```scala
  resolvers += "sbt-deploy-repo" at "http://jackywyz.github.com/sbt-deploy/maven"

  addSbtPlugin("fi.reaktor" %% "sbt-deploy" % "0.4.0")
```

Then, start using the plugin by amending your settings in the build as follows:

```scala
import sbt._
import Keys._

import sbt.deploy.BasicDeployPlugin
import sbt.deploy.BasicDeployPlugin._
import sbt.deploy.BasicDeployPlugin.{Keys => BDP}

object MyProjectBuild extends Build {
  lazy val envSettings = Seq(
    BDP.user := "user",
    BDP.host := "localhost",
    BDP.instDirParent := new File("/opt/my-project")
  )
  lazy val MyProject = Project(
    id = "my-project",
    base = file("."),
    settings = Defaults.defaultSettings ++ envSettings + basicDeploySettings
  )
}
```
