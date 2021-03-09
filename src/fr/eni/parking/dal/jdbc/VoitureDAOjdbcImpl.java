package fr.eni.parking.dal.jdbc;

import fr.eni.parking.bo.Personne;
import fr.eni.parking.bo.Voiture;
import fr.eni.parking.bo.Voiture;
import fr.eni.parking.bo.Voiture;
import fr.eni.parking.dal.*;
import fr.eni.parking.dal.VoitureDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VoitureDAOjdbcImpl implements VoitureDAO {
    @Override
    public Voiture selectById(int id) throws DALException {
        PreparedStatement rqt;
        ResultSet rs;
        Voiture voiture = new Voiture();
        PersonneDAO dao = DAOFactory.getPersonneDAO();
        Personne conducteur = null;
        try (Connection cnx = JdbcTools.getConnection()){
            rqt = cnx.prepareStatement("SELECT * FROM Voitures WHERE id = ?");
            rqt.setInt(1, id);
            rs = rqt.executeQuery();
            if (rs.next()) {
                voiture.setId(rs.getInt("id"));
                voiture.setNom(rs.getString("nom"));
                voiture.setImmatriculation(rs.getString("immatriculation"));
                conducteur = dao.selectById(rs.getInt("conducteur"));
                voiture.setConducteur(conducteur);
            }
        } catch (SQLException e){
            throw new DALException("Get Voiture failed - id: " + id, e);
        }
        return voiture;
    }

    @Override
    public List<Voiture> selectAll() throws DALException {
        Statement rqt;
        ResultSet rs;
        List<Voiture> liste = new ArrayList<Voiture>();
        try (Connection cnx = JdbcTools.getConnection()){
            rqt = cnx.createStatement();
            rs = rqt.executeQuery("SELECT * FROM Voitures");
            Voiture voiture = new Voiture();
            PersonneDAO dao = DAOFactory.getPersonneDAO();
            Personne conducteur = null;

            while (rs.next()) {
                voiture.setId(rs.getInt("id"));
                voiture.setNom(rs.getString("nom"));
                voiture.setImmatriculation(rs.getString("immatriculation"));
                conducteur = dao.selectById(rs.getInt("conducteur"));
                voiture.setConducteur(conducteur);
                liste.add(voiture);
            }
        } catch (SQLException e) {
            throw new DALException("selectAll failed - ", e);
        }
        return liste;
    }

    @Override
    public void update(Voiture item) throws DALException {
        PreparedStatement rqt;
        try(Connection cnx = JdbcTools.getConnection()) {
            rqt = cnx.prepareStatement("UPDATE Voitures SET nom=?, immatriculation=?, conducteur=? WHERE id=?");
            rqt.setString(1, item.getNom());
            rqt.setString(2, item.getImmatriculation());
            rqt.setInt(3, item.getConducteur().getId());
            rqt.setInt(4, item.getId());

            rqt.executeUpdate();

        } catch (SQLException e) {
            throw new DALException("Update voiture failed - " + item, e);
        }
    }

    @Override
    public void insert(Voiture item) throws DALException {
        PreparedStatement rqt;
        String sqlInsert = "INSERT INTO Voitures(nom,immatriculation,conducteur) values(?,?,?)";
        try(Connection cnx = JdbcTools.getConnection()) {
            rqt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            rqt.setString(1, item.getNom());
            rqt.setString(2, item.getImmatriculation());
            rqt.setInt(3, item.getConducteur().getId());
            rqt.executeUpdate();
            ResultSet rs = rqt.getGeneratedKeys();
            if(rs.next()) {
                item.setId(rs.getInt(1));
            }
        } catch (SQLException e){

            throw new DALException("Insert failed - voiture: " + item, e);
        }
    }

    @Override
    public void delete(int id) throws DALException {
        PreparedStatement rqt;
        try(Connection cnx = JdbcTools.getConnection()) {
            rqt = cnx.prepareStatement("DELETE FROM Voiture WHERE id=?");
            rqt.setInt(1, id);
            rqt.executeUpdate();
        } catch (SQLException e) {
            throw new DALException("Delete voiture failed - id=" + id, e);
        }
    }
}
