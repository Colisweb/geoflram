package com.guizmaii

import com.guizmaii.geofla.Geofla

object Main extends App {

  val lat  = 43.45603285
  val long = 0.5390568833333329

  val a = Geofla.findBy(lat, long)
  val b = Geofla.findByWithSpatialIndex(lat, long)

  assert(a == b)

  println(s"TEST: ${a}")

  println(s"TEST: ${b}")

  println("END")

}
