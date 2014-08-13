package net.almost_done.recipes

import net.almost_done._
/**
 * Created by nietaki on 12.08.14.
 */
object GreenDay extends BandNameRecipe{
  override def requirements: List[SpeechPart] = Adjective :: Noun :: Nil

  override def stitcher: PartialFunction[List[Word], String] = {
    case adj :: nounLike :: Nil => s"${adj.capitalized.word} ${nounLike.capitalized.word}"
  }

  override def recipeName: String = "GreenDay"
}
