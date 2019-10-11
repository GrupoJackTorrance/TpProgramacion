import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PanelMiniJuegoAlaSuerte extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private	JButton botonAceptarModalidad=new JButton("aceptar");
	private JButton boton1=new JButton("1");
	private	JButton boton2=new JButton("2");
	private JButton boton3=new JButton("3");
	private	JButton boton4=new JButton("4");
	private JButton boton5=new JButton("5");
	private	JButton boton6=new JButton("6");
	private JLabel texto1=new JLabel("En este minijuego se va a sortear un numero del 1 al 6.");
	private JLabel texto2=new JLabel("Cada jugador debe ingresar un número:\n");
	private JLabel texto3=new JLabel("-los jugadores que acierten van a recirbir puntos, los que "
			+ "no aciertan van a perder puntos.\n");
	private JLabel texto4=new JLabel("-si nadie acierta, no reciben castigo.\n");
	private JLabel texto5=new JLabel("-El jugador que no ingrese un número va a ser castigado con la quita de puntos");
	private JButton botonAceptarResultados=new JButton("Aceptar");
	private JLabel textoResultado=new JLabel("Resultados:");
	private JLabel elijaNum= new JLabel("seleccione un número:");
	private JLabel jugador1=new JLabel("");
	private JLabel jugador2=new JLabel("");
	private JLabel jugador3=new JLabel("");
	private JLabel jugador4=new JLabel("");
	private JLabel turnoJugador=new JLabel("");
	private JLabel turnoDe=new JLabel("Turno de:");
	
	private MiniJuegoAlaSuerte mini;
	

	public  PanelMiniJuegoAlaSuerte(MiniJuegoAlaSuerte mini) {
		this.mini=mini;
		add(boton1);
		add(boton2);
		add(boton3);
		add(boton4);
		add(boton5);
		add(boton6);
		
		
		botonAceptarModalidad.setLocation(0, 10000);
		add(botonAceptarModalidad);
		
		add(botonAceptarResultados);
		add(texto1);
		add(texto2);
		add(texto3);
		add(texto4);
		add(texto5);
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
		botonAceptarModalidad.addActionListener(botonesListener);
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
	
	    texto1.setFont(new Font("Courier",Font.BOLD,17));
		texto2.setFont(new Font("Courier",Font.BOLD,17));
		texto3.setFont(new Font("Courier",Font.BOLD,17));
		texto4.setFont(new Font("Courier",Font.BOLD,17));
		texto5.setFont(new Font("Courier",Font.BOLD,17));
		texto1.setLocation(20, 50);
		texto2.setLocation(20, 90);
		texto3.setLocation(20, 130);
		texto4.setLocation(20, 170);
		texto5.setLocation(20, 210);
		botonAceptarModalidad.setLocation(400,250);
		botonAceptarModalidad.setSize(100, 50);
	
	    turnoJugador.setFont(new Font("Courier",Font.BOLD,45));
	    elijaNum.setFont(new Font("Courier",Font.BOLD,45));
	    turnoDe.setFont(new Font("Courier",Font.BOLD,45));			
	    turnoDe.setLocation(150,10);
	    turnoJugador.setLocation(450, 10);
	    elijaNum.setLocation(150, 70);
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
		
		
		jugador1.setFont(new Font("Courier",Font.BOLD,35));
		jugador2.setFont(new Font("Courier",Font.BOLD,35));
		jugador3.setFont(new Font("Courier",Font.BOLD,35));
		jugador4.setFont(new Font("Courier",Font.BOLD,35));
		textoResultado.setFont(new Font("Courier",Font.BOLD,50));
		jugador1.setLocation(200,100);
		jugador2.setLocation(200,150);
		jugador3.setLocation(200,200);
		jugador4.setLocation(200,250);
		botonAceptarResultados.setLocation(500, 175);
		botonAceptarResultados.setSize(100, 50);
		
	}
	class Botones implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==botonAceptarModalidad) {
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
				System.exit(0);
			}
			
		}
		
	}
	private void visibilizarModalidad() {
		System.out.println("mostrando modalidad");
		texto1.setVisible(true);
		texto2.setVisible(true);
		texto3.setVisible(true);
		texto4.setVisible(true);
		texto5.setVisible(true);
		boton1.setVisible(false);
		boton2.setVisible(false);
		boton3.setVisible(false);
		boton4.setVisible(false);
		boton5.setVisible(false);
		boton6.setVisible(false);
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
		botonAceptarModalidad.setVisible(false);
		boton1.setVisible(true);
		boton2.setVisible(true);
		boton3.setVisible(true);
		boton4.setVisible(true);
		boton5.setVisible(true);
		boton6.setVisible(true);
		botonAceptarResultados.setVisible(false);
		texto1.setVisible(false);
		texto2.setVisible(false);
		texto3.setVisible(false);
		texto4.setVisible(false);
		texto5.setVisible(false);
		elijaNum.setVisible(true);
		turnoJugador.setVisible(true);
		turnoDe.setVisible(true);
	}
	public void mostrarNumeroSorteado(int numeroSorteado) {
		System.out.println("mostrando resultados, el numero sorteado fue el :" +numeroSorteado);
		boton1.setVisible(false);
		boton2.setVisible(false);
		boton3.setVisible(false);
		boton4.setVisible(false);
		boton5.setVisible(false);
		boton6.setVisible(false);
		botonAceptarModalidad.setVisible(false);
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
	public void setearNombreDeTurnoJugador(String nombre) {
		turnoJugador.setText(nombre);
		
	}
}
