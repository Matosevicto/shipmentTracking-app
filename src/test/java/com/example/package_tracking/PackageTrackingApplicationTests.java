package com.example.package_tracking;

import com.example.package_tracking.shipment.ShipmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

@SpringBootTest
class PackageTrackingApplicationTests {

	@Autowired
	ShipmentService shipmentService;

	@Test
	public void parseDateTest() {
		LocalDate localDate = shipmentService.getDateFromString("2022-02-03");
		Assertions.assertEquals(2022, localDate.getYear());
		Assertions.assertEquals(Month.FEBRUARY, localDate.getMonth());
		Assertions.assertEquals(3, localDate.getDayOfMonth());

	}

	@Test
	void contextLoads() {
	}

}
