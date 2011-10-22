package se.anyro.tewonderhack;

import java.util.ArrayList;
import java.util.List;

import com.wikidot.entitysystems.rdbmsbeta.SubSystem;

public class MainThread implements Runnable {

    private List<SubSystem> mSubSystems = new ArrayList<SubSystem>();

    Thread mThread;
    private boolean mRunning;

    public MainThread() {

    }

    public void addSubSystem(SubSystem subSystem) {
        mSubSystems.add(subSystem);
    }

    public void start() {
        if (mThread == null) {
            mThread = new Thread(this, "GameThread");
            mThread.start();
        }
    }

    public void stop() {

    }

    @Override
    public void run() {
        mRunning = true;
        while (mRunning) {
            for (SubSystem system : mSubSystems) {
                system.processOneGameTick(0);
            }
        }
    }
}
