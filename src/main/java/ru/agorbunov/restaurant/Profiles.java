package ru.agorbunov.restaurant;

/**
 * Created by Admin on 24.02.2017.
 */
//// TODO: 24.02.2017 remove class before production
public class Profiles {
    public static final String
            POSTGRES = "postgres",
            HSQLDB = "hsqldb",
            JDBC = "jdbc",
            JPA = "jpa";

    public static String getActiveDbProfile() {
        try {
            Class.forName("org.postgresql.Driver");
            return Profiles.POSTGRES;
        } catch (ClassNotFoundException ex) {
            try {
                Class.forName("org.hsqldb.jdbcDriver");
                return Profiles.HSQLDB;
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Could not resolve DB profile");
            }
        }
    }
}
