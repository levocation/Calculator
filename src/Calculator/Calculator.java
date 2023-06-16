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

public class Calculator extends JFrame {

	private JPanel contentPane;
	private JTextField txt_input;
	private JTextField txt_cal_formula;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator frame = new Calculator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Calculator() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txt_input = new JTextField();
		txt_input.setEditable(false);
		txt_input.setFont(new Font("Arial", Font.BOLD, 45));
		txt_input.setHorizontalAlignment(SwingConstants.RIGHT);
		txt_input.setText("0");
		txt_input.setBounds(0, 35, 334, 52);
		contentPane.add(txt_input);
		contentPane.setLayout(null);
		
		txt_cal_formula = new JTextField();
		txt_cal_formula.setHorizontalAlignment(SwingConstants.RIGHT);
		txt_cal_formula.setBounds(0, 0, 334, 25);
		contentPane.add(txt_cal_formula);
		txt_cal_formula.setColumns(10);

		CalClass cal = new CalClass();

		String[] btn_names = {"%", "C", "CE", "DE", "÷",
							  "√", "7", "8" , "9" , "×",
							  "x²", "4", "5", "6" , "－",
							  "x³", "1", "2", "3" , "＋",
							  "¹⁄ₓ", "±", "0", ".", "=" };
		JButton[] btnArr = new JButton[btn_names.length];
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				int index = i * 5 + j;
				btnArr[index] = new JButton(btn_names[index]);
				btnArr[index].setBounds(50 + (50 * j), 100 + (50 * i), 50, 50);
				
				btnArr[index].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String btn_name = e.getActionCommand();
						if (btn_name.equals("%")) {
							cal.setOperator("%");
						} else if (btn_name.equals("C")) {
							cal.setInput(cal.to_string(0));
							cal.setTextField(txt_input, cal.getInput());
						} else if (btn_name.equals("CE")) {
							cal.reset();
							cal.setTextField(txt_input, cal.getInput());
						} else if (btn_name.equals("DE")) {
							cal.popInput();
						} else if (btn_name.equals("÷")) {

							//--------------------
							cal.calculate("÷");
							cal.setTextField(txt_input, cal.getValue());
							cal.setCalFormula(txt_cal_formula, cal.getCal_formula());
							
						} else if (btn_name.equals("√")) {
							cal.setCalculated(true);
							cal.setInput(Math.sqrt(cal.stod(cal.getInput())));
							cal.setValue(cal.getInput());
							cal.setTextField(txt_input, cal.getInput());
						} else if (btn_name.equals("×")) {

							//--------------------
							cal.calculate("×");
							cal.setTextField(txt_input, cal.getValue());
							cal.setCalFormula(txt_cal_formula, cal.getCal_formula());
							
						} else if (btn_name.equals("x²")) {
							cal.setCalculated(true);
							cal.setInput(Math.pow(cal.stod(cal.getInput()), 2));
							cal.setValue(cal.getInput());
							cal.setTextField(txt_input, cal.getInput());
						} else if (btn_name.equals("－")) {
							
							//--------------------
							cal.calculate("－");
							cal.setTextField(txt_input, cal.getValue());
							cal.setCalFormula(txt_cal_formula, cal.getCal_formula());
							
						} else if (btn_name.equals("x³")) {
							cal.setCalculated(true);
							cal.setInput(Math.pow(cal.stod(cal.getInput()), 3));
							cal.setValue(cal.getInput());
							cal.setTextField(txt_input, cal.getInput());
						} else if (btn_name.equals("＋")) {

							//--------------------
							cal.calculate("＋");
							cal.setTextField(txt_input, cal.getValue());
							cal.setCalFormula(txt_cal_formula, cal.getCal_formula());
							
						} else if (btn_name.equals("¹⁄ₓ")) {
							cal.setCalculated(true);
							cal.setInput(1.0 / cal.stod(cal.getInput()));
							cal.setValue(cal.getInput());
							cal.setTextField(txt_input, cal.getInput());
						} else if (btn_name.equals("±")) {
							if (cal.stod(cal.getInput()) != Double.parseDouble("0")) {
								if (cal.getInput().contains("-")) {
									cal.setInput(cal.getInput().replace("-", ""));
								} else {
									cal.setInput("-" + cal.getInput());
								}
								cal.setTextField(txt_input, cal.getInput());
							}
						} else if (btn_name.equals(".")) {
							if (!cal.getInput().contains(".")) {
								cal.setInput(cal.getInput() + ".");
								cal.setTextField(txt_input, cal.getInput());
							}
						} else if (btn_name.equals("=")) {
							cal.calculate("=");
							cal.setTextField(txt_input, cal.getValue());
							cal.setCalFormula(txt_cal_formula, cal.getCal_formula());
						} else {
							cal.pushInput(Integer.parseInt(e.getActionCommand()));
							cal.setTextField(txt_input, cal.getInput());
						}
					}
				});
				
				contentPane.add(btnArr[index]);
			}
		}
	}
}