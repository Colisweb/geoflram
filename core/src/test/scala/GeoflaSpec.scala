import com.guizmaii.geofla.Geofla
import org.scalatest.{FlatSpec, Matchers}

class GeoflaSpec extends FlatSpec with Matchers {

  "true" should "be true" in {
    true shouldBe true
  }

  "Geofla#findBy" should "find the correct commune" in {
    final case class GpsPoint(latitude: Double, longitude: Double)

    val `Rue de la Fondance, tertiary, Bois-d'Ennebourg, France` =
      GpsPoint(latitude = 49.4296517, longitude = 1.2554941)

    val `Rue des Huttes, tertiary, Coingt, France` = GpsPoint(latitude = 49.7847695, longitude = 4.0952504)

    val `Cimetière de Lourties-Monbrun, cemetery, Lourties-Monbrun, France` =
      GpsPoint(latitude = 43.45603285, longitude = 0.5390568833333329)

    val london = GpsPoint(latitude = 51.5073219, longitude = -0.1276474)

    Geofla
      .findBy(
        `Rue de la Fondance, tertiary, Bois-d'Ennebourg, France`.latitude,
        `Rue de la Fondance, tertiary, Bois-d'Ennebourg, France`.longitude,
      )
      .map(_.nomCommune) shouldBe Some("BOIS-D'ENNEBOURG")

    Geofla
      .findBy(
        `Rue des Huttes, tertiary, Coingt, France`.latitude,
        `Rue des Huttes, tertiary, Coingt, France`.longitude,
      )
      .map(_.nomCommune) shouldBe Some("COINGT")

    Geofla
      .findBy(
        `Cimetière de Lourties-Monbrun, cemetery, Lourties-Monbrun, France`.latitude,
        `Cimetière de Lourties-Monbrun, cemetery, Lourties-Monbrun, France`.longitude,
      )
      .map(_.nomCommune) shouldBe Some("LOURTIES-MONBRUN")

    Geofla
      .findBy(
        london.latitude,
        london.longitude,
      )
      .map(_.nomCommune) shouldBe None
  }

  "Geofla#strTreefindBy" should "find the correct commune" in {
    final case class GpsPoint(latitude: Double, longitude: Double)

    val `Rue de la Fondance, tertiary, Bois-d'Ennebourg, France` =
      GpsPoint(latitude = 49.4296517, longitude = 1.2554941)

    val `Rue des Huttes, tertiary, Coingt, France` = GpsPoint(latitude = 49.7847695, longitude = 4.0952504)

    val `Cimetière de Lourties-Monbrun, cemetery, Lourties-Monbrun, France` =
      GpsPoint(latitude = 43.45603285, longitude = 0.5390568833333329)

    val london = GpsPoint(latitude = 51.5073219, longitude = -0.1276474)

    Geofla
      .strTreefindBy(
        `Rue de la Fondance, tertiary, Bois-d'Ennebourg, France`.latitude,
        `Rue de la Fondance, tertiary, Bois-d'Ennebourg, France`.longitude,
      )
      .map(_.nomCommune) shouldBe Some("BOIS-D'ENNEBOURG")

    Geofla
      .strTreefindBy(
        `Rue des Huttes, tertiary, Coingt, France`.latitude,
        `Rue des Huttes, tertiary, Coingt, France`.longitude,
      )
      .map(_.nomCommune) shouldBe Some("COINGT")

    Geofla
      .strTreefindBy(
        `Cimetière de Lourties-Monbrun, cemetery, Lourties-Monbrun, France`.latitude,
        `Cimetière de Lourties-Monbrun, cemetery, Lourties-Monbrun, France`.longitude,
      )
      .map(_.nomCommune) shouldBe Some("LOURTIES-MONBRUN")

    Geofla
      .strTreefindBy(
        london.latitude,
        london.longitude,
      )
      .map(_.nomCommune) shouldBe None
  }
}
