package springexamples.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import springexamples.database.dao.UserDAO;
import springexamples.database.entity.User;

@Component
public class AuthenticatedUserService {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    // this method will authenticate a user as if they had logged in through the login page.
    public void changeLoggedInUsername(String username, String unencryptedPassword) {
        // reset security principal to be the new user information
        Authentication request = new UsernamePasswordAuthenticationToken(username, unencryptedPassword);
        Authentication result = authenticationManager.authenticate(request);
        SecurityContextHolder.getContext().setAuthentication(result);
    }

    public User loadCurrentUser() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        User user = userDao.findByEmail(getCurrentUsername());
        return user;
    }

    public String getCurrentUsername() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context != null && context.getAuthentication() != null) {
            final org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) context.getAuthentication().getPrincipal();
            return principal.getUsername();
        } else {
            return null;
        }
    }
}
