package jdbc;

import fr.eni.parking.bo.Personne;
import fr.eni.parking.bo.Voiture;
import fr.eni.parking.dal.DAOFactory;
import fr.eni.parking.dal.PersonneDAO;
import fr.eni.parking.dal.VoitureDAO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class VoitureDAOjdbcImplTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void selectById() {
        try {
            VoitureDAO dao = DAOFactory.getVoitureDAO();
            List<Voiture> liste = dao.selectAll();
            if(liste.size() > 0)
            {
                Voiture voiture =  liste.get(0);
                Voiture voitureATester =  dao.selectById(voiture.getId());

                assertEquals(voiture.getId() , voitureATester.getId());
                assertEquals(voiture.getNom(), (voitureATester.getNom()));
                assertEquals(voiture.getImmatriculation(),voitureATester.getImmatriculation());
                assertEquals(voiture.getConducteur().getId(), voitureATester.getConducteur().getId());
            }
        }catch (Exception e)
        {
            fail(e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void selectAll() {
        try {
            Personne personne = new Personne();
            personne.setNom("depp");
            personne.setPrenom("Johnny");

            Voiture voiture = new Voiture();
            voiture.setNom("Clio 3");
            voiture.setImmatriculation("SHSI342");
            voiture.setConducteur(personne);

            PersonneDAO dalPersonne = DAOFactory.getPersonneDAO();
            dalPersonne.insert(personne);
            VoitureDAO dal = DAOFactory.getVoitureDAO();
            dal.insert(voiture);
            List<Voiture> liste = dal.selectAll();
            if (liste.size() == 0) {
                fail("On recupere rien");
            }
        }catch (Exception e){
            fail(e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void update() {
        try {
            //preparer la personne
            PersonneDAO dalPersonne = DAOFactory.getPersonneDAO();
            Personne personne = new Personne();
            personne.setNom("alba");
            personne.setPrenom("Jessica");
            dalPersonne.insert(personne);

            VoitureDAO dao = DAOFactory.getVoitureDAO();
            List<Voiture> liste = dao.selectAll();
            Voiture voitureAModifer =  liste.get(0);
            voitureAModifer.setImmatriculation("1WOLOLO");
            voitureAModifer.setNom("BMW");
            voitureAModifer.setConducteur(personne);
            dao.update(voitureAModifer);

            Voiture apresModification = dao.selectById(voitureAModifer.getId());
            assertEquals("1WOLOLO",apresModification.getImmatriculation());
            assertEquals("BMW",apresModification.getNom());
            assertEquals(personne.getId(),apresModification.getConducteur().getId());
        }catch (Exception e)
        {
            fail(e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void insert() {
        try {
            //creer une personne
            Personne personne = new Personne();
            personne.setNom("angela");
            personne.setPrenom("jolie");
            //creer une voiture
            Voiture voiture = new Voiture();
            voiture.setNom("peugeot");
            voiture.setImmatriculation("Q23I342");
            voiture.setConducteur(personne);
            //declaration et instanciation de DAO
            PersonneDAO daoPersonne = DAOFactory.getPersonneDAO();
            VoitureDAO daoVoiture = DAOFactory.getVoitureDAO();

            daoPersonne.insert(personne);

            List<Voiture> liste = daoVoiture.selectAll();
            int avant = liste.size();

            daoVoiture.insert(voiture);

            List<Voiture> listeApres = daoVoiture.selectAll();
            int apres = listeApres.size();

            int resultat = apres - avant;
            assertEquals(1,resultat);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void delete() {
        try {
            VoitureDAO daoVoiture = DAOFactory.getVoitureDAO();
            List<Voiture> liste = daoVoiture.selectAll();
            int tailleAvant = liste.size();
            daoVoiture.delete(liste.get(0).getId());
            liste = daoVoiture.selectAll();
            int tailleApres = liste.size();
            int resultat = tailleAvant - tailleApres;
            assertEquals(1,resultat);

        }catch (Exception e)
        {
            fail(e.getMessage());
        }
    }
}