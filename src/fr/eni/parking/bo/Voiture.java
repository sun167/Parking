package fr.eni.parking.bo;
/**
 * Represente une voiture
 */
public class Voiture {
        private int id;
        private String nom;
        private String immatriculation;
        private Personne conducteur;
        public Voiture(){

        }
        public Voiture(String nom, String immatriculation, Personne conducteur){
            this.nom = nom;
            this.immatriculation = immatriculation;
            this.conducteur = conducteur;
        }
        public Voiture(int id, String nom, String immatriculation, Personne conducteur) {
            this.id = id;
            this.nom = nom;
            this.immatriculation = immatriculation;
            this.conducteur = conducteur;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getImmatriculation() {
            return immatriculation;
        }

        public void setImmatriculation(String immatriculation) {
            this.immatriculation = immatriculation;
        }

        public Personne getConducteur() {
            return conducteur;
        }

        public void setConducteur(Personne conducteur) {
            this.conducteur = conducteur;
        }

        @Override
        public String toString() {
            return "Voiture{" +
                    "nom='" + nom + '\'' +
                    ", immatriculation='" + immatriculation + '\'' +
                    ", personne=" + conducteur +
                    '}';
        }
}


