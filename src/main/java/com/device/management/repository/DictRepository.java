package com.device.management.repository;

import com.device.management.entity.Dict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictRepository extends JpaRepository<Dict, String> {
}