
public class Personaje{
	String nombre;
	private double x, y;
	private int radio;
	
	public Personaje(String nombre, double x, double y, int radio) {
		this.nombre=nombre;
		this.x=x;
		this.y=y;
		this.radio = radio;
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getRadio() {
		return radio;
	}

	public void setRadio(int radio) {
		this.radio = radio;
	}
	
//	public void desplazarHorizontalmente(double delta_x) {
//		this.x+=delta_x;
//	}
//	
//	public void desplazarVerticalmente(double delta_y) {
//		this.y+=delta_y;
//	}

	public void actualizarCoordenadasGraficas(int lugarTableroX, int lugarTableroY) {
		this.x=(double)lugarTableroX;
		this.y=(double)lugarTableroY;
		
		
	}
}
