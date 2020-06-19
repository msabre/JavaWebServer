package servlets;

import accounts.AccountService;
import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class Servlet extends HttpServlet
{
    @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
    protected final AccountService accountService;
    protected final String[] forms;
    protected boolean reg;

    public Servlet(AccountService accountService, String ...filenames)
    {
        this.accountService = accountService;
        this.reg = false;
        forms = new String[filenames.length];
        for (int i = 0; i < forms.length; i++)
            forms[i] = getPage(filenames[i]);
    }

    //get public user profile
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
            response.getWriter().println(forms[0]);
    }

    protected String getPage(String filename)
    {
        Writer writer = new StringWriter();
        Template template = null;
        try
        {
            template = new Configuration().getTemplate("public_html" + File.separator + filename);
            template.process(null, writer);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return writer.toString();
    }
}
