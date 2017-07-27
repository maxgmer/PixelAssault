package com.brothergamecompany.pixelassault.toweroffence.Other.Notifications;

import com.brothergamecompany.pixelassault.framework.gl.SpriteBatcherColored;
import com.brothergamecompany.pixelassault.framework.impl.GLGraphics;
import com.brothergamecompany.pixelassault.toweroffence.Other.Assets.Assets;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxgm_umv4xdu on 18.07.2017.
 */

public class NotificationManager {
    private static List<Notification> notificationsQueue = new ArrayList<>();
    private static SpriteBatcherColored coloredBatcher;
    private static GLGraphics glGraphics;
    private static Notification currentNotification;
    private static boolean initialized = false;
    public static void makeNotification(float x, float y, String text, float duration,
                                        float red, float green, float blue, float glyphWidth, float glyphHeight) {
        notificationsQueue.add(new Notification(x, y, text, duration, red, green, blue, 1, glyphWidth, glyphHeight));
    }

    public static void update(float deltaTime) {
        if (initialized) {
            if (notificationsQueue.size() != 0) {
                currentNotification = notificationsQueue.get(0);
                currentNotification.timeLeft -= deltaTime;
                if (currentNotification.timeLeft <= 0)
                    currentNotification.A -= deltaTime / currentNotification.fadeTime;
                if (currentNotification.A <= 0) {
                    notificationsQueue.remove(0);
                }
            }
        }
    }

    public static void draw() {
        if (initialized) {
            if (notificationsQueue.size() != 0) {
                currentNotification = notificationsQueue.get(0);

                Assets.font.drawColoredText(coloredBatcher, currentNotification.text, currentNotification.x, currentNotification.y,
                        currentNotification.R, currentNotification.G, currentNotification.B, currentNotification.A, currentNotification.glyphWidth, currentNotification.glyphHeight);
            }
        }
    }

    public static void initNotificationManager(SpriteBatcherColored batcher, GLGraphics glGraphics) {
        if (!initialized) {
            NotificationManager.coloredBatcher = batcher;
            NotificationManager.glGraphics = glGraphics;
            initialized = true;
        }
    }
}
