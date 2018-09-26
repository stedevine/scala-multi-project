package example

object OnPrem {
  def main(args: Array[String]): Unit = {
    println(common.Formatter.outputText("This service is running on prem", this))
  }

}
