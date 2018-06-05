package repositories

import model.Models.User

import scala.concurrent.{ExecutionContext, Future}
import dal.Dao
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.H2Profile.api._

@Singleton
class UsersRepository @Inject()(override protected val  dbConfigProvider: DatabaseConfigProvider)
                               (implicit ec: ExecutionContext)
  extends Dao(dbConfigProvider) with Repository[User] {

  override def all: Future[Seq[User]] =
    db.run(users.result)

  override def insert(user: User): Future[Int] =
    db.run(users += user)

  override def getById(id: Int): Future[Option[User]] =
    db.run(users.filter(_.id === id).result.headOption)
}
