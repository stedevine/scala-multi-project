package example.common

object Service {
  def outputText(message: String, o: Object): String = {
    val className = o.getClass.getName
    s"$message [$className]"
  }
}
