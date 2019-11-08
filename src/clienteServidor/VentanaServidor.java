package clienteServidor;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VentanaServidor extends JFrame{
	static ServerSocket servidor;
	PanelVentanaServidor panelServidor;
	public VentanaServidor(ServerSocket servidor) {
		this.servidor=servidor;
		setVisible(true);
		setLocation(10,20);
		setSize(300, 300);
		setTitle("Servidor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelServidor=new PanelVentanaServidor();
		add(panelServidor);
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
	private static final long serialVersionUID = 1L;
	private JButton botonSalir;
	private JTextArea texto;
	private JScrollPane scroll = new JScrollPane(texto);
	
	public PanelVentanaServidor() {
		texto = new JTextArea("Servidor prendido");
		botonSalir = new JButton("Apagar servidor");
		add(scroll, BorderLayout.CENTER);
		texto.setLineWrap(true);
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
		texto.setBounds(10, 10, 100, 20);
		botonSalir.setBounds(10, 30, 80, 20);
	}
	
}