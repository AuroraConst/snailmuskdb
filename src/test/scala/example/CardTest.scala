package org.snailmusk.cards
import org.scalatest._,  wordspec._, matchers._

import io.getquill._

val db = new PostgresJdbcContext(SnakeCase,"db")
export db._  


inline def addCard(card:Card) = {
  query[Card].insertValue(lift(card))
}

inline def selectAllCards() = quote {
  query[Card]
}




class CardTest  extends AnyWordSpec with should.Matchers :
  "First test" should {"work" in {

    db.run(selectAllCards()).foreach(c => info(s"$c"))

    files.foreach{ p =>
      val c = card(p)
      info(s"$c")
      db.run(addCard(c))
    }
    // info(s"$imagesdir")

    // val result = db.run(q)

  }}


  