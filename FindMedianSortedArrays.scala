/** Problem url: https://leetcode.com/problems/median-of-two-sorted-arrays/#/description
  * Example:
  * nums1 = [1, 3]
  * nums2 = [2]
  *
  * The median is 2.0
  * Created by leijun on 18/03/2017.
  */
class FindMedianSortedArrays {
  def findMedianSortedArrays(nums1: Array[Int], nums2: Array[Int]): Double = {
    val (m, n) = (nums1.length, nums2.length)

    if (m > n) findMedianSortedArrays(nums2, nums1)

    var (i_min, i_max) = (0, m)
    val half_len = (m + n + 1) / 2
    var med = 0.0

    while (i_min <= i_max) {
      var i = (i_min + i_max) / 2
      var j = half_len - i

      if (i == 0 || j == n || nums1(i - 1) <= nums2(j)) {
        if (j == 0 || i == m || nums2(j - 1) <= nums1(i)) {
          i_min = i_max + 1
          var max_left, min_right = 0
          if (i == 0) {
            max_left = nums2(j - 1)
          }
          else if (j == 0) {
            max_left = nums1(i - 1)
          }
          else {
            max_left = nums1(i - 1).max(nums2(j - 1))
          }

          if (i == m) {
            min_right = nums2(j)
          }
          else if (j == n) {
            min_right = nums1(i)
          }
          else {
            min_right = nums1(i).min(nums2(j))
          }

          if ((m + n) % 2 == 1)
            med = max_left
          else
            med = (max_left + min_right).toFloat / 2.0
        }
        else
          i_max += 1
      }
      else
        i_max -= 1
    }
    med
  }

}
