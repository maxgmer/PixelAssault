package com.brothergamecompany.pixelassault.backend;

import com.brothergamecompany.pixelassault.backend.ModelClasses.AccountModel;
import com.brothergamecompany.pixelassault.backend.ModelClasses.TileModel;
import com.brothergamecompany.pixelassault.backend.ModelClasses.TowerModel;
import com.brothergamecompany.pixelassault.backend.Other.MonsterStats;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by maxgm_umv4xdu on 26.08.2017.
 */

public class SyncData {
    public static final String servletPath = "/syncData";

    public static void doPost(HttpServletRequest req, HttpServletResponse resp, final FirebaseDatabase database) throws IOException {
        resp.setContentType("text/plain");
        final String myUid = req.getParameter("uid");

        final String syncValuesStr = req.getParameter("monstersCoinsExp");

        final String[] syncValuesStrA = syncValuesStr.split(";");
        final int[] syncValues = new int[3];
        int i = 0;
        for (String value : syncValuesStrA) {
            syncValues[i] = Integer.valueOf(value);
            i++;
        }

        final String monstersKilled = req.getParameter("monsterLevelsKilled");
        String[] number_levelPairs = monstersKilled.split(";");
        int coinsBasedOnKilledMonsters = 0;
        int expBasedOnKilledMonsters = 0;
        int totalMonstersNumber = 0;
        for (String pair : number_levelPairs) {
            String[] pairA = pair.split(":");
            totalMonstersNumber += Integer.valueOf(pairA[0]);
            coinsBasedOnKilledMonsters += (Integer.valueOf(pairA[0]) * (MonsterStats.getMonsterCoins(Integer.valueOf(pairA[1]))));
            expBasedOnKilledMonsters += (Integer.valueOf(pairA[0]) * (MonsterStats.getMonsterExp(Integer.valueOf(pairA[1]))));
        }
        final PrintWriter writer = resp.getWriter();
        // As an admin, the app has access to read and write all data, regardless of Security Rules
        final DatabaseReference ref = database.getReference().child("Users").child(myUid).child("Account");
        final CountDownLatch synchronizer = new CountDownLatch(1);

        final int finalCoinsBasedOnKilledMonsters = coinsBasedOnKilledMonsters;
        final int finalTotalMonstersNumber = totalMonstersNumber;
        final int finalExpBasedOnKilledMonsters = expBasedOnKilledMonsters;
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    if (finalTotalMonstersNumber == syncValues[0] && finalCoinsBasedOnKilledMonsters == syncValues[1] && finalExpBasedOnKilledMonsters == syncValues[2]) {
                        AccountModel account = dataSnapshot.getValue(AccountModel.class);
                        int maxLevelExp = account.getMaxLvlExp();
                        if (account.currentExp + finalExpBasedOnKilledMonsters >= maxLevelExp) {
                            dataSnapshot.child("totalMonstersKilled").getRef().setValue(finalTotalMonstersNumber + account.totalMonstersKilled);
                            dataSnapshot.child("totalCoins").getRef().setValue(finalCoinsBasedOnKilledMonsters + account.totalCoins);
                            dataSnapshot.child("currentExp").getRef().setValue(-(maxLevelExp - (account.currentExp + finalExpBasedOnKilledMonsters)));
                            if (((account.currentLevel + 1) % 10) == 0) {
                                account.path.add(new TileModel());
                                dataSnapshot.child("path").getRef().setValue(account.path);
                            }
                            if (((account.currentLevel + 1) % 50) == 0) {
                                account.towers.add(new TowerModel());
                                dataSnapshot.child("towers").getRef().setValue(account.towers);
                            }
                            dataSnapshot.child("currentLevel").getRef().setValue(account.currentLevel + 1);
                        } else {
                            dataSnapshot.child("totalMonstersKilled").getRef().setValue(finalTotalMonstersNumber + account.totalMonstersKilled);
                            dataSnapshot.child("totalCoins").getRef().setValue(finalCoinsBasedOnKilledMonsters + account.totalCoins);
                            dataSnapshot.child("currentExp").getRef().setValue(finalExpBasedOnKilledMonsters + account.currentExp);
                        }
                        writer.write("SyncSuccess");
                        writer.flush();
                        writer.close();
                        synchronizer.countDown();
                    }
                    else {
                        writer.write("SyncFailed");
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
