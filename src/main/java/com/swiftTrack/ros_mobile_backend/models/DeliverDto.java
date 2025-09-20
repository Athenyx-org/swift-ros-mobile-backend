package com.swiftTrack.ros_mobile_backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.Data;

@Data
public class DeliverDto {

    private Integer id;
    private Long endedAt;
    private Long startedAt;
    private Long arrivedAt;
    private String status;

}
