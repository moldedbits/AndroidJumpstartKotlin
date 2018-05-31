package com.moldedbits.android.utils.fragmenttransactionhandler

import android.os.Message
import android.support.v4.app.FragmentActivity

import com.moldedbits.android.dialogs.ThemedInfoDialog

class FragmentTransactionHandler : PauseHandler() {

    private var activity: FragmentActivity? = null

    /**
     * Set the activity associated with the handler
     *
     * @param activity the activity to set
     */
    fun setActivity(activity: FragmentActivity?) {
        this.activity = activity
    }

    /**
     * Notification that the message is about to be stored as the activity is
     * paused. If not handled the message will be saved and replayed when the
     * activity resumes.
     *
     * @param message the message which optional can be handled
     * @return true if the message is to be stored
     */
    override fun storeMessage(message: Message): Boolean {
        return true
    }

    /**
     * Notification message to be processed. This will either be directly from
     * handleMessage or played back from a saved message when the activity was
     * paused.
     *
     * @param message the message to be handled
     */
    override fun processMessage(message: Message) {
        if (activity != null) {
            when (message.what) {
                SHOW_THEMED_INFO_DIALOG -> {
                    val dialog = message.obj as ThemedInfoDialog
                    dialog.show(activity!!.supportFragmentManager, "themedInfoDialog")
                }
                else -> {
                }
            }// do nothing
        }
    }

    companion object {
        val LOADING_DIALOG_DISMISS_MSG = 100002
        val SHOW_THEMED_INFO_DIALOG = 100003
    }
}