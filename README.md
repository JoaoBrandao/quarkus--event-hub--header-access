# Azure Event Hub with Kafka dependency

Currently, I am are not able to access header properties from events that arrive at the topic of Azure Event Hub using <Strong>smallrye-reactive-messaging-kafka</strong> dependency.

# Flow

Let's assume that we are using Azure IoT hub to forward events to Azure Event Hub, and we want to consume those events and based on a certain property we will route the event to another topic.

```
Azure IoT Hub --> Event Hub (topic) --> Application ---> Topic 1
                                                    ---> Topic 2
```

# How to Run

1. create .env file and add new variable with Event Hub Namespace SAS POLICY

```
AZURE_EVENT_HUB_CONNECTION=Endpoint=sb://<<AZURE-EVENT-HUB-HOST-NAME>>;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=<<EVENT_HUB>>
```

2. run the command

```
mvn quarkus:dev
```

# Troubleshooting

Using the postman collection in the root of the project, we are publishing a event to Azure Event Hub using eventType <Strong>1045</Strong>

On the code we are trying to get access to the property and log it, but we are getting

```
2022-10-13 15:37:05,982 INFO  [com.jb.azu.MessageConsumer] (vert.x-eventloop-thread-1) Header - eventType q
```

instead

```
2022-10-13 15:37:05,982 INFO  [com.jb.azu.MessageConsumer] (vert.x-eventloop-thread-1) Header - eventType 1045
```

Code that access the property

```
new String(((IncomingKafkaRecord) message).getHeaders().lastHeader("eventType").value(), StandardCharsets.UTF_8)
```
