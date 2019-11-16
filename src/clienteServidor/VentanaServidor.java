package clienteServidor;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class VentanaServidor extends JFrame{
	static ServerSocket servidor;
	PanelVentanaServidor panelServidor;
	public VentanaServidor(ServerSocket servidor) throws UnknownHostException {
		this.servidor=servidor;
		setLocation(10,100);
		setSize(500, 500);
		setBounds(100, 100, 448, 333);
		
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
	private JLabel lblServer;
	private JLabel lblIp;
	private JLabel lpuerto;
	private JTextField ipText;
	private JTextField puerText;
	//JPanel panelSur = new JPanel(new GridLayout(1, 2));
	
	public PanelVentanaServidor() throws UnknownHostException {
		texto = new JTextField(InetAddress.getLocalHost().getHostName().toString());
		texto.setEnabled(false);
		
		
		lblServer = new JLabel("SERVIDOR");
		lblServer.setForeground(SystemColor.textHighlight);
		lblServer.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 18));
		
		lblIp = new JLabel("IP");
		lblIp.setEnabled(true);
		
		lpuerto = new JLabel("Puerto");
		lpuerto.setEnabled(true);
		
		ipText=new JTextField("9836");
		ipText.setEnabled(false);
		
		puerText=new JTextField(InetAddress.getLocalHost().getHostAddress().toString());
		puerText.setEnabled(false);
		
		botonSalir = new JButton("Apagar servidor");
		
		botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaServidor.cerrarServidor();
			}
		});
		add(texto);
		add(botonSalir);
		add(lblServer);
		add(lblIp);
		add(lpuerto);
		add(ipText);
		add(puerText);
	}
	
	public void apagar() {
		botonSalir.setEnabled(false);
		texto.setText("Servidor apagado");
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		texto.setBounds(50, 40, 300, 30);
		botonSalir.setBounds(50, 200, 300, 30);
		lblServer.setBounds(150, 10, 200, 26);
		lblIp.setBounds(50, 100, 100, 14);
		lpuerto.setBounds(50, 130, 100, 14);
		ipText.setBounds(100, 120, 100, 30);
		puerText.setBounds(100, 90, 150, 30);
	}
	
}