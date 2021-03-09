package fr.eni.parking.dal.jdbc;

import fr.eni.parking.bo.Personne;
import fr.eni.parking.dal.DALException;
import fr.eni.parking.dal.PersonneDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonneDAOJdbcImpl implements PersonneDAO {
    @Override
    public Personne selectById(final int id) throws DALException {
        PreparedStatement rqt;
        ResultSet rs;
        Personne conducteur = new Personne();
        try (Connection cnx = JdbcTools.getConnection()){
            rqt = cnx.prepareStatement("SELECT * FROM Personnes WHERE id = ?");
            rqt.setInt(1, id);
            rs = rqt.executeQuery();
            if (rs.next()) {
                conducteur.setId(rs.getInt("id"));
                conducteur.setNom(rs.getString("nom"));
                conducteur.setPrenom(rs.getString("prenom"));
            }
        } catch (SQLException e){
            throw new DALException("Get personne failed - id: " + id, e);
        }
        return conducteur;
    }

    @Override
    public List<Personne> selectAll() throws DALException {
        Statement rqt;
        ResultSet rs;
        List<Personne> liste = new ArrayList<Personne>();
        try (Connection cnx = JdbcTools.getConnection()){
            rqt = cnx.createStatement();
            rs = rqt.executeQuery("SELECT * FROM Personnes");
            Personne conducteur = new Personne();

            while (rs.next()) {
                conducteur.setId(rs.getInt("id"));
                conducteur.setNom(rs.getString("nom"));
                conducteur.setPrenom(rs.getString("prenom"));
                liste.add(conducteur);
            }
        } catch (SQLException e) {
            throw new DALException("selectAll failed - ", e);
        }
        return liste;
    }

    @Override
    public void update(final Personne item) throws DALException {
        PreparedStatement rqt;
        try(Connection cnx = JdbcTools.getConnection()) {
            rqt = cnx.prepareStatement("UPDATE Personnes SET nom=?, prenom=? WHERE id=?");
            rqt.setString(1, item.getNom());
            rqt.setString(2, item.getPrenom());
            rqt.setInt(3, item.getId());

            rqt.executeUpdate();

        } catch (SQLException e) {
            throw new DALException("Update personne failed - " + item, e);
        }


    }

    @Override
    public void insert(final Personne item) throws DALException {
        PreparedStatement rqt;
        String sqlInsert = "INSERT INTO Personnes(nom,prenom) values(?,?)";
        try(Connection cnx = JdbcTools.getConnection()) {
            rqt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            rqt.setString(1, item.getNom());
            rqt.setString(2, item.getPrenom());
            rqt.executeUpdate();
            ResultSet rs = rqt.getGeneratedKeys();
            if(rs.next()) {
                item.setId(rs.getInt(1));
            }
        } catch (SQLException e){

            throw new DALException("Insert failed - personne: " + item, e);
        }
    }

    @Override
    public void delete(final int id) throws DALException {
        PreparedStatement rqt;
        try(Connection cnx = JdbcTools.getConnection()) {
            rqt = cnx.prepareStatement("DELETE FROM Personnes WHERE id=?");
            rqt.setInt(1, id);
            rqt.executeUpdate();
        } catch (SQLException e) {
            throw new DALException("Delete personne failed - id=" + id, e);
        }
    }
}
