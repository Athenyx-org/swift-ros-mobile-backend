package com.swiftTrack.ros_mobile_backend.controller;

import com.swiftTrack.ros_mobile_backend.models.DeliverDto;
import com.swiftTrack.ros_mobile_backend.models.Delivery;
import com.swiftTrack.ros_mobile_backend.repos.DeliveryRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @GetMapping("/by-code")
    public Delivery byCode(@RequestParam String code) {

        System.out.println(code);

       Optional<Delivery> x = deliveryRepository.findByCode(code);

       if (x.isPresent()) {

           return x.get();

       }

       return null;

    }

    @GetMapping("/ended")
    public List<Delivery> history(@RequestParam String status) {

        List<Delivery> list = deliveryRepository.findByStatus(status);

        return list;

    }

    @PutMapping("/changeStatus")
    public String updateStatus(@RequestBody DeliverDto dto) {
        Delivery delivery = deliveryRepository.findById(dto.getId()).orElse(null);
        if (delivery == null) {
            return "Delivery not found";
        }

        delivery.setStatus(dto.getStatus());
        if ("en_route".equals(dto.getStatus()) && dto.getStartedAt() != null) {
            delivery.setStartedAt(dto.getStartedAt());
        }
        if ("arrived".equals(dto.getStatus()) && dto.getArrivedAt() != null) {
            delivery.setArrivedAt(dto.getArrivedAt());
        }
        if ("ended".equals(dto.getStatus()) && dto.getEndedAt() != null) {
            delivery.setEndedAt(dto.getEndedAt());
        }
        if ("cancelled".equals(dto.getStatus())) {
            delivery.setStatus(("not_started"));
            delivery.setEndedAt(null);
            delivery.setArrivedAt(null);
            delivery.setStartedAt(null);
        }
        deliveryRepository.save(delivery);
        return "Delivery status updated";
    }

}
