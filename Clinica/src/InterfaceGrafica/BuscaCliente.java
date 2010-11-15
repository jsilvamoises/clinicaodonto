/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BuscaCliente.java
 *
 * Created on 04/11/2010, 23:39:10
 */

package InterfaceGrafica;

import classes.Cliente;
import facades.SistemaFacade;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author usuário
 */
public class BuscaCliente extends javax.swing.JPanel implements KeyListener {
    private JTabbedPane tabbed;
    private JScrollPane scroll;
    private SistemaFacade fachada = SistemaFacade.getInstance();


     @SuppressWarnings("deprecation")
    @Override
    public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (!fieldPorID.getText().equals(""))
                    botaoPorIDActionPerformed(null);
                else
                    botaoPorNomeActionPerformed(null);

            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                   botaoFecharActionPerformed(null);
            }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
            // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent arg0) {
            // TODO Auto-generated method stub

    }

    /** Creates new form BuscaCliente */
    public BuscaCliente(JTabbedPane tabbed, JScrollPane scroll) {
        this.tabbed = tabbed;
        this.scroll = scroll;
        initComponents();

        scroll.addKeyListener(this);
        scroll.show();

        tabbed.addKeyListener(this);
        tabbed.show();

        this.addKeyListener(this);
        this.show();

        jScrollPane1.addKeyListener(this);
        jScrollPane1.show();

        fieldPorNome.addKeyListener(this);
        fieldPorNome.show();

        fieldPorID.addKeyListener(this);
        fieldPorID.show();

        botaoFechar.addKeyListener(this);
        botaoFechar.show();


        botaoLimpar.addKeyListener(this);
        botaoLimpar.show();

        botaoPorID.addKeyListener(this);
        botaoPorID.show();

        botaoPorNome.addKeyListener(this);
        botaoPorNome.show();


        botaoEditar.addKeyListener(this);
        botaoEditar.show();

        jList1.addKeyListener(this);
        jList1.show();

        reiniciaCampos();
    }
    
    private void reiniciaCampos() {
        fieldPorID.setText(null);
        fieldPorNome.setText(null);
        jList1.setModel(new javax.swing.AbstractListModel() {
            Cliente[] clientes = {};
            public int getSize() {return clientes.length;}
            public Cliente getElementAt(int i){return clientes[i];}
        });
        jScrollPane1.setViewportView(jList1);
    }

    private void atualizaListaPorNome() throws Exception {
    	jList1.setModel(new javax.swing.AbstractListModel() {
        Cliente[] clientes = fachada.recuperaClientePorNome(fieldPorNome.getText());
        public int getSize() {return clientes.length;}
        public Cliente getElementAt(int i){return clientes[i];}
        });
        jScrollPane1.setViewportView(jList1);
    }

    private void atualizaListaPorID() throws Exception {
    	jList1.setModel(new javax.swing.AbstractListModel() {
        Cliente[] clientes = {fachada.recuperaClientePorID(fieldPorID.getText())};
        public int getSize() {return clientes.length;}
        public Cliente getElementAt(int i){return clientes[i];}
        });
        jScrollPane1.setViewportView(jList1);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fieldPorID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        botaoPorID = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        fieldPorNome = new javax.swing.JTextField();
        botaoPorNome = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        botaoEditar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        botaoLimpar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 18));
        jLabel1.setText("Busca de Clientes");

        jLabel2.setText("Buscar pelo nome cadastrado");

        jLabel3.setText("Busca pelo nome ou sobrenome dos clientes");

        botaoPorID.setText("Buscar");
        botaoPorID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPorIDActionPerformed(evt);
            }
        });

        jLabel4.setText("Buscar pelo número de identificação");

        botaoPorNome.setText("Buscar");
        botaoPorNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPorNomeActionPerformed(evt);
            }
        });

        jLabel5.setText("A busca é feita procurando pelo identificador único para");

        jLabel6.setText("cada Cliente cadastrado no sistema");

        jScrollPane1.setViewportView(jList1);

        botaoEditar.setText("Editar");
        botaoEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEditarActionPerformed(evt);
            }
        });

        jLabel7.setText("Clicando no botão Editar com um Cliente selecionado, serão");

        jLabel8.setText("exibidas informações editáveis deste mesmo que fora selecionado");

        botaoLimpar.setText("Limpar Dados");
        botaoLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimparActionPerformed(evt);
            }
        });

        botaoFechar.setText("Fechar Aba");
        botaoFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(fieldPorNome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(10, 10, 10)
                                            .addComponent(jLabel5))
                                        .addComponent(fieldPorID, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(botaoLimpar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                                        .addComponent(botaoFechar)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel8))
                                    .addComponent(botaoEditar)
                                    .addComponent(botaoPorNome)
                                    .addComponent(botaoPorID)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(273, 273, 273)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(246, 246, 246)
                        .addComponent(jLabel3)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(botaoPorNome)
                    .addComponent(fieldPorNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(fieldPorID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoPorID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botaoEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(117, 117, 117))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botaoPorNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPorNomeActionPerformed
        try {
            if(!fieldPorNome.getText().isEmpty())
                atualizaListaPorNome();
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(),
                "Busca Sem Sucesso",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botaoPorNomeActionPerformed

    private void botaoPorIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPorIDActionPerformed
        try {
            if(!fieldPorID.getText().isEmpty())
                atualizaListaPorID();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                "Busca Sem Sucesso",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botaoPorIDActionPerformed

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        reiniciaCampos();
        tabbed.remove(scroll);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        reiniciaCampos();
    }//GEN-LAST:event_botaoLimparActionPerformed

    private void botaoEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEditarActionPerformed
        if(jList1.isSelectionEmpty())
            JOptionPane.showMessageDialog(null, "Nenhum cliente foi selecionado",
                "Selecione um cliente",
                JOptionPane.ERROR_MESSAGE);
        else
            new CadastroEditavelFrame((Cliente)jList1.getSelectedValue());
    }//GEN-LAST:event_botaoEditarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoEditar;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoLimpar;
    private javax.swing.JButton botaoPorID;
    private javax.swing.JButton botaoPorNome;
    private javax.swing.JTextField fieldPorID;
    private javax.swing.JTextField fieldPorNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}