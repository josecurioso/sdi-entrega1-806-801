package com.uniovi.tests;

import com.uniovi.services.DatabaseResetService;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class SDIWallapopApplicationTestsLocal extends SDIWallapopApplicationTestsAbstract {

	@Autowired
	private DatabaseResetService databaseResetService;

	@Override
    public void init() {
		URL = "http://localhost:8080";
		databaseResetService.resetDb();
	}

}