import com.rabbitmq.client._

object Producer extends App {

  val QUEUE_NAME = "gojideth-queue"
  val factory = new ConnectionFactory()

  factory.setHost("rabbitmq")
  factory.setUsername("admin")
  factory.setPassword("1234")

  val connection = factory.newConnection()
  val channel = connection.createChannel(1)

  // Declare durable
  channel.queueDeclare(QUEUE_NAME, true, false, false, null)

  val message = "hola estudiantes!"
  channel.basicPublish(
    /* exchange */ "",
    /* routingKey */ QUEUE_NAME,
    MessageProperties.PERSISTENT_TEXT_PLAIN,
    message.getBytes("UTF-8")
  )
  println(s"[x] Sent '$message'")

  channel.close()
  connection.close()

}
