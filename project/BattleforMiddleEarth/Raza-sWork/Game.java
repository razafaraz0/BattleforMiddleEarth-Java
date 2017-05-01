package myPart;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.util.concurrent.TimeUnit;




	public class Game extends Canvas implements Runnable  {
		
		private static final long serialVersionUID = -262936385838274399L;
		public static final int WIDTH = 690 , HEIGHT = 690;
		
		private Thread thread;
		private boolean running = false;
		
		protected GameManager gameManager;
		private GameBar health;
		private GameBar armor;

		
		
		public Game()
		{
			gameManager = new GameManager();
			this.addKeyListener(new InputManager(gameManager)); //this says listen for the key events
			new gameScreen(this, "Battle For Middle Earth!" , WIDTH , HEIGHT);
			armor = new GameBar();
			health = new GameBar();
			gameManager.addObject(new Player(340 , 550 , GameIDs.Player , gameManager));
			//gameManager.addObject(new Enemy(0 , 20 , GameIDs.Enemy));
			//gameManager.addObject(gameManager.addEnemy(new Enemy(0 , 20 , GameIDs.Enemy)));
			
	
			
			gameManager.addEnemyObjects(this);
			
			
			//the following method calls random fire after every 1 second
			while(true)
			{
				try {
					TimeUnit.SECONDS.sleep(1);
					gameManager.randomFire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
			
		}
		
		


		public synchronized void start(){

			thread = new Thread(this);
			thread.start();
			setRunning(true);
		}
		
		public synchronized void stop(){

			try{
				thread.join();
				setRunning(false);
			}catch (Exception error){
				error.printStackTrace();// running the error stuff
			}
			
			
		}
		
		public void run(){
			// game loop not typed by ourself
			// riginally written by gameNotch
			
			long lastTime = System.nanoTime();
			double amountOfTicks = 60.0;
			double ns = 1000000000 / amountOfTicks;
			double delta = 0;
			long timer = System.currentTimeMillis();
			int frames = 0;
			while(running)
			{
				this.requestFocus();
				long now = System.nanoTime();
				delta += (now - lastTime) / ns;
				lastTime = now;
				while(delta >= 1)
				{
					tick();
					delta--;
				}
				if(running)
				{
					render();
				}
				frames++;
				
				if(System.currentTimeMillis() - timer > 1000){
					timer += 1000;
				//	System.out.println("FPS"+ frame);
					//frames = 0;
					
				}
				
			}
			stop();
		}
		
		private void render() {
			// TODO Auto-generated method stub
			BufferStrategy 	bs = this.getBufferStrategy();
			if(bs == null)
			{
				this.createBufferStrategy(3); //meaning 3 uffers are created 3 is recommded
				return;
			}
			
			Graphics graphics = bs.getDrawGraphics();
			
			graphics.setColor(Color.BLACK);
			graphics.fillRect(0, 0, WIDTH, HEIGHT);

			gameManager.render(graphics);
			armor.render(graphics);
			health.render(graphics);
			graphics.dispose();
			bs.show();
		}


		private void tick() {
			// TODO Auto-generated method stub
			gameManager.tick(); //update all the elements of the game
			health.tick();
			armor.tick();

		}
		public boolean isRunning() {
			return running;
		}


		public void setRunning(boolean running) {
			this.running = running;
		}
		public static int boundControl(int coordinate , int minBound , int maxBound)
		{
			if(coordinate >= maxBound)
			{
				coordinate = maxBound;
				return coordinate;
			}
			else if(coordinate <= minBound)
			{
				coordinate = minBound;
				return coordinate;
			}
			else
			{
				 return coordinate;
			}
		}
		
		public static void main(String[] args) {
			new Game();
		}
	}


