package com.colisweb.geoflram.benchmark

import java.util.concurrent.TimeUnit

import com.colisweb.geoflram.Geoflram
import com.guizmaii.geofla.Geofla.Commune
import org.openjdk.jmh.annotations._

object FindByLatitudeLongitudeBenchmark {

  final case class GpsPoint(latitude: Double, longitude: Double)

  // GpsPoints found thanks to https://www.maps.ie/coordinates.html

  val `Rue de la Fondance, tertiary, Bois-d'Ennebourg, France` = GpsPoint(latitude = 49.4296517, longitude = 1.2554941)

  val `Rue des Huttes, tertiary, Coingt, France` = GpsPoint(latitude = 49.7847695, longitude = 4.0952504)

  val `Cimetière de Lourties-Monbrun, cemetery, Lourties-Monbrun, France` =
    GpsPoint(latitude = 43.45603285, longitude = 0.5390568833333329)

  val london = GpsPoint(latitude = 51.5073219, longitude = -0.1276474)

}

@Warmup(iterations = 10, time = 2000, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 2000, timeUnit = TimeUnit.MILLISECONDS)
@Fork(1)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
class FindByLatitudeLongitudeBenchmark {

  import FindByLatitudeLongitudeBenchmark._

  @Benchmark
  def firstCommuneOfTheList: Option[Commune] =
    Geoflram.findBy(
      `Cimetière de Lourties-Monbrun, cemetery, Lourties-Monbrun, France`.latitude,
      `Cimetière de Lourties-Monbrun, cemetery, Lourties-Monbrun, France`.longitude
    )

  @Benchmark
  def middleOfTheList: Option[Commune] =
    Geoflram.findBy(
      `Rue des Huttes, tertiary, Coingt, France`.latitude,
      `Rue des Huttes, tertiary, Coingt, France`.longitude
    )

  @Benchmark
  def lastCommuneOfTheList: Option[Commune] =
    Geoflram.findBy(
      `Rue de la Fondance, tertiary, Bois-d'Ennebourg, France`.latitude,
      `Rue de la Fondance, tertiary, Bois-d'Ennebourg, France`.longitude
    )

  @Benchmark
  def notInTheList: Option[Commune] = Geoflram.findBy(london.latitude, london.longitude)

}
