package test.fr.eni.parking.dal.jdbc;

import fr.eni.parking.bo.Personne;
import fr.eni.parking.dal.DAOFactory;
import fr.eni.parking.dal.PersonneDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonneDAOJdbcImplTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void selectById() {
        try {
            PersonneDAO dao = DAOFactory.getPersonneDAO();
            List<Personne> liste = dao.selectAll();
            if(liste.size() > 0)
            {
                Personne personne =  liste.get(0);
                Personne personneATester =  dao.selectById(personne.getId());
                assertEquals(personne.getId() , personneATester.getId());
                assertTrue(personne.getNom().equals(personneATester.getNom()));
                assertEquals(personne.getPrenom(),personneATester.getPrenom());
            }
        }catch (Exception e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    void selectAll() {
        try {
            Personne personne = new Personne();
            personne.setNom("Tare");
            personne.setPrenom("Gui");

            PersonneDAO dal = DAOFactory.getPersonneDAO();
            dal.insert(personne);
            List<Personne> liste = dal.selectAll();
            if (liste.size() == 0) {
                fail("On recupere rien");
            }
        }catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    void update() {
        try {
            PersonneDAO dao = DAOFactory.getPersonneDAO();
            List<Personne> liste = dao.selectAll();
            Personne personneAModifer =  liste.get(0);
            personneAModifer.setPrenom("WOLOLO");
            personneAModifer.setNom("ZOLOLO");
            dao.update(personneAModifer);
            Personne apresModification = dao.selectById(personneAModifer.getId());
            assertEquals("WOLOLO",apresModification.getPrenom());
            assertEquals("ZOLOLO",apresModification.getNom());
        }catch (Exception e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    void insert() {
        try {
            //creer une personne
            Personne conducteur = new Personne();
            conducteur.setNom("John");
            conducteur.setPrenom("Anthony");
            //declaration et instanciation de DAO
            PersonneDAO dao = DAOFactory.getPersonneDAO();

            List<Personne> liste = dao.selectAll();
            int avant = liste.size();

            dao.insert(conducteur);

            List<Personne> listeApres = dao.selectAll();
            int apres = listeApres.size();

            int resultat = apres - avant;
            assertEquals(1,resultat);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void delete() {
        try {
            PersonneDAO dal = DAOFactory.getPersonneDAO();
            List<Personne> liste = dal.selectAll();
            int tailleAvant = liste.size();
            dal.delete(liste.get(0).getId());
            liste = dal.selectAll();
            int tailleApres = liste.size();
            int resultat = tailleAvant - tailleApres;
            assertEquals(1,resultat);

        }catch (Exception e)
        {
            fail(e.getMessage());
        }
    }
}