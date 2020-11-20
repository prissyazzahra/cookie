package id.ac.ui.cs.mobileprogramming.prissy.cookie.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import id.ac.ui.cs.mobileprogramming.prissy.cookie.ui.timer.TimerFragment

class TimerExpiredReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        PrefUtil.setTimerState(TimerFragment.TimerState.Stopped, context)
        PrefUtil.setAlarmSetTime(0, context)
    }
}
