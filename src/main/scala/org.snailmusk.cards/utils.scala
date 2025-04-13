package org.snailmusk.cards

// export zio.test.{test}
// export zio.test.Assertion._

export java.io.IOException
export io.getquill.extras._
export io.getquill.ast.Entity
export io.getquill.quat.Quat
export scala.util.Random

import io.getquill.*

val devdir :os.Path =  os.pwd 
val imagesdir = devdir /".." / "images"
val subdirs = List("commanders","operations","source","structures","summons")
val subdirsfull = subdirs.map(imagesdir / _)

val files = subdirsfull.map(os.list(_)).flatten

// import CardProperties._
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

enum Color(cost:Int=1):
  case Red(cost:Int=1)   extends Color(cost)
  case Green(cost:Int=1) extends Color(cost)
  case Blue(cost:Int=1)  extends Color(cost)
  case Yellow(cost:Int=1) extends Color(cost)
  case Purple(cost:Int=1) extends Color(cost)

object Color :  
  def fromString(s:String):Color =  {
    val pattern = """(\w+)\((\d+)\)""".r // Regex to match "EnumName(param)"
    // import CardProperties._

    s match {
      case pattern(name, cost) =>
        name match {
          case "Red"    => Color.Red(cost.toInt)
          case "Green"  => Color.Green(cost.toInt)
          case "Blue"   => Color.Blue(cost.toInt)
          case "Yellow" => Color.Yellow(cost.toInt)
          case "Purple" => Color.Purple(cost.toInt)
          case _        => throw new IllegalArgumentException(s"Unknown color: $name")
        }
      case _ => throw new IllegalArgumentException(s"Invalid format: $s")
    }
}
  given   MappedEncoding[Color, String](_.toString)
  given   MappedEncoding[String, Color](Color.fromString(_))
enum Kind :
    case Commander extends Kind
    case Operation extends Kind
    case Summon extends Kind
    case Structure extends Kind
    case Source extends Kind
    case Weapon extends Kind
    case Undefined extends Kind  


object Kind :
  def fromString(s: String): Kind = 
    s match {
          case "Commander"  => Kind.Commander
          case "Operation"  => Kind.Operation
          case "Summon"     => Kind.Summon
          case "Structure"  => Kind.Structure
          case "Source"     => Kind.Source
          case "Weapon"     => Kind.Weapon
          case _ => throw new IllegalArgumentException(s"Invalid kind: $s")
      }
  given   MappedEncoding[Kind, String](_.toString)
  given   MappedEncoding[String, Kind](Kind.fromString(_))


enum Rarity () :
  case Common extends Rarity
  case UnCommon extends Rarity
  case Rare extends Rarity
  case Sublime extends Rarity  

object Rarity :
  def fromString(s: String): Rarity = 
    s match {
          case "Common"  => Rarity.Common
          case "UnCommon"  => Rarity.UnCommon
          case "Rare"     => Rarity.Rare
          case "Sublime"  => Rarity.Sublime
          case _ => throw new IllegalArgumentException(s"Invalid rarity: $s")
      }  
  given   MappedEncoding[Rarity, String](_.toString)
  given   MappedEncoding[String, Rarity](Rarity.fromString(_))



def randomRarity(): Rarity =
  Random.nextInt(4) match
    case 0 => Rarity.Common
    case 1 => Rarity.UnCommon
    case 2 => Rarity.Rare
    case 3 => Rarity.Sublime

def randomColor(): Color = 
    Random.nextInt(5) match
    case 0 => Color.Red(Random.nextInt(5))
    case 1 => Color.Green(Random.nextInt(5))
    case 2 => Color.Blue(Random.nextInt(5))
    case 3 => Color.Yellow(Random.nextInt(5))
    case 4 => Color.Purple(Random.nextInt(5))

def randomKind(): Kind = 
  Random.nextInt(6) match
    case 0 => Kind.Commander
    case 1 => Kind.Operation
    case 2 => Kind.Summon
    case 3 => Kind.Structure
    case 4 => Kind.Source
    case 5 => Kind.Weapon   

case class Card(name:String,color:Color, kind:Kind,rarity:Rarity, path:String )


  