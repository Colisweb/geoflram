package com.guizmaii

import org.geolatte.geom.codec.Wkt
import org.geolatte.geom.crs.CoordinateReferenceSystems
import org.geolatte.geom.{Geometry, Position}
import org.locationtech.jts.io.WKTReader

import scala.io.{Codec, Source}

final case class CommuneLine(
    wkt: Geometry[_ <: Position],
    idGeofla: String,
    codeCommune: String,
    inseeCommune: String,
    nomCommune: String,
    statut: String,
    xChefLieu: String,
    yChefLieu: String,
    xCentroid: String,
    yCentroid: String,
    zMoyen: String,
    superficie: String,
    population: String,
    codeArr: String, // arr ?
    codeDepartement: String,
    nomDepartement: String,
    codeRegion: String,
    nomRegion: String
)

object Main extends App {

  val lines  = Source.fromResource("COMMUNE.csv")(Codec.UTF8).getLines().drop(1)
  val reader = new WKTReader()

  val decoder = Wkt.newDecoder()
  val wgs84   = CoordinateReferenceSystems.WGS84

  val geometries =
    lines
      .map(_.split("\"").drop(1))
      .map {
        case Array(wkt: String, rest: String) => wkt :: rest.split(',').drop(1).toList
      }
      .map { list =>
        CommuneLine(
          wkt = decoder.decode(list(0), wgs84),
          idGeofla = list(1),
          codeCommune = list(2),
          inseeCommune = list(3),
          nomCommune = list(4),
          statut = list(5),
          xChefLieu = list(6),
          yChefLieu = list(7),
          xCentroid = list(8),
          yCentroid = list(9),
          zMoyen = list(10),
          superficie = list(11),
          population = list(12),
          codeArr = list(13),
          codeDepartement = list(14),
          nomDepartement = list(15),
          codeRegion = list(16),
          nomRegion = list(17)
        )
      }
      .toList

  println("END")

}
