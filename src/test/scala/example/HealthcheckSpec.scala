package example

import zio.test._
// import zio.test.{test, _}
// import zio.test.Assertion._

import java.io.IOException
import io.getquill._
import io.getquill.extras._
import io.getquill.ast.Entity
import io.getquill.quat.Quat

object HealthcheckSpec extends ZIOSpecDefault:
  lazy val db = new PostgresJdbcContext(SnakeCase,"db")
  case class Person(id:Int,name:String)
  import db._
  def spec = 
    suite("HelloWorldSpec"){
      test("HelloWorld") {
        inline def q = quote {
          query[Person].filter(p => p.name == "Joe")
        }
        val result = db.run(q)
        

        for {
          _ <- zio.Console.print(result)
          output <-TestConsole.output
          _ <- zio.Console.print(s"!!!!$output")

        } 
        yield    assertTrue(true == true) 
      }
    }