package com.moldedbits.android.utils.fragmenttransactionhandler


import android.os.Handler
import android.os.Message
import java.util.*


/**
 * Message Handler class that supports buffering up of messages when the
 * activity is paused i.e. in the background.
 */

abstract class PauseHandler : Handler() {
    /**
     * Message Queue Buffer
     */
    private val messageQueueBuffer = Vector<Message>()

    /**
     * Flag indicating the pause state
     */
    private var paused: Boolean = false

    /**
     * Resume the handler
     */
    fun resume() {
        paused = false

        while (messageQueueBuffer.size > 0) {
            val msg = messageQueueBuffer.elementAt(0)
            messageQueueBuffer.removeElementAt(0)
            sendMessage(msg)
        }
    }

    /**
     * Pause the handler
     */
    fun pause() {
        paused = true
    }

    /**
     * Notification that the message is about to be stored as the activity is
     * paused. If not handled the message will be saved and replayed when the
     * activity resumes.
     *
     * @param message the message which optional can be handled
     * @return true if the message is to be stored
     */
    protected abstract fun storeMessage(message: Message): Boolean

    /**
     * Notification message to be processed. This will either be directly from
     * handleMessage or played back from a saved message when the activity was
     * paused.
     *
     * @param message the message to be handled
     */
    protected abstract fun processMessage(message: Message)

    /**
     * {@inheritDoc}
     */

    override fun handleMessage(msg: Message) {
        if (paused) {
            if (storeMessage(msg)) {
                val msgCopy = Message()
                msgCopy.copyFrom(msg)
                messageQueueBuffer.add(msgCopy)
            }
        } else {
            processMessage(msg)
        }
    }
}
