
package controller;

import Geradores.CodigoArtesGraficas;
import Geradores.DataHora;
import javax.swing.table.DefaultTableModel;
import model.ArtesGraficasModel;
import object.ArteGrafica;
import view.CadastroArtesView;

public class ArtesGraficasController {
  /*
    Campos::  
  */
  private CadastroArtesView arteGraficaView;
  private ArteGrafica arteGraficaVal;
  private ArtesGraficasModel arteGraficaModel;
  
  /*
    Construtores::
  */  
  public ArtesGraficasController() {
  }

  // lógica de inicilização
  public void iniciarController () throws Exception {
    ArteGrafica novaArte = new ArteGrafica();
    this.arteGraficaVal = novaArte;
    ArtesGraficasModel novoModel = new ArtesGraficasModel(novaArte);
    this.arteGraficaModel = novoModel;
    this.arteGraficaModel.iniciarModel();
    iniciarColunasTabela();
    carregarNumeroRegistros();
    carregarSomaValores();
    carregarDados();
    iniciarInterface();
  }
  
  // gets e sets
  public CadastroArtesView getArteGraficaView() {
    return arteGraficaView;
  }
  public void setArteGraficaView(CadastroArtesView arteGraficaView) {
    this.arteGraficaView = arteGraficaView;
  }
  public ArteGrafica getArteGraficaVal() {
    return arteGraficaVal;
  }
  public void setArteGraficaVal(ArteGrafica arteGraficaVal) {
    this.arteGraficaVal = arteGraficaVal;
  }
  public ArtesGraficasModel getArteGraficaModel() {
    return arteGraficaModel;
  }
  public void setArteGraficaModel(ArtesGraficasModel arteGraficaModel) {
    this.arteGraficaModel = arteGraficaModel;
  }
  
  /*
    Métodos para controlar a interface
  */
  public String criarData () {
    DataHora novo = new DataHora();
    return novo.getHojeBarra();
  }
  public String criarHora () {
    DataHora novo = new DataHora();
    return novo.getAgora();
  }
  public String criarNumeroDesenho (int numero) {
    CodigoArtesGraficas novo = new CodigoArtesGraficas();
    return novo.criarCodigoNumerico(numero, ".");
  }
  
  // dinâmica da interface
  public void iniciarInterface () {
    visibilidadeCampos();
    limparCboTipo();
    this.arteGraficaView.getTxtDataObj().setText(criarData());
    this.arteGraficaView.getTxtHoraObj().setText(criarHora());
    this.arteGraficaView.getTxtNumeroObj().setText(criarNumeroDesenho(Integer.parseInt(this.arteGraficaView.getTxtNumeroRegistros())));
    iniciarComboTipos();
    this.arteGraficaView.setLblTeste("");
  }
  public void visibilidadeCampos () {
    this.arteGraficaView.switchCamposTempo(false, false);
    this.arteGraficaView.switchCamposDados(true, false, true, true, true);
    this.arteGraficaView.switchBotoes(true, true, true);
    this.arteGraficaView.switchComboTipo(true);
    this.arteGraficaView.switchCamposInformacoes(false, false);
    this.arteGraficaView.getTxtNomeObj().requestFocus();
  }
  public void limparCboTipo () {
    this.arteGraficaView.getCboTipo().removeAllItems();
  }
  public void iniciarColunasTabela () {
    DefaultTableModel novo = new DefaultTableModel();
    for (int i = 0; i < this.arteGraficaModel.getColunas().length; i++) {
      novo.addColumn(this.arteGraficaModel.getColunas()[i]);
    }
    this.arteGraficaView.getTabela().setModel(novo);
  }
  public void iniciarComboTipos () {
    for (int i = 0; i < this.arteGraficaModel.getTipos().length; i++) {
      this.arteGraficaView.getCboTipo().addItem(this.arteGraficaModel.getTipos()[i]);
    }
  }

  // métodos de ação dos botões
  public void btnSalvar () throws Exception {
    inserirDados();
  }
  public void btnLimpar () {
    this.arteGraficaView.setTxtNome("");
    this.arteGraficaView.getTxtNumeroObj().setText(criarNumeroDesenho(Integer.parseInt(this.arteGraficaView.getTxtNumeroRegistros())));
    this.arteGraficaView.setTxtCliente("");
    this.arteGraficaView.setTxtDescricao("");
    this.arteGraficaView.setTxtValor("");
    this.arteGraficaView.getCboTipo().setSelectedIndex(0);
    // setar foco do cursor no campo de nome;
    this.arteGraficaView.getTxtNomeObj().requestFocus();
  }
  public void btnSair () {
    System.exit(0);
  }
  
