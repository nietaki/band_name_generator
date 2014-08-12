package net.almost_done.recipes

import net.almost_done._

/**
 * Created by nietaki on 12.08.14.
 */
object TheWhiteStripes extends BandNameRecipe{
  override def requirements: List[SpeechPart] = Adjective :: Plural :: Nil

  override def stitcher: PartialFunction[List[Word], String] = {
    case adj :: plural :: Nil => s"the ${adj.capitalized.word} ${plural.capitalized.word}"
  }
}
