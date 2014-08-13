package net.almost_done

/**
 * Created by nietaki on 12.08.14.
 */
case class Word(word: String, flags: String, isCommon: Boolean) {
  def hasFlag(char: Char): Boolean = flags.contains(char)

  def isSimpleNoun = hasFlag('N')
  def isPlural = hasFlag('p')
  def isNounPhrase = hasFlag('h')
  def isNoun = isSimpleNoun || isNounPhrase
  def isNounLike = isNoun || isPronoun
  //verbs
  def isParticiple = hasFlag('V')
  def isTransitiveVerb = hasFlag('t')
  def isIntransitiveVerb = hasFlag('i')

  def isSimpleVerb = isTransitiveVerb || isIntransitiveVerb

  def isAdjective = hasFlag('A')
  def isAdverb = hasFlag('v')

  def isAdjectiveLike = isAdjective || isParticiple

  def isConjunction = hasFlag('C')

  def isPreposition = hasFlag('P')
  def isInterjection = hasFlag('!')

  def isPronoun = hasFlag('r')

  def isDefiniteArticle = hasFlag('D')
  def isIndefiniteArticle = hasFlag('I')

  def isNominative = hasFlag('o')

  def getPlural: Option[Word] = {
    if(this.isPlural) {
      Some(this)
    } else if(this.isSimpleNoun) {
      val newWord: String = word.last match {
        case 's' => word + "es"
        case 'y' => word.init + "ies"
        case _ => word + "s"
      }
      Some(Word(newWord, flags + "p", isCommon))
    } else {
      None
    }
  }

  def isSameCaseInsensitive(other: String): Boolean = {
    word.equalsIgnoreCase(other)
  }

  def capitalized: Word = Word(word.capitalize, flags, isCommon)

  def withCommon(c: Boolean) = this.copy(isCommon = c)
  def common = this.withCommon(true)

  override def toString: String = word
}
