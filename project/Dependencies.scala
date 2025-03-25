import sbt._

object Dependencies {
  val protoquillVersion = "4.8.5"
  lazy val munit = "org.scalameta" %% "munit" % "0.7.29"

  // https://mvnrepository.com/artifact/mysql/mysql-connector-java
  lazy val mysqljdbc = "mysql" % "mysql-connector-java" % "8.0.33"
  lazy val postgresjdbc = "org.postgresql" % "postgresql" % "42.6.0"
  lazy val scalatoolkit = "org.scala-lang" %% "toolkit" % "0.7.0"

  lazy val protoquill: Def.Initialize[Seq[ModuleID]] = Def.setting {
    Seq(
      // Syncronous JDBC Modules
      // "io.getquill" %% "quill-jdbc" % protoquillVersion,
      // Or ZIO Modules
      "io.getquill" %% "quill-jdbc-zio" % protoquillVersion,
      // Or Cassandra
      // "io.getquill" %% "quill-cassandra" % protoquillVersion,
      // Or Cassandra + ZIO
      // "io.getquill" %% "quill-cassandra-zio" % protoquillVersion,
      // Add for Caliban Integration
      // "io.getquill" %% "quill-caliban" % protoquillCaliban
      )
  }


  val ziotest: Def.Initialize[Seq[ModuleID]] = Def.setting {
    Seq(
      "dev.zio" %% "zio-test"          % "2.1.6" % Test,
      "dev.zio" %% "zio-test-sbt"      % "2.1.6" % Test,
      "dev.zio" %% "zio-test-magnolia" % "2.1.6" % Test    
    )
  }

  val ziologging: Def.Initialize[Seq[ModuleID]] = Def.setting {
    Seq(
      "dev.zio" %% "zio-logging" % "2.3.0",
      "dev.zio" %% "zio-logging-slf4j" % "2.3.0",
       "org.slf4j" % "slf4j-simple" % "1.7.36"
    )
  }

  val scalatest = "org.scalatest" %% "scalatest" % "3.2.9" % Test


}
