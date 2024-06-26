package com.vg.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="user_preference")
public class UserPreferenceStore {
    @Id
    @Column(name = "user_id")
    private String user_id;

    @Column(name = "max_temp_alert")
    private double max_temp_alert;
}
