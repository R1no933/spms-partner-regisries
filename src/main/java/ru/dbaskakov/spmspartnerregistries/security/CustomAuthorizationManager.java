package ru.dbaskakov.spmspartnerregistries.security;

import lombok.AllArgsConstructor;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import ru.dbaskakov.spmspartnerregistries.service.RoleService;

import java.util.Collection;
import java.util.function.Supplier;

@AllArgsConstructor
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private final RoleService roleService;

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
        Authentication auth = authentication.get();
        if (auth == null || !auth.isAuthenticated()) return new AuthorizationDecision(false);

        var principal = auth.getPrincipal();
        if (!(principal instanceof Jwt)) return new AuthorizationDecision(false);

        var jwt = (Jwt) principal;

        Collection<String> userRoles = roleService.extractRolesFromJwt(jwt);
        String requiredRole = roleService.getRequiredRoleForRequest(context);

        if (requiredRole == null) return new AuthorizationDecision(true);

        var hasRole = userRoles.stream()
                .anyMatch(role -> role.equalsIgnoreCase(requiredRole));

        return new AuthorizationDecision(hasRole);
    }
}
