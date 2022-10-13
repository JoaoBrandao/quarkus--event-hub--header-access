package com.jb.azure;

import io.smallrye.reactive.messaging.kafka.IncomingKafkaRecord;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletionStage;
import org.eclipse.microprofile.reactive.messaging.*;

import javax.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

@ApplicationScoped
public class MessageConsumer {

  private static final Logger LOG = Logger.getLogger(MessageConsumer.class);

  /**
   * Consume events from Azure EventHub using Kafka dependency.
   **/
  @Incoming("routing")
  public CompletionStage<Void> consumeEvent(Message<byte[]> message) {

    //Works as expected
    String body = new String(message.getPayload(), StandardCharsets.UTF_8);
    LOG.infov("Message: {0}", body);


    // eventType should be 1045 if we use the collection
    LOG.infov("Header - eventType {0}", new String(((IncomingKafkaRecord) message).getHeaders().lastHeader("eventType").value(), StandardCharsets.UTF_8));

   return message.ack();
  }
}
