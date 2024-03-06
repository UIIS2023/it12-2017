package mvc;

import shapes.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawingFrame extends JFrame {

    private DrawingView view;
    private DrawingController controller;
    private DrawingModel model;
    
    
    private JToggleButton toggleButtonPoint,
    toggleButtonLine,
    toggleButtonRectangle,
    toggleButtonCircle,
    toggleButtonDonut,
    toggleButtonHexagon,
    toggleButtonSelect;

	private JButton buttonDelete,
    buttonModify,
    buttonUndo,
    buttonRedo,
    buttonBringToFront,
    buttonBringToBack,
    buttonToFront,
    buttonToBack,
    buttonFillColor,
    buttonEdgeColor,
	buttonSaveLog,
	buttonOpenLog,
	buttonLoadNext;
    
	private JPanel optionPanel,
    createPanel;
	
	private ButtonGroup buttonGroup;
	
	private JTextArea logArea;
	
    
    public DrawingView getView() {
        return view;
    }

    public void setView(DrawingView view) {
        this.view = view;
    }
    public DrawingController getController() {
        return controller;
    }

    public void setController(DrawingController controller) {
        this.controller = controller;
    }

    public DrawingModel getModel() {
        return model;
    }

    public void setModel(DrawingModel model) {
        this.model = model;
    }
    
    public JToggleButton getToggleButtonPoint() {
        return toggleButtonPoint;
    }

    public void setToggleButtonPoint(JToggleButton toggleButtonPoint) {
        this.toggleButtonPoint = toggleButtonPoint;
    }

    public JToggleButton getToggleButtonLine() {
        return toggleButtonLine;
    }

    public void setToggleButtonLine(JToggleButton toggleButtonLine) {
        this.toggleButtonLine = toggleButtonLine;
    }

    public JToggleButton getToggleButtonRectangle() {
        return toggleButtonRectangle;
    }

    public void setToggleButtonRectangle(JToggleButton toggleButtonRectangle) {
        this.toggleButtonRectangle = toggleButtonRectangle;
    }

    public JToggleButton getToggleButtonCircle() {
        return toggleButtonCircle;
    }

    public void setToggleButtonCircle(JToggleButton toggleButtonCircle) {
        this.toggleButtonCircle = toggleButtonCircle;
    }

    public JToggleButton getToggleButtonDonut() {
        return toggleButtonDonut;
    }

    public void setToggleButtonDonut(JToggleButton toggleButtonDonut) {
        this.toggleButtonDonut = toggleButtonDonut;
    }

    public JToggleButton getToggleButtonHexagon() {
        return toggleButtonHexagon;
    }

    public void setToggleButtonHexagon(JToggleButton toggleButtonHexagon) {
        this.toggleButtonHexagon = toggleButtonHexagon;
    }
    
    public JToggleButton getToggleButtonSelect() {
		return toggleButtonSelect;
	}

	public void setToggleButtonSelect(JToggleButton toggleButtonSelect) {
		this.toggleButtonSelect = toggleButtonSelect;
	}
    
    public JButton getButtonDelete() {
		return buttonDelete;
	}

	public void setButtonDelete(JButton buttonDelete) {
		this.buttonDelete = buttonDelete;
	}

	public JButton getButtonModify() {
		return buttonModify;
	}

	public void setButtonModify(JButton buttonModify) {
		this.buttonModify = buttonModify;
	}

	public JPanel getCreatePanel() {
		return createPanel;
	}

	public void setCreatePanel(JPanel createPanel) {
		this.createPanel = createPanel;
	}

    public JPanel getOptionPanel() {
        return optionPanel;
    }

    public void setOptionPanel(JPanel optionPanel) {
        this.optionPanel = optionPanel;
    }
    
    public JButton getButtonUndo() {
        return buttonUndo;
    }

    public void setButtonUndo(JButton buttonUndo) {
        this.buttonUndo = buttonUndo;
    }

    public JButton getButtonRedo() {
        return buttonRedo;
    }

    public void setButtonRedo(JButton buttonRedo) {
        this.buttonRedo = buttonRedo;
    }

    public JButton getButtonBringToFront() {
        return buttonBringToFront;
    }

    public void setButtonBringToFront(JButton buttonBringToFront) {
        this.buttonBringToFront = buttonBringToFront;
    }

    public JButton getButtonBringToBack() {
        return buttonBringToBack;
    }

    public void setButtonBringToBack(JButton buttonBringToBack) {
        this.buttonBringToBack = buttonBringToBack;
    }

    public JButton getButtonToFront() {
        return buttonToFront;
    }

    public void setButtonToFront(JButton buttonToFront) {
        this.buttonToFront = buttonToFront;
    }

    public JButton getButtonToBack() {
        return buttonToBack;
    }

    public void setButtonToBack(JButton buttonToBack) {
        this.buttonToBack = buttonToBack;
    }

    public JButton getButtonFillColor() {
        return buttonFillColor;
    }

    public void setButtonFillColor(JButton buttonFillColor) {
        this.buttonFillColor = buttonFillColor;
    }

    public JButton getButtonEdgeColor() {
        return buttonEdgeColor;
    }

    public void setButtonEdgeColor(JButton buttonEdgeColor) {
        this.buttonEdgeColor = buttonEdgeColor;
    }

    public JTextArea getLogArea() {
        return logArea;
    }

    public void setLogArea(JTextArea logArea) {
        this.logArea = logArea;
    }
    
    public JButton getButtonSaveLog() {
        return buttonSaveLog;
    }

    public void setButtonSaveLog(JButton buttonSaveLog) {
        this.buttonSaveLog = buttonSaveLog;
    }

    public JButton getButtonLoadNext() {
        return buttonLoadNext;
    }

    public void setButtonLoadNext(JButton buttonLoadNext) {
        this.buttonLoadNext = buttonLoadNext;
    }

    public JButton getButtonOpenLog() {
        return buttonOpenLog;
    }

    public void setButtonOpenLog(JButton buttonOpenLog) {
        this.buttonOpenLog = buttonOpenLog;
    }



    
    public DrawingFrame(){
    	model = new DrawingModel();
        view = new DrawingView(model);
       
        controller = new DrawingController(model, this);

       
        view.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.mouseClicked(e);
            }
        });

        
        getContentPane().add(view, BorderLayout.CENTER);

       
        optionPanel = new JPanel();
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
        getContentPane().add(optionPanel, BorderLayout.EAST);


       
        createPanel = new JPanel();
        getContentPane().add(createPanel, BorderLayout.NORTH);
        createPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        buttonGroup = new ButtonGroup();
        
        toggleButtonPoint = new JToggleButton("Point");
        createPanel.add(toggleButtonPoint);
        toggleButtonLine = new JToggleButton("Line");
        createPanel.add(toggleButtonLine);
        toggleButtonRectangle = new JToggleButton("Rectangle");
        createPanel.add(toggleButtonRectangle);
        toggleButtonCircle = new JToggleButton("Circle");
        createPanel.add(toggleButtonCircle);
        toggleButtonDonut = new JToggleButton("Donut");
        createPanel.add(toggleButtonDonut);
        toggleButtonHexagon = new JToggleButton("Hexagon");
        createPanel.add(toggleButtonHexagon);  
        toggleButtonSelect = new JToggleButton("Select");
        createPanel.add(toggleButtonSelect);
        
        
        buttonGroup.add(toggleButtonPoint);
        buttonGroup.add(toggleButtonLine);
        buttonGroup.add(toggleButtonRectangle);
        buttonGroup.add(toggleButtonCircle);
        buttonGroup.add(toggleButtonDonut);
        buttonGroup.add(toggleButtonHexagon);
        buttonGroup.add(toggleButtonSelect);
        
        createPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        
        buttonFillColor = new JButton("Fill color");
        buttonFillColor.setBackground(Color.RED);
        buttonFillColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buttonFillColor.setBackground(JColorChooser.showDialog(null, "Pick fill color", Color.BLUE));
            }
        });
        createPanel.add(buttonFillColor);

        buttonEdgeColor = new JButton("Edge color");
        buttonEdgeColor.setBackground(Color.BLUE);
        buttonEdgeColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buttonEdgeColor.setBackground(JColorChooser.showDialog(null, "Pick edge color", Color.BLUE));
            }
        });
        createPanel.add(buttonEdgeColor);
        
        optionPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        
        
        buttonDelete = new JButton("Delete");
        buttonDelete.setEnabled(false);
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.mouseClickedDelete(e);
            }
        });
        createPanel.add(buttonDelete);
        optionPanel.add(Box.createRigidArea(new Dimension(0, 10)));

      
        buttonModify = new JButton("Modify");
        buttonModify.setEnabled(false);
        buttonModify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.mouseClickedModify(e);
            }
        });
        createPanel.add(buttonModify);
        optionPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        
        buttonUndo = new JButton("Undo");
        buttonUndo.setEnabled(false);
        buttonUndo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.undo();
                buttonRedo.setEnabled(true);
                buttonLoadNext.setEnabled(false);
                if(controller.getCmdStack().getCount()+1 == 0){
                    buttonUndo.setEnabled(false);
                    buttonLoadNext.setEnabled(false);
                }
            }
        });
        optionPanel.add(buttonUndo);
        optionPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        buttonRedo = new JButton("Redo");
        buttonRedo.setEnabled(false);
        buttonRedo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.redo();
                buttonUndo.setEnabled(true);
                if(controller.getCmdStack().getCount()==controller.getCmdStack().getCommands().size()-1){
                    buttonRedo.setEnabled(false);
                }
            }
        });
        optionPanel.add(buttonRedo);
        optionPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        buttonBringToBack = new JButton("BringToBack");
        buttonBringToBack.setEnabled(false);
        buttonBringToBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.bringToBack();
                controller.enableButtons();
            }
        });
        optionPanel.add(buttonBringToBack);
        optionPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        buttonBringToFront = new JButton("BringToFront");
        buttonBringToFront.setEnabled(false);
        buttonBringToFront.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.bringToFront();
                controller.enableButtons();
            }
        });
        optionPanel.add(buttonBringToFront);
        optionPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        buttonToFront = new JButton("ToFront");
        buttonToFront.setEnabled(false);
        buttonToFront.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.toFront();
                controller.enableButtons();
            }
        });
        optionPanel.add(buttonToFront);
        optionPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        buttonToBack = new JButton("ToBack");
        buttonToBack.setEnabled(false);
        buttonToBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.toBack();
                controller.enableButtons();
            }
        });
        optionPanel.add(buttonToBack);
        optionPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        
        buttonSaveLog = new JButton("Save log");
        buttonSaveLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.saveLog();
            }
        });
        optionPanel.add(buttonSaveLog);
        optionPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        buttonOpenLog = new JButton("Open log");
        buttonOpenLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.openLog();
            }
        });
        optionPanel.add(buttonOpenLog);
        optionPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        buttonLoadNext = new JButton("Load next");
        buttonLoadNext.setEnabled(false);
        buttonLoadNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.loadNext();
            }
        });

        optionPanel.add(buttonLoadNext);
        optionPanel.add(Box.createRigidArea(new Dimension(0, 10)));

         

        logArea = new JTextArea(5, 22);
        logArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(logArea);
        getContentPane().add(scrollPane, BorderLayout.SOUTH);

        
    }
}
