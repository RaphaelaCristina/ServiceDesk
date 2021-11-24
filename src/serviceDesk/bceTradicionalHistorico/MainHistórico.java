package serviceDesk.bceTradicionalHistorico;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainHistórico extends Application implements  CommandExecution {

    HistoricoBoundary historicoBoundary = new HistoricoBoundary();
    CreditoBoundary creditoBoundary = new CreditoBoundary();


    public MainHistórico(){
        historicoBoundary.addExecution(this);
        creditoBoundary.addExecution(this);
    }

    BorderPane panePrincipal = new BorderPane();



    @Override
    public void start(Stage stage) throws Exception {

        Scene scn = new Scene(panePrincipal, 1024, 768);

        MenuBar menuPrincipal = new MenuBar();
        Menu menuArquivo = new Menu("Arquivos");
        Menu menuCadastros = new Menu("Cadastros");


        MenuItem itemSair = new MenuItem("Sair");
        MenuItem itemHistorico = new MenuItem("Históricos");


        menuArquivo.getItems().addAll(itemSair);
        menuCadastros.getItems().addAll(itemHistorico);


        itemSair.setOnAction((e) -> {
            execute("SAIR");
        });

        itemHistorico.setOnAction((e) -> {
            execute("BOUNDARY-HISTORICO");
        });


        menuPrincipal.getMenus().addAll(menuArquivo, menuCadastros);
        panePrincipal.setTop(menuPrincipal);

        stage.setScene(scn);
        stage.setTitle("Gestão Historicos");
        stage.show();
    }



//    @Override
//    public void handle(ActionEvent event) {
//        EventTarget target = event.getTarget();
//        if(target instanceof MenuItem){
//            MenuItem menuItem = (MenuItem) target;
//            String texto = menuItem.getText();
//            StrategyBoundary tela = telas.get(texto);
//            panePrincipal.setCenter(tela.render());
//    }
//}


    @Override
    public void execute(String command) {
        if("BOUNDARY-HISTORICO".equals(command)) {
            panePrincipal.setCenter(historicoBoundary.render());
        } else if("SAIR".equals(command)){
            Platform.exit();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Application.launch(MainHistórico.class, args);
    }
}
