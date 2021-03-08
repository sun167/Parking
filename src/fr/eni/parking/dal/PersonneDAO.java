package fr.eni.parking.dal;

import fr.eni.parking.bo.Personne;

import java.util.List;

public interface PersonneDAO {
    Personne selectById(final int id) throws DALException;
    List<Personne> selectAll() throws DALException;
    void update(final Personne item) throws DALException;
    void insert(final Personne item) throws DALException;
    void delete(final int id) throws DALException;
}
