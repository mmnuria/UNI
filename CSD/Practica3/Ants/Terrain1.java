
/**
 * Native monitor based Terrain
 * 
 * @author CSD Juansa Sendra
 * @version 2021
 */
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Terrain1 implements Terrain {
    Viewer v;
    ReentrantLock rl;
    Condition cond;
    
    public  Terrain1 (int t, int ants, int movs, String msg) {
        v=new Viewer(t,ants,movs,msg);
        rl = new ReentrantLock();
        cond = rl.newCondition();

        for (int i = 0; i < ants; i++){
            new Ant(i, this, movs).start();
        }
    }
    public synchronized void     hi      (int a) {
        try {
            rl.lock();

            v.hi(a);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rl.unlock();
        }
    }
    public synchronized void     bye     (int a) {
        try {
            rl.lock();
            cond.signalAll();
            v.bye(a);
        } catch (Exception ie) {
            ie.printStackTrace();
        } finally {
            rl.unlock();
        }
    }
    public synchronized void     move    (int a) throws InterruptedException {
        try {
            rl.lock();

            v.turn(a);
            Pos dest = v.dest(a);

            while (v.occupied(dest)) {
                cond.await();
                v.retry(a);
            }
            v.go(a);

            cond.signalAll();
        } catch (Exception ie) {
            ie.printStackTrace();
        } finally {
            rl.unlock();
        }
    }
}
