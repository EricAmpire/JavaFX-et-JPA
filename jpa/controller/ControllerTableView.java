package teste.jpa.controller;

import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import teste.jpa.Main;
import teste.jpa.model.Eleve;

import javax.persistence.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Eric-A- on 31/08/2017.
 */
public class ControllerTableView implements Initializable
{
    @FXML private TableView<Eleve> tableListeEleve;
    @FXML private TextField nomEleveField;
    @FXML private TextField prenomEleveField;

    public static final ObservableList<Eleve> ELEVES = FXCollections.observableArrayList();

    public static final EntityManagerFactory entitt = Persistence.createEntityManagerFactory("ExercicesPU");
    public static final EntityManager em = entitt.createEntityManager();

    public static final Stage DIALOG = new Stage();

    private static Eleve eleve;
    private static int indix;

    public static Eleve getEleve()
    {
        return eleve;
    }

    public static int getIndix()
    {
        return indix;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        tableListeEleve.setItems(ELEVES);

        DIALOG.initModality(Modality.APPLICATION_MODAL);

        Query requete = em.createNativeQuery("SELECT * FROM eleve");
        ELEVES.addAll(requete.getResultList());
    }

    public void sauver(ActionEvent actionEvent)
    {
        Eleve eleve = new Eleve();

        eleve.setNom(nomEleveField.getText());
        eleve.setPrenom(prenomEleveField.getText());

        //ajout dans la base de donnees
        em.getTransaction().begin();
        em.persist(eleve);
        em.getTransaction().commit();

        //ajout dans la tableView
        ELEVES.add(eleve);

        //suppression de
        nomEleveField.clear();
        prenomEleveField.clear();
    }

    public void modifier(ActionEvent actionEvent) throws IOException
    {
        if (tableListeEleve.getSelectionModel().getSelectedItem() != null)
        {
            Parent root = FXMLLoader.load(Main.class.getResource("vues/dialog.fxml"));

            indix = tableListeEleve.getSelectionModel().getSelectedIndex();
            eleve = tableListeEleve.getSelectionModel().getSelectedItem();

            DIALOG.setTitle("Modifier eleve ");
            DIALOG.setResizable(false);
            DIALOG.setScene(new Scene(root));
            DIALOG.showAndWait();
        }
    }

    public void supprimer(ActionEvent actionEvent)
    {
        if (tableListeEleve.getSelectionModel().getSelectedItem() != null)
        {
            //suppression de la database
            em.getTransaction().begin();
            em.remove(tableListeEleve.getSelectionModel().getSelectedItem());
            em.getTransaction().commit();

            //suppression de la tableView
            ELEVES.remove(tableListeEleve.getSelectionModel().getSelectedItem());
        }
    }
}
