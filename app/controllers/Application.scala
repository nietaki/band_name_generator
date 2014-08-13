package controllers

import net.almost_done.recipes.BandNameRecipe
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
    //println(rh.body.asJson)
    val bodyJsonOption = rh.body.asJson
    val chosenRecipes: Seq[BandNameRecipe] = bodyJsonOption match {
      case None => BandNameRecipe.recipes
      case Some(json) => {
        //TODO make this bit safe - wrap it in a Try?
        val recipeObjects: Seq[JsValue] = (json \ "recipes").as[JsArray].value
        val chosen = recipeObjects.filter({jsObj => (jsObj \ "selected").as[JsBoolean].value})
        val recipeNames:Seq[String] = chosen.map({jsObj => (jsObj \ "name").as[JsString].value})
        BandNameRecipe.getRecipesByNames(recipeNames)
      }
    }
    val chosenRecipesIndexed = chosenRecipes.toIndexedSeq

    val provider: (SpeechPart) => Word = Words.getRandomWithUncommonProbability(uncommonProbability)

    val bandNames = (1 to count) map { _ =>
      val curRecipe = Utils.randomElement(chosenRecipesIndexed)
      curRecipe.generateName(provider)
    }
    /*
    val tws = recipes.TheWhiteStripes.generateNames(provider)(10)
    val atc = recipes.AlvinAndTheChipmunks.generateNames(provider)(10)
    val gd = recipes.GreenDay.generateNames(provider)(10)
    val bandNames = Random.shuffle(tws ++ atc ++ gd)
    */
    val ret = JsArray(bandNames.map(JsString(_)))
    Ok(ret)
  }

}