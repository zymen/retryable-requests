package net.zymen.retryablerequests.tests.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CodeDto implements Comparable<CodeDto> {

    private String code;

    public CodeDto(final String code) {
        this.code = code;
    }

    public CodeDto() {
    }

    public String getCode() {
        return code;
    }

    @Override
    public int compareTo(final CodeDto obj) {
        return code.compareTo(obj.code);
    }
}
