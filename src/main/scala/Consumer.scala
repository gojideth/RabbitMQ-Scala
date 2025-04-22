import com.rabbitmq.client.{
  AMQP,
  Channel,
  Connection,
  ConnectionFactory,
  DefaultConsumer,
  Envelope
}

object Consumer {
  val QUEUE_NAME = "hello"
  val factory = new ConnectionFactory()
  factory.setHost("rabbitmq")
  factory.setUsername("admin")
  factory.setPassword("1234")

  val connection: Connection = factory.newConnection()
  val channel: Channel = connection.createChannel()

  channel.queueDeclare(QUEUE_NAME, true, false, false, null)
  println(s"[+] Waiting for messages on '$QUEUE_NAME'...")

  val consumer: DefaultConsumer = new DefaultConsumer(channel) {
    override def handleDelivery(
        consumerTag: String,
        envelope: Envelope,
        properties: AMQP.BasicProperties,
        body: Array[Byte]
    ): Unit = {
      val message = new String(body, "UTF-8")
      println(s"[>] Received '$message'")
      channel.basicAck(envelope.getDeliveryTag, false)

    }
  }
  // autoAck = false
  channel.basicConsume(QUEUE_NAME, false, consumer)

}
