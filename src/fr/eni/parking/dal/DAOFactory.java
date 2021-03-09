package fr.eni.parking.dal;

import fr.eni.parking.dal.jdbc.PersonneDAOJdbcImpl;
import fr.eni.parking.dal.jdbc.VoitureDAOjdbcImpl;

public class DAOFactory {
    public static PersonneDAO getPersonneDAO() {
        PersonneDAO item = new PersonneDAOJdbcImpl();
        return item;
    }
    public static VoitureDAO getVoitureDAO() {
        VoitureDAO item = new VoitureDAOjdbcImpl();
        return item;
    }
}