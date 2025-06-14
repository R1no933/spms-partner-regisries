package ru.dbaskakov.spmspartnerregistries.service;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Service
public class RoleService {
    private static final String CLIENT_ID = "spms-client";

    public Collection<String> extractRolesFromJwt(Jwt jwt) {
        Object resourceAccessObj = jwt.getClaim("resource_access");
        if (!(resourceAccessObj instanceof Map)) {
            return Collections.emptyList();
        }
        Map<String, Object> resourceAccess = (Map<String, Object>) resourceAccessObj;

        Object clientRolesObj = resourceAccess.get(CLIENT_ID);
        if (!(clientRolesObj instanceof Map)) {
            return Collections.emptyList();
        }
        Map<String, Object> clientRoles = (Map<String, Object>) clientRolesObj;

        Object rolesObj = clientRoles.get("roles");
        if (!(rolesObj instanceof Collection)) {
            return Collections.emptyList();
        }
        return (Collection<String>) rolesObj;
    }

    public String getRequiredRoleForRequest(RequestAuthorizationContext context) {
        String path = context.getRequest().getRequestURI();
        if (path.startsWith("/api/admin")) return "ADMIN";
        else if (path.startsWith("/api/user")) return "USER";
        else if (path.startsWith("/api/process-file")) return "USER";

        return null;
    }
}
