package repositories

import scala.concurrent.Future

trait Repository[A] {

  def all: Future[Seq[A]]

  def insert(entity: A): Future[Int]

  def getById(id: Int): Future[Option[A]]

}
