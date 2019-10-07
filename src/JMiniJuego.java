import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JList;

public class JMiniJuego{

	private JFrame frame;
	 Minijuego mini;
	static List<Jugador> jugadores;
	JButton boton1 ;
	JButton boton2 ;
	JButton boton3 ;
	JButton boton4 ;
	JButton boton5 ;
	JButton boton6 ;
	JLabel lblSeleccioneUnNmero;
	GroupLayout groupLayout;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JMiniJuego window = new JMiniJuego(jugadores);
					window.frame.setVisible(true);
				
					
				} catch (Exception e) {
        					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JMiniJuego(List<Jugador> jugadores) {
		this.jugadores=jugadores;
		mini=new Minijuego(jugadores);
		mini.numeroSorteado=mini.sortearNumero();
		initialize();
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		System.out.println(mini.numeroSorteado);
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		boton1 = new JButton("1");
		boton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(mini.agregarNumero(1)==false) {
				mostrarResultados();
				
			}
			System.out.println("1");
			}
		});
		
		boton2 = new JButton("2");
		boton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mini.agregarNumero(2)==false) {
					mostrarResultados();
					
				}
				System.out.println("2");
			}
		});
		
		boton3 = new JButton("3");
		boton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mini.agregarNumero(3)==false) {
					mostrarResultados();
					
				}
				System.out.println("3");
			}
		});
		
		boton4 = new JButton("4");
		boton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mini.agregarNumero(4)==false) {
					mostrarResultados();
					
				}
				System.out.println("4");
			}
		});
		
		boton5 = new JButton("5");
		boton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mini.agregarNumero(5)==false) {
					mostrarResultados();
					
				}
				System.out.println("5");
			}
		});
		
		boton6 = new JButton("6");
		boton6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mini.agregarNumero(6)==false) {
					mostrarResultados();
					
				}
				System.out.println("6");
			}
		});
		
		JLayeredPane layeredPane = new JLayeredPane();
		
		lblSeleccioneUnNmero = new JLabel("seleccione un n\u00FAmero:");
		groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(72)
							.addComponent(boton4)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(boton5)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(boton6))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(73)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSeleccioneUnNmero)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(boton1)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(boton2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(boton3)))))
					.addContainerGap(228, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(236, Short.MAX_VALUE)
					.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(197))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(123)
					.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
					.addComponent(lblSeleccioneUnNmero)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(boton1)
						.addComponent(boton2)
						.addComponent(boton3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(boton5)
						.addComponent(boton6)
						.addComponent(boton4))
					.addGap(52))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	public void mostrarResultados() {
		boton1.setVisible(false);
		boton2.setVisible(false);
		boton3.setVisible(false);
		boton4.setVisible(false);
		boton5.setVisible(false);
		boton6.setVisible(false);
		String mensaje="Resultados:\n";
		for (Jugador jugador : jugadores) {
			mensaje=mensaje+" " +jugador.getNombre()+" "+jugador.getPuntos();
		}
		
		
		lblSeleccioneUnNmero.setText(mensaje);
		lblSeleccioneUnNmero.repaint();
	}
}
