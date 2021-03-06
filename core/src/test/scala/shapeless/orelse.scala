package shapeless

import test._
import testutil.assertTypedEquals

import org.junit.Test

class OrElseTests {
  sealed trait T
  class A extends T
  class B extends T
  class C extends T
  class D extends T

  implicit val a: A = new A
  implicit val b: B = new B

  @Test
  def testPrimaryFound {
    assertTypedEquals[T](the[A OrElse C].unify, a)
  }

  @Test
  def testSecondaryFound {
    assertTypedEquals[T](the[C OrElse A].unify, a)
  }

  @Test
  def testBothFound {
    assertTypedEquals[T](the[A OrElse B].unify, a)
  }

  @Test
  def testNeitherFound {
    illTyped("the[C OrElse D]")
  }
}
