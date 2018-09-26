package example.common

import org.scalatest.FunSuite

class TestFormatter extends FunSuite {
  test("output text is formatted") {
    assert("test [example.common.TestFormatter]" == Formatter.outputText("test", this))
  }
}
