package com.guizmaii

import com.guizmaii.geofla.Geofla

object Main extends App {

  println(s"TEST: ${Geofla.findBy(43.45603285, 0.5390568833333329)}")

  println(s"TEST: ${Geofla.findByWithSpatialIndex(43.45603285, 0.5390568833333329)}")

  println("END")

}
