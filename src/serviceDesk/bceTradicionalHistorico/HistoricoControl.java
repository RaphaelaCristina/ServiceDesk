package serviceDesk.bceTradicionalHistorico;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class HistoricoControl {

    LongProperty idHistorico = new SimpleLongProperty(0);
    LongProperty idFormulario = new SimpleLongProperty(0);
    StringProperty nomeAtendente = new SimpleStringProperty("");
    StringProperty status = new SimpleStringProperty("Em aberto");

    private ObservableList<Historico> listaView = FXCollections.observableArrayList();
    private HistoricoDAO historicoDAO = new HistoricoDAOImpl();


    public Historico getEntity(){
        Historico h = new Historico();
        h.setIdHistorico(idHistorico.get());
        h.setIdFormulario(idFormulario.get());
        h.setNomeAtendente(nomeAtendente.get());
        h.setStatus(status.get());
        return h;
    }

    public void setEntity(Historico h){
        idHistorico.set(h.getIdHistorico());
        idFormulario.set(h.getIdFormulario());
        nomeAtendente.set(h.getNomeAtendente());
        status.set(h.getStatus());
    }

    public void pesquisar(){
        listaView.clear();
        List<Historico> encontrados = historicoDAO.pesquisarPorNome(nomeAtendente.get());
        listaView.addAll(encontrados);

    }

    public void salvar() {
        Historico h = getEntity();
        if(h.getIdHistorico() == 0){
            historicoDAO.adicionar(h);
            setEntity(new Historico());
        } else {
            historicoDAO.atualizar(idHistorico.get(), h);
        }
        atualizarListaView();
    }

    public void remover(long id){
        historicoDAO.remover(id);
        atualizarListaView();
    }

    private void atualizarListaView() {
        listaView.clear();
        listaView.addAll(historicoDAO.pesquisarPorNome(""));
    }

    public void novoHistorico() {

        Historico h = new Historico();
        h.setIdHistorico(0);
        setEntity(h);
    }

    public ObservableList<Historico> getListaView(){
        return listaView;
    }
}
