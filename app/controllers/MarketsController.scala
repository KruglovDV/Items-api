package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import repositories.{MarketsRepository, Repository}
import model.Models._
import model.ModelsSerializer

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class MarketsController @Inject()(cc: ControllerComponents, marketsRepo: MarketsRepository)
                                 (implicit executionContext: ExecutionContext) extends Controller[Market](cc) {

  override def companion = Market
  override def repo = marketsRepo
}
