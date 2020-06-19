package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ChangeServelet extends Servlet
{
    private String login;

    public ChangeServelet(AccountService accountService, String... filenames) {
        super(accountService, filenames);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
        login = null;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=utf-8");
        if (login == null) {
            String login = request.getParameter("login");
            if (login == null || login.isEmpty())
                response.getWriter().println("Enter your login");
            else if (accountService.getUserByLogin(login) == null)
                response.getWriter().println("User not registrated");
            else {
                this.login = login;
                response.getWriter().println(forms[1]);
            }
        }
        else {
            String newPass = request.getParameter("password");
            String newEmail = request.getParameter("email");
            response.getWriter().println("UserProfile hash been changed");
            accountService.addNewUser(new UserProfile(login, newPass, newEmail));

        }
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
