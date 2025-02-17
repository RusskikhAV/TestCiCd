package api.core;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestParam {
    private RequestParamType type;
    private String name;
    private String value;

    public static @NotNull RequestParam getRP(RequestParamType type, String name, String value) {
        return new RequestParam(type, name, value);
    }
}
