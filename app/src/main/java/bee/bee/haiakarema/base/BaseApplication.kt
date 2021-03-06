package bee.bee.haiakarema.base

import android.app.Application
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration

class BaseApplication : Application() {

    companion object {
        lateinit var application: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        val config: ImageLoaderConfiguration = ImageLoaderConfiguration.Builder(this)
            .build()
        ImageLoader.getInstance().init(config)

    }


}