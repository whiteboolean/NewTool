package com.mtool.toolslib.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;

import static com.mtool.toolslib.utils.SoundUtil.SoundTrackName.SOUND_BUY_LOTTERY;
import static com.mtool.toolslib.utils.SoundUtil.SoundTrackName.SOUND_COUND_DOWN_END;
import static com.mtool.toolslib.utils.SoundUtil.SoundTrackName.SOUND_COUNT_DOWN;
import static com.mtool.toolslib.utils.SoundUtil.SoundTrackName.SOUND_MATCH;
import static com.mtool.toolslib.utils.SoundUtil.SoundTrackName.SOUND_PRESS_BALL;
import static com.mtool.toolslib.utils.SoundUtil.SoundTrackName.SOUND_SHAKE;

/***
 * 统一加载及播放的声音类型
 */
public class SoundUtil {
    // 声音池--存放soundID

    private static boolean soundPoolOnOff;
    private static int soundPoolID;
    private SoundPool soundPool;
    private HashMap<Integer, Integer> soundPoolMap;

    private static SoundUtil instance;

    public enum SoundTrackName {
        SOUND_SHAKE(0),
        SOUND_MATCH(1),
        SOUND_COUNT_DOWN(2),
        SOUND_COUND_DOWN_END(3),
        SOUND_PRESS_BALL(4),
        SOUND_BUY_LOTTERY(5);

        int TYPE;

        SoundTrackName(int type) {
            TYPE = type;
        }

        public int getSoundCode() {
            return TYPE;
        }
    }


    public static SoundUtil getInstance() {
        if (instance == null) {
            instance = new SoundUtil();
        }
        return instance;
    }

    /**
     * 加载声音
     */
    @SuppressLint("UseSparseArrays")
    public void initSoundTrack(final Context appContext, boolean soundPoolOnOff) {
        this.soundPoolOnOff = soundPoolOnOff;
        soundPoolMap = new HashMap<Integer, Integer>();
        soundPool = new SoundPool(2, AudioManager.STREAM_SYSTEM, 5);
        new Thread() {
            @Override
            public void run() {
                try {
                    soundPoolMap.put(SOUND_SHAKE.getSoundCode(), soundPool.load(appContext.getAssets().openFd("sound/shake_sound_male.mp3"), 1));
                    soundPoolMap.put(SOUND_MATCH.getSoundCode(), soundPool.load(appContext.getAssets().openFd("sound/shake_match.mp3"), 1));
                    soundPoolMap.put(SOUND_COUNT_DOWN.getSoundCode(), soundPool.load(appContext.getAssets().openFd("sound/sound_countdown.wav"), 1));
                    soundPoolMap.put(SOUND_COUND_DOWN_END.getSoundCode(), soundPool.load(appContext.getAssets().openFd("sound/sound_countdown_end1.wav"), 1));
                    soundPoolMap.put(SOUND_PRESS_BALL.getSoundCode(), soundPool.load(appContext.getAssets().openFd("sound/sound_touch.mp3"), 1));
                    soundPoolMap.put(SOUND_BUY_LOTTERY.getSoundCode(), soundPool.load(appContext.getAssets().openFd("sound/sound_pay.mp3"), 1));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void playSound(SoundTrackName PlaySoundIndex) {
        try {
            if (soundPoolOnOff) {
                soundPoolID = soundPool.play(soundPoolMap.get(PlaySoundIndex.getSoundCode()), 1.0f, 1.0f, 0, 0, 1.0f);// 播放声
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void closeSound() {
        try {
            soundPool.stop(soundPoolID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
