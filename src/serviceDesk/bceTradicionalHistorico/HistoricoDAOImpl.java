package serviceDesk.bceTradicionalHistorico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HistoricoDAOImpl implements HistoricoDAO {

    private static final String DBURL = "jdbc:mariadb://localhost:3306/historicodb?allowMultiQueries=true";
    private static final String DBUSER = "root";
    private static final String DBPASS = "123456";

    public HistoricoDAOImpl(){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }catch ( Exception e ){
            e.printStackTrace();
        }
    }

    @Override
    public void adicionar(Historico h) {

        try{
            Connection con =  DriverManager.getConnection(DBURL, DBUSER, DBPASS);

            String sql = "INSERT INTO historico (idHistorico, idFormulario, nomeAtendente, status)" +
                    "VALUES (?, ?, ?, ?)";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, h.getIdHistorico());
            stmt.setLong(2, h.getIdFormulario());
            stmt.setString(3, h.getNomeAtendente());
            stmt.setString(4, h.getStatus());
            stmt.executeUpdate();

            con.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public List<Historico> pesquisarPorNome(String nome) {
        List<Historico> encontrados = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

            String sql = "SELECT * FROM historico WHERE nomeAtendente like ?";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            while( rs.next() ) {
                Historico h = new Historico();
                h.setIdHistorico(rs.getLong("idHistorico"));
                h.setIdFormulario(rs.getLong("idFormulario"));
                h.setNomeAtendente(rs.getString("nomeAtendente"));
                h.setStatus(rs.getString("status"));
                encontrados.add(h);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encontrados;
    }

    @Override
    public void atualizar(long id, Historico h) {
        try{
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

            String sql = " UPDATE historico SET idFormulario = ?, nomeAtendente = ?, status = ?  WHERE idHistorico = ?";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, h.getIdFormulario());
            stmt.setString(2, h.getNomeAtendente());
            stmt.setString(3, h.getStatus());
            stmt.setLong(4, h.getIdHistorico());
            stmt.executeUpdate();

            con.close();

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void remover(long id) {

        try{
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

            String sql = " DELETE FROM historico WHERE idHistorico = ?";

            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();

            con.close();

        }catch (Exception e) {
            e.printStackTrace();
        }


    }
}
