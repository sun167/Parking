package fr.eni.parking.dal;

import fr.eni.parking.bo.Personne;

import java.util.List;

public class AppliTestDAL {
    public static void main(String[] args) {
        // Declaration et instanciation de DAO
        PersonneDAO conducteur = PersonneDAOFactory.getArticleDAO();

        Personne c1 = new Personne("John","Doe");
        Personne c2 = new Personne("Hannah","Loo");
        Personne c3 = new Personne("Chan","Jacky");

        try {
            conducteur.insert(c1);
            System.out.println("Article ajouté  : " + c1.toString());
            conducteur.insert(c2);
            System.out.println("Article ajouté  : " + c2.toString());
            conducteur.insert(c3);
            System.out.println("Article ajouté  : " + c3.toString());
            Personne c = conducteur.selectById(c2.getId());
            System.out.println("Sélection d'une personne par id  : " + c2.toString() );

            //Sélection de tous les articles
            //TODO...
            List<Personne> personnes = conducteur.selectAll();

            //Modification d'un article
            //TODO...
            System.out.println("Modification d'une personne  : " );
            System.out.println("Personne avant modification : "  + c1.toString());
            c1.setNom("Jojo");
            c1.setPrenom("sasa");
            conducteur.update(c1);
            System.out.println("Personne après modification  : " + c1.toString() );


            //Suppression d'un article
            //TODO...
            System.out.println("Suppression de : " + c1.toString());
            conducteur.delete(c1.getId());
            personnes = conducteur.selectAll();
            System.out.println("---------------------------------------------------------------");
        }catch (DALException e){
            e.printStackTrace();
        }
    }
}
