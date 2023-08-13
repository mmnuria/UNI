
/**
 * Native monitor based Terrain
 * 
 * @author CSD Juansa Sendra
 * @version 2021
 */

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Terrain3 implements Terrain {
    Viewer v;
    ReentrantLock rl;
    Condition[][] cond;
    long defaultTimeoutMs = 1000;
    TimeUnit defaultTimeUnit = TimeUnit.MILLISECONDS;
    
    public  Terrain3 (int t, int ants, int movs, String msg) {
        v=new Viewer(t,ants,movs,msg);
        rl = new ReentrantLock();

        cond = new Condition[t][t];

        for (int i = 0; i < t; i++) {
            for (int j = 0; j < t; j++) {
                // lock for each cell
                cond[i][j] = rl.newCondition();
            }
        }

        for (int i = 0; i < ants; i++)
            new Ant(i, this, movs).start();
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
            Pos currentPos = v.getPos(a); // save current position to free it after using the next position

            while (v.occupied(dest)) {
                if (!cond[dest.x][dest.y].await(defaultTimeoutMs, defaultTimeUnit)) {
                    v.chgDir(a); // if after timeout position is not free, change direction randomly
                    dest = v.dest(a); // set new dest as the while condition
                }
                v.retry(a);
            }
            v.go(a);

            cond[currentPos.x][currentPos.y].signal(); // just one ant can use it, signal just the next that is
                                                               // waiting
        } catch (Exception ie) {
            ie.printStackTrace();
        } finally {
            rl.unlock();
        }
    }
}
