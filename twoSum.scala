def twoSum(nums: List[Int], target: Int): List[Int] =
  nums match {
    case List() => List()
    case x :: xs => {
      val index = xs.indexOf(target - x)
      if (index >= 0) List(0, index + 1)
      else twoSum(xs, target).map(_ + 1)
    }
  }
