import android.app.Activity
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle


inline fun <reified T : Context> Context.newIntent(): Intent =
        Intent(this, T::class.java)

inline fun <reified T : Context> Context.newIntent(flags: Int): Intent {
    val intent = newIntent<T>()
    intent.flags = flags
    return intent
}

inline fun <reified T : Context> Context.newIntent(extras: Bundle): Intent =
        newIntent<T>(0, extras)

inline fun <reified T : Context> Context.newIntent(flags: Int, extras: Bundle): Intent {
    val intent = newIntent<T>(flags)
    intent.putExtras(extras)
    return intent
}

inline fun <reified T : Activity> Activity.launchActivity(): Unit =
        this.startActivity(newIntent<T>())

inline fun <reified T : Activity> Activity.launchActivity(flags: Int): Unit =
        this.startActivity(newIntent<T>(flags))

inline fun <reified T : Activity> Activity.launchActivity(extras: Bundle): Unit =
        this.startActivity(newIntent<T>(extras))

inline fun <reified T : Activity> Activity.launchActivity(flags: Int, extras: Bundle): Unit =
        this.startActivity(newIntent<T>(flags, extras))

inline fun <reified T : Activity> Activity.launchActivityForResult(requestCode: Int): Unit =
        this.startActivityForResult(newIntent<T>(), requestCode)

inline fun <reified T : Activity> Activity.launchActivityForResult(requestCode: Int,
                                                                  flags: Int): Unit =
        this.startActivityForResult(newIntent<T>(flags), requestCode)

inline fun <reified T : Activity> Activity.launchActivityForResult(
        extras: Bundle, requestCode: Int): Unit =
        this.startActivityForResult(newIntent<T>(extras), requestCode)

inline fun <reified T : Activity> Activity.launchActivityForResult(
        extras: Bundle, requestCode: Int, flags: Int): Unit =
        this.startActivityForResult(newIntent<T>(flags, extras), requestCode)

inline fun <reified T : Service> Context.launchService(): ComponentName =
        this.startService(newIntent<T>())

inline fun <reified T : Service> Context.launchService(flags: Int): ComponentName =
        this.startService(newIntent<T>(flags))

inline fun <reified T : Service> Context.launchService(extras: Bundle): ComponentName =
        this.startService(newIntent<T>(extras))

inline fun <reified T : Service> Context.launchService(extras: Bundle,
                                                      flags: Int): ComponentName
        = this.startService(newIntent<T>(flags, extras))