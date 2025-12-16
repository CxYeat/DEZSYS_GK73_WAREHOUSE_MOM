package com.example.demo.config;

import com.example.demo.data.WarehouseData;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Kafka-Konfiguration für das Projekt.
 * <p>
 * Diese Klasse definiert Producer- und Consumer-Factorys sowie Kafka-Templates für
 * die Kommunikation mit Kafka. Sie enthält:
 * <ul>
 *     <li>Producer für WarehouseData (JSON-Objekte)</li>
 *     <li>Consumer für Feedback-Strings</li>
 *     <li>ConcurrentKafkaListenerContainerFactory für Listener</li>
 * </ul>
 *
 * @author Aron Handan
 */
@Configuration
public class KafkaConfig {

    /**
     * ProducerFactory für WarehouseData.
     * <p>
     * Erstellt eine Factory, die JSON-Objekte vom Typ {@link WarehouseData} serialisiert
     * und an den Kafka-Broker auf "localhost:9092" sendet.
     *
     * @return ProducerFactory für String-Keys und WarehouseData-Values
     */
    @Bean
    public ProducerFactory<String, WarehouseData> warehouseProducerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    /**
     * KafkaTemplate für WarehouseData.
     * <p>
     * Stellt eine Spring KafkaTemplate bereit, um Nachrichten vom Typ {@link WarehouseData}
     * an Kafka-Themen zu senden.
     *
     * @return KafkaTemplate für WarehouseData
     */
    @Bean
    public KafkaTemplate<String, WarehouseData> warehouseKafkaTemplate() {
        return new KafkaTemplate<>(warehouseProducerFactory());
    }

    /**
     * ConsumerFactory für Feedback-Strings.
     * <p>
     * Erstellt eine Factory für Consumer, die String-Nachrichten empfangen.
     * Diese Consumer gehören zur Gruppe "warehouse-feedback-group" und
     * lesen neue Nachrichten ab dem neuesten Offset.
     *
     * @return ConsumerFactory für String-Keys und String-Values
     */
    @Bean
    public ConsumerFactory<String, String> feedbackConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "warehouse-feedback-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        return new DefaultKafkaConsumerFactory<>(props);
    }


    /**
     * ConcurrentKafkaListenerContainerFactory für String-Nachrichten.
     * <p>
     * Diese Factory wird von Spring verwendet, um Listener-Container für Kafka zu erstellen.
     * Sie verwendet die {@link #feedbackConsumerFactory()} und erlaubt parallele Listener.
     *
     * @return ConcurrentKafkaListenerContainerFactory für String-Nachrichten
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> stringKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(feedbackConsumerFactory());
        return factory;
    }
}
