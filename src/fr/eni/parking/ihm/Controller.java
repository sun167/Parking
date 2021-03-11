package fr.eni.parking.ihm;
import fr.eni.parking.bll.PersonneManager;
import fr.eni.parking.bll.VoitureManager;
import fr.eni.parking.bo.Personne;
import fr.eni.parking.bo.Voiture;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class Controller {
    @FXML
    private TableView<Voiture> tableVoiture;
    @FXML
    private TableColumn<Voiture,String> nomVoiture;
    @FXML
    private TableColumn<Voiture,String> immatriculation;
    @FXML
    private TableColumn<Voiture,String> conducteur;

    @FXML
    public TextField voitureField;
    @FXML
    public TextField immatriculationField;
    @FXML
    private Label voitureLabel;
    @FXML
    private Label immatriculationLabel;

    public static VoitureManager voitureData;

    @FXML
    public TableView<Personne> tablePersonne;
    @FXML
    public TableColumn<Personne,String> nomPersonne;
    @FXML
    public TableColumn<Personne,String> prenomPersonne;
    @FXML
    public TextField nomField;
    @FXML
    public TextField prenomField;
    @FXML
    public Label nomLabel;
    @FXML
    public Label prenomLabel;

    public static PersonneManager personData;
    Voiture voitureRecherche;
    Personne personneRecherche;
    int indexVoiture;
    int indexPersonne;

    public void selectVoiture(MouseEvent mouseEvent) {
        Voiture voitureRecherche = tableVoiture.getSelectionModel().getSelectedItem();
        indexVoiture = voitureRecherche.getId();
    }

    public void selectPersonne(MouseEvent mouseEvent) {
        Personne personneRecherche = tablePersonne.getSelectionModel().getSelectedItem();
        indexPersonne = personneRecherche.getId();
    }

    public void initialize() {
        try {
            //initialiser la table personne
            personData = new PersonneManager();

            nomPersonne.setCellValueFactory(new PropertyValueFactory<Personne,String>("nom"));
            prenomPersonne.setCellValueFactory(new PropertyValueFactory<Personne,String>("prenom"));

            List<Personne> listPersonne = personData.getCatalogue();
            ObservableList<Personne> observablePersonne = FXCollections.observableList(listPersonne);

            tablePersonne.setItems(observablePersonne);

            //initialiser la table voiture
            voitureData = new VoitureManager();

            nomVoiture.setCellValueFactory(new PropertyValueFactory<Voiture,String>("nom"));
            immatriculation.setCellValueFactory(new PropertyValueFactory<Voiture,String>("immatriculation"));
            conducteur.setCellValueFactory(new PropertyValueFactory<Voiture,String>("conducteur"));

            List<Voiture> listVoiture = voitureData.getCatalogue();
            ObservableList<Voiture> observableVoiture = FXCollections.observableList(listVoiture);

            tableVoiture.setItems(observableVoiture);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void ajouterPersonne(ActionEvent actionEvent) {
        try {
            Personne personneAjouter = new Personne();
            personneAjouter.setNom(nomField.getText());
            personneAjouter.setPrenom(prenomField.getText());
            personData.addPersonne(personneAjouter);
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modifierPersonne(ActionEvent actionEvent) {
        try{
        Personne personneModifier= new Personne();
        personneModifier.setNom(nomField.getText());
        personneModifier.setPrenom(prenomField.getText());
        personneModifier.setId(indexPersonne);
        personData.updatePersonne(personneModifier);
        initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void supprimerPersonne(ActionEvent actionEvent) {
        try{
            personData.removePersonne(indexPersonne);
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ajouterVoiture(ActionEvent actionEvent) {
        try {
            Voiture voitureAjouter = new Voiture();
            Personne conducteur = comboNom.getSelectionModel().getSelectedItem();
            voitureAjouter.setNom(voitureField.getText());
            voitureAjouter.setImmatriculation(immatriculationField.getText());
            voitureAjouter.setConducteur(conducteur);
            voitureData.addVoiture(voitureAjouter);
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modifierVoiture(ActionEvent actionEvent) {
    }

    public void supprimerVoiture(ActionEvent actionEvent) {
        try{
            voitureData.removeVoiture(indexVoiture);
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
