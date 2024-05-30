package com.vg.model;

import lombok.Data;

@Data
public class UserPreference {
    private String userId;

    private double maxTempAlert;
}
