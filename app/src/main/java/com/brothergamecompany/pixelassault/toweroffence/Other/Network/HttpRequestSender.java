package com.brothergamecompany.pixelassault.toweroffence.Other.Network;

import android.content.Context;
import android.util.Log;

import com.brothergamecompany.pixelassault.toweroffence.GameObjects.MapBuilder;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.UserInterface.UIAndTouchListening;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects.Tile;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects.Tower;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.R.id.list;
import static android.media.CamcorderProfile.get;
import static com.brothergamecompany.pixelassault.toweroffence.Other.Network.Account.totalMonstersKilled;
import static com.brothergamecompany.pixelassault.toweroffence.Other.Network.Account.uid;

/**
 * Created by maxgm on 16.08.2017.
 */

public class HttpRequestSender {
    private static OkHttpClient client;
    private static final String URL = "https://pixel-assault-577a7.appspot.com";
    private Context context;
    private static int totalMonstersKilled;
    private static int totalCoins;
    private static int totalExp;
    private static List<Integer> levelsList;

    public HttpRequestSender(Context context) {
        client = new OkHttpClient();
        this.context = context;
        levelsList = new ArrayList<>();
    }

    public void sendAccountCheckProcedure() {
        RequestBody formBody = new FormBody.Builder()
                .add("uid", uid)
                .build();
        Request request = new Request.Builder()
                .url(URL + "/hello")
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {

                }
            }
        });
    }

    public void confirmMapBuilderBase() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Tile tile : Account.path) {
            stringBuilder.append(tile.gridX).append(";");
            stringBuilder.append(tile.gridY).append(";");
            stringBuilder.append(tile.onMap).append(";");
            stringBuilder.append(tile.type2 == 0 ? 1 : tile.type2).append(";");
        }
        String pathString = stringBuilder.toString();
        stringBuilder.setLength(0);
        stringBuilder.trimToSize();
        for (Tower tower : Account.towers) {
            stringBuilder.append(tower.gridX).append(";");
            stringBuilder.append(tower.gridY).append(";");
            stringBuilder.append(tower.onMap).append(";");
            stringBuilder.append(tower.towerLevel).append(";");
        }
        String towerString = stringBuilder.toString();
        RequestBody formBody = new FormBody.Builder()
                .add("uid", uid)
                .add("path", pathString)
                .add("towers", towerString)
                .build();
        Request request = new Request.Builder()
                .url(URL + "/confirmBase")
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                MapBuilder.baseConfirmed = false;
                MapBuilder.mapBuilderState = MapBuilder.MAP_BUILDER_START_MENU;
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    if (response.body().string().equals("Base confirmed successfully")) {
                        MapBuilder.baseConfirmed = true;
                    }
                }
            }
        });
    }

    public void sendSyncRequest() {
        totalMonstersKilled = BasicValuesSynchronizer.totalMonstersKilled;
        totalCoins = BasicValuesSynchronizer.totalCoins;
        totalExp = BasicValuesSynchronizer.totalExp;
        levelsList.addAll(BasicValuesSynchronizer.killedMobs);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(totalMonstersKilled).append(";")
                .append(totalCoins).append(";")
                .append(totalExp).append(";");
        String syncData = stringBuilder.toString();

        ArrayList<Integer> listWithoutDuplicates = new ArrayList<Integer>(new LinkedHashSet<Integer>(levelsList));
        stringBuilder.setLength(0);
        stringBuilder.trimToSize();
        for (int i = 0; i < listWithoutDuplicates.size(); i++) {
            int mLevel = listWithoutDuplicates.get(i);
            int levelNumber = 0;
            for (int j = 0; j < levelsList.size(); j++) {
                if (mLevel == levelsList.get(j)) {
                    levelNumber++;
                }
            }
            stringBuilder.append(levelNumber).append(":").append(mLevel).append(";");
        }

        String monsterLevelsKilled = stringBuilder.toString();
        RequestBody formBody = new FormBody.Builder()
                .add("uid", uid)
                .add("monstersCoinsExp", syncData)
                .add("monsterLevelsKilled", monsterLevelsKilled)
                .build();
        final Request request = new Request.Builder()
                .url(URL + "/syncData")
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                BasicValuesSynchronizer.stateTime = BasicValuesSynchronizer.SYNC_DELAY;
                Log.wtf("syncR", "FAILURE");
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    String respBody = response.body().string();
                    Log.wtf("syncR", respBody);
                    if (respBody.equals("SyncSuccess")) {
                        BasicValuesSynchronizer.clear(HttpRequestSender.totalMonstersKilled, HttpRequestSender.totalCoins, HttpRequestSender.totalExp, HttpRequestSender.levelsList);
                    }
                    if (respBody.equals("SyncFailed")) {
                        BasicValuesSynchronizer.clear(HttpRequestSender.totalMonstersKilled, HttpRequestSender.totalCoins, HttpRequestSender.totalExp, HttpRequestSender.levelsList);
                    }
                }
            }
        });
    }
}
