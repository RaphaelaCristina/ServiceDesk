package serviceDesk.bceTradicionalFaq;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FaqDAOImpl implements FaqDAO {

    private static final String DBURL = "jdbc:mariadb://localhost:3306/faqDb?allowMultiQueries=true";
    private static final String DBUSER = "root";
    private static final String DBPASS = "123456";

    public void FaqDaoImpl(){
        try{
            Class.forName("org.mariadb.jdbc.Deriver");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void adicionar(Faq f) {
        try{
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

            String sql = "INSERT INTO faq (id, titulo, descricao, status, dataCriacao, resposta)" +
                    "VALUES(? , ? , ? , ? , ? , ?)";

            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, f.getId());
            stmt.setString(2, f.getTitulo());
            stmt.setString(3, f.getDescricao());
            stmt.setString(4, f.getStatus());
            stmt.setDate(5, java.sql.Date.valueOf(f.getDataCriacao()));
            stmt.setString(6, f.getResposta());
            stmt.executeUpdate();

            con.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Faq> pesquisarPorTitulo(String titulo) {
        List<Faq> encontrados = new ArrayList<>();
        try{
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

            String sql = "SELECT* FROM faq WHERE titulo like '%" +titulo +"%' ";
            System.out.println(sql);

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Faq f = new Faq();
                f.setId(rs.getLong("id"));
                f.setTitulo(rs.getString("titulo"));
                f.setDescricao(rs.getString("descricao"));
                f.setStatus(rs.getString("status"));
                f.setDataCriacao(rs.getDate("dataCriacao").toLocalDate());
                f.setResposta(rs.getString("resposta"));

                encontrados.add(f);
            }

            con.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
        return encontrados;
    }

    @Override
    public void atualizar(long id, Faq f) {
        try{
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

            String sql = " UPDATE faq SET titulo = ?, descricao = ?, status = ? , dataCriacao = ?, resposta = ? WHERE id = ?";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, f.getTitulo());
            stmt.setString(2, f.getDescricao());
            stmt.setString(3, f.getStatus());
            stmt.setDate(4, java.sql.Date.valueOf(f.getDataCriacao()));
            stmt.setString(5, f.getResposta());
            stmt.setLong(6, f.getId());
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

            String sql = " DELETE FROM faq WHERE id = ?";

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
