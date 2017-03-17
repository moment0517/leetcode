/*题目地址 https://leetcode.com/problems/two-sum/?tab=Description
*
* Input: 1 2 3 4 5
* Output: List(0, 3)
* */

import scala.collection.mutable

object TwoSum {
  def twoSum(nums: List[Int], target: Int): List[Int] = {

    val map = mutable.HashMap.empty[Int, Int]
    var i = 0

    def search(nums: List[Int]): List[Int] = nums match {
      case Nil => Nil
      case x :: xs => {
        if (map.contains(target - x))
          List(map(target - x), i)
        else {
          map += x -> i
          i += 1
          search(xs)
        }
      }
    }

    search(nums)
  }
}


