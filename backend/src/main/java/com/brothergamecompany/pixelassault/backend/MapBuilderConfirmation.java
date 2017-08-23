package com.brothergamecompany.pixelassault.backend;

import com.brothergamecompany.pixelassault.backend.ModelClasses.AccountModel;
import com.brothergamecompany.pixelassault.backend.ModelClasses.TileModel;
import com.brothergamecompany.pixelassault.backend.ModelClasses.TowerModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by maxgm on 18.08.2017.
 */

class MapBuilderConfirmation {
    public static final String servletPath = "/confirmBase";

    public static void doPost(HttpServletRequest req, HttpServletResponse resp, final FirebaseDatabase database) throws IOException {
        resp.setContentType("text/plain");
        final String myUid = req.getParameter("uid");
        final String path = req.getParameter("path");
        final String towers = req.getParameter("towers");
        final String[] tileFields = path.split(";");
        final String[] towerFields = towers.split(";");
        final PrintWriter writer = resp.getWriter();
        // As an admin, the app has access to read and write all data, regardless of Security Rules
        final DatabaseReference ref = database.getReference().child("Users").child(myUid).child("Account");
        final CountDownLatch synchronizer = new CountDownLatch(1);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    AccountModel account = dataSnapshot.getValue(AccountModel.class);
                    int currentPathSize = account.path.size();
                    int newPathSize = tileFields.length / 4;

                    int currentTowerAmount = account.towers.size();
                    int newTowerAmount = towerFields.length / 4;

                    if (currentPathSize != newPathSize || currentTowerAmount != newTowerAmount) {
                        writer.write("Wrong base sent");
                        writer.flush();
                        writer.close();
                        synchronizer.countDown();
                    } else {
                        List<TileModel> path = new ArrayList<>();
                        for (int i = 0; i < currentPathSize; i++) {
                            path.add(new TileModel
                                    (Integer.valueOf(tileFields[i * 4]),
                                    Integer.valueOf(tileFields[((i * 4) + 1)]),
                                    Boolean.valueOf(tileFields[((i * 4) + 2)]),
                                    Integer.valueOf(tileFields[((i * 4) + 3)])));
                        }

                        List<TowerModel> towers = new ArrayList<>();
                        for (int i = 0; i < currentTowerAmount; i++) {
                            towers.add(new TowerModel(
                                            Integer.valueOf(towerFields[i * 4]),
                                            Integer.valueOf(towerFields[((i * 4) + 1)]),
                                            Boolean.valueOf(towerFields[((i * 4) + 2)]),
                                            Integer.valueOf(towerFields[((i * 4) + 3)])));
                        }
                        dataSnapshot.child("path").getRef().setValue(path);
                        dataSnapshot.child("towers").getRef().setValue(towers);
                        writer.write("Base confirmed successfully");
                        writer.flush();
                        writer.close();
                        synchronizer.countDown();
                    }
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
