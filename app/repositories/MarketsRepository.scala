package repositories

import dal.Dao
import javax.inject.{Inject, Singleton}
import model.Models.Market
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.H2Profile.api._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class MarketsRepository @Inject()(override protected val  dbConfigProvider: DatabaseConfigProvider)
                                 (implicit ec: ExecutionContext)
  extends Dao(dbConfigProvider) with Repository[Market] {

  override def all: Future[Seq[Market]] =
    db.run(markets.result)

  override def insert(entity: Market): Future[Int] =
    db.run(markets += entity)

  override def getById(id: Int): Future[Option[Market]] =
    db.run(markets.filter(_.id === id).result.headOption)
}
