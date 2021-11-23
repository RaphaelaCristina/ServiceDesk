package serviceDesk.bceTradicionalHistorico;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class PrincipalBoundary extends Application implements EventHandler<ActionEvent> {

    HistoricoBoundary historicoBoundary = new HistoricoBoundary();
    CreditoBoundary creditoBoundary = new CreditoBoundary();
    Map<String , StrategyBoundary> telas = new HashMap<>();

    BorderPane panePrincipal = new BorderPane();

    public PrincipalBoundary(){
        telas.put("Históricos", new HistoricoBoundary());
        telas.put("Créditos", new CreditoBoundary());
    }
    @Override
    public void start(Stage stage) throws Exception {

        Scene scn = new Scene(panePrincipal, 1024, 768);

        stage.setScene(scn);
        stage.setTitle("Históricos");
        stage.show();


        MenuBar menuPrincipal = new MenuBar();
        Menu menuArquivo = new Menu("Arquivos");
        Menu menuCadastros = new Menu("Cadastros");
        Menu menuAjuda = new Menu("Ajuda");

        MenuItem itemSair = new MenuItem("Sair");
        MenuItem itemHistorico = new MenuItem("Históricos");
        MenuItem itemCreditos = new MenuItem("Créditos");

        menuArquivo.getItems().addAll(itemSair);
        menuCadastros.getItems().addAll(itemHistorico);
        menuAjuda.getItems().addAll(itemCreditos);

        itemSair.setOnAction((e)->{
            Platform.exit();
            System.exit(0);
        });

        itemHistorico.setOnAction(this);
        itemCreditos.setOnAction(this);

        menuPrincipal.getMenus().addAll(menuArquivo, menuCadastros, menuAjuda);
        panePrincipal.setTop(menuPrincipal);
    }

    public static void main(String[] args){
        Application.launch(PrincipalBoundary.class, args);
    }


    @Override
    public void handle(ActionEvent event) {
        EventTarget target = event.getTarget();
        if(target instanceof MenuItem){
            MenuItem menuItem = (MenuItem) target;
            String texto = menuItem.getText();
            StrategyBoundary tela = telas.get(texto);
            panePrincipal.setCenter(tela.render());
    }
}
}
