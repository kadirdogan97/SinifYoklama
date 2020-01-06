package com.kadirdogan97.sinifyoklama.activities

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ObservableField
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
    ModifyListener, DialogInterface.OnDismissListener {



    companion object {
        var barkod = "BARKOD"
        var cBarkod = ""
        var barkodO: ObservableField<String> = ObservableField()
    }
    private var viewmodel= LessonDetailsViewModel()
    private val discontinuityAdapter = DiscontinuityAdapter()
    private val discontinuityTAdapter = DiscontinuityTAdapter()
    private lateinit var binding: ActivityLessonDetailBinding
    private var myLogin = Student(1,"","","","","")
    private var myLoginT = Teacher(1,"","")
    private var myLesson = Lesson(1,"","","","","","","","");
    private var discontinuityService: DiscontinuityService? =null
    private var yoklamaSw: Boolean = false
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
        var isFirst = true
        barkodO.addOnPropertyChangedCallback(object: Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                Log.d("TAG", barkodO.get())
                Handler(Looper.getMainLooper()).post(object : Runnable{
                    override fun run() {
                        viewmodel.setBarcode(barkodO.get(),myLesson.id.toString())                    }

                })
            }

        })
    }

    override fun onStart() {
        super.onStart()
        if(intent.hasExtra("LoginUser")) {
            viewmodel.fetchDiscontinuityData(myLesson.id.toString(),myLogin.id.toString())
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
                binding.lessonName.text="Ders: "+myLesson.ders_adi
                binding.teacherName.text="Öğretim Görevlisi: "+myLesson.ogr_gorevli_ad_soyad
                binding.devamsizlikSayi.text="Devamsızlık: "+it.devamsizlik_sayi
                discontinuityAdapter.setDiscontinuityList(it.devamsizliklar!!)
            }
            if(intent.hasExtra("LoginUserT")) {
                binding.layoutStudent.visibility = View.GONE
                binding.layoutTeacher.visibility = View.VISIBLE
                binding.lessonNameTeacher.text = "Ders: "+myLesson.ders_adi
                discontinuityTAdapter.setDiscontinuityList(it.devamsizliklar!!)
            }

        })
    }
    override fun onFailure(message: String?) {}

    override fun onCheckClicked() {
     //   toast(discontinuityService?.barkod.toString())

        startActivity(Intent(getApplicationContext(), ScanCodeActivity::class.java))
        onPause()
        //viewmodel.setDiscontinuity()
    }
    override fun onCloseClicked() {
        viewmodel.closeDiscontinuity(myLesson.id.toString())
    }

    override fun onBarcodeClicked() {
        showDialog()
    }

    private fun showDialog() {
        val qrFragment = QRDialogFragment()
        qrFragment.setCancelable(true)
        qrFragment.show(supportFragmentManager, "DIALOG_FRAGMENT")
    }
    override fun onDismiss(p0: DialogInterface?) {
        viewmodel.fetchDiscontinuityTData(myLesson.id.toString())
    }

    override fun onStartedModify() {}

    override fun onSuccessSetDisc(modifyResponse: LiveData<ModifyResponse>) {
        modifyResponse.observe(this, Observer {
      //      toast(it.message)
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
        if(intent.hasExtra("LoginUser")) {
            viewmodel.fetchDiscontinuityData(myLesson.id.toString(),myLogin.id.toString())
        }
        Log.d("TAG","barkod:"+ barkod+" ve "+ discontinuityService?.barkod)
        if(discontinuityService?.barkod.equals(barkod)){
            //  toast("YOKLAMA BASARILI")
            viewmodel.setDiscontinuity(myLesson.id.toString(), myLogin.id.toString())
            barkod=""
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        yoklamaSw=false
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
            /*Toast.makeText(
                this, "$permission is already granted.",
                Toast.LENGTH_SHORT
            ).show()*/
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
