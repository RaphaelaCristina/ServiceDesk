package serviceDesk.bceTradicionalFaq;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import serviceDesk.bceTradicionalHistorico.HistoricoBoundary;
import serviceDesk.bceTradicionalHistorico.MainHistórico;

public class PrincipalBoundary extends Application implements  CommandExecution {

    private FaqBoundary faqBoundary = new FaqBoundary();
    private CreditoBoundary creditoBoundary = new CreditoBoundary();
    private HistoricoBoundary historicoBoundary = new HistoricoBoundary();
    private MainHistórico mainHistórico = new MainHistórico();
    BorderPane panePrincipal = new BorderPane();

    public PrincipalBoundary(){
        faqBoundary.addExecution(this);
        creditoBoundary.addExecution(this);
        historicoBoundary.addExecution(mainHistórico);

    }

    @Override
    public void start(Stage stage) throws Exception {

        Scene scn = new Scene(panePrincipal, 1024, 768);

        MenuBar menuPrincipal = new MenuBar();

        Menu menuArquivo = new Menu("Arquivos");
        Menu menuCadastros = new Menu("Cadastros");
        Menu menuAjuda = new Menu("Ajuda");

        MenuItem itemSair = new MenuItem("Sair");
        MenuItem itemFaqs = new MenuItem("FAQs");
        MenuItem itemCreditos = new MenuItem("Creditos");
        MenuItem itemHistoricos = new MenuItem("Historicos");


        itemSair.setOnAction((e)->{
            execute("SAIR");
        });

        itemFaqs.setOnAction((e)->{
            execute("BOUNDARY-FAQ");
        });

        itemHistoricos.setOnAction((e)->{
            execute("BOUNDARY-HISTORICO");
        });

        itemCreditos.setOnAction((e)->{
            execute("BOUNDARY-CREDITOS");
        });



        menuArquivo.getItems().addAll(itemSair);
        menuCadastros.getItems().addAll(itemFaqs);
        menuCadastros.getItems().addAll(itemHistoricos);
        menuAjuda.getItems().addAll(itemCreditos);

        menuPrincipal.getMenus().addAll(menuArquivo, menuCadastros, menuAjuda);
        panePrincipal.setTop(menuPrincipal);

        stage.setScene(scn);
        stage.setTitle("Gestão FAQS");
        stage.show();

    }


//    @Override
//    public void handle(ActionEvent event) {
//
//        EventTarget target = event.getTarget();
//        if(target instanceof MenuItem){
//            MenuItem menuItem = (MenuItem) target;
//            String texto = menuItem.getText();
//            StrategyBoundary tela = telas.get(texto);
//            panePrincipal.setCenter(tela.render());
//        }
//    }


    @Override
    public void execute(String command) {
        if("BOUNDARY-FAQ".equals(command)){
            panePrincipal.setCenter(faqBoundary.render());
        } else if("BOUNDARY-CREDITOS".equals(command)){
            panePrincipal.setCenter(creditoBoundary.render());
        }else if("SAIR".equals(command)){
            Platform.exit();
            System.exit(0);
        } else if("BOUNDARY-HISTORICO".equals(command)){
            panePrincipal.setCenter(historicoBoundary.render());
        }
    }

    public static void main(String[] args) {
        Application.launch(PrincipalBoundary.class, args);
    }
}



