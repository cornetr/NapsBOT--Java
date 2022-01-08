package napsBOT;

import java.io.File;
import java.util.Random;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
 
public class Bot {
	
	protected TwitterStream twStream;
	
	public static void main(String[] args) {
		Bot napsBot =new Bot();
		
		napsBot.openTwitter();
	}
	
	void draw() {}
	
	
	void openTwitter() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		
		TwitterStream twStream = new TwitterStreamFactory(cb.build()).getInstance();

		FilterQuery fq =new FilterQuery();
		
		String keywords[]={
				"@NapsBot1"};
		
		fq.track(keywords);
		
		twStream.addListener(st);
		
		if(keywords.length==0) {
			twStream.sample();
		}else twStream.filter(fq);		
		
	}
	
	
	StatusListener st = new StatusListener() {
		@Override
		public void onStatus(Status status) {
			
			
			
			  System.out.println("onStatus @" + status.getUser().getScreenName() + " - "+ status.getText());
			  
			  Twitter tf = new TwitterFactory().getInstance();
			  
			  String response="Gamberge...";
			  			  
			  StatusUpdate st = new StatusUpdate("@"+status.getUser().getScreenName()+" "+response);
			  
			  int wichImage;
			  Random r=new Random();
			  wichImage=r.nextInt(26); // nombre d'images + 1
			  
			  File img = new File("banque/"+wichImage+".jpg");
			  
			  st.setMedia(img);
			  
			  st.inReplyToStatusId(status.getId());
			  
			  try {
			    tf.updateStatus(st);
			    
			  } catch (TwitterException e) {
			    e.printStackTrace();
			  }
			
			
		}
		@Override
		public void onException(Exception e) {
			System.out.println("Exception : "+e);
			
		}
		
		@Override
		public void onTrackLimitationNotice(int l) {
			System.out.println("Track limitation notice : "+l);
			
		}
		
		@Override
		public void onStallWarning(StallWarning warning) {
			System.out.println("got stall warning : "+warning);
			
		}
		
		@Override
		public void onScrubGeo(long userId, long upToStatusId) {
			System.out.println("scrub notice : "+userId+", up to status id : "+upToStatusId);
			
		}
		
		@Override
		public void onDeletionNotice(StatusDeletionNotice s) {
			System.out.println("status deletion notice : "+s);
			
		}
	};
	

};
