package serviceDesk.bceTradicionalFaq;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PrincipalBoundary extends Application implements  CommandExecution {

    private FaqBoundary faqBoundary = new FaqBoundary();
    private CreditoBoundary creditoBoundary = new CreditoBoundary();

    BorderPane panePrincipal = new BorderPane();

    public PrincipalBoundary(){
        faqBoundary.addExecution(this);
        creditoBoundary.addExecution(this);
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


        itemSair.setOnAction((e)->{
            execute("SAIR");
        });

        itemFaqs.setOnAction((e)->{
            execute("BOUNDARY-FAQ");
        });
        itemCreditos.setOnAction((e)->{
            execute("BOUNDARY-CREDITOS");
        });

        menuArquivo.getItems().addAll(itemSair);
        menuCadastros.getItems().addAll(itemFaqs);
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
        }
    }

    public static void main(String[] args) {
        Application.launch(PrincipalBoundary.class, args);
    }
}



