package org.snailmusk.cards

// export zio.test.{test}
// export zio.test.Assertion._

export java.io.IOException
export io.getquill.extras._
export io.getquill.ast.Entity
export io.getquill.quat.Quat
export scala.util.Random
export CardProperties.*

import io.getquill.*
val db = new PostgresJdbcContext(SnakeCase,"db")
export db._  

val devdir :os.Path =  os.pwd 
val imagesdir = devdir /".." / "images"
val subdirs = List("commanders","operations","source","structures","summons")
val subdirsfull = subdirs.map(imagesdir / _)

val files = subdirsfull.map(os.list(_)).flatten

import CardProperties._
private def cardName(path:os.Path):String = path.last.substring(0,path.last.lastIndexOf('.'))
private def kind(path:os.Path):Kind = path match {
  case p if p.startsWith(imagesdir / "commanders") => Kind.Commander
  case p if p.startsWith(imagesdir / "operations") => Kind.Operation
  case p if p.startsWith(imagesdir / "source") => Kind.Source
  case p if p.startsWith(imagesdir / "structures") => Kind.Structure
  case p if p.startsWith(imagesdir / "summons") => Kind.Summon
  case _ => Kind.Undefined
}
def card(path:os.Path):Card = Card(cardName(path), randomColor(), kind(path),randomRarity(),path.toString)



    