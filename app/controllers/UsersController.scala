package controllers

import javax.inject._
import play.api.mvc._
import repositories.{Repository, UsersRepository}
import model.Models._

import scala.concurrent.{ExecutionContext}


@Singleton
class UsersController @Inject()(cc: ControllerComponents, usersRepo: UsersRepository)
                                (implicit executionContext: ExecutionContext) extends Controller[User](cc) {
  override def companion = User
  override def repo = usersRepo
}
