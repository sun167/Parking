package fr.eni.parking.dal;

import fr.eni.parking.dal.jdbc.PersonneDAOJdbcImpl;

public class DAOFactory {
    public static PersonneDAO getPersonneDAO() {
        PersonneDAO item = new PersonneDAOJdbcImpl();
        return item;
    }
}