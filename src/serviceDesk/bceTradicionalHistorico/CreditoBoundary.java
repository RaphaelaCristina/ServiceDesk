package serviceDesk.bceTradicionalHistorico;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CreditoBoundary extends  CommandProducer implements StrategyBoundary {

    @Override
    public Pane render(){
        VBox pane = new VBox();
        Button btnHistoricos = new Button("Cadastro Histórico");
        pane.getChildren().addAll(
                new Label("Bruno Espejo"),
                new Label("Cinthya Ravena"),
                new Label("Raphaela Britto"),
                new Label("Professor Antonio Rodrigues - POO"),
                btnHistoricos
        );

        btnHistoricos.setOnAction((e)->{
            executeCommand("BOUNDARY-HISTORICO");
        });
        return pane;
    }

}
