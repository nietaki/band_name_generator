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

  def getBandNames(uncommonProbability: Double, count: Int) = Action {rh =>
    //TODO: make the count count
    val provider: (SpeechPart) => Word = Words.getRandomWithUncommonProbability(uncommonProbability)
    val tws = recipes.TheWhiteStripes.generateNames(provider)(10)
    val atc = recipes.AlvinAndTheChipmunks.generateNames(provider)(10)
    val gd = recipes.GreenDay.generateNames(provider)(10)
    val bandNames = Random.shuffle(tws ++ atc ++ gd)

    val ret = JsArray(bandNames.map(JsString(_)))
    Ok(ret)
  }

}