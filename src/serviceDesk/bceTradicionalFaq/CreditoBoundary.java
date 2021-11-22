package serviceDesk.bceTradicionalFaq;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class CreditoBoundary implements StrategyBoundary{

    @Override
    public Pane render(){
        VBox pane = new VBox();

        pane.getChildren().addAll(
                new Label("Bruno Espejo"),
                new Label("Cinthya Havena"),
                new Label("Raphaela Britto"),
                new Label("Professor Antonio Rodrigues - POO")
        );
        return pane;
    }
}
