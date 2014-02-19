package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import models.database.EventForm


object EventController extends Controller {

  implicit val eventReader = Json.reads[EventForm]
  
  def create = Action(parse.json) { request =>
    request.body.validate[EventForm].map{
      case e: EventForm => {
        play.Logger.debug("eventName: " + e.eventName)
        models.database.Events.create(e)
        Ok
      }
    }.recoverTotal{
      e => BadRequest("Detected error:" + JsError.toFlatJson(e))
    }
    Ok
    
  }
	
}