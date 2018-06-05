package dal

import java.sql.Date

import slick.jdbc.H2Profile.api._
import model.Models._
import slick.ast.Subquery.Default

class Users(tag: Tag) extends Table[User](tag, "USERS") {

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def login = column[String]("LOGIN", O.Unique)
  def email = column[String]("EMAIL", O.Unique)
  def password = column[String]("PASSWORD")

  def * = (id.?, login, email, password) <> ((User.apply _).tupled, User.unapply)
}

class Markets(tag: Tag) extends Table[Market](tag, "MARKETS") {

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def name = column[String]("NAME")
  def city = column[String]("CITY")

  def * = (id.?, name, city) <> ((Market.apply _).tupled, Market.unapply)

  def idx = index("name_city", (name, city), unique = true)
}

class Items(tag: Tag) extends Table[Item](tag, "ITEMS") {

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def name = column[String]("NAME", O.Unique)
  def description = column[String]("DESCRIPTION")

  def * = (id.?, name, description) <> ((Item.apply _).tupled, Item.unapply)
}

class Checks(tag: Tag) extends Table[Check](tag, "CHECKS") {

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def date = column[Date]("DATE")
  def isDone = column[Boolean]("IS_DONE")
  def userId = column[Int]("USER_ID")

  def user = foreignKey("USER_FK", userId, TableQuery[Users])(_.id,
    onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Cascade)

  def * = (id.?, date.?, userId, isDone.?) <> ((Check.apply _).tupled, Check.unapply)
}

class Purchases(tag: Tag) extends Table[Purchase](tag, "PURCHASES") {

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def itemId = column[Int]("ITEM_ID")
  def marketId = column[Int]("MARKET_ID")
  def checkId = column[Int]("CHECK_ID")
  def userId = column[Int]("USER_ID")
  def date = column[Date]("DATE")
  def price = column[Double]("PRICE")

  def item = foreignKey("ITEM_FK", itemId, TableQuery[Items])(_.id,
    onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Cascade)
  def market = foreignKey("MARKET_FK", marketId, TableQuery[Markets])(_.id,
    onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Cascade)
  def check = foreignKey("CHECK_FK", checkId, TableQuery[Checks])(_.id,
    onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Cascade)

  def user = foreignKey("PURCH_USER_FK", userId, TableQuery[Users])(_.id,
    onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Cascade)

  def * = (id.?, itemId, marketId, checkId, userId, date.?, price) <> ((Purchase.apply _).tupled, Purchase.unapply)
}
