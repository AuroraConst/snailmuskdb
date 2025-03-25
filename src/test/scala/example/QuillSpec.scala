package example
import zio.test._
import io.getquill._

object QuillSpec extends ZIOSpecDefault:
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
        yield    assertTrue(true==true) 
      }
    }
end QuillSpec    