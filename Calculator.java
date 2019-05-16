import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Calculator extends JFrame {

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.pack();
        calc.setLocationRelativeTo(null);
                calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calc.setVisible(true);
    }
	
	private JButton jbtNum1;
    private JButton jbtNum2;
    private JButton jbtNum3;
    private JButton jbtNum4;
    private JButton jbtNum5;
    private JButton jbtNum6;
    private JButton jbtNum7;
    private JButton jbtNum8;
    private JButton jbtNum9;
    private JButton jbtNum0;
    private JButton jbtAdd;
    private JButton jbtSubtract;
    private JButton jbtMultiply;
    private JButton jbtDivide;
    private JButton jbtSolve;
    private JButton jbtClear;
    private double temp;
    private double solveTemp;
    private JTextField jtfResult;

    Boolean addBool = false;
    Boolean subBool = false;
    Boolean divBool = false;
    Boolean mulBool = false;

    String display = "";

    public Calculator() {

        JPanel numbersPanel = new JPanel();
        numbersPanel.setLayout(new GridLayout(4, 3));
        numbersPanel.add(jbtNum1 = new JButton("1"));
        numbersPanel.add(jbtNum2 = new JButton("2"));
        numbersPanel.add(jbtNum3 = new JButton("3"));
        numbersPanel.add(jbtNum4 = new JButton("4"));
        numbersPanel.add(jbtNum5 = new JButton("5"));
        numbersPanel.add(jbtNum6 = new JButton("6"));
        numbersPanel.add(jbtNum7 = new JButton("7"));
        numbersPanel.add(jbtNum8 = new JButton("8"));
        numbersPanel.add(jbtNum9 = new JButton("9"));
        numbersPanel.add(jbtNum0 = new JButton("0"));
        numbersPanel.add(jbtClear = new JButton("C"));

        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new FlowLayout());
        resultsPanel.add(jtfResult = new JTextField(20));
        jtfResult.setHorizontalAlignment(JTextField.RIGHT);
        jtfResult.setEditable(false);

        JPanel operationsPanel = new JPanel();
        operationsPanel.setLayout(new GridLayout(5, 1));
        operationsPanel.add(jbtAdd = new JButton("+"));
        operationsPanel.add(jbtSubtract = new JButton("-"));
        operationsPanel.add(jbtMultiply = new JButton("*"));
        operationsPanel.add(jbtDivide = new JButton("/"));
        operationsPanel.add(jbtSolve = new JButton("="));

        JPanel calculatorPanel = new JPanel();
        calculatorPanel.setLayout(new GridLayout());
        calculatorPanel.add(resultsPanel, BorderLayout.NORTH);
        calculatorPanel.add(numbersPanel, BorderLayout.SOUTH);
        calculatorPanel.add(operationsPanel, BorderLayout.EAST);

        add(calculatorPanel);

        jbtNum1.addActionListener(new NumberListener("1"));
        jbtNum2.addActionListener(new NumberListener("2"));
        jbtNum3.addActionListener(new NumberListener("3"));
        jbtNum4.addActionListener(new NumberListener("4"));
        jbtNum5.addActionListener(new NumberListener("5"));
        jbtNum6.addActionListener(new NumberListener("6"));
        jbtNum7.addActionListener(new NumberListener("7"));
        jbtNum8.addActionListener(new NumberListener("8"));
        jbtNum9.addActionListener(new NumberListener("9"));
        jbtNum0.addActionListener(new NumberListener("0"));

        jbtAdd.addActionListener(new OperationListener('+'));
        jbtSubtract.addActionListener(new OperationListener('-'));
        jbtMultiply.addActionListener(new OperationListener('*'));
        jbtDivide.addActionListener(new OperationListener('/'));
        jbtSolve.addActionListener(new ListenToSolve());
        jbtClear.addActionListener(new ListenToClear());
    }

    class NumberListener implements ActionListener {
    	
    	String text;
    	
    	public NumberListener(String text) {
    		this.text = text;
    	}
    	
        public void actionPerformed(ActionEvent e) {
            display = jtfResult.getText();
            jtfResult.setText(display + this.text);
        }
    }
    
    class OperationListener implements ActionListener {
    	
    	char operation;
    	
    	public OperationListener(char operation) {
    		this.operation = operation;
    	}
    	
    	public void actionPerformed(ActionEvent e) {
    		temp = Double.parseDouble(jtfResult.getText());
            jtfResult.setText("");
            switch (this.operation) {
	            case '+':
	            	addBool = true;
	            case '-':
	            	subBool = true;
	            case '*':
	            	mulBool = true;
	            case '/':
	            	divBool = true;
            }
    	}
    }

    class ListenToClear implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            jtfResult.setText("");
            addBool = false;
            subBool = false;
            mulBool = false;
            divBool = false;

            temp = 0;
            solveTemp = 0;
        }
    }

    class ListenToSolve implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            solveTemp = Double.parseDouble(jtfResult.getText());
            if (addBool == true) {
            	solveTemp = temp + solveTemp;
            } else if ( subBool == true) {
            	solveTemp = temp - solveTemp;
            } else if ( mulBool == true) {
            	solveTemp = temp * solveTemp;
            } else if ( divBool == true) {
            	solveTemp = temp / solveTemp;
            }
                
            jtfResult.setText(Double.toString(solveTemp));

            addBool = false;
            subBool = false;
            mulBool = false;
            divBool = false;
        }
    }

}
