package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import repositories.CheckRepository
import model.Models._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ChecksController @Inject()(cc: ControllerComponents, checksRepo: CheckRepository)
                                (implicit executionContext: ExecutionContext) extends Controller[Check](cc) {

  override val companion = Check
  override def repo = checksRepo

  override def create() = Action(parse.json).async { implicit req =>
    req.body.validate.fold(
      _ => Future { BadRequest(Json.obj("error" -> "incorrect json")) },
      check => {
        checksRepo.insert(Check(userId = check.userId)) map { _ => Created }
      }
    )
  }
}
