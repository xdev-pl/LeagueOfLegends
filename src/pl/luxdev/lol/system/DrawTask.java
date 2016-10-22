package pl.luxdev.lol.system;

public class DrawTask implements Runnable {

	@Override
	public void run() {
		if(DrawSystem.getUsers().size() > 9){
			DrawSystem.shuffle();
		}
	}

}
