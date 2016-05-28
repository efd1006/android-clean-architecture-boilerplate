package disono.webmons.com.clean_architecture.DI.modules;

import android.app.Activity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import disono.webmons.com.clean_architecture.util.sensor.Camera.Launcher;
import disono.webmons.com.clean_architecture.util.sensor.GeoLocation.GPS;
import disono.webmons.com.clean_architecture.util.sensor.Motion.AccelListener;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-05-28 01:02 PM
 */
@Module
public class SensorModule {
    private Activity activity;

    public SensorModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @Singleton
    public Launcher provideCamera() {
        return new Launcher(activity);
    }

    @Provides
    @Singleton
    public GPS provideLocation() {
        return new GPS(activity);
    }

    @Provides
    @Singleton
    public AccelListener provideMotion() {
        return new AccelListener(activity);
    }
}
