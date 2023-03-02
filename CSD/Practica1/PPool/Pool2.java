// CSD feb 2015 Juansa Sendra

public class Pool2 extends Pool{ //max kids/instructor
    int instructors = 0;
    int kids = 0;
    int MaxKidsInstr = 0;
    public void init(int ki, int cap){ MaxKidsInstr = ki;}
    public synchronized void kidSwims() throws InterruptedException {
        while (instructors ==0 || kids+1 > instructors*MaxKidsInstr) {
            log.waitingToSwim();//para visualizar la posición del nadador
            wait();
        }
    log.swimming();
    kids++;
    }
    public synchronized void kidRests() throws InterruptedException{
    log.resting(); 
    kids--;
    notifyAll();
    }
    public synchronized void instructorSwims() throws InterruptedException{
    log.swimming();
    instructors++;
    notifyAll();
    }
    
    public synchronized void instructorRests() throws InterruptedException{
    
    while(kids >0 && instructors == 1 || kids > (instructors-1)*MaxKidsInstr){
        log.waitingToRest();
        wait();
    }
    log.resting();
    instructors--;
    }
}