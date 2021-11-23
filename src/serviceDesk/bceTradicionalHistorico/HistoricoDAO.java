package serviceDesk.bceTradicionalHistorico;

import java.util.List;

public interface HistoricoDAO {

    void adicionar(Historico h);
    List<Historico> pesquisarPorNome(String nome);
    void atualizar(long id, Historico h);
    void remover(long id);
}
