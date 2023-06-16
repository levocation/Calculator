package Calculator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CalClass {
	public String input;
	private String value; // 최종값
	private String cal_formula; // 계산중인 수식
	private String operator;
	private boolean isCalculated;
	
	public CalClass() {
		this.input = "0";
		this.value = "null";
		this.cal_formula = "";
		this.operator = "";
		this.setCalculated(false);
	}
	
	public void reset() {
		this.input = "0";
		this.value = "null";
		this.cal_formula = "";
		this.operator = "";
		this.setCalculated(false);
	}
	
	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public void setInput(double input) {
		this.input = this.to_string(input);
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCal_formula() {
		return cal_formula;
	}

	public void setCal_formula(String cal_formula) {
		this.cal_formula = cal_formula;
	}
	
	boolean isDouble(double num) {
		System.out.println(num + " : " + (int)num);
		if (num != (double) ((int) num)) return true;
		return false;
	}
	
	boolean isDouble(String num) {
		double n = this.stod(num);
		if (n != (double) ((int) n)) return true;
		return false;
	}
	
	public void setCalFormula(JTextField textField, String input) {
		textField.setText(input);
	}
	
	public void setTextField(JTextField textField, String input) {
		if (this.getInput().charAt(this.getInput().length() - 1) == '.') {
			if (this.isCalculated()) {
				this.setInput(this.getInput().replace(".", ""));
			} else {
				textField.setText(input);
				return;
			}
		}
		if (this.isDouble(this.stod(input))) {
			textField.setText(input);
		} else {
			textField.setText(Integer.toString((int)this.stod(input)));
		}
		//System.out.println(this.getInput());
	}
	
	public void pushInput(int num) {
		if (this.getInput().equals("0") || this.getInput().equals("") || this.isCalculated()) {
			this.setInput(this.to_string(num));
			this.setCalculated(false);
		}
		else {
			this.setInput(this.getInput() + Integer.toString(num));
		}
	}

	public void popInput() {
		this.setInput(this.getInput().substring(0, this.getInput().length() - 1));
	}
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
		this.setCal_formula(this.getCal_formula() + " " + this.getInput() + " " + this.getOperator());
	}
	
	public double stod(String str) { return Double.parseDouble(str); }
	
	public String to_string(int num) { return Integer.toString(num); }
	
	public String to_string(double num) { return Double.toString(num); }

	public boolean isCalculated() {
		return isCalculated;
	}

	public void setCalculated(boolean isCalculated) {
		this.isCalculated = isCalculated;
	}
	
	public void calculate(String operator) {
		
		if (this.isCalculated()) { 
			this.setOperator(operator);
			
			return;
		}
		
		if (this.getValue().equals("null")) {
			this.setValue(this.getInput());
			this.setCalculated(true);
			this.setOperator(operator);
			return;
		}
		
		if (operator.equals("=")) {
			char ch = this.getInput().charAt(0);
			//System.out.println("ch = " + ch);
			if (ch >= '0' && ch <= '9') {
				this.calculate(this.getOperator());
			} else {
				this.setCal_formula(this.getCal_formula().substring(0, this.getCal_formula().length() - 1));
			}
			this.setCalculated(true);
			return;
		}
		
		if (this.getOperator().equals("＋")) {
			this.setValue(this.to_string(this.stod(this.getValue()) + this.stod(this.getInput())));;
			//System.out.println(this.stod(this.getValue()) + "+" + this.stod(this.getInput()));
			this.setCalculated(true);
		} else if (this.getOperator().equals("－")) {
			this.setValue(this.to_string(this.stod(this.getValue()) - this.stod(this.getInput())));
			//System.out.println(this.stod(this.getValue()) + "-" + this.stod(this.getInput()));
			this.setCalculated(true);
			
		} else if (this.getOperator().equals("×")) {
			this.setValue(this.to_string(this.stod(this.getValue()) * this.stod(this.getInput())));;
			//System.out.println(this.stod(this.getValue()) + "*" + this.stod(this.getInput()));
			this.setCalculated(true);
			
		} else if (this.getOperator().equals("÷")) {
			this.setValue(this.to_string(this.stod(this.getValue()) / this.stod(this.getInput())));;
			//System.out.println(this.stod(this.getValue()) + "/" + this.stod(this.getInput()));
			this.setCalculated(true);
		}
		this.setOperator(operator);
	}
	
}