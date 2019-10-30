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
import javax.swing.JTextPane;

import logica.Jugador;
import logica.MiniJuegoNoExplotes;

public class PanelMiniJuegoNE extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int inter;
	

	private JButton boton1 = new JButton("1");
	private JButton boton2 = new JButton("2");
	private JButton boton3 = new JButton("3");
	private JButton boton4 = new JButton("4");
	private JButton boton5 = new JButton("5");
	private JButton boton6 = new JButton("6");
	private JButton boton7 = new JButton("7");
	private JButton boton8 = new JButton("8");
	private JButton boton9 = new JButton("9");
	private JTextPane txtpnEnEsteMinijuego = new JTextPane();
	private JButton btnAceptar = new JButton("Jugar");
	private JButton botonAceptarResultados = new JButton(
			"Continuar con MarioParty!");
	private JLabel textoResultado = new JLabel("Resultados:");
	private JLabel elijaNum = new JLabel("seleccione un detonador:");
	private JLabel jugador1 = new JLabel("");
	private JLabel jugador2 = new JLabel("");
	private JLabel jugador3 = new JLabel("");
	private JLabel jugador4 = new JLabel("");
	private JLabel turnoJugador = new JLabel("");
	private JLabel turnoDe = new JLabel("Turno de:");
	private Image img = null;

	private MiniJuegoNoExplotes mini;
	private int bandera = 0;
	private JLabel tiempo = new JLabel("");
	private int tiempor=7; 

	public PanelMiniJuegoNE(MiniJuegoNoExplotes mini2) {
		this.mini = mini2;
		add(boton1);
		add(boton2);
		add(boton3);
		add(boton4);
		add(boton5);
		add(boton6);
		add(boton7);
		add(boton8);
		add(boton9);
		try {
			img = ImageIO.read(new File("./fondos/fondo.jpg"));
		} catch (IOException e) {
			System.out
					.println("no se encuentra la imagen para el fondo de miniJuego A la suerte");

		}

		// Boton para aceptar la modalidad y pasar al mini juego
		add(btnAceptar);

		add(botonAceptarResultados);

		// Texto de Modalidad

		add(txtpnEnEsteMinijuego, BorderLayout.CENTER);


		add(textoResultado);
		add(elijaNum);
		add(turnoJugador);
		add(jugador1);
		add(jugador2);
		add(jugador3);
		add(jugador4);
		add(turnoDe);
		
		add(tiempo);

		visibilizarModalidad();
		Botones botonesListener = new Botones();
		btnAceptar.addActionListener(botonesListener);
		boton1.addActionListener(botonesListener);
		boton2.addActionListener(botonesListener);
		boton3.addActionListener(botonesListener);
		boton4.addActionListener(botonesListener);
		boton5.addActionListener(botonesListener);
		boton6.addActionListener(botonesListener);
		boton7.addActionListener(botonesListener);
		boton8.addActionListener(botonesListener);
		boton9.addActionListener(botonesListener);
		botonAceptarResultados.addActionListener(botonesListener);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		txtpnEnEsteMinijuego.setFont(new Font("Courier", Font.BOLD, 20));
		txtpnEnEsteMinijuego.setLocation(20, 50);
		btnAceptar.setLocation(400, 300);
		btnAceptar.setSize(100, 50);

		turnoJugador.setFont(new Font("Courier", Font.BOLD, 45));
		elijaNum.setFont(new Font("Courier", Font.BOLD, 45));
		turnoDe.setFont(new Font("Courier", Font.BOLD, 45));
		turnoDe.setForeground(new Color(102, 204, 204));
		turnoDe.setLocation(150, 10);
		turnoJugador.setLocation(450, 10);
		turnoJugador.setForeground(new Color(102, 204, 204));
		elijaNum.setLocation(150, 70);
		elijaNum.setForeground(new Color(102, 204, 204));
		boton1.setLocation(200, 150);
		boton2.setLocation(350, 150);
		boton3.setLocation(500, 150);
		boton4.setLocation(200, 250);
		boton5.setLocation(350, 250);
		boton6.setLocation(500, 250);
		boton7.setLocation(200, 350);
		boton8.setLocation(350, 350);
		boton9.setLocation(500, 350);
		boton1.setSize(100, 50);
		boton2.setSize(100, 50);
		boton3.setSize(100, 50);
		boton4.setSize(100, 50);
		boton5.setSize(100, 50);
		boton6.setSize(100, 50);
		boton7.setSize(100, 50);
		boton8.setSize(100, 50);
		boton9.setSize(100, 50);

		tiempo.setFont(new Font("Courier",Font.BOLD,45));
		tiempo.setLocation(150, 450);
	    tiempo.setForeground(Color.RED);
	    
		btnAceptar.setBackground(SystemColor.window);
		btnAceptar.setIcon(new ImageIcon("1234.jpg"));

		Dimension height = getSize();
		g.drawImage(img, 0, 0, height.width, height.height, null);

		txtpnEnEsteMinijuego.setFont(new Font("Tahoma",
				Font.BOLD | Font.ITALIC, 20));
		txtpnEnEsteMinijuego.setForeground(new Color(102, 204, 204));
		txtpnEnEsteMinijuego
				.setText("En este minijuego, la meta es no explotar!\n"
						+ "Cada jugador tomara turnos eligiendo un detonador.\n"
						+ "-Cada detonador tiene una posibilidad de hacer explotar la dinamita que tiene\n"
						+ "-Los ultimos jugadores vivos ganaran puntos. Los que explotan perderan puntos.\n"
						+ "-El jugador que no elige a tiempo será castigado con la quita de puntos");
		txtpnEnEsteMinijuego.setOpaque(false);

		jugador1.setFont(new Font("Courier", Font.BOLD, 35));
		jugador2.setFont(new Font("Courier", Font.BOLD, 35));
		jugador3.setFont(new Font("Courier", Font.BOLD, 35));
		jugador4.setFont(new Font("Courier", Font.BOLD, 35));

		textoResultado.setFont(new Font("Courier", Font.BOLD, 40));
		textoResultado.setForeground(new Color(102, 204, 204));
		textoResultado.setLocation(200, 50);

		jugador1.setLocation(200, 100);
		jugador2.setLocation(200, 150);
		jugador3.setLocation(200, 200);
		jugador4.setLocation(200, 250);
		jugador1.setForeground(new Color(102, 204, 204));
		jugador2.setForeground(new Color(102, 204, 204));
		jugador3.setForeground(new Color(102, 204, 204));
		jugador4.setForeground(new Color(102, 204, 204));
		botonAceptarResultados.setLocation(500, 175);
		botonAceptarResultados.setSize(200, 50);

	}

	class Botones implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnAceptar) {
				visibilizarMiniJuego();
			}
			if (e.getSource() == boton1) {
				mini.elegirDetonador(1, boton1);
				repaint();

			}
			if (e.getSource() == boton2) {
				mini.elegirDetonador(2, boton2);
				repaint();

			}
			if (e.getSource() == boton3) {
				mini.elegirDetonador(3, boton3);
				repaint();

			}
			if (e.getSource() == boton4) {
				mini.elegirDetonador(4, boton4);
				repaint();

			}
			if (e.getSource() == boton5) {
				mini.elegirDetonador(5, boton5);
				repaint();

			}
			if (e.getSource() == boton6) {
				mini.elegirDetonador(6, boton6);
				repaint();
			}

			if (e.getSource() == boton7) {
				mini.elegirDetonador(7, boton7);
				repaint();
			}

			if (e.getSource() == boton8) {
				mini.elegirDetonador(8, boton8);
				repaint();
			}

			if (e.getSource() == boton9) {
				mini.elegirDetonador(9, boton9);
				repaint();
			}

			if (e.getSource() == botonAceptarResultados) {
				mini.cerrarMiniJuego();
				synchronized (mini) {
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
		boton7.setVisible(false);
		boton8.setVisible(false);
		boton9.setVisible(false);
		tiempo.setVisible(true);
		botonAceptarResultados.setVisible(false);
		textoResultado.setVisible(false);
		elijaNum.setVisible(false);// s
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
		boton7.setVisible(true);
		boton8.setVisible(true);
		boton9.setVisible(true);
		botonAceptarResultados.setVisible(false);
		txtpnEnEsteMinijuego.setVisible(false);
		elijaNum.setVisible(true);
		turnoJugador.setVisible(true);
		turnoDe.setVisible(true);
		setearTiempo(tiempor);
	}

	public void detonadorActivado(JButton boton) {
		boton.setVisible(false);
	}

	public void mostrarResultados(List<Jugador> jugadores) {
		boton1.setVisible(false);
		boton2.setVisible(false);
		boton3.setVisible(false);
		boton4.setVisible(false);
		boton5.setVisible(false);
		boton6.setVisible(false);
		boton7.setVisible(false);
		boton8.setVisible(false);
		boton9.setVisible(false);
		tiempo.setVisible(false);
		botonAceptarResultados.setVisible(true);
		turnoDe.setVisible(false);
		textoResultado.setVisible(true);
		elijaNum.setVisible(false);
		jugador1.setText(jugadores.get(0).getNombre() + " "
				+ jugadores.get(0).getPuntos());
		jugador2.setText(jugadores.get(1).getNombre() + " "
				+ jugadores.get(1).getPuntos());
		jugador1.setVisible(true);
		jugador2.setVisible(true);
		turnoJugador.setVisible(false);
		if (jugadores.size() > 2) {
			jugador3.setText(jugadores.get(2).getNombre() + " "
					+ jugadores.get(2).getPuntos());
			jugador3.setVisible(true);
			if (jugadores.size() > 3) {
				jugador4.setText(jugadores.get(3).getNombre() + " "
						+ jugadores.get(3).getPuntos());
				jugador4.setVisible(true);
			}
		}
	}

	public void setearNombreDeTurnoJugador(String nombre) {
		turnoJugador.setText(nombre);
	}

	public void setearTiempo(int t) {

		Crono(t);
	}
	
	
	public void Crono(int intervalo) {
		Timer timer=new Timer();
		inter=intervalo;
		TimerTask tarea=new TimerTask() {

			@Override
			public void run() {
				
				
				if(inter==0)
				{
					timer.cancel();
					mini.seAcaboElTiempo();
				}

				if(bandera==1)
					{
					timer.cancel();
					bandera=0;
					}
				tiempo.setText("Tiempo: " + inter + " ");
				inter--;
				
			}
			
		};
		timer.scheduleAtFixedRate(tarea, 0, 1000);
}
	public void interrumpirTimer(){
		bandera=1;
	}
}
