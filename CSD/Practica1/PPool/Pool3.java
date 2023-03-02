// CSD feb 2015 Juansa Sendra

public class Pool3 extends Pool{ //max capacity
    int instructors = 0;
    int kids = 0;
    int MaxKidsInstr = 0;
    int MaxCapacity = 0;
    public void init(int ki, int cap){ 
    MaxKidsInstr = ki;
    MaxCapacity = cap;
    }
    public synchronized void kidSwims() throws InterruptedException {
        while (instructors ==0 || kids+1 > instructors*MaxKidsInstr || MaxCapacity <= kids+instructors+1){
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
        while(MaxCapacity == kids+instructors){
        log.waitingToRest();
        wait();
        }
    log.swimming();
    instructors++;
    notifyAll();
    }
    
    public synchronized void instructorRests() throws InterruptedException{
    
    while((kids >0 && instructors == 1) || kids > (instructors-1)*MaxKidsInstr || MaxCapacity <= kids+instructors+1){
        log.waitingToRest();
        wait();
    }
    log.resting();
    instructors--;
    notifyAll();
    }
}
