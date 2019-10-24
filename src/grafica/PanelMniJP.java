package grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;


import logica.Jugador;
import logica.MiniJuegoPalabras;

public class PanelMniJP extends JPanel {

	private static final long serialVersionUID = 1L;

	private static int inte;
	private JTextPane txtpnEnEsteMinijuego = new JTextPane();
	private JTextField campo=new JTextField(40);
	private JButton btnAceptar = new JButton("Jugar");
	private JLabel mayor = new JLabel("");
	private JButton botonAceptarResultados = new JButton("Continuar con MarioParty!");
	private JLabel textoResultado = new JLabel("Resultados:");
	private JLabel Escriba = new JLabel("Escriba :");
	private JLabel jugador1 = new JLabel("");
	private JLabel jugador2 = new JLabel("");
	private JLabel jugador3 = new JLabel("");
	private JLabel jugador4 = new JLabel("");
	private JLabel turnoJugador = new JLabel("");
	private JLabel palabra = new JLabel("");
	private JLabel turnoDe = new JLabel("Turno de:");
	private Image imagen = null;;
	private JLabel tiempo = new JLabel("");
	private JLabel cant = new JLabel("");
	private int bandera = 0;
	private MiniJuegoPalabras mini;
	private int tiempor=20; 

	public PanelMniJP(MiniJuegoPalabras mini) {
		this.mini=mini;
		try {
			imagen = ImageIO.read(new File("./fondos/Surface.jpg"));
		} catch (IOException e) {
			System.out.println("no se encuentra la imagen para el fondo de miniJuego A la suerte");

		}
		add(btnAceptar);
		add(botonAceptarResultados);
	
		add(txtpnEnEsteMinijuego, BorderLayout.CENTER);
		add(mayor);
		add(palabra);
		add(textoResultado);
		add(Escriba);
		add(turnoJugador);
		add(jugador1);
		add(jugador2);
		add(jugador3);
		add(jugador4);
		add(turnoDe);
		add(campo);
		add(tiempo);
		add(cant);
		visibilizarModalidad();
		Botones botonesListener=new Botones();
		Campo CampoListener=new Campo();
		btnAceptar.addActionListener(botonesListener);
		botonAceptarResultados.addActionListener(botonesListener);
		campo.addActionListener(CampoListener);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		txtpnEnEsteMinijuego.setFont(new Font("Courier",Font.BOLD,20));
		txtpnEnEsteMinijuego.setLocation(20, 50);
		btnAceptar.setLocation(400,300);
		btnAceptar.setSize(100, 50);
		turnoJugador.setFont(new Font("Courier",Font.BOLD,45));
		turnoDe.setFont(new Font("Courier",Font.BOLD,45));	
	    turnoDe.setForeground(Color.ORANGE);
	    turnoDe.setLocation(150,10);
	    Escriba.setFont(new Font("Courier",Font.BOLD,45));
	    turnoJugador.setLocation(450, 10);
	    turnoJugador.setForeground(Color.ORANGE);
	    Escriba.setLocation(150, 70);
	    Escriba.setForeground(Color.ORANGE);
	    
	    palabra.setFont(new Font("Courier",Font.BOLD,45));
	    palabra.setLocation(450, 70);
	    palabra.setForeground(Color.ORANGE);
	    
	    btnAceptar.setBackground(SystemColor.window);
		btnAceptar.setIcon(new ImageIcon("1234.jpg"));
		
		mayor.setOpaque(false);
		mayor.setLocation(700,00);
		mayor.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		mayor.setForeground(Color.RED);
		
		
		Dimension height = getSize();
	    g.drawImage(imagen, 0, 0, height.width, height.height, null);
	    
	    txtpnEnEsteMinijuego.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		txtpnEnEsteMinijuego.setForeground(Color.RED);
		txtpnEnEsteMinijuego.setText("Juego de Palabras:\nEste minijuego consiste en escribir exactamente la palabra indicada\nen un tiempo dado:\nLos jugadores que escriban correctamente en tiempo y forma\nvan sumando palabras correctas,\n" + 
								 "si no acierta o se termina el tiempo pasa al siguiente jugador.\n" + 
								 "Gana puntos el que mas palabras correctas escribe.\n" + 
							 "Pierden 1 punto los jugadores que no llegan a escribir la mayoria de palabras correctas.");
		txtpnEnEsteMinijuego.setOpaque(false);
		jugador1.setFont(new Font("Courier",Font.BOLD,35));
		jugador2.setFont(new Font("Courier",Font.BOLD,35));
		jugador3.setFont(new Font("Courier",Font.BOLD,35));
		jugador4.setFont(new Font("Courier",Font.BOLD,35));
		
		textoResultado.setFont(new Font("Courier",Font.BOLD,40));
		textoResultado.setForeground(Color.RED);
		textoResultado.setLocation(200,50);
		
		jugador1.setLocation(200,100);
		jugador2.setLocation(200,150);
		jugador3.setLocation(200,200);
		jugador4.setLocation(200,250);
		jugador1.setForeground(Color.ORANGE);
		jugador2.setForeground(Color.ORANGE);
		jugador3.setForeground(Color.ORANGE);
		jugador4.setForeground(Color.ORANGE);
		tiempo.setFont(new Font("Courier",Font.BOLD,45));
		tiempo.setLocation(150, 200);
	    tiempo.setForeground(Color.RED);
	    cant.setOpaque(false);
		cant.setLocation(10,00);
		cant.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		cant.setForeground(Color.RED);
		botonAceptarResultados.setLocation(500, 175);
		botonAceptarResultados.setSize(200, 50);
		campo.setFont(new Font("Courier",Font.BOLD,40));
		campo.setLocation(120, 150);
		campo.setForeground(Color.RED);
		campo.setBackground(Color.BLUE);
		
	}

