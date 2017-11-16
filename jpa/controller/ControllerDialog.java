package teste.jpa.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import teste.jpa.model.Eleve;

import static teste.jpa.controller.ControllerTableView.ELEVES;

/**
 * Created by Eric Ampire on 03/09/2017.
 */
public class ControllerDialog
{
    @FXML private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private Text textErreur;

    public void enregModification(ActionEvent actionEvent)
    {
        if (nomField.getText().isEmpty() || prenomField.getText().isEmpty())
        {
            textErreur.setText("Remplissez tout les champs !!!");
        } else
        {
            Eleve eleve = ControllerTableView.getEleve();
            int indix = ControllerTableView.getIndix();

            //suppression de la database
            ControllerTableView.em.getTransaction ().begin ();

            eleve.setNom(nomField.getText());
            eleve.setPrenom(prenomField.getText());

            ControllerTableView.em.getTransaction ().commit ();

            //update de la tableView
            ELEVES.set(indix,eleve);

            ControllerTableView.DIALOG.close();
        }
    }

    public void closeDialog(ActionEvent actionEvent)
    {
        ControllerTableView.DIALOG.close();
    }
}
