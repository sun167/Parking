package fr.eni.parking.dal;

import fr.eni.parking.dal.jdbc.PersonneDAOJdbcImpl;

public class PersonneDAOFactory {
    public static PersonneDAO getArticleDAO() {
        PersonneDAO item = new PersonneDAOJdbcImpl();
        return item;
    }
}