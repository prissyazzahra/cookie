package id.ac.ui.cs.mobileprogramming.prissy.cookie

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import id.ac.ui.cs.mobileprogramming.prissy.cookie.ui.timer.TimerFragment
import id.ac.ui.cs.mobileprogramming.prissy.cookie.utils.NotificationUtil
import id.ac.ui.cs.mobileprogramming.prissy.cookie.utils.PrefUtil

class TimerExpiredReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        NotificationUtil.showTimerExpired(context)
        PrefUtil.setTimerState(
            TimerFragment.TimerState.Stopped,
            context
        )
        PrefUtil.setAlarmSetTime(
            0,
            context
        )
    }
}
