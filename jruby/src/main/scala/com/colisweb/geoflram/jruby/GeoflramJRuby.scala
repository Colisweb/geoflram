package com.colisweb.geoflram.jruby
import com.colisweb.geoflram.Geoflram
import com.colisweb.geoflram.Geoflram.Commune

object GeoflramJRuby {

  def findBy(latitude: Double, longitude: Double): Commune = Geoflram.findBy(latitude, longitude).orNull

}
