# Geoflram

[![Build Status](https://travis-ci.org/Colisweb/geoflram.svg?branch=master)](https://travis-ci.org/Colisweb/geoflram)
[![codecov](https://codecov.io/gh/Colisweb/geoflram/branch/master/graph/badge.svg)](https://codecov.io/gh/Colisweb/geoflram)

Installation
------------

Add the following to your `build.sbt` file:

```scala
libraryDependencies += "com.colisweb" %% "geoflram" % "1.1.2"
```

Use
---

```scala
import com.colisweb.geoflram.Geoflram
import com.colisweb.geoflram.Geoflram.Commune

val found: Option[Commune] = Geoflram.findBy(43.45603285, 0.5390568833333329)
```