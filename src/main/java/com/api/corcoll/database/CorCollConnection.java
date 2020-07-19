package com.api.corcoll.database;

import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class CorCollConnection {

    public Connection connDb = null;

    public static Connection conn() {

        Connection connDb = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connDb = DriverManager.getConnection(
                    "jdbc:mysql://localhost/corcoll_bd?usesSSL=false?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root",
                    "sasa");
            System.out.println("Conexión exitosa");

        } catch (SQLException | ClassNotFoundException e) {

            System.out.println("Conexión fallida: " + e);
            e.printStackTrace();
        }

        return connDb;
    }

}
