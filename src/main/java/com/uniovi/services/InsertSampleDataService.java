package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class InsertSampleDataService {

    @Autowired
    private DatabaseResetService databaseResetService;

    @PostConstruct
    public void init() {
        databaseResetService.resetDb();
    }
}