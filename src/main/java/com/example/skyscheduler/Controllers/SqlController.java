package com.example.skyscheduler.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class SqlController {
    @Autowired
    JdbcTemplate sql;
}
