package repositories

import dal.Dao
import javax.inject.{Inject, Singleton}
import model.Models.Item
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.H2Profile.api._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ItemsRepository @Inject()(override protected val  dbConfigProvider: DatabaseConfigProvider)
                               (implicit ec: ExecutionContext)
  extends Dao(dbConfigProvider) with Repository[Item] {

  override def all: Future[Seq[Item]] =
    db.run(items.result)

  override def insert(entity: Item): Future[Int] =
    db.run(items += entity)

  override def getById(id: Int): Future[Option[Item]] =
    db.run(items.filter(_.id === id).result.headOption)
}
