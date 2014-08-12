package net.almost_done

import scala.annotation.tailrec
import scala.util._

/**
 * Created by nietaki on 30.07.14.
 */
object InteractivePlayer {
  @tailrec
  def getPlayerChoice(optionCount: Int, prompt: String = "prompt"): Int = {
    val t: Try[Int] = Try(Console.readInt()).flatMap[Int]({i: Int =>
      if( i>=0 && i < optionCount)
        Success(i)
      else
        Failure(new IndexOutOfBoundsException())})
    t match {
      case Success(i) => i
      case Failure(_) => getPlayerChoice(optionCount, prompt)
    }
  }
}

