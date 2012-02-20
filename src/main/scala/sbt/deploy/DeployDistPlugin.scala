package sbt.deploy

import sbt._
import Keys._

import SecureConnectivityPlugin._
import sbtassembly.Plugin._
import AssemblyKeys._

object DeployDistPlugin extends Plugin {
  object Keys {
    val instPkgPath = TaskKey[File]("inst-pkg-path")
    val instDirParent = SettingKey[File]("inst-dir-parent")
    val instDir = TaskKey[File]("inst-dir")
    val latestInstDir = TaskKey[File]("latest-inst-dir")
    val instJarPath = TaskKey[File]("inst-jar-path")
    val latestInstJarPath = TaskKey[File]("latest-inst-jar-path")
  }
  import Keys._
  lazy val deployDistSettings = Seq(
    instDir <<= (instDirParent, name, version, scalaVersion) map { (parent, name, version, scalaVersion) =>
      new File(parent, "%s_%s-%s".format(name, scalaVersion, version))
    },
    instPkgPath <<= (instDir, assembly) map { (instDir, pkgPath) =>
      new File(instDir, pkgPath.getName)
    },
    latestInstDir <<= (instDir, name) map { (instDir, name) =>
      new File(instDir.getParent, name)
    },
    instJarPath <<= (instDir) map { (instDir) =>
      new File(instDir, instDir.getName + ".jar")
    },
    latestInstJarPath <<= (latestInstDir, name) map { (latestInstDir, name) =>
      new File(latestInstDir, name + ".jar")
    }
  )
}
