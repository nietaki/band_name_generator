package net.almost_done.recipes

import net.almost_done._

/**
 * Created by nietaki on 12.08.14.
 */
object AlvinAndTheChipmunks extends BandNameRecipe {
  override def requirements: List[SpeechPart] = Noun :: Plural :: Nil

  override def stitcher: PartialFunction[List[Word], String] =  {
    case noun :: plural :: Nil => s"${noun.capitalized.word} and the ${plural.capitalized.word}"
  }
}
