package example

object Cloud {
  def main(args: Array[String]): Unit = {
    println(common.Formatter.outputText("This service is running in the cloud!", this))
  }
}
