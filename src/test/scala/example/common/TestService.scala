package example.common

import org.scalatest.FunSuite

class TestService extends FunSuite {
  test("output text is formatted") {
    assert("test [example.common.TestService]" == Service.outputText("test", this))
  }
}
