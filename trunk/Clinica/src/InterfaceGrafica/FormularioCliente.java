/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormularioCliente.java
 *
 * Created on 04/11/2010, 12:03:18
 */

package InterfaceGrafica;

import enums.Estado;
import enums.EstadoCivil;
import enums.Sexo;
import enums.StatusCliente;
import enums.StatusFinanceiro;
import facades.SistemaFacade;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author usuário
 */
public class FormularioCliente extends javax.swing.JPanel implements KeyListener {
    private JTabbedPane tabbed;
    private JScrollPane scroll;
    private SistemaFacade fachada = SistemaFacade.getInstance();

    @SuppressWarnings("deprecation")
    @Override
    public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    botaoCadastrarActionPerformed(null);

            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    botaoCancelarActionPerformed(null);
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

    /** Creates new form FormularioCliente */
    @SuppressWarnings("deprecation")
    public FormularioCliente(JTabbedPane tabbed, JScrollPane scroll) {
        this.tabbed = tabbed;
        this.scroll = scroll;
        initComponents();

        scroll.addKeyListener(this);
        scroll.show();

        tabbed.addKeyListener(this);
        tabbed.show();

        this.addKeyListener(this);
        this.show();

        fieldBairro.addKeyListener(this);
        fieldBairro.show();

        fieldCEP.addKeyListener(this);
        fieldCEP.show();

        fieldCPF.addKeyListener(this);
        fieldCPF.show();

        fieldCidade.addKeyListener(this);
        fieldCidade.show();

        fieldCodigo.addKeyListener(this);
        fieldCodigo.show();

        fieldComplemento.addKeyListener(this);
        fieldComplemento.show();

        fieldEmail.addKeyListener(this);
        fieldEmail.show();

        fieldNome.addKeyListener(this);
        fieldNome.show();

        fieldNumero.addKeyListener(this);
        fieldNumero.show();

        fieldProfissao.addKeyListener(this);
        fieldProfissao.show();

        fieldRG.addKeyListener(this);
        fieldRG.show();

        fieldRua.addKeyListener(this);
        fieldRua.show();

        fieldTelefone.addKeyListener(this);
        fieldTelefone.show();

        botaoCadastrar.addKeyListener(this);
        botaoCadastrar.show();

        botaoCancelar.addKeyListener(this);
        botaoCancelar.show();

        botaoLimpar.addKeyListener(this);
        botaoLimpar.show();

        comboFinanceiro.addKeyListener(this);
        comboFinanceiro.show();

        comboAno.addKeyListener(this);
        comboAno.show();

        comboDia.addKeyListener(this);
        comboDia.show();

        comboEstado.addKeyListener(this);
        comboEstado.show();

        comboEstadoCivil.addKeyListener(this);
        comboEstadoCivil.show();

        comboMes.addKeyListener(this);
        comboMes.show();

        comboSexo.addKeyListener(this);
        comboSexo.show();

        comboStatus.addKeyListener(this);
        comboStatus.show();

        carregaCombosEnums();
        reiniciaCampos();


    }

    private void reiniciaCampos() {
        fieldCodigo.setText(null);
        fieldBairro.setText(null);
        fieldCEP.setText(null);
        fieldCPF.setText(null);
        fieldCidade.setText(null);
        fieldComplemento.setText(null);
        fieldEmail.setText(null);
        fieldNome.setText(null);
        fieldNumero.setText(null);
        fieldProfissao.setText(null);
        fieldRG.setText(null);
        fieldRua.setText(null);
        fieldTelefone.setText(null);
        comboEstado.setSelectedIndex(-1);
        comboAno.setSelectedIndex(-1);
        comboDia.setSelectedIndex(-1);
        comboEstadoCivil.setSelectedIndex(-1);
        comboMes.setSelectedIndex(-1);
        comboSexo.setSelectedIndex(-1);
        comboStatus.setSelectedIndex(-1);
        comboFinanceiro.setSelectedIndex(-1);
    }

    private void carregaCombosEnums() {
        comboEstado.setModel(new javax.swing.
                DefaultComboBoxModel(fachada.carregaEstados()));
        comboSexo.setModel(new javax.swing.
                DefaultComboBoxModel(fachada.carregaSexos()));
        comboEstadoCivil.setModel(new javax.swing.
                DefaultComboBoxModel(fachada.carregaEstadosCivis()));
        comboAno.setModel(new javax.swing.
                DefaultComboBoxModel(arrayAnos()));
        comboStatus.setModel(new javax.swing.
                DefaultComboBoxModel(fachada.carregaStatusCliente()));
        comboFinanceiro.setModel(new javax.swing.
                DefaultComboBoxModel(fachada.carregaStatusFinanceiro()));
    }

