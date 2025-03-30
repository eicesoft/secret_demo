package org.secreetdemo.http.response;

import lombok.Data;

import java.util.List;

@Data
public class TestResponse {
    private String userId;
    private List<String> permissions;
}
