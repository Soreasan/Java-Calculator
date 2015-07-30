package calculator;

import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator extends JFrame {
	private JTextField displayField;
	private JButton button;
        private JPanel grid;
        
        //buttons for my grid
        //5-9
        private JButton button5;
        private JButton button6;
        private JButton button7;
        private JButton button8;
        private JButton button9;
        //0-4
        private JButton button0;
        private JButton button1;
        private JButton button2;
        private JButton button3;
        private JButton button4;
        //actions
        private JButton buttonPlus;
        private JButton buttonMinus;
        private JButton buttonMultiply;
        private JButton buttonDivide;
        private JButton buttonEquals;
        
        //variables used for calculations
        private double prevNumber;
        private double currentNumber;
        //0 for add, 1 for subtract, 2 for multiply, 3 for divide
        private int prevOperation;
	
	public static void main(String[] args) {
		new Calculator();

	}

        private void generateDisplayField(){
                displayField = new JTextField("0");
		displayField.setPreferredSize(new Dimension(350, 50));
        }
        
        private void generateClearButton(){
            button = new JButton("Clear");
                button.setSize(100, 50);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
                           prevNumber = 0;
                           currentNumber = 0;
                           prevOperation = 0;
                           displayField.setText((String.valueOf(currentNumber)));
			}
		});
        }
        
        private void clickNumber(int input){
            currentNumber = ((currentNumber * 10) + input);
            displayField.setText((String.valueOf(currentNumber)));
        }
        
        private void createNumberedButton(int number){
            JButton button = new JButton(String.valueOf(number));
                button.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent event){
                clickNumber(number);
                }
            });
            grid.add(button);
        }
        
        private void generateGrid(){
                grid = new JPanel(new GridLayout(3,5));
                grid.setPreferredSize(new Dimension(350, 250));
                
                
                for(int i = 5; i <= 9; i++){
                    createNumberedButton(i);
                }
                
                for(int i = 0; i <= 4; i++){
                    createNumberedButton(i);
                }
                
                for(int i = 0; i <=4; i++){
                    createOperatorButtons(i);
                }
        }
        
        private void calculateNumber(int operator){
            
            if(operator == 0){
                displayField.setText("+");
            }else if(operator == 1){
                displayField.setText("-");
            }else if(operator == 2){
                displayField.setText("X");
            }else if(operator == 3){
                displayField.setText("/");
            }else if(operator == 4){
                //=
                //Do nothing initially
            }else{
                displayField.setText("Unknown bug.  It should be impossible to see this text.");
            }
            
            if(prevOperation == 0){
                //addition
                prevNumber = prevNumber + currentNumber;
                currentNumber = 0;
                prevOperation = operator;
            }else if(prevOperation==1){
                //subtraction
                prevNumber = prevNumber - currentNumber;
                currentNumber = 0;
                prevOperation = operator;
            }else if(prevOperation==2){
                //multiplication
                prevNumber = prevNumber * currentNumber;
                currentNumber = 0;
                prevOperation = operator;
            }else if(prevOperation==3 && currentNumber == 0){
                //divide by zero
                displayField.setText("Stop trying to divide by zero you dick.");
                prevNumber = 0;
                currentNumber = 0;
                prevOperation = 0;
            }else if(prevOperation==3){
                //division
                prevNumber = prevNumber / currentNumber;
                currentNumber = 0;
                prevOperation = operator;
            }else if(prevOperation==4){
                // equals
                prevNumber = prevNumber;
                currentNumber = 0;
                prevOperation = operator;
            }else{
                //This should never ever happen
                displayField.setText("Unknown error");
                prevNumber = 0;
                currentNumber = 0;
                prevOperation = 0;
            }
            
            if(operator==4){
                displayField.setText(String.valueOf(prevNumber));
            }
        }
        
        private void createOperatorButtons(int operator){
                JButton button;
                if(operator == 0){
                    button = new JButton("+");
                }else if(operator == 1){
                    button = new JButton("-");
                }else if(operator == 2){
                    button = new JButton("X");
                }else if(operator == 3){
                    button = new JButton("/");
                }else if(operator == 4){
                    button = new JButton("=");
                }else{
                    button = new JButton("Error");
                }
                
                
                button.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent event){
                    calculateNumber(operator);
                }
                
            });
            grid.add(button);
        }
        
	public Calculator() {
                //initialize the variables.
                this.prevNumber = 0;
                this.currentNumber = 0;
                this.prevOperation = 0;
                
                //default stuff for the program
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(400, 400));
		
                //default stuff for the JPanel that I'll actualy be putting stuff on
		final JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(350, 350));
                   
                generateDisplayField();
                generateClearButton();
		generateGrid();
                
                
                        
		panel.add(displayField);
		panel.add(button);
                panel.add(grid);
		
		add(panel);
		setVisible(true);
                
	}
}
