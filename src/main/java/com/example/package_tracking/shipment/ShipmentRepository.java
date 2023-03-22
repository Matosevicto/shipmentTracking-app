package com.example.package_tracking.shipment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    Optional<Shipment> findShipmentById(Long id);

    List<Shipment> findByStatus(Status status);

    List<Shipment> findByCreationBetween(LocalDate startDate, LocalDate endDate);
}

