package persistencia;

import classededados.Contato;
import classededados.Cliente;
import classededados.Contato;
import conexaobancodedados.Conexao;
import java.util.logging.Level;
import java.util.logging.Logger;


import java.sql.SQLException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class ContatosDao {

         public int ProximoId(){
        try {
            Connection con = Conexao.getConnection();
            con.setAutoCommit(false);
            
            try{
                String comando = "select nextval('cliente_id_seq')";
                Statement ps = con.createStatement();
                
                ResultSet rs = ps.executeQuery(comando);
                rs.next();
                return rs.getInt(1);
            }finally{
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContatosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
     }
     

   
        public void incluir(Cliente cliente)throws SQLException{
          Connection con = Conexao.getConnection();
            try {
                   
            con.setAutoCommit(false);
            int clienteId =  ProximoId();
            PreparedStatement pstmt = con.prepareStatement("insert into cliente "
                    + "(id, nome, cpf) values (?, ?, ?)");
            pstmt.setInt(1, clienteId);
            pstmt.setString(2, cliente.getNome());
            pstmt.setLong(3, cliente.getCpf());
            pstmt.executeUpdate();
                
            
            
                for (Contato contato : cliente.getContatos()) {
                    
                
            
            PreparedStatement pstmtContato = con.prepareStatement("insert into contato "
                    + "(descricao, tipo, cliente_id) values (?, ?, ?)");
              
           
            pstmtContato.setString(1, contato.getTipo());
            pstmtContato.setString(2, contato.getDescricao());
            pstmtContato.setInt(3, clienteId);
            pstmtContato.executeUpdate();
                }
            con.commit();
             

                
        } catch (SQLException ex) {
           con.rollback();
            Logger.getLogger(ContatosDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                con.close();
            }
        }
        }

    

    
   
 
//    public ArrayList listar() throws SQLException {
//        ArrayList dados = new ArrayList();
//        Connection con = Conexao.getConnection();
//        String comando = "select * from produto";
//        PreparedStatement ps = con.prepareStatement(comando);
//        ResultSet rs = ps.executeQuery();
//        while(rs.next()){
//            
//            p = new Produto();
//           
//           p.setCodigo(rs.getString("Codigo"));
//           p.setNome(rs.getString("nome"));
//           p.setQtdEstoque(rs.getDouble("qtd_estoque"));
//           p.setQtdEstoqueMin(rs.getDouble("qtd_estoque_min"));
//           p.setUnidadeMedida(rs.getString("und_med"));
//           p.setValorCompra(rs.getDouble("valor_compra"));
//           p.setValorVenda(rs.getDouble("valor_venda"));
//            dados.add(p);
//        }
//        return dados;
//    }
//    
//   public Produto consultar(String nome) throws SQLException {
//        String comando = "select * from produto where nome = ?";
//        Connection con = Conexao.getConnection();
//        PreparedStatement ps = con.prepareStatement(comando);
//        ps.setString(1, nome);
//        ResultSet rs = ps.executeQuery();
//        if(rs.next()){
//            
//            p = new Produto();
//           
//           p.setCodigo(rs.getString("codigo"));
//           p.setNome(rs.getString("nome"));
//           p.setUnidadeMedida(rs.getString("und_med"));
//           p.setQtdEstoque(rs.getDouble("qtd_estoque"));
//           p.setQtdEstoqueMin(rs.getDouble("qtd_estoque_min"));
//           p.setValorCompra(rs.getDouble("valor_compra"));
//           p.setValorVenda(rs.getDouble("valor_venda"));
//           
//            return p;
//        }
//        return null;
//    }
//    public void alterar (Produto objeto,String nome) throws Exception{
//        
//        String comando = ("update produto set nome=?"
//                        + ",codigo=?,und_med=?,valor_venda=?,valor_compra=?,qtd_estoque =?,qtd_estoque_min=? where nome=?");
//        Connection con = Conexao.getConnection();
//        PreparedStatement ps = con.prepareStatement(comando);
//        
//        
//        ps.setString(1, objeto.getNome());
//        ps.setString(2, objeto.getCodigo());
//        ps.setString(3, objeto.getUnidadeMedida());
//        ps.setDouble(4, objeto.getValorVenda());
//        ps.setDouble(5, objeto.getValorCompra());
//        ps.setDouble(6, objeto.getQtdEstoque());
//        ps.setDouble(7, objeto.getQtdEstoqueMin());
//        ps.setString(8, nome);
//        ps.executeUpdate();
//       
//       
//
//    }
//
//  public void deletar(String nome )  throws SQLException{
//      
//      String comando = "delete from produto where nome=?";
//      
//      Connection con = Conexao.getConnection();
//        PreparedStatement ps = con.prepareStatement(comando);
//        ps.setString(1, nome);
//        ps.executeUpdate();
//  }
//
//          
//   }


