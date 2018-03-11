package post;

public class Main {

		@SuppressWarnings("resource")
		public static void main(String[] args) throws Exception {
			PostUrl8 post = new PostUrl8();
			
			long begin_time, finish_time;
			begin_time=System.currentTimeMillis();
			
			for(int loop=0;loop<100;loop++){
				new Thread(() -> post.loopA()).start();				
			}

			finish_time=System.currentTimeMillis();
			System.out.println(finish_time-begin_time);
			
		}

}
