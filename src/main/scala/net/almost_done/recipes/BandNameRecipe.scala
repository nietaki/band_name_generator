package net.almost_done.recipes

import net.almost_done.{SpeechPart, Word, Words}

/**
 * Created by nietaki on 12.08.14.
 */
trait BandNameRecipe {
  def requirements: List[SpeechPart]
  def stitcher: PartialFunction[List[Word], String]

  def generateName: String = {
    val words = requirements.map({sp => Words.getRandom(sp)})
    assert(words.length == requirements.length)
    stitcher(words)
  }

  def generateNames(count: Int): IndexedSeq[String] = {
    (1 to count).map({_ => generateName})
  }
}