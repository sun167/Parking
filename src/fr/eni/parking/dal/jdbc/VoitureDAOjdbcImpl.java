package fr.eni.parking.dal.jdbc;

import fr.eni.parking.bo.Voiture;
import fr.eni.parking.bo.Voiture;
import fr.eni.parking.dal.DALException;
import fr.eni.parking.dal.VoitureDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VoitureDAOjdbcImpl implements VoitureDAO {
    @Override
    public Voiture selectById(int id) throws DALException {
        PreparedStatement rqt;
        ResultSet rs;
        Voiture voiture = new Voiture();
        try (Connection cnx = JdbcTools.getConnection()){
            rqt = cnx.prepareStatement("SELECT * FROM Voitures WHERE id = ?");
            rqt.setInt(1, id);
            rs = rqt.executeQuery();
            if (rs.next()) {
                conducteur.setId(rs.getInt("id"));
                conducteur.setNom(rs.getString("nom"));
                conducteur.setPrenom(rs.getString("prenom"));
            }
        } catch (SQLException e){
            throw new DALException("Get Voiture failed - id: " + id, e);
        }
        return conducteur;
    }

    @Override
    public List<Voiture> selectAll() throws DALException {
        return null;
    }

    @Override
    public void update(Voiture item) throws DALException {

    }

    @Override
    public void insert(Voiture item) throws DALException {

    }

    @Override
    public void delete(int id) throws DALException {

    }
}
