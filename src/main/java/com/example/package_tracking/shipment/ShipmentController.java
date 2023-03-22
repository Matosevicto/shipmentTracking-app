package com.example.package_tracking.shipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/shipment")
public class ShipmentController {

    private final ShipmentService shipmentService;

    @Autowired
    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @GetMapping
    public List<Shipment> getShipments() {
        return shipmentService.getShipments();

    }

    @GetMapping("/status")
    public List<Shipment> getShipmentsByStatus(@RequestParam("status") Status status) {
        return shipmentService.findShipmentsByStatus(status);
    }

    @GetMapping("/{id}")
    public Shipment getShipment(@PathVariable long id) {
        Optional<Shipment> shipment = shipmentService.getShipment(id);
        if (shipment.isPresent()) {
            return shipment.get();
        }
        System.out.println("Ne postoji posiljska s tim id-om");
        throw new RuntimeException("Ne postoji posiljska s tim id-om");
    }

    @GetMapping("/dates")
    public List<Shipment> getShipmentsByCreationDate(@RequestParam("startDate") String startDate,
                                                     @RequestParam("endDate") String endDate) {
        return shipmentService.findShipmentsByCreationDateBetween(startDate, endDate);
    }


    @PostMapping
    public void createNewShipment(@RequestBody Shipment shipment) {
        shipmentService.addNewShipment(shipment);
    }

    @DeleteMapping(path = "{id}")
    public void deleteShipment(@PathVariable("id") Long shipmentId) {
        shipmentService.deleteShipment(shipmentId);
    }

    @PutMapping(path = "{id}")
    public void updateShipment(@PathVariable("id") Long shipmentId,
                               @RequestParam(required = false) String person) {
        shipmentService.updateShipment(shipmentId, person);

    }

}

