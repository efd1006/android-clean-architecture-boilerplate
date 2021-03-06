package disono.webmons.com.dependencies;

import disono.webmons.com.clean_architecture.AndroidApplication;
import disono.webmons.com.dependencies.components.ApplicationComponent;
import disono.webmons.com.dependencies.components.DaggerApplicationComponent;
import disono.webmons.com.dependencies.modules.AppModule;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-05-28 05:17 PM
 */
public class ApplicationBaseComponent {
    private static ApplicationComponent component;

    public static void inject(AndroidApplication application) {
        component = DaggerApplicationComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }

    public static ApplicationComponent component() {
        return component;
    }
}
