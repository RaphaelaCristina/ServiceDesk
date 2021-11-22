package serviceDesk.bceTradicional;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;
import java.util.List;

public class FaqControl {


    // propriedades assinaveis que estão dentro do TextField
    LongProperty id = new SimpleLongProperty(0);
    StringProperty titulo = new SimpleStringProperty("");
    StringProperty descricao = new SimpleStringProperty("");
    StringProperty status = new SimpleStringProperty("");
    ObjectProperty dataCriacao = new SimpleObjectProperty(LocalDate.now());
    StringProperty resposta = new SimpleStringProperty("");

    //Vincular a tabela com
    private ObservableList<Faq> listaView = FXCollections.observableArrayList();
    private FaqDAO faqDAO = new FaqDAOImpl();

    //O control passa a entender tudo o que acontece e nao precisa mais do boundary para nada
    //Ele passa a gerar e consumir a entidade

   public Faq getEntity(){
       Faq f = new Faq();
       f.setId((id.get()));
       f.setTitulo(titulo.get());
       f.setDescricao(descricao.get());
       f.setStatus(status.get());
       f.setDataCriacao((LocalDate) dataCriacao.get()); // Cast para LocalDate pois é objeto
       f.setResposta(resposta.get());

       return f;
    }

    public void setEntity(Faq f){
        id.set(f.getId());
        titulo.set(f.getTitulo());
        descricao.set(f.getDescricao());
        status.set(f.getStatus());
        dataCriacao.set(f.getDataCriacao());
        resposta.set(f.getResposta());
    }

    public void salvar(){
       Faq f = getEntity();
        if(f.getId() == 0){
            faqDAO.adicionar(f);
            setEntity(new Faq());
        } else {
            faqDAO.atualizar(id.get(),f);
        }
       atualizarListaView();
    }


    public void pesquisar(){
        listaView.clear();
        List<Faq> encontrados = faqDAO.pesquisarPorTitulo(titulo.get());
        listaView.addAll(encontrados);
    }

    public void remover(long id){
       faqDAO.remover(id);
       atualizarListaView();
    }

    public void atualizarListaView(){
        listaView.clear();
        listaView.addAll(faqDAO.pesquisarPorTitulo(""));
    }

    public void novoPet() {
        Faq f = new Faq();
        f.setId(0);
        setEntity(f);
    }

    public ObservableList<Faq> getListaView(){
        return listaView;
    }


}
