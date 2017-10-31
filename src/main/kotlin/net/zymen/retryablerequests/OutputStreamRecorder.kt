package net.zymen.retryablerequests;

import java.io.ByteArrayOutputStream
import javax.servlet.ServletOutputStream
import javax.servlet.WriteListener

internal class OutputStreamRecorder(val stream: ServletOutputStream) : ServletOutputStream() {

    private val recordedContentStream = ByteArrayOutputStream()

    val content: String
        get() = String(recordedContentStream.toByteArray())

    override fun isReady() = stream.isReady

    override fun setWriteListener(listener: WriteListener) = stream.setWriteListener(listener)

    override fun write(b: Int) {
        stream.write(b)
        recordedContentStream.write(b)
    }
}
