//package com.pm.patient_service.controller;
//
//import com.pm.patient_service.kafka.KafkaProducer;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("test")
//public class TestProducerController {
//
//    private final KafkaProducer producer;
//
//    public TestProducerController(KafkaProducer producer) {
//        this.producer = producer;
//    }
//
//    @GetMapping("send")
//    public String sendTestEvent() {
//        producer.sendPatientEvent("123", "John Doe", "john@example.com");
//        return "✅ Test event sent!";
//    }
//}
