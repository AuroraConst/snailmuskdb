package example
import org.scalatest._,  wordspec._, matchers._

import io.getquill._

import org.snailmusk.cards.*
// import CardProperties._

inline def addCard(card:Card) = quote {
  query[Card].insertValue(lift(card))
}

inline def selectAllCards() = quote {
  query[Card]
}




class CardTest  extends AnyWordSpec with should.Matchers :
  "First test" should {"work" in {
    db.run(selectAllCards()).foreach(c => info(s"$c"))
    // files.foreach{ p =>
    //   val c = card(p)
    //   db.run(addCard(c))
    // }
    info(s"$imagesdir")
    // val result = db.run(q)

  }}


  