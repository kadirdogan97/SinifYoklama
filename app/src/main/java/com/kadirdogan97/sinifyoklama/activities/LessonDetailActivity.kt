package com.kadirdogan97.sinifyoklama.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kadirdogan97.sinifyoklama.*
import com.kadirdogan97.sinifyoklama.adapters.DiscontinuityAdapter
import com.kadirdogan97.sinifyoklama.adapters.DiscontinuityTAdapter
import com.kadirdogan97.sinifyoklama.databinding.ActivityLessonDetailBinding
import com.kadirdogan97.sinifyoklama.network.DiscontinuityListener
import com.kadirdogan97.sinifyoklama.network.ExcelExporter
import com.kadirdogan97.sinifyoklama.network.ModifyListener
import com.kadirdogan97.sinifyoklama.network.model.*
import com.kadirdogan97.sinifyoklama.util.toast
import com.kadirdogan97.sinifyoklama.viewmodels.LessonDetailsViewModel

class LessonDetailActivity : AppCompatActivity(),
    DiscontinuityListener, LessonDetailsViewModel.DetailClickListener,
    ModifyListener {



    companion object {
        var barkod = "BARKOD"
        var cBarkod = ""
    }
    private var viewmodel= LessonDetailsViewModel()
    private val discontinuityAdapter = DiscontinuityAdapter()
    private val discontinuityTAdapter = DiscontinuityTAdapter()
    private lateinit var binding: ActivityLessonDetailBinding
    private var myLogin = Student(1,"","","","","")
    private var myLoginT = Teacher(1,"","")
    private var myLesson = Lesson(1,"","","","","","","","");
    private var discontinuityService: DiscontinuityService? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_lesson_detail
        )
        viewmodel = ViewModelProviders.of(this).get(LessonDetailsViewModel::class.java)
        binding.viewmodel = viewmodel
        binding.recyclerView.adapter = discontinuityAdapter
        binding.recyclerViewT.adapter = discontinuityTAdapter
        viewmodel.discontinuityListener = this
        viewmodel.detailClickListener = this
        viewmodel.modifyListener = this
        myLesson = intent.getSerializableExtra("lesson") as Lesson
        if(intent.hasExtra("LoginUser")) {
            myLogin = intent.getSerializableExtra("LoginUser") as Student
            viewmodel.fetchDiscontinuityData(myLesson.id.toString(),myLogin.id.toString())
        }
        if(intent.hasExtra("LoginUserT")) {
            myLoginT = intent.getSerializableExtra("LoginUserT") as Teacher
            viewmodel.fetchDiscontinuityTData(myLesson.id.toString())
        }
    }
    override fun onStarted() {}
    override fun onSuccess(discontinuityResponse: LiveData<DiscontinuityService>) {
        discontinuityResponse.observe(this, Observer {
            discontinuityService = it
            if(intent.hasExtra("LoginUser")) {
                if(it.aktif.equals("0")){
                    binding.yoklamaAl.visibility = View.GONE
                }
                binding.layoutStudent.visibility = View.VISIBLE
                binding.layoutTeacher.visibility = View.GONE
                binding.lessonName.text=myLesson.ders_adi
                binding.teacherName.text=myLesson.ogr_gorevli_ad_soyad
                binding.devamsizlikSayi.text=it.devamsizlik_sayi
                discontinuityAdapter.setDiscontinuityList(it.devamsizliklar!!)
            }
            if(intent.hasExtra("LoginUserT")) {
                binding.layoutStudent.visibility = View.GONE
                binding.layoutTeacher.visibility = View.VISIBLE
                binding.lessonNameTeacher.text = myLesson.ders_adi
                discontinuityTAdapter.setDiscontinuityList(it.devamsizliklar!!)
            }
        })
    }
    override fun onFailure(message: String?) {}

    override fun onCheckClicked() {
        toast(discontinuityService?.barkod.toString())

        startActivity(Intent(getApplicationContext(), ScanCodeActivity::class.java));

        //viewmodel.setDiscontinuity()
    }
    override fun onCloseClicked() {
        viewmodel.closeDiscontinuity(myLesson.id.toString())
    }

    override fun onBarcodeClicked() {
        cBarkod="TETETE"
        viewmodel.setBarcode(cBarkod,myLesson.id.toString())
        showDialog()
    }

    private fun showDialog() {
        val qrFragment = QRDialogFragment()
        qrFragment.setCancelable(true)
        qrFragment.show(supportFragmentManager, "DIALOG_FRAGMENT")
    }

    override fun onStartedModify() {}

    override fun onSuccessSetDisc(modifyResponse: LiveData<ModifyResponse>) {
        modifyResponse.observe(this, Observer {
            toast(it.message)
        })
    }

    override fun onSuccessCloseDisc(modifyResponse: LiveData<ModifyResponse>) {
        modifyResponse.observe(this, Observer {
            toast(it.message)
        })
    }

    override fun onSuccessCreateBarcode(modifyResponse: LiveData<ModifyResponse>) {
        modifyResponse.observe(this, Observer {
            toast(it.message)
        })
    }

    override fun onSuccessSetDevice(modifyResponse: LiveData<ModifyResponse>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFailureModify(message: String?) {
        TODO("PROGRESS")
        toast(message)
    }


    override fun onResume() {
        super.onResume()
        if(discontinuityService?.barkod.equals(barkod)){
            toast("YOKLAMA BASARILI")
        }

    }
    private fun askForPermission(permission: String, requestCode: Int?) {
        if (ContextCompat.checkSelfPermission(
                this,
                permission
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, permission
                )
            ) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(permission), requestCode!!
                )

            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(permission), requestCode!!
                )
            }
        } else {
            Toast.makeText(
                this, "$permission is already granted.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    override fun onSendMailClicked() {
        askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE, 1)
        askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, 1)
        ExcelExporter.export(discontinuityService,myLesson)
        toast("excel olusturuldu")
        var fileDirectory = "/storage/emulated/0/Download/test.xls"
        ExcelExporter.sendEmailWithAttachment(this,"zkdr.dgn@gmail.com", "Yoklama", "Icerik", fileDirectory)
    }


}
