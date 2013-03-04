import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public  class UpdateScoreBoardTimer 
{
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private ScoreBoardJFrame theBoard;
    ScheduledFuture<?> periodicTriggerHandle;
    
    public void setBoard(ScoreBoardJFrame aBoard)
    {
    	theBoard = aBoard;
    };
    
    public void start() 
    {
        final Runnable periodicTrigger = new Runnable() 
        {
        	public void run() 
            { 
        		//ScoreBoardEvent event = new ScoreBoardEvent(this);
        		System.out.println("Trigger scoreboard");         	    
        	    theBoard.handleScoreBoardEvent(/*event*/);
        	    
            }
        };
        //periodicTriggerHandle = scheduler.scheduleAtFixedRate(periodicTrigger, 1000, 1000, TimeUnit.MILLISECONDS);
        periodicTriggerHandle = scheduler.schedule(periodicTrigger, 1000, TimeUnit.MILLISECONDS);
    };
    
    public void stop()
    {
    	periodicTriggerHandle.cancel(true); 
    };   
 }

