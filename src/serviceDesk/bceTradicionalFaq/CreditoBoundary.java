package serviceDesk.bceTradicionalFaq;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class CreditoBoundary extends CommandProducer implements StrategyBoundary{

    @Override
    public Pane render(){
        VBox pane = new VBox();
        Button btnFaqs = new Button("Cadastro FAQs");

        pane.getChildren().addAll(
                new Label("Bruno Espejo"),
                new Label("Cinthya Ravena"),
                new Label("Raphaela Britto"),
                new Label("Professor Antonio Rodrigues - POO"), btnFaqs
        );

        btnFaqs.setOnAction((e)->{
            executeCommand("BOUNDARY-FAQ");
        });
        return pane;
    }


}
