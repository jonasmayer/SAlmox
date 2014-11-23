
/*-
 * Classname:             AddBookFPFrame.java
 *
 * Version information:   1.0
 *
 * Date:                  04/04/2013 - 14:59:07
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package uni.uri.salmox.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import uni.uri.salmox.model.BookFP;
import uni.uri.salmox.util.Category;
import uni.uri.salmox.util.ColorConverter;
import uni.uri.salmox.util.FontFactory;
import uni.uri.salmox.util.JAutoSuggestComboBox;
import uni.uri.salmox.util.JContextPopupMenu;
import uni.uri.salmox.util.NumberDocumentFilter;
import uni.uri.salmox.util.PreferencesManager;
import uni.uri.salmox.util.ScreenResolution;

/**
 * Janela para cadastro e edição de livros de formandos de pós-graduação
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class AddBookFPFrame implements AddBookInterface {

    //frame principal
    private JFrame frame;
    //label com categoria
    private JLabel titleLabel;
    //label código
    private JLabel codeLabel;
    //label observação
    private JLabel observationLabel;
    //label titulo do livro
    private JLabel bookLabel;
    //label ano
    private JLabel yearLabel;
    //label a
    private JLabel toLabel;
    //label tipo de curso
    private JLabel typeCourseLabel;
    //campo de texto para código
    private JTextField codeField;
    //campo de texto para ano
    private JTextField yearField;
    //campo de texto primeito intervalo
    private JTextField firstIntervalField;
    //campo de texto segundo intervalo
    private JTextField secondIntervalField;
    //campo de texto para curso
    private JAutoSuggestComboBox courseField;
    //combo com semestre
    private JAutoSuggestComboBox typeCourseField;
    //área de texto para observação
    private JTextArea observationField;
    //radio ano
    private JRadioButton yearRadioButton;
    //radio intervalo
    private JRadioButton intervalRadioButton;
    //scrollPane para o área de texto
    private JScrollPane scrollPane;
    //botão ok
    private JButton okButton;
    //botão cancelar
    private JButton cancelButton;
    //tótulo da caixa
    private String titleBox;
    //título da caixa
    private String title;
    //livro formandos pós-graduação
    private BookFP bookFP;
    //largura da tela
    private int x;
    //altura da tela
    private int y;

    /**
     * Construtor para cadastro de livro de formandos pós-graduação
     *
     * @param codeSugered código sugerido (ultimo código cadastrado +1)
     * @param titleBox título da caixa
     */
    public AddBookFPFrame(int codeSugered, String titleBox) {
        this.titleBox = titleBox;
        title = "Adicionar livro - " + Category.FP.getName();
        createView();
        codeField.setText("" + codeSugered);
        bookFP = new BookFP();
    }//fim do construtor para cadastro

    /**
     * Construtor para edição de livro de formandos pós-graduação
     *
     * @param titleBox título da caixa
     * @param bookF livro de formandos pós-graduação
     */
    public AddBookFPFrame(String titleBox, BookFP bookFP) {
        this.bookFP = bookFP;
        this.titleBox = titleBox;
        title = "Editar livro - " + Category.FP.getName();
        createView();
        fillsView();
    }//fim do construtor para edição

    /**
     * Cria a janela
     */
    private void createView() {

        //inicializa componentes
        frame = new JFrame(title);
        titleLabel = new JLabel(titleBox);
        codeLabel = new JLabel("Código: *");
        yearLabel = new JLabel("Ano: *");
        bookLabel = new JLabel("Curso: *");
        typeCourseLabel = new JLabel("Tipo de Curso: *");
        observationLabel = new JLabel("Observação:");
        toLabel = new JLabel("a");
        codeField = new JTextField();
        yearField = new JTextField();
        firstIntervalField = new JTextField();
        secondIntervalField = new JTextField();
        typeCourseField = new JAutoSuggestComboBox();
        courseField = new JAutoSuggestComboBox();
        observationField = new JTextArea();
        yearRadioButton = new JRadioButton();
        intervalRadioButton = new JRadioButton();
        scrollPane = new JScrollPane();
        okButton = new JButton("OK");
        cancelButton = new JButton("CANCELAR");

        //define layout
        frame.setLayout(null);
        courseField.setEditable(true);
        typeCourseField.setEditable(true);
        yearRadioButton.setSelected(true);
        firstIntervalField.setEditable(false);
        secondIntervalField.setEditable(false);

        //configura textarea
        //quebra de linha
        observationField.setLineWrap(true);
        //impede separação de palavras
        observationField.setWrapStyleWord(true);

        //pega tamanho da tela
        takeScreenSize();

        //define localização e tamanho dos componentes
        frame.setBounds((x - 550) / 2, (y - 560) / 2, 550, 560);
        titleLabel.setBounds((500 - 150) / 2, 10, 150, 30);
        codeLabel.setBounds(30, 60, 90, 30);
        bookLabel.setBounds(30, 110, 120, 30);
        yearLabel.setBounds(30, 185, 120, 30);
        typeCourseLabel.setBounds(30, 260, 120, 30);
        observationLabel.setBounds(30, 310, 100, 30);
        codeField.setBounds(140, 60, 100, 30);
        courseField.setBounds(140, 110, 250, 30);
        yearRadioButton.setBounds(140, 160, 30, 30);
        yearField.setBounds(180, 160, 100, 30);
        intervalRadioButton.setBounds(140, 210, 30, 30);
        firstIntervalField.setBounds(180, 210, 100, 30);
        toLabel.setBounds(290, 210, 20, 30);
        secondIntervalField.setBounds(310, 210, 100, 30);
        typeCourseField.setBounds(140, 260, 200, 30);
        scrollPane.setBounds(140, 310, 250, 150);
        okButton.setBounds(120, 480, 140, 30);
        cancelButton.setBounds(300, 480, 140, 30);

        //define configurações visuais
        //cor
        frame.getContentPane().setBackground(getColor());
        titleLabel.setForeground(Color.BLACK);
        codeLabel.setForeground(Color.BLACK);
        bookLabel.setForeground(Color.BLACK);
        yearLabel.setForeground(Color.BLACK);
        typeCourseLabel.setForeground(Color.BLACK);
        observationLabel.setForeground(Color.BLACK);
        codeField.setForeground(Color.BLACK);
        courseField.setForeground(Color.BLACK);
        yearRadioButton.setForeground(Color.BLACK);
        yearRadioButton.setBackground(getColor());
        yearField.setForeground(Color.BLACK);
        intervalRadioButton.setForeground(Color.BLACK);
        intervalRadioButton.setBackground(getColor());
        firstIntervalField.setForeground(Color.BLACK);
        toLabel.setForeground(Color.BLACK);
        secondIntervalField.setForeground(Color.BLACK);
        typeCourseField.setForeground(Color.BLACK);
        scrollPane.setForeground(Color.BLACK);
        okButton.setForeground(Color.BLACK);
        cancelButton.setForeground(Color.BLACK);

        //aligamento
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        //fonte
        titleLabel.setFont(FontFactory.getFontLarge());
        codeLabel.setFont(FontFactory.getFontDefault());
        bookLabel.setFont(FontFactory.getFontDefault());
        yearLabel.setFont(FontFactory.getFontDefault());
        typeCourseLabel.setFont(FontFactory.getFontDefault());
        observationLabel.setFont(FontFactory.getFontDefault());
        codeField.setFont(FontFactory.getFontDefault());
        courseField.setFont(FontFactory.getFontDefault());
        yearRadioButton.setFont(FontFactory.getFontDefault());
        yearField.setFont(FontFactory.getFontDefault());
        intervalRadioButton.setFont(FontFactory.getFontDefault());
        firstIntervalField.setFont(FontFactory.getFontDefault());
        toLabel.setFont(FontFactory.getFontDefault());
        secondIntervalField.setFont(FontFactory.getFontDefault());
        typeCourseField.setFont(FontFactory.getFontDefault());
        scrollPane.setFont(FontFactory.getFontDefault());
        okButton.setFont(FontFactory.getFontLarge());
        cancelButton.setFont(FontFactory.getFontLarge());

        //dicas
        codeField.setToolTipText("Insira o código do livro ");
        yearField.setToolTipText("Insira o ano do livro");
        yearRadioButton.setToolTipText("Marque para apenas 1 ano");
        intervalRadioButton.setToolTipText("Marque para um intervalo de anos");
        firstIntervalField.setToolTipText("Insira o primeito ano");
        secondIntervalField.setToolTipText("Insira o segundo ano");
        typeCourseField.setToolTipText("Insira o tipo de curso");
        courseField.setToolTipText("Insira o curso do livro");
        observationField.setToolTipText("Se desejado insira uma observação");
        if (bookFP == null) {
            okButton.setToolTipText("Cadastra livro");
            cancelButton.setToolTipText("Cancela o cadastro do livro");
        } else {
            okButton.setToolTipText("Salva alterações do livro");
            cancelButton.setToolTipText("Cancela a edição do livro");
        }

        //define filtros
        DocumentFilter numberDocumentFilter = new NumberDocumentFilter();
        ((AbstractDocument) codeField.getDocument()).setDocumentFilter(numberDocumentFilter);
        ((AbstractDocument) yearField.getDocument()).setDocumentFilter(numberDocumentFilter);
        ((AbstractDocument) firstIntervalField.getDocument()).setDocumentFilter(numberDocumentFilter);
        ((AbstractDocument) secondIntervalField.getDocument()).setDocumentFilter(numberDocumentFilter);

        //adiciona os componentes ao frame
        frame.add(titleLabel);
        frame.add(codeLabel);
        frame.add(yearLabel);
        frame.add(bookLabel);
        frame.add(typeCourseLabel);
        frame.add(observationLabel);
        frame.add(codeField);
        frame.add(yearField);
        frame.add(yearRadioButton);
        frame.add(intervalRadioButton);
        frame.add(firstIntervalField);
        frame.add(toLabel);
        frame.add(secondIntervalField);
        frame.add(typeCourseField);
        frame.add(courseField);
        scrollPane.setViewportView(observationField);
        frame.add(scrollPane);
        frame.add(okButton);
        frame.add(cancelButton);


        //define evento
        intervalRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (intervalRadioButton.isSelected()) {
                    yearRadioButton.setSelected(false);
                    firstIntervalField.setEditable(true);
                    secondIntervalField.setEditable(true);
                    yearField.setEditable(false);
                    firstIntervalField.requestFocus();
                } else {
                    intervalRadioButton.setSelected(false);
                    firstIntervalField.setEditable(false);
                    secondIntervalField.setEditable(false);
                    yearField.setEditable(true);
                    yearRadioButton.setSelected(true);
                    yearField.requestFocus();
                }
            }
        });

        yearRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (yearRadioButton.isSelected()) {
                    intervalRadioButton.setSelected(false);
                    firstIntervalField.setEditable(false);
                    secondIntervalField.setEditable(false);
                    yearField.setEditable(true);
                    yearField.requestFocus();

                } else {
                    yearRadioButton.setSelected(false);
                    firstIntervalField.setEditable(true);
                    secondIntervalField.setEditable(true);
                    yearField.setEditable(false);
                    intervalRadioButton.setSelected(true);
                    firstIntervalField.requestFocus();
                }
            }
        });

        //limita campo de ano para ter no máximo 4 caracteres
        KeyAdapter limitYearField = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                JTextField tf = (JTextField) keyEvent.getComponent();
                if (tf.getText().length() >= 4) {
                    keyEvent.setKeyChar((char) KeyEvent.VK_CLEAR);
                }
            }
        };
        yearField.addKeyListener(limitYearField);
        firstIntervalField.addKeyListener(limitYearField);
        secondIntervalField.addKeyListener(limitYearField);

        JContextPopupMenu contextPopupMenu = new JContextPopupMenu();

        //adiciona evento aos itens do popupmenu
        contextPopupMenu.addInComponet(codeField);
        contextPopupMenu.addInComponet(yearField);
        contextPopupMenu.addInComponet(courseField);
        contextPopupMenu.addInComponet(observationField);
        contextPopupMenu.addInComponet(firstIntervalField);
        contextPopupMenu.addInComponet(secondIntervalField);
        contextPopupMenu.addInComponet(courseField);
        contextPopupMenu.addInComponet(typeCourseField);


        //configurações finais do frame
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setVisible(true);


        //define focus primario
        courseField.requestFocusInWindow();
    }//fim do método createView

    /**
     * Preenche os campos com dados para edição
     */
    private void fillsView() {
        codeField.setText("" + bookFP.getCodeBookFP());
        courseField.setText(bookFP.getTitleBook());
        String year = bookFP.getYear();
        if (year.contains("-")) {
            String[] years = year.split("-");
            firstIntervalField.setText(years[0]);
            secondIntervalField.setText(years[1]);
            intervalRadioButton.setSelected(true);
            yearRadioButton.setSelected(false);
            firstIntervalField.setEditable(true);
            secondIntervalField.setEditable(true);
            yearField.setEditable(false);
        } else {
            yearField.setText(year);
        }
        typeCourseField.setText(bookFP.getTypeCourse());
        observationField.setText(bookFP.getObservationBook());
    }//fim do método fillsView

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
     * Fecha a janela
     */
    public void close() {
        frame.dispose();
    }//fim do método close

    /**
     * Define visibilidade do frame
     *
     * @param visible true-visivel false-invisivel
     */
    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }//fim do método setVisible

    /**
     * Define se a janela está ativa
     *
     * @param enable true-ativada false-desativada
     */
    public void enableFrame(boolean enable) {
        frame.setEnabled(enable);
    }//fim do método enableFrame

    /**
     * Retorna dados dos campos em forma de livro
     *
     * @return <code>BookFP</code> livro de formandos pós-graduação
     */
    public BookFP getBook() {
        BookFP bookFP = new BookFP();
        if (codeField.getText() == null || codeField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe o código do livro!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (courseField.getText() == null || courseField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe o curso do livro!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (yearRadioButton.isSelected() && yearField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe o ano da caixa!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (intervalRadioButton.isSelected()) {
            if (firstIntervalField.getText().isEmpty() || secondIntervalField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Informe o ano da caixa!", "Atenção!", JOptionPane.WARNING_MESSAGE);
                return null;
            }
        }
        if (typeCourseField.getText() == null || typeCourseField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe o tipo de curso da caixa!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        try {
            bookFP.setCodeBookFP(Integer.valueOf(codeField.getText()));
            bookFP.setTitleBook(courseField.getText());
            if (yearRadioButton.isSelected()) {
                bookFP.setYear(yearField.getText());
            } else {
                bookFP.setYear(firstIntervalField.getText() + "-" + secondIntervalField.getText());
            }
            bookFP.setTypeCourse(typeCourseField.getText());
            bookFP.setObservationBook(observationField.getText());
            return bookFP;
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null, "Formato incorreto!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }//fim do método getBook

    /**
     * Define ouvinte para o botão ok
     *
     * @param actionLiatener ouvinte
     */
    public void setActionListenerOKButton(ActionListener actionLiatener) {
        okButton.addActionListener(actionLiatener);
    }//fim do método setActionListenerOKButton

    /**
     * Define ouvinte para o botão cancelar
     *
     * @param actionListener ouvinte
     */
    public void setActionListenetCancelButton(ActionListener actionListener) {
        cancelButton.addActionListener(actionListener);
    }//fim do método setActionListenetCancelButton

    /**
     * Define evento de fechamento de janela
     *
     * @param windowAdapter ouvinte
     */
    public void setCloseWindowListener(WindowAdapter windowAdapter) {
        frame.addWindowListener(windowAdapter);
    }//fim do método setCloseWindowListener

    /**
     * Informa lista de cursos para o auto-completar
     *
     * @param list lista com tipos de caixa de almoxarifado
     */
    public void setCourseList(List<String> list) {
        courseField.setSuggestList(list);
    }//fim do método setCourseList

    /**
     * Define evento de teclas para o frame
     *
     * @param keyListener ouvinte
     */
    public void setKeyListenerFrame(KeyListener keyListener) {
        frame.addKeyListener(keyListener);
        codeField.addKeyListener(keyListener);
        yearField.addKeyListener(keyListener);
        firstIntervalField.addKeyListener(keyListener);
        secondIntervalField.addKeyListener(keyListener);
        courseField.addKeyListener(keyListener);
        typeCourseField.addKeyListener(keyListener);
        observationField.addKeyListener(keyListener);
        yearRadioButton.addKeyListener(keyListener);
        intervalRadioButton.addKeyListener(keyListener);
        okButton.addKeyListener(keyListener);
        cancelButton.addKeyListener(keyListener);
    }//fim do método setKeyListenerFrame

    /**
     * Informa lista de tipos de cursos para o auto-completar
     *
     * @param list lista com tipos de curso
     */
    public void setTypeCourseList(List<String> list) {
        typeCourseField.setSuggestList(list);
    }//fim do método setTypeCourseList

    /**
     * Obtêm cor definida para essa janela
     *
     * @return <code>Color</code> cor
     */
    private Color getColor() {
        ColorConverter colorConverter = new ColorConverter();
        return colorConverter.getColor(PreferencesManager.KEY_COLOR_BOOK_FP);
    }//fim do método getColor

    //método para testes
    public static void main(String args[]) {
        AddBookFPFrame addBookFPFrame = new AddBookFPFrame(0, "test");
        List<String> list = new ArrayList();
        list.add("teste");
        addBookFPFrame.setCourseList(list);
    }
}//fim da classe AddBookFPFrame

