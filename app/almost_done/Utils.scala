package net.almost_done

import scala.io.Codec
import scala.util.Random

/**
 * Created by nietaki on 12.08.14.
 */
object Utils {
  def getWords: Iterator[Word] = {
    import scala.io.Source
    val lines = Source.fromFile("data/mobyposi.i")(Codec("MacRoman")).getLines()
    lines.map{line:String =>
      //println(line)
      val parts =  line.split('◊')
      //println(parts)
      assert(parts.length == 2)
      Word(parts(0), parts(1))
    }
  }

  def randomElement[T](collection: IndexedSeq[T]): T = {
    val pos = Random.nextInt(collection.length)
    collection(pos)
  }

  def either[T](a: T, b: T): T = {
    if(Random.nextBoolean()) a else b
  }
}
