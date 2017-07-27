package com.brothergamecompany.pixelassault.framework.gl;

public class Animation {
    public static final int ANIMATION_LOOPING = 0;
    public static final int ANIMATION_NONLOOPING = 1;
    
    final TextureRegion[] keyFrames;
    float frameDuration;
    public int keyFramesNumber;
    public float animationLength;
    
    public Animation(float frameDuration, TextureRegion ... keyFrames) {
        this.frameDuration = frameDuration;
        this.keyFrames = keyFrames;
        keyFramesNumber = keyFrames.length;
        this.animationLength = frameDuration * keyFrames.length;
    }

    public Animation(TextureRegion ... keyFrames) {
        this.keyFrames = keyFrames;
        keyFramesNumber = keyFrames.length;
        this.animationLength = frameDuration * keyFrames.length;
    }

    public void setFrameDuration(float frameDuration) {
        this.frameDuration = frameDuration;
    }
    
    public TextureRegion getKeyFrame(float stateTime, int mode) {
        int frameNumber = (int)(stateTime / frameDuration);
        
        if(mode == ANIMATION_NONLOOPING) {
            frameNumber = Math.min(keyFrames.length-1, frameNumber);            
        } else {
            frameNumber = frameNumber % keyFrames.length;
        }
        return keyFrames[frameNumber];
    }
}
