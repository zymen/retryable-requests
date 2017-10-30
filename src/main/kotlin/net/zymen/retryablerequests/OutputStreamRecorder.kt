package net.zymen.retryablerequests;

import javax.servlet.ServletOutputStream
import javax.servlet.WriteListener

internal class OutputStreamRecorder(val stream: ServletOutputStream) : ServletOutputStream() {
//    private static final Logger log = getLogger(OutputStreamRecorder.class);

    override fun isReady() = stream.isReady

    override fun setWriteListener(listener: WriteListener) = stream.setWriteListener(listener)

    override fun write(b: Int): Unit {
//        log.info("Byte from hell: '{}'", (char)b);
        stream.write(b);
    }
}
