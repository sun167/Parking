package fr.eni.parking.bll;

import fr.eni.parking.bo.Personne;
import fr.eni.parking.dal.DAOFactory;
import fr.eni.parking.dal.PersonneDAO;

import java.util.List;

public class PersonneManager {
    private PersonneDAO pDAO = DAOFactory.getPersonneDAO();
    private static PersonneManager INSTANCE = null;

    private PersonneManager() throws BLLException {

    }

    public static PersonneManager getInstance() throws BLLException {
        if(INSTANCE == null) {
            INSTANCE = new PersonneManager();
        }
        return INSTANCE;
    }

    // lister tous les Personnes du catalogue
    public List<Personne> getCatalogue() throws BLLException {
        try {
            return pDAO.selectAll();
        } catch (Exception e) {
            throw new BLLException("Erreur");
        }
    }

    // ajouter une Personne au catalogue
    public void addPersonne(Personne personne) throws BLLException {
        validerPersonne(personne);
        try {
            pDAO.insert(personne);
        } catch (Exception e) {
            throw new BLLException("Erreur");
        }
    }

    // modifier les caractéristiques d’une Personne
    public void updatePersonne(Personne personne) throws BLLException {
        validerPersonne(personne);
        try {
            pDAO.update(personne);
        } catch (Exception e) {
            throw new BLLException("Erreur");
        }
    }

    // supprimer une Personne
    public void removePersonne(int index) throws BLLException {
        try {
            pDAO.delete(index);
        } catch (Exception e) {
            throw new BLLException("Erreur");
        }
    }

    public void validerPersonne(Personne personne) throws BLLException{
        if ("".equals(personne.getNom()) || "".equals(personne.getPrenom()) ) {
            throw new BLLException("Tous les champs sont obligatoires");
        }

    }

    // récupérer une Personne en connaissant son identifiant
    public Personne getPersonne(int index) throws BLLException {
        try {
            return pDAO.selectById(index);
        } catch (Exception e) {
            throw new BLLException("Erreur");
        }
    }
}
