package com.vg.repository;

import com.vg.repository.model.UserPreferenceStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPreferenceRepository extends JpaRepository<UserPreferenceStore, String> {

}
