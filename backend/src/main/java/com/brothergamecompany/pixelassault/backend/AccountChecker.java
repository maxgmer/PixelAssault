package com.brothergamecompany.pixelassault.backend;

import com.brothergamecompany.pixelassault.backend.ModelClasses.AccountModel;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by maxgm on 18.08.2017.
 */

public class AccountChecker {

    public static final String servletPath = "/hello";

    public static void doPost(HttpServletRequest req, HttpServletResponse resp, final FirebaseDatabase database) throws IOException {
        resp.setContentType("text/plain");
        final String myUid = req.getParameter("uid");
        final PrintWriter writer = resp.getWriter();
        // As an admin, the app has access to read and write all data, regardless of Security Rules
        final DatabaseReference ref = database.getReference().child("Users");
        final CountDownLatch synchronizer = new CountDownLatch(1);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                    Iterator<DataSnapshot> iterator = iterable.iterator();
                    boolean uidFound = false;
                    while (iterator.hasNext()) {
                        String uid = iterator.next().getKey();
                        if(myUid.equals(uid)){
                            uidFound = true;
                            writer.write("Account exists");
                        }
                    }
                    if (!uidFound) {
                        writer.write("Account will be created soon");
                        database.getReference().child("Users").child(myUid).child("Account").setValue(new AccountModel());
                    }
                    writer.flush();
                    writer.close();
                    synchronizer.countDown();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        try {
            synchronizer.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
