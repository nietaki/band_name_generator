package net.almost_done

/**
 * Created by nietaki on 12.08.14.
 */
object Words {
  val all: Vector[Word] = Utils.getWords.toVector
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

  def getRandom(sp: SpeechPart): Word = Utils.randomElement(speechPartWordsMap(sp))
}

