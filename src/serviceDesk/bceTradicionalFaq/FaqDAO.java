package serviceDesk.bceTradicionalFaq;

import java.util.List;

public interface FaqDAO {

    void adicionar(Faq f);
    List<Faq> pesquisarPorTitulo(String titulo);
    void atualizar(long id, Faq f);
    void remover(long id);

}
