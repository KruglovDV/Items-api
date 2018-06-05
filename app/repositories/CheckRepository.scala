package repositories

import dal.Dao
import javax.inject.{Inject, Singleton}
import model.Models.Check
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.H2Profile.api._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class CheckRepository @Inject()(override protected val  dbConfigProvider: DatabaseConfigProvider)
                               (implicit ec: ExecutionContext)
  extends Dao(dbConfigProvider) with Repository[Check] {

  override def all: Future[Seq[Check]] =
    db.run(checks.result)

  override def insert(entity: Check): Future[Int] =
    db.run(checks += entity)

  override def getById(id: Int): Future[Option[Check]] =
    db.run(checks.filter(_.id === id).result.headOption)
}
