/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package com.brothergamecompany.pixelassault.backend;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.*;

public class MyServlet extends HttpServlet {
    public static Logger Log = Logger.getLogger("com.brothergamecompany.pixelassault.backend.MyServlet");

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/plain");
        resp.getWriter().println("Please use the form to POST to this url");
    }

    @Override
    public void doPost(final HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        // Note: Ensure that the [PRIVATE_KEY_FILENAME].json has read
        // permissions set.
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setServiceAccount(getServletContext().getResourceAsStream("/WEB-INF/Pixel Assault-eb3ed43e3517.json"))
                .setDatabaseUrl("https://pixel-assault-577a7.firebaseio.com/")
                .build();

        try {
            FirebaseApp.getInstance();
        }
        catch (Exception error){
            MyServlet.Log.info("doesn't exist...");
        }

        try {
            FirebaseApp.initializeApp(options);
        }
        catch(Exception error){
            MyServlet.Log.info("already exists...");
        }
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        String requestType = req.getRequestURI();
        Log.info(requestType);
        switch (requestType) {
            case AccountChecker.servletPath:
                AccountChecker.doPost(req, resp, database);
                break;
            case MapBuilderConfirmation.servletPath:
                MapBuilderConfirmation.doPost(req, resp, database);
                break;
        }
    }
}