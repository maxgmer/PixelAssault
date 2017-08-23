package com.brothergamecompany.pixelassault.toweroffence.Other.Network;

import android.content.Context;
import android.util.Log;

import com.brothergamecompany.pixelassault.toweroffence.GameObjects.MapBuilder;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.UserInterface.UIAndTouchListening;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects.Tile;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects.Tower;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.brothergamecompany.pixelassault.toweroffence.Other.Network.Account.uid;

/**
 * Created by maxgm on 16.08.2017.
 */

public class HttpRequestSender {
    private static OkHttpClient client;
    private static final String URL = "https://pixel-assault-577a7.appspot.com";
    private Context context;

    public HttpRequestSender(Context context) {
        client = new OkHttpClient();
        this.context = context;
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
}
