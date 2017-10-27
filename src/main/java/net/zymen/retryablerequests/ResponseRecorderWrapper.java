package net.zymen.retryablerequests;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

class ResponseRecorderWrapper extends HttpServletResponseWrapper {

    ResponseRecorderWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new OutputStreamRecorder(super.getOutputStream());
    }
}

