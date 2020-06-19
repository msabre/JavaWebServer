package servlets;

import accounts.AccountService;

import javax.servlet.ServletException;
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

public class SignIn extends Servlet
{
    public SignIn(AccountService accountService, String filename)
    {
        super(accountService, filename);
    }

    //sign up
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        response.setContentType("text/html;charset=utf-8");
        if (login.isEmpty() || password.isEmpty())
            response.getWriter().println("Enter your login and password");
        else if (accountService.getUserByLogin(login) == null)
            response.getWriter().println("Unregistrated user");
        else
            response.getWriter().println("Welcome " + login);
        response.setStatus(HttpServletResponse.SC_OK);
        reg = false;
    }
}
