package net.zymen.retryablerequests;

import org.slf4j.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

class OutputStreamRecorder extends ServletOutputStream {
    private static final Logger log = getLogger(OutputStreamRecorder.class);

    private final ServletOutputStream outputStream;

    OutputStreamRecorder(ServletOutputStream stream) {
        this.outputStream = stream;
    }

    @Override
    public boolean isReady() {
        return outputStream.isReady();
    }

    @Override
    public void setWriteListener(WriteListener listener) {
        outputStream.setWriteListener(listener);
    }

    @Override
    public void write(int b) throws IOException {
        log.info("Byte from hell: '{}'", (char)b);
        outputStream.write(b);
    }
}
