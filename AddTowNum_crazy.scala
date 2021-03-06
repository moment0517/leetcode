/**
  * Created by leijun on 17/03/2017.
  * 题义理解错误，硬将Input当成字符串处理，结果是一样的。。。
  * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
  * Output: 7 -> 0 -> 8
  */
object AddTwoNum {

  def addTwoNum(input: String): String = {
    input
      .split("\\+")
      .map(
        """(\d+)"""
          .r.findAllIn
      )
      .map(
        _.toList
          .foldRight(0)(
            (a, b) =>
              10 * (a.toInt + b)
          )
      )
      .foldLeft(0)( _  + _ / 10)
      .toString
      .toList
      .mkString(" -> ")
  }

}
