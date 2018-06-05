package dal

import javax.inject.{ Singleton}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.H2Profile.api._

import scala.concurrent.{ExecutionContext}
import slick.lifted.TableQuery
import model.Models._

@Singleton()
class Dao (protected val dbConfigProvider: DatabaseConfigProvider)
                   (implicit ec: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {

  lazy val users = TableQuery[Users]
  lazy val markets = TableQuery[Markets]
  lazy val items = TableQuery[Items]
  lazy val checks = TableQuery[Checks]
  lazy val purchases = TableQuery[Purchases]

  val setup = DBIO.seq(
    (users.schema ++ markets.schema ++
      items.schema ++ checks.schema ++ purchases.schema).create
  )

  db.run(setup)
//    .flatMap { _ =>
//    val q = DBIO.seq(users += User(email = "testLogin?", login = "testLogin", password = "testPass"),
//                     users += User(email = "testLogin", login = "testLogin?", password = "testPass"))
//    db.run(q)
//  }
}
