package com.swiftTrack.ros_mobile_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "deliveries")
@Getter
@Setter
public class Delivery {
    @Id
    private Integer id;

    private String code;
    private String destination;
    private Double expectedFee;
    private Long createdAt;
    private Long startedAt;
    private Long arrivedAt;
    private Long endedAt;
    private String status;
}