package model

import java.sql.Date
import play.api.libs.json._

object Models {

  case class User(id: Option[Int] = None, login: String, email: String, password: String)

  case class Market(id: Option[Int] = None, name: String, city: String)

  case class Item(id: Option[Int] = None, name: String, description: String)

  case class Check(id: Option[Int] = None, date: Option[Date] = Some(new Date(System.currentTimeMillis())),
                   userId: Int, isDone: Option[Boolean] = Some(false))

  case class Purchase(id: Option[Int] = None, itemId: Int, marketId: Int, checkId: Int, userId: Int,
                      date: Option[Date] = Some(new Date(System.currentTimeMillis())),
                      price: Double)

  object User extends ModelsSerializer[User] {
    implicit val reads: Reads[User] = Json.reads[User]
    implicit val writes: Writes[User] = Json.writes[User]
  }

  object Market extends ModelsSerializer[Market] {
    implicit val reads: Reads[Market] = Json.reads[Market]
    implicit val writes: Writes[Market] = Json.writes[Market]
  }

  object Item extends ModelsSerializer[Item] {
    implicit val reads: Reads[Item] = Json.reads[Item]
    implicit val writes: Writes[Item] = Json.writes[Item]
  }

  object Check extends ModelsSerializer[Check] {
    implicit val reads: Reads[Check] = Json.reads[Check]
    implicit val writes: Writes[Check] = Json.writes[Check]
  }

  object Purchase extends ModelsSerializer[Purchase] {
    implicit val reads: Reads[Purchase] = Json.reads[Purchase]
    implicit val writes: Writes[Purchase] = Json.writes[Purchase]
  }
}
