package com.brothergamecompany.pixelassault.toweroffence.Other;

import com.brothergamecompany.pixelassault.framework.gl.SpriteBatcher;
import com.brothergamecompany.pixelassault.framework.gl.SpriteBatcherColored;
import com.brothergamecompany.pixelassault.framework.gl.Texture;
import com.brothergamecompany.pixelassault.framework.gl.TextureRegion;

/**
 * Created by maxgm_umv4xdu on 17.07.2017.
 */

public class Font {
    public final Texture texture;
    private final int glyphWidth;
    private final int glyphHeight;
    public static final float DEFAULT_SPACE_BETWEEN_LETTERS = 5;
    public final TextureRegion[] glyphs = new TextureRegion[96];
    public static float spaceBetweenLetters = DEFAULT_SPACE_BETWEEN_LETTERS;

    public Font(Texture texture,
                int offsetX, int offSetY,
                int glyphsPerRow, int glyphWidth, int glyphHeight) {
        this.texture = texture;
        this.glyphWidth = glyphWidth;
        this.glyphHeight = glyphHeight;
        int x = offsetX;
        int y = offSetY;
        for (int i = 0; i < 96; i++) {
            glyphs[i] = new TextureRegion(texture, x, y, glyphWidth, glyphHeight);
            x += glyphWidth;
            if (x == offsetX + glyphsPerRow * glyphWidth) {
                x = offsetX;
                y += glyphHeight;
            }
        }
    }

    public void drawText(SpriteBatcher batcher, String text, float x, float y, float glyphWidthDraw, float glyphHeightDraw) {
        int len = text.length();
        for (int i = 0; i < len; i++) {
            int c = text.charAt(i) - ' ';
            if (c < 0 || c > glyphs.length - 1)
                continue;

            TextureRegion glyph = glyphs[c];
            batcher.drawSprite(x, y, glyphWidthDraw, glyphHeightDraw, glyph);
            x += glyphWidthDraw + spaceBetweenLetters;
        }
    }

    public void drawColoredText(SpriteBatcherColored coloredBatcher, String text, float x, float y, float R, float G, float B, float A, float glyphWidthDraw, float glyphHeightDraw) {
        int len = text.length();
        for (int i = 0; i < len; i++) {
            int c = text.charAt(i) - ' ';
            if (c < 0 || c > glyphs.length - 1)
                continue;

            TextureRegion glyph = glyphs[c];
            coloredBatcher.drawSprite(x, y, glyphWidthDraw, glyphHeightDraw, glyph, R, G, B, A);
            x += glyphWidthDraw + spaceBetweenLetters;
        }
    }

}