    private String[] arrayAnos() {
        String[] retorno = new String[111];
        for(int i = 1910; i - 1910 < retorno.length; i++)
            retorno[i - 1910] = String.valueOf(i);
        return retorno;
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        fieldCPF = new javax.swing.JTextField();
        comboDia = new javax.swing.JComboBox();
        comboMes = new javax.swing.JComboBox();
        comboAno = new javax.swing.JComboBox();
        fieldTelefone = new javax.swing.JTextField();
        fieldProfissao = new javax.swing.JTextField();
        fieldNumero = new javax.swing.JTextField();
        fieldComplemento = new javax.swing.JTextField();
        fieldEmail = new javax.swing.JTextField();
        fieldRua = new javax.swing.JTextField();
        fieldBairro = new javax.swing.JTextField();
        fieldCEP = new javax.swing.JTextField();
        fieldCidade = new javax.swing.JTextField();
        fieldRG = new javax.swing.JTextField();
        comboEstadoCivil = new javax.swing.JComboBox();
        comboSexo = new javax.swing.JComboBox();
        botaoCadastrar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        comboEstado = new javax.swing.JComboBox();
        fieldNome = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        fieldCodigo = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        comboStatus = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();
        comboFinanceiro = new javax.swing.JComboBox();
        jLabel22 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(800, 507));

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 18));
        jLabel1.setText("Cadastro do Cliente");

        jLabel2.setText("Nome*");

        jLabel3.setText("CPF");

        jLabel4.setText("RG");

        jLabel5.setText("Data de Nascimento");

        jLabel6.setText("Telefone");

        jLabel8.setText("E-mail");

        jLabel9.setText("Profissão");

        jLabel10.setText("Rua");

        jLabel11.setText("Número");

        jLabel12.setText("Compl.");

        jLabel13.setText("Bairro");

        jLabel14.setText("Cidade");

        jLabel15.setText("Estado");

        jLabel16.setText("CEP");

        jLabel17.setText("Sexo");

        jLabel18.setText("Estado Civil");

        comboDia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        comboMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        botaoCadastrar.setText("Cadastrar");
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });

        botaoLimpar.setText("Limpar");
        botaoLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimparActionPerformed(evt);
            }
        });

        botaoCancelar.setText("Fechar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        jLabel7.setText("Código*");

        jLabel19.setText("Status do Cliente");

        jLabel20.setText("Financeiro");

        jLabel22.setText("* campos brigatórios.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel17)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8)
                            .addComponent(jLabel13)
                            .addComponent(jLabel15)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2)
                            .addComponent(jLabel19))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(comboEstado, 0, 258, Short.MAX_VALUE)
                                        .addComponent(fieldBairro, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                                        .addComponent(fieldEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                                        .addComponent(fieldTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                                        .addComponent(fieldCPF, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                                        .addComponent(comboSexo, 0, 258, Short.MAX_VALUE)
                                        .addComponent(fieldNome, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(comboDia, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(comboMes, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(comboAno, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(comboStatus, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(fieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel16)))
                            .addComponent(jLabel7)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(fieldCEP)
                            .addComponent(fieldRua, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieldRG)
                            .addComponent(fieldProfissao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                            .addComponent(fieldCodigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                            .addComponent(comboEstadoCivil, javax.swing.GroupLayout.Alignment.LEADING, 0, 231, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(botaoCancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoLimpar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoCadastrar))
                            .addComponent(comboFinanceiro, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fieldComplemento, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                            .addComponent(fieldCidade))))
                .addGap(124, 124, 124))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(fieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(fieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(fieldRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9)
                            .addComponent(fieldProfissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(fieldRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(fieldComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(fieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(fieldCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(comboEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(comboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(comboFinanceiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCancelar)
                    .addComponent(botaoCadastrar)
                    .addComponent(botaoLimpar))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        reiniciaCampos();
        tabbed.remove(scroll);
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        reiniciaCampos();
    }//GEN-LAST:event_botaoLimparActionPerformed

    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        try {
            SistemaFacade.getInstance().cadastrarCliente(fieldNome.getText(), 
                    fieldCodigo.getText(), fieldCPF.getText(), fieldRG.getText(),
                    String.valueOf(comboDia.getSelectedItem()) + "/"
                    + String.valueOf(comboMes.getSelectedItem())
                    + "/" + String.valueOf(comboAno.getSelectedItem()),
                    fieldTelefone.getText(), fieldEmail.getText(),
                    fieldProfissao.getText(), fieldRua.getText(), fieldNumero.getText(),
                    fieldComplemento.getText(), fieldBairro.getText(),
                    fieldCidade.getText(), fieldCEP.getText(),
                    Sexo.getSexo(String.valueOf(comboSexo.getSelectedItem())),
                    Estado.getEstado(String.valueOf(comboEstado.getSelectedItem())),
                    EstadoCivil.getEstadoCivil(
                    String.valueOf(comboEstadoCivil.getSelectedItem())),
                    StatusCliente.getStatusCliente(
                    String.valueOf(comboStatus.getSelectedItem())),
                    StatusFinanceiro.getStatusFinanceiro(
                    String.valueOf(comboFinanceiro.getSelectedItem())));
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso",
                "Cadastrado",
                JOptionPane.CLOSED_OPTION);
            reiniciaCampos();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                "Problemas com o cadastro",
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                "Problemas com o cadastro",
                JOptionPane.ERROR_MESSAGE);
        }        
    }//GEN-LAST:event_botaoCadastrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoLimpar;
    private javax.swing.JComboBox comboAno;
    private javax.swing.JComboBox comboDia;
    private javax.swing.JComboBox comboEstado;
    private javax.swing.JComboBox comboEstadoCivil;
    private javax.swing.JComboBox comboFinanceiro;
    private javax.swing.JComboBox comboMes;
    private javax.swing.JComboBox comboSexo;
    private javax.swing.JComboBox comboStatus;
    private javax.swing.JTextField fieldBairro;
    private javax.swing.JTextField fieldCEP;
    private javax.swing.JTextField fieldCPF;
    private javax.swing.JTextField fieldCidade;
    private javax.swing.JTextField fieldCodigo;
    private javax.swing.JTextField fieldComplemento;
    private javax.swing.JTextField fieldEmail;
    private javax.swing.JTextField fieldNome;
    private javax.swing.JTextField fieldNumero;
    private javax.swing.JTextField fieldProfissao;
    private javax.swing.JTextField fieldRG;
    private javax.swing.JTextField fieldRua;
    private javax.swing.JTextField fieldTelefone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables

}
