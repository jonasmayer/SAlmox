/*-
 * Classname:             DevolutionDocumentPanel.java
 *
 * Version information:   1.0
 *
 * Date:                  30/04/2013 - 14:01:50
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package uni.uri.salmox.view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.prefs.Preferences;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import uni.uri.salmox.model.Withdrawal;
import uni.uri.salmox.util.ColorConverter;
import uni.uri.salmox.util.FontFactory;
import uni.uri.salmox.util.PreferencesManager;
import uni.uri.salmox.util.ScreenResolution;

/**
 * Janela para entrega dos documentos
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class DevolutionDocumentPanel {

    //modelo da tabela
    private DevolutionDocumentTableModel tableModel;
    //paenl principal
    private JPanel panel;
    //scrollPane para tabela
    private JScrollPane scrollPane;
    //tabela
    private JTable table;
    //label com título do panel
    private JLabel titleLabel;
    //botão entrega de documento
    private JButton returnButton;
    //botão mais informações
    private JButton moreButton;
    //largura
    private int x;
    //altura
    private int y;

    /**
     * Construtor sem parametros
     */
    public DevolutionDocumentPanel() {
        tableModel = new DevolutionDocumentTableModel();
        createView();
    }//fim do construtor sem parametros

    /**
     * Construtor com lista de usuários
     *
     * @param users lista com usuários
     */
    public DevolutionDocumentPanel(List<Withdrawal> whitdrawals) {
        tableModel = new DevolutionDocumentTableModel(whitdrawals);
        createView();
    }//fim do construtor com lista de usuários

    /**
     * Cria a visão
     */
    private void createView() {
        //inicializa componentes
        panel = new JPanel();
        scrollPane = new JScrollPane();
        table = new JTable(tableModel);
        titleLabel = new JLabel("Entregar retiradas");
        returnButton = new JButton("Entregar");
        moreButton = new JButton("Mais informações");

        //pega tamanho da tela e define layout 
        panel.setLayout(null);
        takeScreenSize();

        //define tamanho e posição dos componentes
        panel.setBounds(0, 0, x - 150, y - 200);
        scrollPane.setBounds(50, 60, x - 450, y - 200);
        titleLabel.setBounds((x - 300) / 2, 10, 300, 30);
        table.setBounds(0, 0, x - 400, y);
        returnButton.setBounds(x - 300, 100, 200, 30);
        moreButton.setBounds(x - 300, 150, 200, 30);

        //define tamanho das colunas da tabela
        TableColumn column;
        for (int i = 0; i < table.getColumnCount(); i++) {
            column = table.getColumnModel().getColumn(i);
            if (i == 0) {
                int sizeColumnCode = (x - 500) / 5;
                column.setPreferredWidth(sizeColumnCode);
                column.setMaxWidth(sizeColumnCode);
            }
            if (i == 1) {
                int sizeColumnCode = (x - 500) / 4;
                column.setPreferredWidth(sizeColumnCode);
                column.setMaxWidth(sizeColumnCode);
            }
            if (i == 2) {
                int sizeColumnCode = (x - 500) / 5;
                column.setPreferredWidth(sizeColumnCode);
                column.setMaxWidth(sizeColumnCode);
            }
        }

        //define cor e fonte
        panel.setBackground(getColor());
        table.setFont(FontFactory.getFontDefault());
        returnButton.setFont(FontFactory.getFontLarge());
        moreButton.setFont(FontFactory.getFontLarge());
        titleLabel.setFont(FontFactory.getFontLarge());

        //dicas
        moreButton.setToolTipText("Abre janela com informações sobre a retirada");
        returnButton.setToolTipText("Define a retirada como entregue e a remove da lista");

        table.setAutoCreateRowSorter(true);

        //adiciona os componentes
        scrollPane.setViewportView(table);
        panel.add(scrollPane);
        panel.add(moreButton);
        panel.add(returnButton);
        panel.add(titleLabel);

        //define panel como visivel
        panel.setVisible(true);

    }//fim do método createViewAdministrator

    /**
     * Busca o tamanho da tela
     */
    private void takeScreenSize() {
        Preferences preferences = PreferencesManager.getPreferencesSystem();
        x = preferences.getInt(PreferencesManager.KEY_SCREEN_WIDTH, 0);
        y = preferences.getInt(PreferencesManager.KEY_SCREEN_HEIGTH, 0);
        if (x == 0 || y == 0) {
            ScreenResolution resolution = new ScreenResolution();
            x = resolution.getX();
            y = resolution.getY();
        }
    }//fim do método takeScreenSize

    /**
     * Atualiza tabela
     *
     * @param users lista com usuários
     */
    public void refresh(List<Withdrawal> withdrawals) {
        tableModel.refresh(withdrawals);
        table.repaint();
    }//fim do método refresh

    /**
     * Obtêm panel
     *
     * @return <code>JPanel</code> de gerenciamento de usuários
     */
    public JPanel getPanel() {
        return panel;
    }//fim do método getPanel

    /**
     * Define evento para o botão entregar
     *
     * @param actionListener ouvinte
     */
    public void setActionListenerReturnButton(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }//fim do método setActionListenerReturnButton

    /**
     * Define evento para o botão mais informações
     *
     * @param actionListener ouvinte
     */
    public void setActionListenerMoreButton(ActionListener actionListener) {
        moreButton.addActionListener(actionListener);
    }//fim do método setActionListenerEditButton

    /**
     * Pega a retirada selecionado
     *
     * @return <code>Withdrawal</code> retirada
     */
    public Withdrawal getSelectedRow() {
        if (table.getSelectedRow() < 0) {
            return null;
        }
        return tableModel.getRow(table.convertRowIndexToModel(table.getSelectedRow()));
    }//fim do método getSelectedBook

    /**
     * Obtêm cor definida para essa janela
     *
     * @return <code>Color</code> cor
     */
    private Color getColor() {
        ColorConverter colorConverter = new ColorConverter();
        return colorConverter.getColor(PreferencesManager.KEY_COLOR_DEVOLUTION);
    }//fim do método getColor

    //metodo para teste
    public static void main(String args[]) {
        DevolutionDocumentPanel devolutionDocumentPanel = new DevolutionDocumentPanel();
        AdministratorFrame administratorFrame = new AdministratorFrame();
        administratorFrame.changePanel(devolutionDocumentPanel.getPanel());
    }
}//fim da classe DevolutionDocumentPanel 

