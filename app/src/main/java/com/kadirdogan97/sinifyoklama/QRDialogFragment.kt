package com.kadirdogan97.sinifyoklama

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import com.kadirdogan97.sinifyoklama.activities.LessonDetailActivity

class QRDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.fragment_dialog_qr,container,false)
        val writer = QRCodeWriter()
        try {
            val bitMatrix = writer.encode(LessonDetailActivity.cBarkod, BarcodeFormat.QR_CODE, 800, 800)
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

        return rootView
    }
}
