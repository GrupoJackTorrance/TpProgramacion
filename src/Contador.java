
import java.util.TimerTask;

public class Contador extends TimerTask {
    int seconds;
    
   public Contador(int turno) {
	   this.seconds=60;
   }

	public void run() {
		System.out.println("seg: "+this.seconds);
    	if(this.seconds>=0) {
    		
    		this.seconds = this.seconds -1;
    	}
    	else {
    		this.seconds = -1;
    	}
    
    }
   
		
	public int getSeconds() {
		return this.seconds;
	}

}