  // extrair e validar dados da interface
  public boolean extrairDadosView () {
    int [] camposValidos = new int[5];
    ArteGrafica arte = new ArteGrafica();
    DataHora novaData = new DataHora();
    boolean valido = false;
    if ("".equals(this.arteGraficaView.getTxtNome())) {
      camposValidos[0] = 0;
      arte.setNome("");
    } else {
      arte.setNome(this.arteGraficaView.getTxtNome());
      camposValidos[0] = 1;
      arte.setNome(this.arteGraficaView.getTxtNome());
    }
    if ("".equals(this.arteGraficaView.getTxtCliente())) {
      camposValidos[1] = 0;
    } else {
      camposValidos[1] = 1;
      arte.setCliente(this.arteGraficaView.getTxtCliente());
    }
    if ("".equals(this.arteGraficaView.getCboTipo().getSelectedItem())) {
      camposValidos[2] = 0;
    } else {
      camposValidos[2] = 1;
      arte.setTipo((String) this.arteGraficaView.getCboTipo().getSelectedItem());
    }
    if ("".equals(this.arteGraficaView.getTxtDescricao())) {
      camposValidos[3] = 0;
    } else {
      camposValidos[3] = 1;
      arte.setDescricao((String) this.arteGraficaView.getTxtDescricao());
    }
    if ("".equals(this.arteGraficaView.getTxtValor())) {
      camposValidos[4] = 0;
    } else {
      camposValidos[4] = 1;
      arte.setValor((String) this.arteGraficaView.getTxtValor());
    }
    arte.setNumero(this.arteGraficaView.getTxtNumero());
    arte.setData(novaData.getHojeMySql());
    arte.setHora(novaData.getAgora());
    for (int i = 0; i < camposValidos.length; i++) {
      if (camposValidos[i] == 0) {
        valido = false;
      } else {
        this.arteGraficaVal = arte;
        valido = true;
      }
    }
    return valido;
  }
  
  // acesso aos dadosdo banco de dados
  public void carregarNumeroRegistros () throws Exception {
    this.arteGraficaModel.contarRegistros();
    this.arteGraficaView.setTxtNumeroRegistros(Integer.toString(this.arteGraficaModel.getNumeroRevistros()));
  }
  public void carregarSomaValores () throws Exception {
    this.arteGraficaModel.somarValores();
    String total = String.format("R$ %.2f", (this.arteGraficaModel.getValorTotal()));
    this.arteGraficaView.setTxtValorTotal(total);
  }
  public void carregarDados () throws Exception {
    this.arteGraficaModel.carregarDados();
    int tamanho = this.arteGraficaModel.getListaArtes().size();
    DefaultTableModel dtm = new DefaultTableModel();
    dtm = (DefaultTableModel) this.arteGraficaView.getTabela().getModel();
    int size = dtm.getRowCount();
    for (int i = 0; i < size; i++) {
      dtm.removeRow(0);
    }
    for (int i = 0; i < tamanho; i++) {
      dtm.addRow(new Object [] {
        this.arteGraficaModel.getListaArtes().get(i).getId(),
        this.arteGraficaModel.getListaArtes().get(i).getNome(),
        this.arteGraficaModel.getListaArtes().get(i).getNumero(),
        this.arteGraficaModel.getListaArtes().get(i).getCliente(),
        this.arteGraficaModel.getListaArtes().get(i).getTipo(),
        this.arteGraficaModel.getListaArtes().get(i).getDescricao(),
        this.arteGraficaModel.getListaArtes().get(i).getValor(),
        this.arteGraficaModel.getListaArtes().get(i).getData(),
        this.arteGraficaModel.getListaArtes().get(i).getHora(),
      });
    }
    this.arteGraficaView.getTabela().setModel(dtm);
  }
  public void inserirDados () throws Exception {
    boolean valido = extrairDadosView();
    if (valido) {
      this.arteGraficaModel.getArteGraficaVal().setNome(this.arteGraficaVal.getNome());
      this.arteGraficaModel.getArteGraficaVal().setNumero(this.arteGraficaVal.getNumero());
      this.arteGraficaModel.getArteGraficaVal().setCliente(this.arteGraficaVal.getCliente());
      this.arteGraficaModel.getArteGraficaVal().setTipo(this.arteGraficaVal.getTipo());
      this.arteGraficaModel.getArteGraficaVal().setDescricao(this.arteGraficaVal.getDescricao());
      this.arteGraficaModel.getArteGraficaVal().setValor(this.arteGraficaVal.getValor());
      this.arteGraficaModel.getArteGraficaVal().setData(this.arteGraficaVal.getData());
      this.arteGraficaModel.getArteGraficaVal().setHora(this.arteGraficaVal.getHora());
      this.arteGraficaModel.inserirDados();
      this.arteGraficaView.setLblTeste("Dados salvos com sucesso");
      this.arteGraficaView.getTxtNomeObj().requestFocus();
      carregarDados();
      btnLimpar();
      carregarNumeroRegistros();
      carregarSomaValores();
    } else {
      this.arteGraficaView.setLblTeste("Valores informados estão errados");
    }
  }
  
}
