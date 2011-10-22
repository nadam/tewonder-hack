package se.anyro.tewonderhack;

import java.util.ArrayList;
import java.util.List;

import com.wikidot.entitysystems.rdbmsbeta.SubSystem;

public class MainThread implements Runnable {

    private List<SubSystem> mSubSystems = new ArrayList<SubSystem>();

    private Thread mThread;
    private boolean mRunning;

    // mThreadSuspended is increased each time we pause and decreased when we resume
    private volatile int mThreadSuspended = 1;

    public MainThread() {
    }

    public void addSubSystem(SubSystem subSystem) {
        mSubSystems.add(subSystem);
    }

    public void start() {
        if (mThread == null) {
            mThread = new Thread(this, "GameThread");
            mThread.start();
        } else {
            synchronized (this) {
                --mThreadSuspended;
                notify();
            }
        }
    }

    public void stop() {
        synchronized (this) {
            ++mThreadSuspended;
        }
    }

    @Override
    public void run() {
        mRunning = true;
        while (mRunning) {
            synchronized (this) {
                while (mThreadSuspended > 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
            for (SubSystem system : mSubSystems) {
                system.processOneGameTick(0);
            }
        }
    }
}
