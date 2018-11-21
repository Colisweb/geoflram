package com.colisweb.geoflram.jruby

import com.colisweb.geoflram.jruby
import org.scalatest.{FlatSpec, Matchers}

class GeoflaJRubySpec extends FlatSpec with Matchers {

  "true" should "be true" in {
    true shouldBe true
  }

  "GeoflaJRuby#findBy" should "find the correct commune" in {
    final case class GpsPoint(latitude: Double, longitude: Double)

    val `Rue de la Fondance, tertiary, Bois-d'Ennebourg, France` =
      GpsPoint(latitude = 49.4296517, longitude = 1.2554941)

    val `Rue des Huttes, tertiary, Coingt, France` = GpsPoint(latitude = 49.7847695, longitude = 4.0952504)

    val `Cimetière de Lourties-Monbrun, cemetery, Lourties-Monbrun, France` =
      GpsPoint(latitude = 43.45603285, longitude = 0.5390568833333329)

    val london = GpsPoint(latitude = 51.5073219, longitude = -0.1276474)

    jruby.GeoflramJRuby
      .findBy(
        `Rue de la Fondance, tertiary, Bois-d'Ennebourg, France`.latitude,
        `Rue de la Fondance, tertiary, Bois-d'Ennebourg, France`.longitude,
      )
      .nomCommune shouldBe "BOIS-D'ENNEBOURG"

    jruby.GeoflramJRuby
      .findBy(
        `Rue des Huttes, tertiary, Coingt, France`.latitude,
        `Rue des Huttes, tertiary, Coingt, France`.longitude,
      )
      .nomCommune shouldBe "COINGT"

    jruby.GeoflramJRuby
      .findBy(
        `Cimetière de Lourties-Monbrun, cemetery, Lourties-Monbrun, France`.latitude,
        `Cimetière de Lourties-Monbrun, cemetery, Lourties-Monbrun, France`.longitude,
      )
      .nomCommune shouldBe "LOURTIES-MONBRUN"

    jruby.GeoflramJRuby
      .findBy(
        london.latitude,
        london.longitude,
      ) shouldBe null
  }

}
