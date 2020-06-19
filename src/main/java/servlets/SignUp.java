package servlets;

import accounts.AccountService;
import accounts.UserProfile;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class SignUp extends Servlet {

    public SignUp(AccountService accountService, String filename) {
        super(accountService, filename);
    }

    //sign up
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String eamil = request.getParameter("email");

        response.setContentType("text/html;charset=utf-8");
        if (login.isEmpty() || password.isEmpty() || eamil.isEmpty())
            response.getWriter().println("Fill the form");
        else if (accountService.getUserByLogin(login) == null)
        {
            accountService.addNewUser(new UserProfile(login, password, eamil));
            response.getWriter().println("Welcom " + login);
        }
        else
            response.getWriter().println("Registreted Already");
        response.setStatus(HttpServletResponse.SC_OK);
        reg = false;
    }
}
