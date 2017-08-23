package com.brothergamecompany.pixelassault.toweroffence.Other.Network;

import android.util.Log;

import com.brothergamecompany.pixelassault.toweroffence.GameLauncher;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.World;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects.Tile;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects.Tower;
import com.brothergamecompany.pixelassault.toweroffence.GameScreens.GameScreen;
import com.brothergamecompany.pixelassault.toweroffence.Other.Network.ModelClasses.AccountModel;
import com.brothergamecompany.pixelassault.toweroffence.Other.Network.ModelClasses.TileModel;
import com.brothergamecompany.pixelassault.toweroffence.Other.Network.ModelClasses.TowerModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.R.attr.y;

/**
 * Created by maxgm_umv4xdu on 02.08.2017.
 */

public class DatabaseReader {
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private ValueEventListener accountListener;
    private boolean firstSync;

    public DatabaseReader() {
        firstSync = true;
    }

    public void startDatabaseListening() {
        final String uid = GameLauncher.signIn.currentUser.getUid();
        Account.uid = uid;
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference().child("Users").child(uid).child("Account");
        accountListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean mapSizeChanged = false;
                AccountModel accountModel = dataSnapshot.getValue(AccountModel.class);
                if (accountModel != null) {
                    if (Account.WORLD_WIDTH != accountModel.WORLD_WIDTH || Account.WORLD_HEIGHT != accountModel.WORLD_HEIGHT) {
                        Account.WORLD_WIDTH = accountModel.WORLD_WIDTH;
                        Account.WORLD_HEIGHT = accountModel.WORLD_HEIGHT;
                        mapSizeChanged = true;
                    }
                    Account.totalMonstersKilled = accountModel.totalMonstersKilled;
                    Account.nickname = accountModel.nickname;
                    Account.currentExp = accountModel.currentExp;
                    Account.currentLevel = accountModel.currentLevel;
                    Account.totalCoins = accountModel.totalCoins;
                    Account.spawnPortalPower = accountModel.spawnPortalPower;

                    Account.path.clear();
                    Account.towers.clear();
                    for (int i = 0; i < accountModel.path.size(); i++) {
                        TileModel tileModel = accountModel.path.get(i);
                        if (!(tileModel.type == 1)) {
                            Tile tile = new Tile(tileModel.gridX, tileModel.gridY, 1, tileModel.type);
                            tile.onMap = tileModel.onMap;
                            Account.path.add(tile);
                        } else {
                            Tile tile = new Tile(tileModel.gridX, tileModel.gridY, 1);
                            tile.onMap = tileModel.onMap;
                            Account.path.add(tile);
                        }
                    }

                    for (int i = 0; i < accountModel.towers.size(); i++) {
                        TowerModel towerModel = accountModel.towers.get(i);
                        Tower tower = new Tower(towerModel.gridX, towerModel.gridY, towerModel.towerLevel);
                        tower.onMap = towerModel.onMap;
                        Account.towers.add(tower);
                    }
                    if (firstSync) {
                        GameScreen.world.initializeWorld();
                        firstSync = false;
                    } else {
                        GameScreen.world.initializeWorld();
                    }
                    GameLauncher.accountLoaded = true;
                } else {
                    firstSync = true;
                    GameLauncher.httpRequestSender.sendAccountCheckProcedure();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mDatabase.addValueEventListener(accountListener);
    }
}



