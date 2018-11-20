package com.guizmaii.geofla

import org.locationtech.jts.geom._
import org.locationtech.jts.index.strtree.STRtree
import org.locationtech.jts.io.WKTReader

import scala.io.{Codec, Source}

object Geofla {

  import scala.collection.JavaConverters._

  final case class Commune(
      geometry: Geometry,
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

  private[this] final val lines           = Source.fromResource("COMMUNE.csv")(Codec.UTF8).getLines().drop(1)
  private[this] final val geometryFactory = new GeometryFactory
  private[this] final val reader          = new WKTReader(geometryFactory)

  private[this] final val geometries =
    lines
      .map(_.split("\"").drop(1))
      .map {
        case Array(wkt: String, rest: String) => wkt :: rest.split(',').drop(1).toList
      }
      .map { list =>
        Commune(
          geometry = reader.read(list(0)),
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
      .toArray

  private[this] final val strTree = new STRtree()
  geometries.foreach(g => strTree.insert(g.geometry.getEnvelopeInternal, g))
  strTree.build()

  def findBy(latitude: Double, longitude: Double): Option[Commune] = {
    val point: Geometry = geometryFactory.createPoint(new Coordinate(longitude, latitude))

    strTree
      .query(point.getEnvelopeInternal)
      .asScala
      .find(_.asInstanceOf[Commune].geometry.contains(point))
      .asInstanceOf[Option[Commune]]
  }

}
