@file:JvmName("FileUtil")
@file:JvmMultifileClass

package com.messcat.mclibrary.util

import android.app.Dialog
import android.os.Environment
import android.text.TextUtils
import com.google.zxing.common.StringUtils
import com.messcat.kotlin.utils.e
import com.messcat.kotlin.utils.isSpace
import okhttp3.ResponseBody
import org.jsoup.helper.StringUtil
import java.io.*
import java.util.ArrayList

/**
 * 文件类
 * Created by Administrator on 2017/8/30 0030.
 */
/**
 * 获取SD卡目录
 */
fun getSDPath(): String? {
    var sdDis: String? = null
    val sdCardExit = Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()
    if (sdCardExit) {
        sdDis = Environment.getExternalStorageDirectory().path
    }
    return sdDis
}

/**
 * 获取File
 */
fun getFile(fileName: String, filePaths: String): String? {
    val filePath = getSDPath() + File.separator + fileName
    val file = File(filePath)
    if (file.exists()) {
        val file1 = File(filePath + File.separator + filePaths)
        if (file1.exists()) {
            return file1.path
        } else {
            return null
        }
    } else {
        return null
    }
}

/**
 * 创建SD卡下的文件
 *
 * @param path
 */
fun createFile(fileName: String, filePaths: String): File {
    val filePath = getSDPath() + File.separator + fileName
    val file = File(filePath)
    if (!file.exists()) {
        file.mkdir()
    }
    val file1 = File(filePath + File.separator + filePaths)
    if (!file1.exists()) {
        try {
            file1.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
    return file1
}

/**
 * 根据文件路径获取文件
 *
 * @param filePath 文件路径
 */
fun getFileByPath(filePath: String): File? {
    return if (isSpace(filePath)) null else File(filePath)
}

/**
 * 判断文件是否存在
 *
 * @param filePath 文件路径
 */
fun isFileExists(filePath: String): Boolean {
    return isFileExists(getFileByPath(filePath))
}

/**
 * 判断文件是否存在
 *
 * @param file 文件
 */
fun isFileExists(file: File?): Boolean {
    return file != null && file.exists()
}

/**
 * 重命名文件
 *
 * @param filePath 文件路径
 * @param newName  新名称
 */
fun rename(filePath: String, newName: String): Boolean {
    return rename(getFileByPath(filePath), newName)
}

/**
 * 重命名文件
 *
 * @param file    文件
 * @param newName 新名称
 */
fun rename(file: File?, newName: String): Boolean {
    // 文件为空返回false
    if (file == null) return false
    // 文件不存在返回false
    if (!file.exists()) return false
    // 新的文件名为空返回false
    if (isSpace(newName)) return false
    // 如果文件名没有改变返回true
    if (newName == file.name) return true
    val newFile = File(file.parent + File.separator + newName)
    // 如果重命名的文件已存在返回false
    return !newFile.exists() && file.renameTo(newFile)
}

/**
 * 判断文件是否存在，不存在则判断是否创建成功
 * @param filePath 文件路径
 */
fun createOrExistsFile(filePath: String): Boolean {
    return createOrExistsFile(getFileByPath(filePath))
}

/**
 * 判断目录是否存在，不存在则判断是否创建成功
 *
 * @param file 文件
 */
fun createOrExistsDir(file: File?): Boolean {
    // 如果存在，是目录则返回true，是文件则返回false，不存在则返回是否创建成功
    return file != null && if (file.exists()) file.isDirectory else file.mkdirs()
}

/**
 * 判断文件是否存在，不存在则判断是否创建成功
 *
 * @param file 文件
 */
fun createOrExistsFile(file: File?): Boolean {
    if (file == null) return false
    // 如果存在，是文件则返回true，是目录则返回false
    if (file.exists()) return file.isFile
    if (!createOrExistsDir(file.parentFile)) return false
    try {
        return file.createNewFile()
    } catch (e: IOException) {
        e.printStackTrace()
        return false
    }

}


/**
 * 删除文件
 *
 * @param srcFilePath 文件路径
 */
fun deleteFile(srcFilePath: String): Boolean {
    return deleteFile(getFileByPath(srcFilePath))
}

/**
 * 删除文件
 *
 * @param file 文件
 */
fun deleteFile(file: File?): Boolean {
    return file != null && (!file.exists() || file.isFile && file.delete())
}

private var rootPaths = "PlayMusic"
//原始文件(不能播放)
private val AUDIO_PCM_BASEPATH = "/$rootPaths/pcm/"
//可播放的高质量音频文件
private val AUDIO_WAV_BASEPATH = "/$rootPaths/wav/"

private fun setRootPath(rootPath: String) {
    rootPaths = rootPath
}

fun getPcmFileAbsolutePath(fileName: String): String {
    var fileName = fileName
    if (TextUtils.isEmpty(fileName)) {
        throw NullPointerException("fileName isEmpty")
    }
    if (!isSdcardExit()) {
        throw IllegalStateException("sd card no found")
    }
    var mAudioRawPath = ""
    if (isSdcardExit()) {
        if (!fileName.endsWith(".pcm")) {
            fileName = fileName + ".pcm"
        }
        val fileBasePath = Environment.getExternalStorageDirectory().absolutePath + AUDIO_PCM_BASEPATH
        val file = File(fileBasePath)
        //创建目录
        if (!file.exists()) {
            file.mkdirs()
        }
        mAudioRawPath = fileBasePath + fileName
    }

    return mAudioRawPath
}

fun getWavFileAbsolutePath(fileName: String?): String {
    var fileName: String? = fileName ?: throw NullPointerException("fileName can't be null")
    if (!isSdcardExit()) {
        throw IllegalStateException("sd card no found")
    }

    var mAudioWavPath = ""
    if (isSdcardExit()) {
        if (!fileName!!.endsWith(".wav")) {
            fileName = fileName + ".wav"
        }
        val fileBasePath = Environment.getExternalStorageDirectory().absolutePath + AUDIO_WAV_BASEPATH
        val file = File(fileBasePath)
        //创建目录
        if (!file.exists()) {
            file.mkdirs()
        }
        mAudioWavPath = fileBasePath + fileName
    }
    return mAudioWavPath
}

/**
 * 判断是否有外部存储设备sdcard
 *
 * @return true | false
 */
fun isSdcardExit(): Boolean {
    return if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED)
        true
    else
        false
}

/**
 * 获取全部pcm文件列表
 *
 * @return
 */
fun getPcmFiles(): List<File> {
    val list = ArrayList<File>()
    val fileBasePath = Environment.getExternalStorageDirectory().absolutePath + AUDIO_PCM_BASEPATH

    val rootFile = File(fileBasePath)
    if (!rootFile.exists()) {
    } else {

        val files = rootFile.listFiles()
        for (file in files!!) {
            list.add(file)
        }

    }
    return list

}

/**
 * 获取全部wav文件列表
 *
 * @return
 */
fun getWavFiles(): List<File> {
    val list = ArrayList<File>()
    val fileBasePath = Environment.getExternalStorageDirectory().absolutePath + AUDIO_WAV_BASEPATH

    val rootFile = File(fileBasePath)
    if (!rootFile.exists()) {
    } else {
        val files = rootFile.listFiles()
        for (file in files!!) {
            list.add(file)
        }

    }
    return list
}

fun writeResponseBodyToDisk(body: ResponseBody, path: String): Boolean {
    try {
        var inputStream: InputStream? = null
        var outputStream: OutputStream? = null

        try {
            val fileReader = ByteArray(4096)

            val fileSize = body.contentLength()
            var fileSizeDownloaded: Long = 0

            inputStream = body.byteStream()
            outputStream = FileOutputStream(createFile("PlayMusic", path))

            while (true) {
                val read = inputStream!!.read(fileReader)

                if (read == -1) {
                    break
                }

                outputStream!!.write(fileReader, 0, read)

                fileSizeDownloaded += read.toLong()

                e("file download: $fileSizeDownloaded of $fileSize")
            }

            outputStream!!.flush()
            return true
        } catch (e: IOException) {
            return false
        } finally {
            if (inputStream != null) {
                inputStream.close()
            }

            if (outputStream != null) {
                outputStream.close()
            }
        }
    } catch (e: IOException) {
        return false
    }

}
