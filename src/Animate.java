
public class Animate implements Runnable{

	BlockBreakerPanel bp;
	
	Animate(BlockBreakerPanel b){
		this.bp = b;
	}
	
	
	
	public void run() {
		while(true) {
			bp.update();
			try {
				Thread.sleep(10);
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
	}
	
}
