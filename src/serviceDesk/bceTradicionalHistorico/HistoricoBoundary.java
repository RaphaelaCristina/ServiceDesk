package serviceDesk.bceTradicionalHistorico;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import javafx.util.converter.NumberStringConverter;

import java.util.Optional;

public class HistoricoBoundary extends CommandProducer implements StrategyBoundary {

    private TextField txtIdHistorico = new TextField();
    private TextField txtIdFormulario = new TextField();
    private TextField txtNomeAtendente = new TextField();
    private ComboBox<String> cmbStatus = new ComboBox<>();

    private Button btnNovoHistorico = new Button("Novo Histórico");
    private Button btnAdicionar = new Button("Salvar/Atualizar");
    private Button btnPesquisar = new Button("Pesquisar");

    private HistoricoControl control = new HistoricoControl();

    private TableView<Historico> table = new TableView<>();

    private void criarTabela() {

        ObservableList<String> items =
                FXCollections.observableArrayList("Fechado", "Em aberto");
        cmbStatus.setItems(items);

        TableColumn<Historico, Long> col1 = new TableColumn<>("Id Histórico");
        col1.setCellValueFactory(new PropertyValueFactory<>("idHistorico"));

        TableColumn<Historico, Long> col2 = new TableColumn<>("Id Formulário");
        col2.setCellValueFactory(new PropertyValueFactory<>("idFormulario"));

        TableColumn<Historico, String> col3 = new TableColumn<>("Nome Atendente");
        col3.setCellValueFactory(new PropertyValueFactory<>("nomeAtendente"));

        TableColumn<Historico, String> col4 = new TableColumn<>("Status");
        col4.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<Historico, String> col5 = new TableColumn<>("Ações");
        col5.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        col5.setCellFactory((tbCol) -> {
            TableCell<Historico, String> cell = new TableCell<Historico, String>() {
                final Button btn = new Button("Remover");

                public void updateItem(String item, boolean empty) {
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        btn.setOnAction((e) -> {
                            Historico h = getTableView().getItems().get(getIndex());
                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    "Você confirma a remoção do Histórico Nº " +
                                            h.getIdHistorico(), ButtonType.OK, ButtonType.CANCEL);
                            Optional<ButtonType> clicado = alert.showAndWait();
                            if (clicado.isPresent() && clicado.get().equals(ButtonType.OK)) {
                                control.remover(h.getIdHistorico());
                            }

                        });

                        setGraphic(btn);
                        setText(null);
                    }
                }

            };
            return cell;
        });


        table.getColumns().addAll(col1, col2, col3, col4, col5);

        table.setItems(control.getListaView());

        table
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, antigo, novo) -> {
                            if (novo != null) {
                                control.setEntity(novo);
                            }

                        }
                );

    }

    @Override
    public Pane render() {

        BorderPane panPrincipal = new BorderPane();
        GridPane panCampos = new GridPane();
        txtIdHistorico.setEditable(false);
        txtIdHistorico.setDisable(true);

        Bindings.bindBidirectional(txtIdHistorico.textProperty(), control.idHistorico, new NumberStringConverter());
        Bindings.bindBidirectional(txtIdFormulario.textProperty(), control.idFormulario, new NumberStringConverter());
        Bindings.bindBidirectional(txtNomeAtendente.textProperty(), control.nomeAtendente);
        Bindings.bindBidirectional(cmbStatus.valueProperty(), control.status);


        panCampos.add(new Label("Id Historico"), 0, 0);
        panCampos.add(txtIdHistorico, 1, 0);
        panCampos.add(btnNovoHistorico, 2, 0);

        panCampos.add(new Label("Id Formulário"), 0, 1);
        panCampos.add(txtIdFormulario, 1, 1);

        panCampos.add(new Label("Atendente"), 0, 2);
        panCampos.add(txtNomeAtendente, 1, 2);

        panCampos.add(new Label("Status"), 0, 3);
        panCampos.add(cmbStatus, 1, 3);

        panCampos.add(btnAdicionar, 0, 4);
        panCampos.add(btnPesquisar, 1, 4);

        Button btnCreditos = new Button("Creditos");
        panCampos.add(btnCreditos, 2, 5);

        btnCreditos.setOnAction((e) -> {
            executeCommand("BOUNDARY-CREDITOS");
        });

        btnAdicionar.setOnAction(e -> {
            control.salvar();
        });

        btnPesquisar.setOnAction(e -> {
            control.pesquisar();
        });

        btnNovoHistorico.setOnAction(e -> {
            control.novoHistorico();
        });

        panPrincipal.setTop(panCampos); //colocando painel de campos dentro do principal
        panPrincipal.setCenter(table); //colocando a tabela no meio do painel principal
        this.criarTabela(); //criar tabela
        //Scene scn = new Scene(panPrincipal, 600, 400);

        return panPrincipal;
    }


}
