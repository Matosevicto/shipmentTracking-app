package com.example.package_tracking.shipment;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;

    public ShipmentService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public List<Shipment> getShipments() {
        return shipmentRepository.findAll();
    }

    public Optional<Shipment> getShipment(long id) {
        return shipmentRepository.findShipmentById(id);
    }

    public void addNewShipment(Shipment shipment) {
        shipmentRepository.save(shipment);
    }

    public List<Shipment> findShipmentsByStatus(Status status) {
        return shipmentRepository.findByStatus(status);
    }


    public List<Shipment> findShipmentsByCreationDateBetween(String startDate, String endDate) {
        LocalDate start = getDateFromString(startDate);
        LocalDate end = getDateFromString(endDate);
        return shipmentRepository.findByCreationBetween(start, end);
    }

    public LocalDate getDateFromString(String date) {
        return LocalDate.parse(date);
    }

    public void deleteShipment(Long shipmentId) {
        boolean exists = shipmentRepository.existsById(shipmentId);
        if (!exists) {
            throw new IllegalStateException("Posiljka sa " + shipmentId + " ne postoji.");
        }
        shipmentRepository.deleteById(shipmentId);
    }

    @Transactional
    public void updateShipment(Long shipmentId, String person) {
        Shipment shipment = shipmentRepository.findShipmentById(shipmentId).orElseThrow(() -> new IllegalStateException("Posiljka sa " + shipmentId + " ne postoji"));
        if (person != null && person.length() > 0) {
            shipment.setPerson(person);
        }
    }
}

