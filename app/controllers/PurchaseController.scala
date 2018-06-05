package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import repositories.PurchaseRepository
import model.Models._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class PurchaseController @Inject()(cc: ControllerComponents, purchaseRepo: PurchaseRepository)
                                  (implicit executionContext: ExecutionContext) extends Controller[Purchase](cc) {
  override def repo = purchaseRepo
  override val companion = Purchase

  override def create() = Action(parse.json).async { implicit req =>
    req.body.validate.fold(
      _ => Future { BadRequest(Json.obj("error" -> "incorrect json")) },
      purchase => {
        purchaseRepo.insert(
          Purchase(itemId = purchase.itemId, userId = purchase.userId,
            price = purchase.price, checkId = purchase.checkId, marketId = purchase.marketId)) map { _ => Created }
      }
    )
  }
}
