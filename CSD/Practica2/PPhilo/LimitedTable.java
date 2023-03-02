// CSD Mar 2013 Juansa Sendra

public class LimitedTable extends RegularTable { //max 4 in dinning-room
    public int tablePhil = 0;
    
    public LimitedTable(StateManager state) {
        super(state);
    }
    
    public synchronized void enter(int id) throws InterruptedException {
        while(tablePhil == 4){
            state.wenter(id);
            wait();
        }
        tablePhil++;
        state.enter(id);
    }
    
    public synchronized void exit(int id){
        state.exit(id);
        tablePhil--;
        notifyAll();
    }
}
