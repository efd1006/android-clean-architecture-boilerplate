package disono.webmons.com.clean_architecture.domain.interactors.base;

import disono.webmons.com.clean_architecture.domain.executor.interfaces.Executor;
import disono.webmons.com.clean_architecture.domain.executor.interfaces.MainThread;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-04-12 11:26 AM
 * <p>
 * This abstract class implements some common methods for all interactors. Cancelling an interactor, check if its running
 * and finishing an interactor has mostly the same code throughout so that is why this class was created. Field methods
 * are declared volatile as we might use these methods from different threads (mainly from UI).
 * <p>
 * For example, when an activity is getting destroyed then we should probably cancel an interactor
 * but the request will come from the UI thread unless the request was specifically assigned to a background thread.
 */
public abstract class AbstractInteractor implements Interactor {
    protected Executor mThreadExecutor;
    protected MainThread mMainThread;

    protected volatile boolean mIsCanceled;
    protected volatile boolean mIsRunning;

    public AbstractInteractor(Executor threadExecutor, MainThread mainThread) {
        mThreadExecutor = threadExecutor;
        mMainThread = mainThread;
    }

    /**
     * This method contains the actual business logic of the interactor. It SHOULD NOT BE USED DIRECTLY but, instead, a
     * developer should call the execute() method of an interactor to make sure the operation is done on a background thread.
     * <p>
     * This method should only be called directly while doing unit/integration tests. That is the only reason it is declared
     * public as to help with easier testing.
     */
    public abstract void run();

    public void cancel() {
        mIsCanceled = true;
        mIsRunning = false;

        mThreadExecutor.close();
    }

    public boolean isRunning() {
        return mIsRunning;
    }

    public void onFinished() {
        mIsRunning = false;
        mIsCanceled = false;
    }

    public void execute() {
        // mark this interactor as running
        this.mIsRunning = true;

        // start running this interactor in a background thread
        mThreadExecutor.execute(this);
    }
}
