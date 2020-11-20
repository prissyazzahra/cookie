package id.ac.ui.cs.mobileprogramming.prissy.cookie.ui.timer

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import id.ac.ui.cs.mobileprogramming.prissy.cookie.R
import id.ac.ui.cs.mobileprogramming.prissy.cookie.utils.PrefUtil
import id.ac.ui.cs.mobileprogramming.prissy.cookie.utils.TimerExpiredReceiver
import kotlinx.android.synthetic.main.fragment_timer.*
import kotlinx.android.synthetic.main.fragment_timer.view.*
import java.util.*

class TimerFragment : Fragment() {

    companion object {
        fun setAlarm(context: Context, current: Long, timeLeft: Long): Long {
            val wakeUpTime = (current + timeLeft) * 1000
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, TimerExpiredReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, wakeUpTime, pendingIntent)
            PrefUtil.setAlarmSetTime(nowSeconds, context)
            return wakeUpTime
        }

        fun removeAlarm(context: Context) {
            val intent = Intent(context, TimerExpiredReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.cancel(pendingIntent)
            PrefUtil.setAlarmSetTime(0, context)
        }

        val nowSeconds: Long
            get() = Calendar.getInstance().timeInMillis / 1000

    }

    enum class TimerState {
        Stopped, Paused, Running
    }

    private lateinit var timer : CountDownTimer
    private var timerLengthSeconds : Long = 0
    private var timerState = TimerState.Stopped

    private var secondsRemaining = 0L

    private lateinit var timerViewModel: TimerViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        timerViewModel =
                ViewModelProviders.of(this).get(TimerViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_timer, container, false)
        root.play.setOnClickListener {v ->
            startTimer()
            timerState = TimerState.Running
            updateButtons()
        }

        root.pause.setOnClickListener { v ->
            timer.cancel()
            timerState = TimerState.Paused
            updateButtons()
        }

        root.stop.setOnClickListener { v ->
            timer.cancel()
            timerState = TimerState.Paused
            onTimerFinished()
        }
//        val textView: TextView = root.findViewById(R.id.text_dashboard)
//        timerViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    override fun onResume() {
        super.onResume()
        initTimer()

        removeAlarm(requireContext())
    }

    override fun onPause() {
        super.onPause()

        if (timerState == TimerState.Running) {
            timer.cancel()
            val wakeUpTime = setAlarm(requireContext(), nowSeconds, secondsRemaining)
        } else if (timerState == TimerState.Paused) {
            // TODO
        }
        PrefUtil.setPreviousTimerLengthSeconds(timerLengthSeconds, requireContext())
        PrefUtil.setSecondsRemaining(secondsRemaining, requireContext())
        PrefUtil.setTimerState(timerState, requireContext())
    }

    private fun initTimer() {
        timerState = PrefUtil.getTimerState(requireContext())

        if (timerState == TimerState.Stopped) {
            setNewTimerLength()
        } else { setPreviousTimerLength() }
        secondsRemaining = if (timerState == TimerState.Stopped) timerLengthSeconds
            else PrefUtil.getSecondsRemaining(requireContext())

        val alarmSetTime = PrefUtil.getAlarmSetTime(requireContext())
        if (alarmSetTime > 0 )
            secondsRemaining -= nowSeconds - alarmSetTime

        if (secondsRemaining <= 0)
            onTimerFinished()
        else if (timerState == TimerState.Running)
            startTimer()

        updateButtons()
        updateCountdownUI()
    }

    private fun onTimerFinished() {
        timerState = TimerState.Stopped

        setNewTimerLength()

        PrefUtil.setSecondsRemaining(timerLengthSeconds, requireContext())
        secondsRemaining = timerLengthSeconds

        updateButtons()
        updateCountdownUI()
    }

    private fun startTimer() {
        timerState = TimerState.Running

        timer = object : CountDownTimer(secondsRemaining * 1000, 1000) {
            override fun onFinish() = onTimerFinished()

            override fun onTick(p0: Long) {
                secondsRemaining = p0 / 1000
                updateCountdownUI()

            }
        }.start()
    }

    private fun setNewTimerLength() {
        val length = PrefUtil.getTimerLength(requireContext())
        timerLengthSeconds = (length * 60L)
    }

    private fun setPreviousTimerLength() {
        timerLengthSeconds = PrefUtil.getPreviousTimerLengthSeconds(requireContext())
    }

    private fun updateCountdownUI() {
        val mins = secondsRemaining / 60
        val secondsLeft = secondsRemaining - mins * 60
        val secondsLeftStr = secondsLeft.toString()
        timer_text.text = "$mins:${
            if (secondsLeftStr.length == 2) secondsLeftStr
            else "0" + secondsLeftStr
        }"
    }

    private fun updateButtons() {
        when (timerState) {
            TimerState.Running -> {
                play.isEnabled = false
                pause.isEnabled = true
                stop.isEnabled = true
            }
            TimerState.Paused -> {
                play.isEnabled = true
                pause.isEnabled = false
                stop.isEnabled = true
            }
            TimerState.Stopped -> {
                play.isEnabled = true
                pause.isEnabled = false
                stop.isEnabled = false
            }
        }
    }
}