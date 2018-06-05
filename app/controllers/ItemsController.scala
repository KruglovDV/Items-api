package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import repositories.ItemsRepository
import model.Models._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ItemsController @Inject()(cc: ControllerComponents, itemsRepo: ItemsRepository)
                               (implicit executionContext: ExecutionContext) extends Controller[Item](cc) {

  override val companion = Item
  override def repo = itemsRepo
}
