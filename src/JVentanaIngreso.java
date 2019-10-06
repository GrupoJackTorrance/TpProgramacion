package pqt;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JVentanaIngreso extends JFrame{
	
	private JPanel contentPane;
	private JLabel label;
	private JButton botonIngreso;
	private JTextField textFieldUsuario;
	
	public static void main(String[] args) {
		JVentanaIngreso ventana = new JVentanaIngreso();
		
		ventana.setVisible(true);
	}
	
	public JVentanaIngreso(){
		setResizable(false);
		setTitle("Bienvenido al juego");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 227);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label = new JLabel("Ingrese su nombre de usuario");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setBounds(100, 40, 250, 30);
		contentPane.add(label);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(184, 80, 86, 20);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		botonIngreso = new JButton("Aceptar");
		botonIngreso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ingresarASala();
			}
		});
		botonIngreso.setBounds(130, 120, 200, 30);
		contentPane.add(botonIngreso);
		
		setLocationRelativeTo(null);
	}
	
	private void ingresarASala() {
		new JVentanaTablero(textFieldUsuario.getText());
	}

}