	class Botones implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==btnAceptar) {
				visibilizarMiniJuego();
			}
			
			if(e.getSource()==botonAceptarResultados) {
				mini.cerrarMiniJuego();
				synchronized(mini){
						mini.notify();
				}
			}
			
		}
		
	}
	
	class Campo implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==campo) {
				if(campo.getText().equals(palabra.getText())) {
					System.out.println("correcto");
					mini.incrementarContador();
					repaint();
				}else
				{
					mini.Falso();
					bandera=1;
				}
			campo.setText("");
				
			}
			
			
			
		}
		
	}
	public void mostrarNumeroMayor(int numeroMayor) {
		System.out.println("mostrando resultados, la mayor cantidad de palabras fue :" +numeroMayor);
		mayor.setVisible(true);
		
		btnAceptar.setVisible(false);
		
		botonAceptarResultados.setVisible(true);
		textoResultado.setVisible(true);
		Escriba.setVisible(false);
		turnoDe.setVisible(false);
		campo.setVisible(false);
		palabra.setVisible(false);
		tiempo.setVisible(false);
		cant.setVisible(false);
	}
	public void mostrarResultados(List<Jugador> jugadores) {
		jugador1.setText(jugadores.get(0).getNombre() + " " + jugadores.get(0).getPuntos());
		jugador2.setText(jugadores.get(1).getNombre() + " " + jugadores.get(1).getPuntos());
		jugador1.setVisible(true);
		jugador2.setVisible(true);
		turnoJugador.setVisible(false);
		campo.setVisible(false);
		palabra.setVisible(false);
		Escriba.setVisible(false);
		turnoDe.setVisible(false);
		mayor.setVisible(true);
		tiempo.setVisible(false);
		cant.setVisible(false);
		if (jugadores.size() > 2) {
			jugador3.setText(jugadores.get(2).getNombre() + " " + jugadores.get(2).getPuntos());
			jugador3.setVisible(true);
			if (jugadores.size() > 3) {
				jugador4.setText(jugadores.get(3).getNombre() + " " + jugadores.get(3).getPuntos());
				jugador4.setVisible(true);
			}
		}
	}
	
	
	public void visibilizarModalidad() {
		System.out.println("mostrando modalidad");
		txtpnEnEsteMinijuego.setVisible(true);
		campo.setVisible(false);
		botonAceptarResultados.setVisible(false);
		textoResultado.setVisible(false);
		Escriba.setVisible(false);
		jugador1.setVisible(false);
		jugador2.setVisible(false);
		jugador3.setVisible(false);
		jugador4.setVisible(false);
		turnoJugador.setVisible(false);
		turnoDe.setVisible(false);
		palabra.setVisible(false);
		mayor.setVisible(false);
		tiempo.setVisible(false);
		cant.setVisible(false);
	}
	private void visibilizarMiniJuego() {
		System.out.println("mostrando minijuego");
		btnAceptar.setVisible(false);
		mayor.setVisible(true);
		botonAceptarResultados.setVisible(false);
		txtpnEnEsteMinijuego.setVisible(false);
		Escriba.setVisible(true);
		turnoJugador.setVisible(true);
		turnoDe.setVisible(true);
		campo.setVisible(true);
		palabra.setVisible(true);
		tiempo.setVisible(true);
		cant.setVisible(true);
		setearTiempo(tiempor);
	}
	
	public void Crono(int intervalo) {
		Timer timer=new Timer();
		inte=intervalo;
		TimerTask tarea=new TimerTask() {

			@Override
			public void run() {
				
				
				if(inte==0)
				{
					timer.cancel();
					mini.Falso();
				}

				if(bandera==1)
					{
					timer.cancel();
					bandera=0;
					}
				tiempo.setText("Tiempo: " + inte + " ");
				inte--;
				
			}
			
		};
		
		timer.scheduleAtFixedRate(tarea, 0, 1000);
		
	}


	public void mostrarNumero(int numeroMayor) {
		mayor.setText("El numero mayor de palabras es: " + numeroMayor + " ");
	}

	public void setearNombreDeTurnoJugador(String nombre) {
		turnoJugador.setText(nombre);
	}
	public void setearPalabra(String nombre) {
		palabra.setText(nombre);
	}
	
	public void setearTiempo(int t) {
		
		
		Crono(t);
	}
	
	public void setearCantXJ(int c) {
		cant.setText("Cantidad de aciertos: " + c + " ");
	}
	
	
}
