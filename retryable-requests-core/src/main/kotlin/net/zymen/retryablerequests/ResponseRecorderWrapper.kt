package net.zymen.retryablerequests;

import javax.servlet.ServletOutputStream
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletResponseWrapper

internal class ResponseRecorderWrapper(response: HttpServletResponse?) : HttpServletResponseWrapper(response) {

    private var recordedOutputStream: OutputStreamRecorder? = null

    init {
        recordedOutputStream = OutputStreamRecorder(super.getOutputStream())
    }

    val recordedContent: String get() = recordedOutputStream!!.content

    override fun getOutputStream(): ServletOutputStream {
        return recordedOutputStream!!;
    }
}

