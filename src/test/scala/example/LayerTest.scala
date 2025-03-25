package example
import zio._


import org.scalatest._
import wordspec._
import matchers._



import zio._
// case class ServerConfig(host: String, port: Int)
// object ServerConfig {
//   val layer: ULayer[ServerConfig] =
//     ZLayer.succeed(ServerConfig("localhost", 8080))
// }

// case class DBConfig(name: String)
// object DBConfig {
//   val layer: ULayer[DBConfig] =
//     ZLayer.succeed(DBConfig("my-test-db"))
// }

// case class AppConfig(db: DBConfig, serverConfig: ServerConfig)
// object AppConfig {
//   val layer: ZLayer[DBConfig with ServerConfig, Nothing, AppConfig] =
//     ZLayer {
//       for {
//         db     <- ZIO.service[DBConfig]
//         server <- ZIO.service[ServerConfig]
//       } yield AppConfig(db, server)
//     }
// }

// object MainApp extends ZIOAppDefault {
//   val myApp =
//     for 
//       c <- ZIO.service[AppConfig]
//       _ <- zio.Console.printLine(s"Application startedx with config: ${c}")
//     yield ()

//   def run = myApp.provide(AppConfig.layer, DBConfig.layer, ServerConfig.layer)
// }

// class LayerTest extends AnyWordSpec with should.Matchers :
//   "this" should {"be a test" in {
//     info ("Running LayerTest")
//     MainApp.run
//     info ("Finished Running LayerTest")
//    } 
//   }
