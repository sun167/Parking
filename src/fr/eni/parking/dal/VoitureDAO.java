package fr.eni.parking.dal;

import fr.eni.parking.bo.Voiture;
import fr.eni.parking.bo.Voiture;

import java.util.List;

public interface VoitureDAO {
    Voiture selectById(final int id) throws DALException;
    List<Voiture> selectAll() throws DALException;
    void update(final Voiture item) throws DALException;
    void insert(final Voiture item) throws DALException;
    void delete(final int id) throws DALException;
}
