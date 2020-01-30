package com.kadirdogan97.sinifyoklama.activities

import android.content.DialogInterface
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import com.kadirdogan97.sinifyoklama.R
import com.kadirdogan97.sinifyoklama.util.getTimeWithSec
import com.kadirdogan97.sinifyoklama.util.toMD5Hash
import java.util.*
import kotlin.concurrent.schedule


class QRDialogFragment : DialogFragment() {
    var timer: TimerTask ?=null
    var timeNow: String?=null
    lateinit var imageView: ImageView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.fragment_dialog_qr,container,false)
        imageView = (rootView.findViewById(R.id.imageView) as ImageView)
        timeNow= toMD5Hash(getTimeWithSec())
        LessonDetailActivity.barkodO.set(timeNow)
        val writer = QRCodeWriter()
        try {
            val bitMatrix = writer.encode(getTimeWithSec(), BarcodeFormat.QR_CODE, 800, 800)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bmp.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
                }
            }
            (rootView.findViewById(R.id.imageView) as ImageView).setImageBitmap(bmp)

        } catch (e: WriterException) {
            e.printStackTrace()
        }
        timer = Timer("SettingUp", false).schedule(60000,60000) {
            timeNow = toMD5Hash(getTimeWithSec())
            LessonDetailActivity.barkodO.set(timeNow)
            setPixels()
        }


        return rootView
    }

    fun setPixels(){
        val writer = QRCodeWriter()
        try {
            val bitMatrix = writer.encode(timeNow, BarcodeFormat.QR_CODE, 800, 800)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bmp.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
                }
            }
            imageView.setImageBitmap(bmp)

        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        val activity = activity
        if (activity is DialogInterface.OnDismissListener) {
            (activity as DialogInterface.OnDismissListener).onDismiss(dialog)
        }
        timer?.cancel()

    }
}
