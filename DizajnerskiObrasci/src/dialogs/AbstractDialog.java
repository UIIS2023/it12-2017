package dialogs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public abstract class AbstractDialog extends JDialog {
	
    private JPanel mainPanel;
    private JPanel bottomPane;

    private Color edgeColor;
    private Color fillColor;
    private Boolean windowOk = false;

    private JTextField textX,
            textY,
            textLineX,
            textLineY,
            textRadius,
            textInnerRadius,
            textWidth,
            textHeight;

    private JButton edgeColorButton,
            fillColorButton;


    public AbstractDialog(String title){

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                windowOk=false;
            }
        });

        setTitle(title);
        setModal(true);

        mainPanel = new JPanel();
        bottomPane = new JPanel();
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(bottomPane, BorderLayout.SOUTH);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        bottomPane.setLayout(new FlowLayout(FlowLayout.CENTER));

        textX = setUpTextField();
        mainPanel.add(new JLabel("X: "));
        mainPanel.add(textX);
        textY = setUpTextField();
        mainPanel.add(new JLabel("Y: "));
        mainPanel.add(textY);


        if(title.equals("LineDialog")){
            textLineX = setUpTextField();
            mainPanel.add(new JLabel("Second X:"));
            mainPanel.add(textLineX);
            textLineY = setUpTextField();
            mainPanel.add(new JLabel("Second Y:"));
            mainPanel.add(textLineY);
        }

        if(title.equals("RectangleDialog")){
            textWidth = setUpTextField();
            mainPanel.add(new JLabel("Width:"));
            mainPanel.add(textWidth);
            textHeight = setUpTextField();
            mainPanel.add(new JLabel("Height:"));
            mainPanel.add(textHeight);

        }

        if(title.equals("CircleDialog") || title.equals("DonutDialog") || title.equals("HexagonDialog")){
            textRadius = setUpTextField();
            mainPanel.add(new JLabel("Radius:"));
            mainPanel.add(textRadius);
            if(title.equals("DonutDialog")){
                textInnerRadius = setUpTextField();
                mainPanel.add(new JLabel("Inner radius:"));
                mainPanel.add(textInnerRadius);
            }
        }
     
        


        edgeColorButton = new JButton("Edge color");
        edgeColorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                edgeColor = JColorChooser.showDialog(null, "Choose color", Color.BLACK);
                edgeColorButton.setBackground(edgeColor);
            }
        });
        mainPanel.add(edgeColorButton);


        if(!title.equals("PointDialog") && !title.equals("LineDialog")){
            fillColorButton = new JButton("Fill color");
            fillColorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fillColor = JColorChooser.showDialog(null, "Choose color", Color.BLACK);
                    fillColorButton.setBackground(fillColor);
                }
            });
            mainPanel.add(fillColorButton);
        }



        //Dugme za cuvanje
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!checkForEmptyFields()) {
                    windowOk = false;
                    setVisible(true);
                    JOptionPane.showMessageDialog(null, "Fields must not be empty!","Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    windowOk = true;
                    dispose();
                }
            }
        });

        //Ovako znamo da je sacuvan
        saveButton.setActionCommand("Save");
        bottomPane.add(saveButton);
        getRootPane().setDefaultButton(saveButton);



        //Dugme za cancel
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                windowOk=false;
                dispose();
            }
        });
        cancelButton.setActionCommand("Cancel");
        bottomPane.add(cancelButton);

        pack();

    }

    public JTextField setUpTextField(){
        JTextField textField = new JTextField();
        textField.setTransferHandler(null);
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                    getToolkit().beep();
                }
            }
        });

        return textField;
    }

    public boolean checkForEmptyFields(){
        for(Component comp: mainPanel.getComponents()){
            if(comp instanceof JTextField){
                if(((JTextField) comp).getText().isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public Color getEdgeColor() {
        return edgeColor;
    }

    public void setEdgeColor(Color edgeColor) {
        this.edgeColor = edgeColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Boolean getWindowOk() {
        return windowOk;
    }

    public void setWindowOk(Boolean windowOk) {
        this.windowOk = windowOk;
    }

    public JTextField getTextX() {
        return textX;
    }

    public void setTextX(JTextField textX) {
        this.textX = textX;
    }

    public JTextField getTextY() {
        return textY;
    }

    public void setTextY(JTextField textY) {
        this.textY = textY;
    }

    public JTextField getTextLineX() {
        return textLineX;
    }

    public void setTextLineX(JTextField textLineX) {
        this.textLineX = textLineX;
    }

    public JTextField getTextLineY() {
        return textLineY;
    }

    public void setTextLineY(JTextField textLineY) {
        this.textLineY = textLineY;
    }

    public JTextField getTextRadius() {
        return textRadius;
    }

    public void setTextRadius(JTextField textRadius) {
        this.textRadius = textRadius;
    }

    public JTextField getTextInnerRadius() {
        return textInnerRadius;
    }

    public void setTextInnerRadius(JTextField textInnerRadius) {
        this.textInnerRadius = textInnerRadius;
    }

    public JTextField getTextWidth() {
        return textWidth;
    }

    public void setTextWidth(JTextField textWidth) {
        this.textWidth = textWidth;
    }

    public JTextField getTextHeight() {
        return textHeight;
    }

    public void setTextHeight(JTextField textHeight) {
        this.textHeight = textHeight;
    }

    public JButton getEdgeColorButton() {
        return edgeColorButton;
    }

    public void setEdgeColorButton(JButton edgeColorButton) {
        this.edgeColorButton = edgeColorButton;
    }

    public JButton getFillColorButton() {
        return fillColorButton;
    }

    public void setFillColorButton(JButton fillColorButton) {
        this.fillColorButton = fillColorButton;
    }
}
