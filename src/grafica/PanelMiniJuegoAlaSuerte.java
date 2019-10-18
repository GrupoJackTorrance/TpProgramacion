package grafica;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import logica.Jugador;
import logica.MiniJuegoAlaSuerte;


public class PanelMiniJuegoAlaSuerte extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton boton1=new JButton("1");
	private	JButton boton2=new JButton("2");
	private JButton boton3=new JButton("3");
	private	JButton boton4=new JButton("4");
	private JButton boton5=new JButton("5");
	private	JButton boton6=new JButton("6");
	private JTextPane txtpnEnEsteMinijuego = new JTextPane();
	private JButton btnAceptar = new JButton("Jugar");
	private JLabel dado = new JLabel("");
	private JLabel dadoresultado= new JLabel("");
	private JButton botonAceptarResultados=new JButton("Continuar con MarioParty!");
	private JLabel textoResultado=new JLabel("Resultados:");
	private JLabel elijaNum= new JLabel("seleccione un número:");
	private JLabel jugador1=new JLabel("");
	private JLabel jugador2=new JLabel("");
	private JLabel jugador3=new JLabel("");
	private JLabel jugador4=new JLabel("");
	private JLabel turnoJugador=new JLabel("");
	private JLabel turnoDe=new JLabel("Turno de:");
	private ImageIcon imagen = new ImageIcon(getClass().getResource("../fondo.jpg"));
	private MiniJuegoAlaSuerte mini;
	

	public PanelMiniJuegoAlaSuerte(MiniJuegoAlaSuerte mini) {
		this.mini=mini;
		add(boton1);
		add(boton2);
		add(boton3);
		add(boton4);
		add(boton5);
		add(boton6);
		
		//Boton para aceptar la modalidad y pasar al mini juego
		add(btnAceptar);
		
		
		add(botonAceptarResultados);
		
		//Texto de Modalidad
		
		add(txtpnEnEsteMinijuego, BorderLayout.CENTER);
		
		//dado giratorio
		add(dado);
		add(dadoresultado);
		
		add(textoResultado);
		add(elijaNum);
		add(turnoJugador);
		add(jugador1);
		add(jugador2);
		add(jugador3);
		add(jugador4);
		add(turnoDe);
		
		visibilizarModalidad();
		Botones botonesListener=new Botones();
		btnAceptar.addActionListener(botonesListener);
		boton1.addActionListener(botonesListener);
		boton2.addActionListener(botonesListener);
		boton3.addActionListener(botonesListener);
		boton4.addActionListener(botonesListener);
		boton5.addActionListener(botonesListener);
		boton6.addActionListener(botonesListener);
		botonAceptarResultados.addActionListener(botonesListener);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		txtpnEnEsteMinijuego.setFont(new Font("Courier",Font.BOLD,20));
		txtpnEnEsteMinijuego.setLocation(20, 50);
		btnAceptar.setLocation(400,300);
		btnAceptar.setSize(100, 50);
		
	    turnoJugador.setFont(new Font("Courier",Font.BOLD,45));
	    elijaNum.setFont(new Font("Courier",Font.BOLD,45));
	    turnoDe.setFont(new Font("Courier",Font.BOLD,45));	
	    turnoDe.setForeground(new Color(102, 204, 204));
	    turnoDe.setLocation(150,10);
	    turnoJugador.setLocation(450, 10);
	    turnoJugador.setForeground(new Color(102, 204, 204));
	    elijaNum.setLocation(150, 70);
	    elijaNum.setForeground(new Color(102, 204, 204));
		boton1.setLocation(200,150);
		boton2.setLocation(350,150);
		boton3.setLocation(500,150);
		boton4.setLocation(200,250);
		boton5.setLocation(350,250);
		boton6.setLocation(500,250);
		boton1.setSize(100, 50);
		boton2.setSize(100, 50);
		boton3.setSize(100, 50);
		boton4.setSize(100, 50);
		boton5.setSize(100, 50);
		boton6.setSize(100, 50);
		
		btnAceptar.setBackground(SystemColor.window);
		btnAceptar.setIcon(new ImageIcon("1234.jpg"));
		
		dado.setOpaque(false);
		dado.setIcon(new ImageIcon("dado.gif"));
		dado.setLocation(700,000);
		
		
		dadoresultado.setOpaque(false);
		dadoresultado.setLocation(700,00);
		dadoresultado.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		dadoresultado.setForeground(new Color(102, 204, 204));
		
		Dimension height = getSize();
		g.drawImage(imagen.getImage(), 0, 0, height.width, height.height, null);
		
		txtpnEnEsteMinijuego.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		txtpnEnEsteMinijuego.setForeground(new Color(102, 204, 204));
		txtpnEnEsteMinijuego.setText("A la Suerte:\r\nEn este minijuego se va a sortear un numero \r\ndel 1 al 6. Cada jugador debe ingresar un \r\nn\u00FAmero: los jugadores que acierten \r\nvan a recirbir puntos, \r\nlos que no aciertan van a perder puntos. \r\nSi nadie acierta, no reciben castigo.\r\nEl jugador que no ingrese un n\u00FAmero\r\nva a ser castigado con la quita de \r\npuntos");
		txtpnEnEsteMinijuego.setOpaque(false);
		
		jugador1.setFont(new Font("Courier",Font.BOLD,35));
		jugador2.setFont(new Font("Courier",Font.BOLD,35));
		jugador3.setFont(new Font("Courier",Font.BOLD,35));
		jugador4.setFont(new Font("Courier",Font.BOLD,35));
		
		textoResultado.setFont(new Font("Courier",Font.BOLD,40));
		textoResultado.setForeground(new Color(102, 204, 204));
		textoResultado.setLocation(200,50);
		
		jugador1.setLocation(200,100);
		jugador2.setLocation(200,150);
		jugador3.setLocation(200,200);
		jugador4.setLocation(200,250);
		jugador1.setForeground(new Color(102, 204, 204));
		jugador2.setForeground(new Color(102, 204, 204));
		jugador3.setForeground(new Color(102, 204, 204));
		jugador4.setForeground(new Color(102, 204, 204));
		botonAceptarResultados.setLocation(500, 175);
		botonAceptarResultados.setSize(200, 50);
		
	}
	class Botones implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==btnAceptar) {
				visibilizarMiniJuego();
			}
			if(e.getSource()==boton1) {
				mini.agregarNumero(1);
				repaint();
				
			}
			 if(e.getSource()==boton2) {
				mini.agregarNumero(2);
				repaint();
			
			}
			if(e.getSource()==boton3) {
				mini.agregarNumero(3);
				repaint();
				
			}
			if(e.getSource()==boton4) {
				mini.agregarNumero(4);
				repaint();
				
			}
		if(e.getSource()==boton5) {
				mini.agregarNumero(5);
				repaint();
				
			}
			if(e.getSource()==boton6) {
				mini.agregarNumero(6);
				repaint();
			}
			if(e.getSource()==botonAceptarResultados) {
				mini.cerrarMiniJuego();
				synchronized(mini){
						mini.notify();
				}
			}
			
		}
		
	}
	
	public void visibilizarModalidad() {
		System.out.println("mostrando modalidad");
		txtpnEnEsteMinijuego.setVisible(true);
		boton1.setVisible(false);
		boton2.setVisible(false);
		boton3.setVisible(false);
		boton4.setVisible(false);
		boton5.setVisible(false);
		boton6.setVisible(false);
		dado.setVisible(false);
		botonAceptarResultados.setVisible(false);
		textoResultado.setVisible(false);
		elijaNum.setVisible(false);
		jugador1.setVisible(false);
		jugador2.setVisible(false);
		jugador3.setVisible(false);
		jugador4.setVisible(false);
		turnoJugador.setVisible(false);
		turnoDe.setVisible(false);
	
		
	}
	private void visibilizarMiniJuego() {
		System.out.println("mostrando minijuego");
		btnAceptar.setVisible(false);
		boton1.setVisible(true);
		boton2.setVisible(true);
		boton3.setVisible(true);
		boton4.setVisible(true);
		boton5.setVisible(true);
		boton6.setVisible(true);
		dado.setVisible(true);
		botonAceptarResultados.setVisible(false);
		txtpnEnEsteMinijuego.setVisible(false);
		elijaNum.setVisible(true);
		turnoJugador.setVisible(true);
		turnoDe.setVisible(true);
	}
	
	public void mostrarNumeroSorteado(int numeroSorteado) {
		System.out.println("mostrando resultados, el numero sorteado fue el :" +numeroSorteado);
		dadoresultado.setVisible(true);
		boton1.setVisible(false);
		boton2.setVisible(false);
		boton3.setVisible(false);
		boton4.setVisible(false);
		boton5.setVisible(false);
		boton6.setVisible(false);
		btnAceptar.setVisible(false);
		dado.setVisible(false);
		botonAceptarResultados.setVisible(true);
		textoResultado.setVisible(true);
		elijaNum.setVisible(false);
		turnoDe.setVisible(false);
	}
	
	public void mostrarResultados(List<Jugador> jugadores) {
			jugador1.setText(jugadores.get(0).getNombre() +" "+jugadores.get(0).getPuntos());
			jugador2.setText(jugadores.get(1).getNombre()+" "+jugadores.get(1).getPuntos());
			jugador1.setVisible(true);
			jugador2.setVisible(true);
			turnoJugador.setVisible(false);
			if(jugadores.size()>2) {
				jugador3.setText(jugadores.get(2).getNombre()+" "+jugadores.get(2).getPuntos());
				jugador3.setVisible(true);
				if(jugadores.size()>3) {
					jugador4.setText(jugadores.get(3).getNombre()+" "+jugadores.get(3).getPuntos());
				jugador4.setVisible(true);
				}
			}
	}
	
	public void mostrarNumero(int numeroSorteado) {
		dadoresultado.setText("El numero sorteado fue: "+numeroSorteado+" ");
	}
	
	public void setearNombreDeTurnoJugador(String nombre) {
		turnoJugador.setText(nombre);
	}
	
	
}
