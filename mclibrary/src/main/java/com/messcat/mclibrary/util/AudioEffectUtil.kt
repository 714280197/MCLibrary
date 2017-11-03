@file:JvmName("AudioEffectUtil")
@file:JvmMultifileClass

package com.messcat.mclibrary.util

import android.media.MediaPlayer
import android.media.audiofx.BassBoost
import android.media.audiofx.Equalizer
import android.media.audiofx.PresetReverb
import android.media.audiofx.Visualizer
import java.util.ArrayList

/**
 * 控制音乐音色和场景
 *
 * Created by Administrator on 2017/9/18 0018.
 */
private val reverbNames = ArrayList<Short>()
private val reverbVals = ArrayList<String>()
private var visualizer: Visualizer? = null//环绕音
private var equalizer: Equalizer? = null//均匀器
private var bassBoost: BassBoost? = null//重低音
private var presetReverb: PresetReverb? = null//预设音场控制器


/**
 * 初始化均匀器
 */
fun initEqualizer(mediaPlayer: MediaPlayer): Equalizer {
    equalizer = Equalizer(0, mediaPlayer.getAudioSessionId())
    equalizer?.setEnabled(true)
    return equalizer!!
}

/**
 * 初始化预设场合控制器
 */
fun initPresetReverb(mediaPlayer: MediaPlayer): PresetReverb {
    presetReverb = PresetReverb(0, mediaPlayer.getAudioSessionId())
    presetReverb?.setEnabled(true)
    return presetReverb!!
}

/**
 * 初始化重低音
 */
fun initBassBoost(mediaPlayer: MediaPlayer): BassBoost {
    bassBoost = BassBoost(0, mediaPlayer.getAudioSessionId())
    bassBoost?.setEnabled(true)
    return bassBoost!!
}

/**
 * 获取最小均匀器
 */
fun getMinEqualizer(equalizer: Equalizer): Short = equalizer.getBandLevelRange()!![0]

/**
 * 获取最大均匀器
 */
fun getMaxEqualizer(equalizer: Equalizer): Short = equalizer.getBandLevelRange()!![1]

/**
 * 获取均衡器控制器支持的所有频率
 */
fun setupEqualizer(equalizer: Equalizer): Short? = equalizer?.getNumberOfBands()

/**
 * 设置重低音
 */
fun setupBassBoost(bassBoost: BassBoost, progress: Short) {
    bassBoost.setStrength(progress)
}

/**
 * 预设场合名称
 */
fun setupPresetReverbName(equalizer: Equalizer): MutableList<String> {
    for (i in 0..equalizer?.getNumberOfPresets()!!) {
        reverbVals.add(equalizer?.getPresetName(i.toShort())!!)
    }
    return reverbVals
}

/**
 * 预设场合ID
 */
fun setupPresetReverbID(equalizer: Equalizer): MutableList<Short> {
    for (i in 0..equalizer?.getNumberOfPresets()!!) {
//        presetReverb?.setPreset(i.toShort()) //设置场景
        reverbNames.add(i.toShort())
    }
    return reverbNames
}

/**
 * 释放
 */
fun release(mediaPlayer: MediaPlayer?) {
    visualizer?.release()
    equalizer?.release()
    presetReverb?.release()
    bassBoost?.release()
    mediaPlayer?.release()
}