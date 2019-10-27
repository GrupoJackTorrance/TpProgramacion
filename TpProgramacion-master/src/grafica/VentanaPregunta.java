package grafica;
import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logica.EfectoDarObjeto;
import logica.Jugador;
import logica.ObjDescuentaPuntos;
import logica.ObjDuplicarPuntaje;
import logica.ObjRobarPuntos;

public class VentanaPregunta extends JFrame{

	private static final long serialVersionUID = 1L;
	private PanelPregunta panelPregunta;
	
	public VentanaPregunta() {
		this.setBounds(200, 200, 600, 500);
		setTitle("Ataque");
		this.panelPregunta=new PanelPregunta();
		getContentPane().add(panelPregunta);
		add(panelPregunta);
		setResizable(false);
	}
	
	public int ataque (Jugador jugador) throws InterruptedException {
		int resp = panelPregunta.deseaAtacarVentana(jugador);
		dispose();
		return resp;
	}
}

	class PanelPregunta extends JPanel {

		private static final long serialVersionUID = 1L;
		private int resp;
		JButton noAtaca = new JButton("No hacer nada");
		JLabel cronometro= new JLabel();
		JLabel pregunta= new JLabel();
		JButton btnObjetoDescontarPuntos = new JButton();
		JButton btnObjetoRobarPuntos = new JButton();
		JButton btnObjetoDuplicarPuntaje = new JButton();
		String mensaje;
		
		public PanelPregunta() {
			this.resp = 999;
			this.setLayout(null);
			btnObjetoDuplicarPuntaje.setEnabled(false);
			btnObjetoRobarPuntos.setEnabled(false);
			btnObjetoDescontarPuntos.setEnabled(false);
			this.add(pregunta);
			this.add(noAtaca);
			this.add(cronometro);
			this.add(btnObjetoDuplicarPuntaje);
			this.add(btnObjetoDescontarPuntos);
			this.add(btnObjetoRobarPuntos);
			Botones escuchador = new Botones();
			btnObjetoDuplicarPuntaje.addActionListener(escuchador);
			btnObjetoRobarPuntos.addActionListener(escuchador);
			btnObjetoDescontarPuntos.addActionListener(escuchador);
			noAtaca.addActionListener(escuchador);
			this.setVisible(true);
		}
		
		public void paintComponent(Graphics grafico) {
			super.paintComponent(grafico);
			this.setBounds(0, 0, 600, 500);
			
			btnObjetoDuplicarPuntaje.setBounds(50, 400, 100, 30);
			btnObjetoDuplicarPuntaje.setBackground(SystemColor.window);


			cronometro.setBounds(25, 25, 300, 30);
			cronometro.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20 ));
			cronometro.setForeground(new Color(0, 0, 0));
			
			pregunta.setBounds(15, 70, 350, 50);
			pregunta.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
			pregunta.setForeground(new Color(0, 0, 0));
						
			btnObjetoDuplicarPuntaje.setBounds(100, 150, 400, 30);
			btnObjetoDuplicarPuntaje.setBackground(SystemColor.window);
			
			btnObjetoRobarPuntos.setBounds(100, 200, 400, 30);
			btnObjetoRobarPuntos.setBackground(SystemColor.window);

			btnObjetoDescontarPuntos.setBounds(100, 250, 400, 30);
			btnObjetoDescontarPuntos.setBackground(SystemColor.window);

			noAtaca.setBounds(100, 300, 400, 30);
			noAtaca.setBackground(SystemColor.window);
			
			
			//Se selecciona la imagen que tenemos en el paquete de la //ruta del programa
			Image img=null;
			try {
				img = ImageIO.read(new File("./fondos/fondo_ataque.jpg"));
			} catch (IOException e) {
				System.out.println("no se encuentra la imagen para el fondo de Resultados");

			}
			//se dibuja la imagen que tenemos en el paquete Images //dentro de un panel
			grafico.drawImage(img, 0, 0, 600, 600, null);
			setOpaque(false);
		}
		
		public int deseaAtacarVentana(Jugador jugador) throws InterruptedException {
			
			int cont = 10;
			mensaje = "TIEMPO RESTANTE: ";
			ObjDescuentaPuntos obj1 =  new ObjDescuentaPuntos();
			this.setVisible(true);
			pregunta.setText( jugador.getNombre().toUpperCase() + "\n\r escoja una opción");
			btnObjetoDescontarPuntos.setText("Descuenta " + " -" + obj1.getPuntosParaDescontar() + " puntos al siguiente jugador");
			btnObjetoDuplicarPuntaje.setText("Duplica tus puntos X2");
			btnObjetoRobarPuntos.setText("Roba 8 puntos al siguiente jugador");
			if(jugador.getObj1().getIdObjeto()==1 || jugador.getObj2().getIdObjeto()==1 || jugador.getObj3().getIdObjeto()==1)
				btnObjetoDescontarPuntos.setEnabled(true);
			if(jugador.getObj1().getIdObjeto()==2 || jugador.getObj2().getIdObjeto()==2 || jugador.getObj3().getIdObjeto()==2)
				btnObjetoRobarPuntos.setEnabled(true);
			if(jugador.getObj1().getIdObjeto()==3 || jugador.getObj2().getIdObjeto()==3 || jugador.getObj3().getIdObjeto()==3)
				btnObjetoDuplicarPuntaje.setEnabled(true);
			
			while(cont>=0 && resp==999) {
				Thread.sleep(1000);
				cronometro.setText(mensaje + cont);
				if(cont==3)
					mensaje = "TE QUEDA POCO TIEMPO!  ";
				cont--;
			}
			
			this.setVisible(false);
			return resp;
		}
		
		class Botones implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				 Object click = e.getSource();
				 if(click == btnObjetoDescontarPuntos)
					 resp = 1;
				 else if (click == btnObjetoRobarPuntos)
					 resp = 2;
				 else if(click == btnObjetoDuplicarPuntaje)
					 resp = 3;
				 else
					 resp = 0;
			}
		}
	}

