package net.almost_done

import scala.io.Codec
import scala.io.Source
import scala.util.Random

/**
 * Created by nietaki on 12.08.14.
 */
object Utils {
  def getWords: Iterator[Word] = {
    val lines = Source.fromFile("data/mobyposi.i")(Codec("MacRoman")).getLines()
    lines.map{line:String =>
      //println(line)
      val parts =  line.split('◊')
      //println(parts)
      assert(parts.length == 2)
      Word(parts(0), parts(1), false)
    }
  }

  def getTopWords(count: Int = 10000): Iterator[String] = {
    Source.fromFile("data/top10000en.txt").getLines().take(count)
  }

  def randomElement[T](collection: IndexedSeq[T]): T = {
    val pos = Random.nextInt(collection.length)
    collection(pos)
  }

  def either[T](a: => T, b: => T): T = {
    if(Random.nextBoolean()) a else b
  }

  def eitherWithProbability[T](a: => T, b: => T)(firstProbability: Double): T = {
    if(Random.nextDouble <= firstProbability) a else b
  }

}
