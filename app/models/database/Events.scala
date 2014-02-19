package models.database

import scala.slick.driver.H2Driver.simple._
import java.sql.Date
import play.api.db.DB
import play.api.Play.current
/* memo
 * イベント名
 * ツアー名の下にイベントつけるか？
 * redmine みたいに親子関係？
 * */
case class Event(eventId: Int, eventName: String, eventDate: Date, performer: String, location: String)
case class EventForm(eventName: String, eventDate: java.sql.Date, performer: String, location: String)
  
object Events {
  
  val database = Database.forDataSource(DB.getDataSource())

  class Events(tag: Tag) extends Table[Event](tag, "EVENTS") {
    def eventId = column[Int]("EVENT_ID", O.PrimaryKey, O.AutoInc)
    def eventName = column[String]("EVENT_NAME")
    def eventDate = column[Date]("EVENT_DATE")
    def performer = column[String]("PERFORMER")
    def location = column[String]("LOCATION")
    def * = (eventId, eventName, eventDate, performer, location) <> (Event.tupled, Event.unapply)
  }

  val events = TableQuery[Events]
  
  def create(event: EventForm) = database.withTransaction { implicit session: Session =>
    events.insert(Event(-1, event.eventName, event.eventDate, event.performer, event.location))
  }
  
  def createTable = database.withSession { implicit session: Session =>
    events.ddl.create
  }
 
  def dropTable = database.withSession { implicit session: Session =>
    events.ddl.drop
  }
}

class Tickets(tag: Tag) extends Table[(Int, Int)](tag, "TICKETS") {
  def ticketId = column[Int]("TICKET_ID", O.PrimaryKey)
  def eventId = column[Int]("EVENT_ID")
  def * = (ticketId, eventId)
}