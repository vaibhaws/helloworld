/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * The simplest possible servlet.
 *
 * @author James Duncan Davidson
 */

public class HelloWorldExample extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        ResourceBundle rb =
            ResourceBundle.getBundle("LocalStrings",request.getLocale());
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\" />");

        String title = rb.getString("helloworld.title");
        String getNum = request.getParameter("worldnum");
        String mesg = "Blank Mesg";

        out.println("<title>" + title + "</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"blue\">");

        // note that all links are created to be relative. this
        // ensures that we can move the web application that this
        // servlet belongs to to a different place in the url
        // tree and not have any harmful side effects.
try {
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/vk","root","root"); 
Statement stmt=con.createStatement();  
ResultSet rs=stmt.executeQuery("select id, worldname from helloworld");  
while(rs.next())  
	if (rs.getString(1).equals(getNum)) mesg = rs.getString(2);
con.close();  
}
catch (Exception e) {//	out.println(e);
mesg = "Exception Ocurred";
}

        // making these absolute till we work out the
        // addition of a PathInfo issue

        out.println("<a href=\"../helloworld.html\">");
        out.println("<img src=\"../images/code.gif\" height=24 " +
                    "width=24 align=right border=0 alt=\"view code\"></a>");
        out.println("<a href=\"../index.html\">");
        out.println("<img src=\"../images/return.gif\" height=24 " +
                    "width=24 align=right border=0 alt=\"return\"></a>");
        out.println("<h1>" + title + " #" +getNum +"  ||  "+mesg+"</h1>");
        out.println("</body>");
        out.println("</html>");


    }
}



