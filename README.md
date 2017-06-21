# SlickWarts

[![Build Status](https://travis-ci.org/danielnixon/slickwarts.svg?branch=master)](https://travis-ci.org/danielnixon/slickwarts)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.danielnixon/slickwarts_2.11/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.danielnixon/slickwarts_2.11)

[WartRemover](https://github.com/wartremover/wartremover) warts for [Slick](http://slick.typesafe.com/).

## Versions

| SlickWarts version | WartRemover version | Slick version | Scala version  |
|--------------------|---------------------|---------------|----------------|
| 0.3.0              | 2.0.2               | 3.2.0-RC1     | 2.11.8, 2.12.1 |
| 0.2.0              | 2.0.1               | 3.1.1         | 2.11.8         |

## Usage

1. Setup [WartRemover](http://www.wartremover.org/doc/install-setup.html).
2. Add the following to your `plugins.sbt`:

    ```scala
    addSbtPlugin("org.danielnixon" % "sbt-slickwarts" % "0.3.0")
    ```

3. Add the following to your `build.sbt`:
    ```scala
    wartremoverWarnings ++= Seq(
      SlickWart.BasicStreamingActionPartial)
    ```

## Warts

#### BasicStreamingActionPartial

`slick.profile.BasicStreamingAction` has a `head` method which will fail if the stream is empty (i.e. if the `SELECT` SQL query returned zero rows). Use `headOption` instead.

## See also

* [ExtraWarts](https://github.com/danielnixon/extrawarts): Extra WartRemover warts.
* [PlayWarts](https://github.com/danielnixon/playwarts):  WartRemover warts for [Play Framework](https://www.playframework.com/).
* [Scala.js Warts](https://github.com/danielnixon/scalajswarts):  WartRemover warts for [Scala.js](https://www.scala-js.org/).
