package controllers

import play.api._
import play.api.libs.json._
import play.api.mvc._
import net.almost_done._

import scala.util.Random

object Application extends Controller {

  def index = Action {
    Ok(views.html.index())
  }

  def getBandNames = Action {rh =>
    val tws = recipes.TheWhiteStripes.generateNames(10)
    val atc = recipes.AlvinAndTheChipmunks.generateNames(10)
    val gd = recipes.GreenDay.generateNames(10)
    val bandNames = Random.shuffle(tws ++ atc ++ gd)

    val ret = JsArray(bandNames.map(JsString(_)))
    Ok(ret)
  }

}