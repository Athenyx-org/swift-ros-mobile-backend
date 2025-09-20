package com.swiftTrack.ros_mobile_backend.repos;

import com.swiftTrack.ros_mobile_backend.models.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
    Optional <Delivery> findByCode(String code);

    List<Delivery> findByStatus(String status);
}