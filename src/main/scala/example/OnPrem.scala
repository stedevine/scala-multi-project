package example

object OnPrem {
  def main(args: Array[String]): Unit = {
    println(common.Service.outputText("This service is running on prem", this))
  }

}
