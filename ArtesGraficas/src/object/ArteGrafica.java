
package object;

public class ArteGrafica {
  private String id;
  private String nome;
  private String numero;
  private String cliente;
  private String tipo;
  private String descricao;
  private String valor;
  private String data;
  private String hora;

  public ArteGrafica() {
  }
  public ArteGrafica(String id, String nome, String numero, String cliente, String tipo, String descricao, String valor, String data, String hora) {
    this.id = id;
    this.nome = nome;
    this.numero = numero;
    this.cliente = cliente;
    this.tipo = tipo;
    this.descricao = descricao;
    this.valor = valor;
    this.data = data;
    this.hora = hora;
  }
  public String getId () {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public String getNumero() {
    return numero;
  }
  public void setNumero(String numero) {
    this.numero = numero;
  }
  public String getCliente() {
    return cliente;
  }
  public void setCliente(String cliente) {
    this.cliente = cliente;
  }
  public String getTipo() {
    return tipo;
  }
  public void setTipo(String tipo) {
    this.tipo = tipo;
  }
  public String getDescricao() {
    return descricao;
  }
  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
  public String getValor() {
    return valor;
  }
  public void setValor(String valor) {
    this.valor = valor;
  }
  public String getData() {
    return data;
  }
  public void setData(String data) {
    this.data = data;
  }
  public String getHora() {
    return hora;
  }
  public void setHora(String hora) {
    this.hora = hora;
  }
}
