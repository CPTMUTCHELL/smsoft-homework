package com.smsoft.view;

import java.util.ArrayList;

public record SSOIDAndFormIDs(String ssoId, ArrayList<String> formIds) {

    public String formIdsToString() {
        var sb = new StringBuilder();
        formIds.forEach(id -> {
            sb.append(id);
            sb.append("; ");
        });
        return sb.toString();
    }

    public String getSsoId() {
        return ssoId;
    }

}
