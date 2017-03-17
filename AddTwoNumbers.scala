/**
  * Created by leijun on 17/03/2017.
  * Problem link:https://leetcode.com/problems/add-two-numbers/#/description
  * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
  * Output: 7 -> 0 -> 8
  */


object AddTwoNumbers {

  case class ListNode(var value: Int) {
    var next: ListNode = null
  }

  def addTwoNumbers2(l1: ListNode, l2: ListNode): ListNode = {
    var sum = l1.value + l2.value
    var carry = sum / 10

    def add(p: ListNode, q: ListNode): ListNode =
      (p, q) match {
        case (null, null) => null
        case (null, _) => {
          q.value += carry
          q
        }
        case (_, null) => {
          p.value += carry
          p
        }
        case (_, _) => {
          val newListNode = ListNode(sum % 10)
          val v1 = if (p.next != null) p.next.value else 0
          val v2 = if (q.next != null) q.next.value else 0
          sum = v1 + v2 + carry
          carry = sum / 10
          newListNode.next = add(p.next, q.next)
          newListNode
        }
      }

    add(l1, l2)
  }
}
