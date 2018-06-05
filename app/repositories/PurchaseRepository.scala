package repositories

import dal.Dao
import javax.inject.{Inject, Singleton}
import model.Models.Purchase
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.H2Profile.api._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class PurchaseRepository @Inject()(override protected val  dbConfigProvider: DatabaseConfigProvider)
                                   (implicit ec: ExecutionContext)
  extends Dao(dbConfigProvider) with Repository[Purchase] {

  override def all: Future[Seq[Purchase]] =
    db.run(purchases.result)

  override def insert(entity: Purchase): Future[Int] =
    db.run(purchases += entity)

  override def getById(id: Int): Future[Option[Purchase]] =
    db.run(purchases.filter(_.id === id).result.headOption)
}
