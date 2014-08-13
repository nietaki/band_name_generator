package net.almost_done

/**
 * Created by nietaki on 12.08.14.
 */
object Words {
  val topWords = Utils.getTopWords().map(_.toLowerCase).toSet

  val all: Vector[Word] = {
    val allWithoutCommonality = Utils.getWords.toVector
    allWithoutCommonality.map({w =>
      if(topWords.contains(w.word.toLowerCase)){
        w.common
      } else {
        w
      }
    })
  }


  val nouns = all.filter(_.isSimpleNoun)
  val nounPhrases = all.filter(_.isNounPhrase)
  val nounLike = all.filter(w => w.isNounLike)

  val adjectives = all.filter(_.isAdjective)
  val adverbs = all.filter(_.isAdverb)
  val adjectiveLikes = all.filter(_.isAdjectiveLike)

  val plurals = all.flatMap({w: Word =>
    w.getPlural match {
      case None => Nil
      case Some(plural) => plural :: Nil
    }
  })

  val speechPartWordsMap: Map[SpeechPart, Vector[Word]] = Map(
    (Noun, nouns),
    (NounLike, nounLike),
    (NounPhrase, nounPhrases),
    (Plural, plurals),
    (Adjective, adjectives),
    (Adverb, adverbs)
  )

  val commonAndUncommonWordsMap: Map[SpeechPart, (Vector[Word], Vector[Word])] = {
    speechPartWordsMap.map({case(sp, wv) =>
      val partition = wv.partition(_.isCommon)
      (sp, partition)
    })
  }



  def getRandom(sp: SpeechPart): Word = Utils.randomElement(speechPartWordsMap(sp))
  def getRandomWithUncommonProbability(probability: Double)(sp: SpeechPart): Word = {
    val (commons, uncommons) = commonAndUncommonWordsMap(sp)
    Utils.eitherWithProbability(Utils.randomElement(commons), Utils.randomElement(uncommons))(1.0 - probability)
  }
}

