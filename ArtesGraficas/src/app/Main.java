
package app;

import view.CadastroArtesView;

public class Main {
  
  public static void main(String[] args) throws Exception {
    CadastroArtesView novoFrame = new CadastroArtesView();
    novoFrame.setLocationRelativeTo(null);
    novoFrame.setDefaultCloseOperation(CadastroArtesView.DISPOSE_ON_CLOSE);
    novoFrame.setVisible(true);
    
  }
  
}
