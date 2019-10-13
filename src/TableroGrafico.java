import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TableroGrafico extends JFrame{
	private int resp = 2;
	JPanel pane = new JPanel();
	JButton ataca = new JButton("Atacar");
	JButton noAtaca = new JButton("No hacer nada");
	JLabel cronometro= new JLabel();
	JLabel pregunta= new JLabel();
	String mensaje;

	
	
	public TableroGrafico() {
		this.resp = 2;
		pane.setLayout(null);
		pane.add(pregunta);
		pane.add(ataca);
		pane.add(noAtaca);
		pane.add(cronometro);
		this.getContentPane().add(pane);
		this.setVisible(true);
		this.setResizable(false);
		this.setTitle("Ventana");
		mensaje = "tiempo restante: ";
	}

	public int deseaAtacarVentana() throws InterruptedException {
		this.setBounds(100, 100, 400, 400);
		pane.setBounds(0, 0, 400, 400);
		ataca.setBounds(50, 150, 100, 30);
		noAtaca.setBounds(200, 150, 150, 30);
		pregunta.setBounds(25, 100, 350, 30);
		cronometro.setBounds(5, 5, 150, 30);
		
		int cont = 10;
		Botones escuchador = new Botones();
		ataca.addActionListener(escuchador);
		noAtaca.addActionListener(escuchador);
		cronometro.setText(mensaje);
		pregunta.setText("Debe seleccionar una opción antes de finalizar el tiempo");
		while(cont>=0 && resp==2) {
			Thread.sleep(1000);
			cronometro.setText(mensaje + cont);
			if(cont==3)
				mensaje = "TE QUEDA POCO TIEMPO!! ";
			cont--;
		}
		//resp = JOptionPane.showConfirmDialog(null, "¿Desea atacar?\r\n" + "tiempo: " + cont, "ATAQUE", JOptionPane.YES_NO_OPTION);// si = 0, no = 1, cancelar = 2

		this.setVisible(false);
		dispose();
		System.out.println(resp);
		return resp;

	}
	
	class Botones implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Object click = e.getSource();
			if(click == ataca) {
				resp = 1;
			}
			
			if(click == noAtaca) {
				resp = 0;
			}
		}
	}
	

}
