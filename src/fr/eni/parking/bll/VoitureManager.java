package fr.eni.parking.bll;

import fr.eni.parking.bo.Voiture;
import fr.eni.parking.dal.DAOFactory;
import fr.eni.parking.dal.VoitureDAO;

import java.util.List;

public class VoitureManager {
    private VoitureDAO pDAO = DAOFactory.getVoitureDAO();
    private static VoitureManager INSTANCE = null;

    private VoitureManager() throws BLLException {

    }

    public static VoitureManager getInstance() throws BLLException {
        if(INSTANCE == null) {
            INSTANCE = new VoitureManager();
        }
        return INSTANCE;
    }

    // lister tous les Voitures du catalogue
    public List<Voiture> getCatalogue() throws BLLException {
        try {
            return pDAO.selectAll();
        } catch (Exception e) {
            throw new BLLException("Erreur");
        }
    }

    // ajouter une Voiture au catalogue
    public void addVoiture(Voiture voiture) throws BLLException {
        validerVoiture(voiture);
        try {
            pDAO.insert(voiture);
        } catch (Exception e) {
            throw new BLLException("Erreur");
        }
    }

    // modifier les caractéristiques d’une Voiture
    public void updateVoiture(Voiture voiture) throws BLLException {
        validerVoiture(voiture);
        try {
            pDAO.update(voiture);
        } catch (Exception e) {
            throw new BLLException("Erreur");
        }
    }

    // supprimer une Voiture
    public void removeVoiture(int index) throws BLLException {
        try {
            pDAO.delete(index);
        } catch (Exception e) {
            throw new BLLException("Erreur");
        }
    }

    public void validerVoiture(Voiture voiture) throws BLLException{
        if ("".equals(voiture.getNom())
                || "".equals(voiture.getImmatriculation())){
            throw new BLLException("Tous les champs sont obligatoires (sauf le conducteur)");
        }

        if (voiture.getImmatriculation().length() != 7){
            throw new BLLException("La plague d'immatriculation devrait avoir 7 chiffres et lettres");
        }
    }

    // récupérer une Voiture en connaissant son identifiant
    public Voiture getVoiture(int index) throws BLLException {
        try {
            return pDAO.selectById(index);
        } catch (Exception e) {
            throw new BLLException("Erreur");
        }
    }
}
