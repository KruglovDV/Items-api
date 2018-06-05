package controllers


import play.api.mvc._
import play.api.libs.json._
import repositories._
import model.ModelsSerializer

import scala.concurrent.{ExecutionContext, Future}

abstract class Controller[A](cc: ControllerComponents)
                            (implicit executionContext: ExecutionContext) extends AbstractController(cc) {

  def companion: ModelsSerializer[A]

  def repo: Repository[A]

  implicit def reads: Reads[A] = companion.reads

  implicit def writes: Writes[A] = companion.writes

  def all() = Action.async { implicit req =>
    repo.all map { entities =>
      Ok(Json.toJson(entities))
    }
  }

  def create() = Action(parse.json).async { implicit req =>
    req.body.validate.fold(
      _ => Future { BadRequest(Json.obj("error" -> "incorrect json")) },
      entity => repo.insert(entity) map { _ => Created }
    )
  }

  def getById(id: Int) = Action.async { implicit req =>
    repo.getById(id) map {
      case Some(entity) => Ok(Json.toJson(entity))
      case None => NotFound
    }
  }
}
