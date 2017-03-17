/** Problem url: https://leetcode.com/problems/longest-substring-without-repeating-characters/#/description
  * Created by leijun on 17/03/2017.
  */

import scala.collection.mutable.HashMap

object LengthOfLongestString {

  def lengthOfLongestString(s: String): Int = {
    val map = HashMap.empty[Char, Int]
    var i, posTag, ans = 0
    for (c <- s) {
      i += 1
      if (map.contains(c))
        posTag = map(c)
      map(c) = i
      ans = ans.max(i - posTag)
    }
    ans
  }
}
