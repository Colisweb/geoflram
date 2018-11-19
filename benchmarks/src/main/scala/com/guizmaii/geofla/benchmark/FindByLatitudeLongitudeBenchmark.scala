package com.guizmaii.geofla.benchmark
import java.util.concurrent.TimeUnit

import com.guizmaii.geofla.Geofla
import com.guizmaii.geofla.Geofla.Commune
import org.openjdk.jmh.annotations._

object FindByLatitudeLongitudeBenchmark {

  final case class GpsPoint(latitude: Double, longitude: Double)

  // GpsPoints found thanks to https://www.maps.ie/coordinates.html

  val `Rue de la Fondance, tertiary, Bois-d'Ennebourg, France` = GpsPoint(latitude = 49.4296517, longitude = 1.2554941)

  val `Cimetière de Lourties-Monbrun, cemetery, Lourties-Monbrun, France` =
    GpsPoint(latitude = 43.45603285, longitude = 0.5390568833333329)

  val london = GpsPoint(latitude = 51.5073219, longitude = -0.1276474)

}

@Warmup(iterations = 10, time = 2000, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 2000, timeUnit = TimeUnit.MILLISECONDS)
@Fork(1)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
class WithSpatialIndexFindByLatitudeLongitudeBenchmark {

  import FindByLatitudeLongitudeBenchmark._

  @Benchmark
  def inTheFirstCommuneOfTheList: Option[Commune] =
    Geofla.withSpatialIndexFindBy(
      `Cimetière de Lourties-Monbrun, cemetery, Lourties-Monbrun, France`.latitude,
      `Cimetière de Lourties-Monbrun, cemetery, Lourties-Monbrun, France`.longitude
    )

  @Benchmark
  def inTheLastCommuneOfTheList: Option[Commune] =
    Geofla.withSpatialIndexFindBy(
      `Rue de la Fondance, tertiary, Bois-d'Ennebourg, France`.latitude,
      `Rue de la Fondance, tertiary, Bois-d'Ennebourg, France`.longitude
    )

  @Benchmark
  def notInTheList: Option[Commune] = Geofla.withSpatialIndexFindBy(london.latitude, london.longitude)

}

@Warmup(iterations = 10, time = 2000, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 2000, timeUnit = TimeUnit.MILLISECONDS)
@Fork(1)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
class ArrayFindByLatitudeLongitudeBenchmark {

  import FindByLatitudeLongitudeBenchmark._

  @Benchmark
  def inTheFirstCommuneOfTheList: Option[Commune] =
    Geofla.arrayFindBy(
      `Cimetière de Lourties-Monbrun, cemetery, Lourties-Monbrun, France`.latitude,
      `Cimetière de Lourties-Monbrun, cemetery, Lourties-Monbrun, France`.longitude
    )

  @Benchmark
  def inTheLastCommuneOfTheList: Option[Commune] =
    Geofla.arrayFindBy(
      `Rue de la Fondance, tertiary, Bois-d'Ennebourg, France`.latitude,
      `Rue de la Fondance, tertiary, Bois-d'Ennebourg, France`.longitude
    )

  @Benchmark
  def notInTheList: Option[Commune] = Geofla.arrayFindBy(london.latitude, london.longitude)

}

@Warmup(iterations = 10, time = 2000, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 2000, timeUnit = TimeUnit.MILLISECONDS)
@Fork(1)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
class ParArrayWithoutLocatorFindByLatitudeLongitudeBenchmark {

  import FindByLatitudeLongitudeBenchmark._

  @Benchmark
  def inTheFirstCommuneOfTheList: Option[Commune] =
    Geofla.parArrayWithoutLocatorFindBy(
      `Cimetière de Lourties-Monbrun, cemetery, Lourties-Monbrun, France`.latitude,
      `Cimetière de Lourties-Monbrun, cemetery, Lourties-Monbrun, France`.longitude
    )

  @Benchmark
  def inTheLastCommuneOfTheList: Option[Commune] =
    Geofla.parArrayWithoutLocatorFindBy(
      `Rue de la Fondance, tertiary, Bois-d'Ennebourg, France`.latitude,
      `Rue de la Fondance, tertiary, Bois-d'Ennebourg, France`.longitude
    )

  @Benchmark
  def notInTheList: Option[Commune] = Geofla.parArrayWithoutLocatorFindBy(london.latitude, london.longitude)

}

@Warmup(iterations = 10, time = 2000, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 2000, timeUnit = TimeUnit.MILLISECONDS)
@Fork(1)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
class ParArrayFindByLatitudeLongitudeBenchmark {

  import FindByLatitudeLongitudeBenchmark._

  @Benchmark
  def inTheFirstCommuneOfTheList: Option[Commune] =
    Geofla.parArrayFindBy(
      `Cimetière de Lourties-Monbrun, cemetery, Lourties-Monbrun, France`.latitude,
      `Cimetière de Lourties-Monbrun, cemetery, Lourties-Monbrun, France`.longitude
    )

  @Benchmark
  def inTheLastCommuneOfTheList: Option[Commune] =
    Geofla.parArrayFindBy(
      `Rue de la Fondance, tertiary, Bois-d'Ennebourg, France`.latitude,
      `Rue de la Fondance, tertiary, Bois-d'Ennebourg, France`.longitude
    )

  @Benchmark
  def notInTheList: Option[Commune] = Geofla.parArrayFindBy(london.latitude, london.longitude)

}

@Warmup(iterations = 10, time = 2000, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 2000, timeUnit = TimeUnit.MILLISECONDS)
@Fork(1)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
class FindByWithSpatialIndex_0_Benchmark {

  import FindByLatitudeLongitudeBenchmark._

  @Benchmark
  def inTheFirstCommuneOfTheList: Option[Commune] =
    Geofla.findByWithSpatialIndex_0(
      `Cimetière de Lourties-Monbrun, cemetery, Lourties-Monbrun, France`.latitude,
      `Cimetière de Lourties-Monbrun, cemetery, Lourties-Monbrun, France`.longitude
    )

  @Benchmark
  def inTheLastCommuneOfTheList: Option[Commune] =
    Geofla.findByWithSpatialIndex_0(
      `Rue de la Fondance, tertiary, Bois-d'Ennebourg, France`.latitude,
      `Rue de la Fondance, tertiary, Bois-d'Ennebourg, France`.longitude
    )

  @Benchmark
  def notInTheList: Option[Commune] = Geofla.findByWithSpatialIndex_0(london.latitude, london.longitude)

}


@Warmup(iterations = 10, time = 2000, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 2000, timeUnit = TimeUnit.MILLISECONDS)
@Fork(1)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
class FindByWithSpatialIndex_1_Benchmark {

  import FindByLatitudeLongitudeBenchmark._

  @Benchmark
  def inTheFirstCommuneOfTheList: Option[Commune] =
    Geofla.findByWithSpatialIndex_1(
      `Cimetière de Lourties-Monbrun, cemetery, Lourties-Monbrun, France`.latitude,
      `Cimetière de Lourties-Monbrun, cemetery, Lourties-Monbrun, France`.longitude
    )

  @Benchmark
  def inTheLastCommuneOfTheList: Option[Commune] =
    Geofla.findByWithSpatialIndex_1(
      `Rue de la Fondance, tertiary, Bois-d'Ennebourg, France`.latitude,
      `Rue de la Fondance, tertiary, Bois-d'Ennebourg, France`.longitude
    )

  @Benchmark
  def notInTheList: Option[Commune] = Geofla.findByWithSpatialIndex_1(london.latitude, london.longitude)

}
