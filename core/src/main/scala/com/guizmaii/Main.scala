package com.guizmaii

import com.guizmaii.geofla.Geofla

object Main extends App {

  val found = Geofla.findBy(43.45603285, 0.5390568833333329)

  println(s"TEST: ${found}")

  println("END")

}
