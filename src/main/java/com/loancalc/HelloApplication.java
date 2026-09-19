package com.loancalc;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(5, 5, 2, 15));
        gridPane.setHgap(15);
        gridPane.setVgap(15);

        int i = 0; //Columns grow as and 2 rows so loop to 2
        while (i < 2) {
            ColumnConstraints labelCol = new ColumnConstraints();
            labelCol.setPercentWidth(50); // labels only get 550% of width
            gridPane.getColumnConstraints().add(labelCol);
            i++;
        }
        //same as columns
        int k = 0;
        while (k < 6) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(20);
            gridPane.getRowConstraints().add(row);
            k++;
        }


        //collum1:
        Label apr = new Label("Annual interest rate: ");
        gridPane.add(apr, 0, 0);

        //col2
        Label numYears = new Label("Number of years :");
        gridPane.add(numYears, 0, 1);

        //col3
        Label loanAm = new Label("Loan Amount :");
        gridPane.add(loanAm, 0, 2);

        //col4
        Label monthly = new Label("Monthly payment :");
        gridPane.add(monthly, 0, 3);

        //col5
        Label total = new Label("Total payment :");
        gridPane.add(total, 0, 4);

        //rows
        TextField txtApr = new TextField();
        TextField txtyears = new TextField();
        TextField txtLnAm = new TextField();
        TextField txtMonthly = new TextField();
        TextField txtTotal = new TextField();

        gridPane.add(txtApr, 1, 0);
        gridPane.add(txtyears, 1, 1);
        gridPane.add(txtLnAm, 1, 2);
        gridPane.add(txtMonthly, 1, 3);
        gridPane.add(txtTotal, 1, 4);


        //calc button
        Button Calulate = new Button("Calculate");
        Calulate.setMinSize(35, 30);
        gridPane.add(Calulate, 1, 5);
        GridPane.setHalignment(Calulate, HPos.RIGHT); //button stays to the right


        Calulate.setOnAction(e -> {
            try {
                double annualInterestRate = Double.parseDouble(txtApr.getText());
                int months = Integer.parseInt(txtyears.getText()) * 12;
                double loanAmount = Double.parseDouble(txtLnAm.getText());
                double montlyinterestrate = (annualInterestRate / 100) / 12;

                double monthlyPayment = loanAmount * (montlyinterestrate * (Math.pow(1 + montlyinterestrate, months)))
                        / ((Math.pow(1 + montlyinterestrate, months)) - 1);
                double totalPayment = monthlyPayment * months;


                txtMonthly.setText(String.format("%.2f", monthlyPayment));
                txtTotal.setText(String.format("%.2f", totalPayment));

            } catch (NumberFormatException exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("PLS ENTER VALID NUMBERS");
                alert.showAndWait();
            }
        });


        Scene scene = new Scene(gridPane, 350, 250);
        stage.setTitle("LoanCalculator");
        stage.setScene(scene);
        stage.show();


        //Makes the fonts bigger so the size actually get bigger as the window gets bigger
        List<Label> lbls = List.of(apr, numYears, loanAm, monthly, total);

        for (Label lables : lbls) {
            lables.styleProperty().bind(Bindings.concat("-fx-font-size: ", (scene.widthProperty().add(scene.heightProperty())).divide(35), ";"));


        }
        Calulate.styleProperty().bind(Bindings.concat("-fx-font-size: ", (scene.widthProperty().add(scene.heightProperty())).divide(40), ";"));


    }


}
