package example
import zio._
import zio.logging.LogFormat
import zio.logging.backend.SLF4J

import zio.test._
import zio.test.{test, _}
import zio.test.Assertion._

object MainLoggerApp extends ZIOAppDefault :
  // override val bootstrap = Runtime.removeDefaultLoggers ++ Runtime.addLogger(logger)
  override val bootstrap = SLF4J.slf4j(LogFormat.colored)

  val logger: ZLogger[String, Unit] =
    new ZLogger[String, Unit] {
      override def apply(
        trace: Trace,
        fiberId: FiberId,
        logLevel: LogLevel,
        message: () => String,
        cause: Cause[Any],
        context: FiberRefs,
        spans: List[LogSpan],
        annotations: Map[String, String]
      ): Unit =
        println(s"${java.time.Instant.now()} - ${logLevel.label} - ${message()}")
    }


  def run =
    for {
      _ <- ZIO.log("Application started!")
      _ <- ZIO.log("Another log message.")
      log <- ZIO.log("Application stopped!")
    } yield ()
end MainLoggerApp


object ZioCustomLoggerSpec extends ZIOSpecDefault:
  lazy val testLogger =       test("logger") {
    for
      _ <- ZIO.log("Application started!")
      _ <- ZIO.log("Another log message.")
      _ <- ZIO.log("Application stopped!")
    yield assertTrue(true)  
  }
  def spec = 
    suite("CustomLoggerSpec")(
      testLogger
   
     )
        

end ZioCustomLoggerSpec