package grafica;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Personaje;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class JVentanaTablero extends JFrame{
	private PanelTablero contentPanel;
	private JLabel lbl, lblUser;
	
	//EN PRINCIPIO ESTA CLASE NO SE ESTÁ USANDO. LA DEJO POR LAS DUDAS.
	public JVentanaTablero(String user) {
		//setModal(true);
		contentPanel = new PanelTablero();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lbl = new JLabel("Que comience el juego");
		lbl.setBounds(10, 14, 300, 50);
		contentPanel.add(lbl);
		
		lblUser = new JLabel(user);
		lblUser.setBounds(5, 64, 200, 50);
		contentPanel.add(lblUser);
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				//setMovimiento(arg0);
			}
		});
		
		this.setVisible(true);
	}
	
//	public void setMovimiento(KeyEvent evento){
//		Personaje nuevo = contentPanel.getPersonaje();
//		
//		if(evento.getKeyCode() == KeyEvent.VK_LEFT) {
//			nuevo.desplazarHorizontalmente(-5);
//			contentPanel.moverPersonaje(nuevo);
//		}
//		if(evento.getKeyCode() == KeyEvent.VK_RIGHT) {
//			nuevo.desplazarHorizontalmente(5);
//			contentPanel.moverPersonaje(nuevo);
//		}
//		if(evento.getKeyCode() == KeyEvent.VK_UP) {
//			nuevo.desplazarVerticalmente(-5);
//			contentPanel.moverPersonaje(nuevo);
//		}
//		if(evento.getKeyCode() == KeyEvent.VK_DOWN) {
//			nuevo.desplazarVerticalmente(5);
//			contentPanel.moverPersonaje(nuevo);
//		}
//		repaint();
//	}
	
	public void reubicarPersonaje(Personaje nuevo){
		//Personaje nuevo = contentPanel.getPersonaje();
		
		contentPanel.moverPersonaje(nuevo);

		repaint();
	}
	
}