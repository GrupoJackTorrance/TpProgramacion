package clienteServidor;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class VentanaServidor extends JFrame{
	static ServerSocket servidor;
	PanelVentanaServidor panelServidor;
	public VentanaServidor(ServerSocket servidor) {
		this.servidor=servidor;
		setLocation(10,100);
		setSize(300, 300);
		setTitle("Servidor");
		panelServidor=new PanelVentanaServidor();
		add(panelServidor);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void cerrarServidor() {
		try {
			servidor.close();
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
	}
}



class PanelVentanaServidor extends JPanel {
	private JButton botonSalir;
	private JTextField texto;
	JPanel panelSur = new JPanel(new GridLayout(1, 2));
	
	public PanelVentanaServidor() {
		texto = new JTextField("Servidor prendido");
		texto.setEnabled(false);
		botonSalir = new JButton("Apagar servidor");
		botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaServidor.cerrarServidor();
			}
		});
		add(texto);
		add(botonSalir);
	}
	
	public void apagar() {
		botonSalir.setEnabled(false);
		texto.setText("Servidor apagado");
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		texto.setBounds(10, 10, 170, 30);
		botonSalir.setBounds(10, 150, 180, 20);
	}
	
}