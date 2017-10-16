
package model;

import java.sql.*;

public class Conexao {
  
  public Connection retornaConexao = null;
  
  public Connection conectar () throws Exception {
    System.out.println("Iniciando conexão com o banco de dados...");
    try {
      Class.forName("com.mysql.jdbc.Driver");
      this.retornaConexao = DriverManager.getConnection("jdbc:mysql://localhost/artesgraficas", "root", "senha");
      System.out.println("Conectado.");
    } catch (ClassNotFoundException e) {
      System.out.println("Classe não encontrada. " + e);
    } catch (SQLException e) {
      System.out.println("Erro SQL. " + e);
    }
    return this.retornaConexao;
  }
  public void desconectar () {
    try {
      this.retornaConexao.close();
      System.out.println("Conexão encerrada com sucesso");
    } catch (SQLException e) {
      System.out.println("Não foi possível desconectar do banco de dados");
      System.out.println(e);
    }
  }
  
}
