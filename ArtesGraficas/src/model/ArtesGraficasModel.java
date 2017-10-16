
package model;

import java.sql.*;
import java.util.ArrayList;
import object.ArteGrafica;

public class ArtesGraficasModel {
  /*
    Campos::  
  */
  private Conexao conexaoModel;
  private ArteGrafica arteGraficaVal;
  private ArrayList <ArteGrafica> listaArtes;
  private String[] colunas;
  private String[] tipos;
  private int numeroRegistros;
  private double valorTotal;
  
  /*
    Construtores::
  */  
  public ArtesGraficasModel() {
  }
  public ArtesGraficasModel(Conexao conexaoModel) {
    this.conexaoModel = conexaoModel;
  }
  public ArtesGraficasModel(ArteGrafica arteGraficaVal) {
    this.arteGraficaVal = arteGraficaVal;
  }
  
  // lógica de inicilização
  public final void iniciarModel () {
    ArrayList <ArteGrafica> listaArteGrafica = new ArrayList<>();
    this.listaArtes = listaArteGrafica;
    iniciarColunas();
    iniciarTipos();
  }
  public final void iniciarColunas () {
    String[] novaColunas = new String[9];
    novaColunas[0]= "Id";
    novaColunas[1]= "Nome";
    novaColunas[2]= "Numero";
    novaColunas[3]= "Cliente";
    novaColunas[4]= "Tipo";
    novaColunas[5]= "Descrição";
    novaColunas[6]= "Valor";
    novaColunas[7]= "Data";
    novaColunas[8]= "Hora";
    this.colunas = novaColunas;
  }
  public void iniciarTipos () {
    String[] novoTipos = new String [7];
    novoTipos[0] = "Bordado";
    novoTipos[1] = "Camiseta";
    novoTipos[2] = "PhotoShop";
    novoTipos[3] = "Illustrator";
    novoTipos[4] = "AutoCAD";
    novoTipos[5] = "SolidWorks";
    novoTipos[6] = "Manutenção PC";
    this.tipos = novoTipos;
  }
  
  // gets e sets
  public Conexao getConexaoModel() {
    return conexaoModel;
  }
  public void setConexaoModel(Conexao conexaoModel) {
    this.conexaoModel = conexaoModel;
  }
  public ArteGrafica getArteGraficaVal() {
    return arteGraficaVal;
  }
  public void setArteGraficaVal(ArteGrafica arteGraficaVal) {
    this.arteGraficaVal = arteGraficaVal;
  }
  public ArrayList<ArteGrafica> getListaArtes() {
    return listaArtes;
  }
  public void setListaArtes(ArrayList<ArteGrafica> listaArtes) {
    this.listaArtes = listaArtes;
  }
  public String[] getColunas() {
    return colunas;
  }
  public void setColunas(String[] colunas) {
    this.colunas = colunas;
  }
  public String[] getTipos() {
    return tipos;
  }
  public void setTipos(String[] tipos) {
    this.tipos = tipos;
  }
  public int getNumeroRevistros() {
    return numeroRegistros;
  }
  public void setNumeroRevistros(int numeroRegistros) {
    this.numeroRegistros = numeroRegistros;
  }
  public double getValorTotal() {
    return valorTotal;
  }
  public void setValorTotal(double valorTotal) {
    this.valorTotal = valorTotal;
  }
  
  // manipulação do banco de dados
  public void novo () {
    
  }
  public void inserirDados () throws Exception {
    Conexao novaConexao = new Conexao();
    this.conexaoModel = novaConexao;
    try {
      Connection conexao = this.conexaoModel.conectar();
      PreparedStatement query = conexao.prepareStatement(stringInsert());
      query.executeUpdate();
      this.conexaoModel.desconectar();
    } catch (SQLException e) {
      System.out.println("Falha ao tentar inserir dados");
      System.out.println(e);
    }
  }
  public void carregarDados () throws Exception {
    Conexao novaConexao = new Conexao();
    this.conexaoModel = novaConexao;
    this.listaArtes.clear();
    try {
      Connection conexao = this.conexaoModel.conectar();
      Statement consulta = conexao.createStatement();
      ResultSet resultado = consulta.executeQuery(stringSelect());
      while (resultado.next()) {
        ArteGrafica novaArte = new ArteGrafica();
        novaArte.setId(Integer.toString(resultado.getInt("IDArtes")));
        novaArte.setNome(resultado.getString("Nome"));
        novaArte.setNumero(resultado.getString("Numero"));
        novaArte.setCliente(resultado.getString("Cliente"));
        novaArte.setTipo(resultado.getString("Tipo"));
        novaArte.setDescricao(resultado.getString("Descricao"));
        novaArte.setValor(resultado.getString("Valor"));
        novaArte.setData(resultado.getString("DataProduto"));
        novaArte.setHora(resultado.getString("HoraProduto"));
        this.listaArtes.add(novaArte);
      }
      System.out.println("Consulta para carregar todos os dados da tabela realizada com sucesso");
      this.conexaoModel.desconectar();
    } catch (SQLException e) {
      System.out.println("Falha ao tentar carregar todos os dados do banco");
      System.out.println(e);
    }
  }
  public void contarRegistros () throws Exception {
    Conexao novaConexao = new Conexao();
    this.conexaoModel = novaConexao;
    try {
      Connection conexao = this.conexaoModel.conectar();
      Statement consulta = conexao.createStatement();
      ResultSet resultado = consulta.executeQuery(stringContarRegistros());
      if (resultado != null && resultado.next()) {
        this.numeroRegistros = resultado.getInt(1);
      }
      System.out.println("Consulta de contar todos os registros realizada com sucesso");
      this.conexaoModel.desconectar();
    } catch (SQLException e) {
      System.out.println("Falha ao tentar contar os registros do banco");
      System.out.println(e);
    }
  }
  public void somarValores () throws Exception {
    Conexao novaConexao = new Conexao();
    this.conexaoModel = novaConexao;
    try {
      Connection conexao = this.conexaoModel.conectar();
      Statement consulta = conexao.createStatement();
      ResultSet resultado = consulta.executeQuery(stringSomarValor());
      if (resultado != null && resultado.next()) {
        this.valorTotal = resultado.getDouble(1);
      }
      System.out.println("Consulta para somar os valores realizada com sucesso");
      this.conexaoModel.desconectar();
    } catch (SQLException e) {
      System.out.println("Falha ao tentar somar os valores do banco");
      System.out.println(e);
    }
  }
  
  // preparar as strings com os comandos sql
  public String stringSelect () {
    return "select * from artes";
  }
  public String stringInsert () {
    String query;
    query = String.format(
            "insert into Artes(Nome, Numero, Cliente, Tipo, Descricao, Valor, DataProduto, HoraProduto)"
          + "values (\"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\")", 
            this.arteGraficaVal.getNome(),
            this.arteGraficaVal.getNumero(),
            this.arteGraficaVal.getCliente(),
            this.arteGraficaVal.getTipo(),
            this.arteGraficaVal.getDescricao(),
            Double.parseDouble(this.arteGraficaVal.getValor()),
            this.arteGraficaVal.getData(),
            this.arteGraficaVal.getHora()
    );
    return query;
  }
  public String stringContarRegistros () {
    String query = "select count(*) from artes";
    return query;
  }
  public String stringSomarValor () {
    String query = "select sum(valor) from artes";
    return query;
  }
  public String stringUpdate () {
    return "Não suportado ainda";
  }
  public String stringDelete () {
    return "Não suportado ainda";
  }
  
}
