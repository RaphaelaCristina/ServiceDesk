package serviceDesk.bceTradicionalFaq;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.NumberStringConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


public class FaqBoundary implements StrategyBoundary{

    private TextField txtId = new TextField();
    private TextField txtTitulo = new TextField();
    private TextField txtDescricao = new TextField();
    private ComboBox<String> cmbStatus = new ComboBox<>();
    private TextField txtDataCriacao = new TextField();
    private TextField txtResposta = new TextField();

    private Button btnNovoFaq = new Button("Novo FAQ");
    private Button btnSalvar = new Button("Salvar");
    private Button btnPesquisar = new Button("Pesquisar");

    private FaqControl control = new FaqControl(); //composição

    private TableView<Faq> table = new TableView<>();

    private DateTimeFormatter dtc = DateTimeFormatter.ofPattern("dd/MM/yyy");

    private void criarTabela(){

        ObservableList<String> itens =
                FXCollections.observableArrayList("Não Respondido", "Respondido");
        cmbStatus.setItems(itens);

        TableColumn<Faq, Long> col1 = new TableColumn<>("Id");
        col1.setCellValueFactory(new PropertyValueFactory<Faq, Long>("id")); //Extraindo a propriedade do objeto

        TableColumn<Faq, String> col2 = new TableColumn<>("Titulo");
        col2.setCellValueFactory(new PropertyValueFactory<Faq, String>("titulo")); //Extraindo a propriedade do objeto

        TableColumn<Faq, String> col3 = new TableColumn<>("Descrição");
        col3.setCellValueFactory(new PropertyValueFactory<Faq, String>("descricao")); //Extraindo a propriedade do objeto

        TableColumn<Faq, String> col4 = new TableColumn<>("Status");
        col4.setCellValueFactory(new PropertyValueFactory<Faq, String>("status")); //Extraindo a propriedade do objeto

        TableColumn<Faq, String> col5 = new TableColumn<>("Data de Criação");
        col5.setCellValueFactory((faqProp)->{
            LocalDate d = faqProp.getValue().getDataCriacao();
            String strData = d.format(this.dtc);
            return new ReadOnlyStringWrapper(strData);
        }); //Extraindo a propriedade do objeto

        TableColumn<Faq, String> col6 = new TableColumn<>("Resposta");
        col6.setCellValueFactory(new PropertyValueFactory<Faq, String>("resposta")); //Extraindo a propriedade do objeto

        TableColumn<Faq, String> col7 = new TableColumn<>("Ações");
        col7.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        col7.setCellFactory((tbCol)->{
            TableCell<Faq, String> cell = new TableCell<Faq, String>(){
                final Button btn = new Button("Remover");

                public void updateItem(String item, boolean empty){
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }else{
                        btn.setOnAction((e)->{
                            Faq f = getTableView().getItems().get(getIndex());
                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    "Você confirma a remoção do FAQ " +
                                            f.getId(), ButtonType.OK, ButtonType.CANCEL);
                            Optional<ButtonType> clicado = alert.showAndWait();
                            if(clicado.isPresent() && clicado.get().equals(ButtonType.OK)){
                                control.remover(f.getId());
                            }

                        });

                        setGraphic(btn);
                        setText(null);
                    }
                }

            };
            return cell;
        });

        table.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7);

        table.setItems(control.getListaView()); //pegando as informações do control

        table
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, antigo, novo ) -> {
                    if (novo != null){
                        control.setEntity(novo);
                    }

                }
        );

    }

    @Override
    public Pane render () {
        BorderPane panPrincipal = new BorderPane();
        GridPane panCampos = new GridPane();

        //O ID não será editável e está desabilitado
        txtId.setEditable(false);
        txtId.setDisable(true);

        Bindings.bindBidirectional(txtId.textProperty(), control.id, new NumberStringConverter());
        Bindings.bindBidirectional(txtTitulo.textProperty(), control.titulo);
        Bindings.bindBidirectional(txtDescricao.textProperty(), control.descricao);
        Bindings.bindBidirectional(cmbStatus.valueProperty(), control.status);
        Bindings.bindBidirectional(txtResposta.textProperty(), control.resposta);
        Bindings.bindBidirectional(txtDataCriacao.textProperty(), control.dataCriacao, new LocalDateStringConverter());


        panCampos.add(new Label("Id"), 0,0);
        panCampos.add(txtId, 1 , 0);
        panCampos.add(btnNovoFaq, 2 , 0);

        panCampos.add(new Label("Titulo"), 0,1);
        panCampos.add(txtTitulo, 1 , 1);

        panCampos.add(new Label("Descrição"), 0,2);
        panCampos.add(txtDescricao, 1 , 2);

        panCampos.add(new Label("Status"), 0,3);
        panCampos.add(cmbStatus, 1 , 3);

        panCampos.add(new Label("Data de Criação"), 0,4);
        panCampos.add(txtDataCriacao, 1 , 4);

        panCampos.add(new Label("Resposta"), 0,5);
        panCampos.add(txtResposta, 1 , 5);

        panCampos.add(btnSalvar, 0 , 6);
        panCampos.add(btnPesquisar, 1 , 6);

        btnPesquisar.setOnAction( e -> {
            control.pesquisar();
        });

        btnSalvar.setOnAction(e ->{
            control.salvar();
        });

        btnNovoFaq.setOnAction((e ->{
            control.novoFaq();
        }));

        panPrincipal.setTop(panCampos); //colocando painel de campos dentro do principal
        panPrincipal.setCenter(table); //colocando a tabela no meio do painel principal
        this.criarTabela(); //criar tabela

        return panPrincipal;

    }
}




