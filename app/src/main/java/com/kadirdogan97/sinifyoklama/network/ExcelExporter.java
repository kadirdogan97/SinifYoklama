package com.kadirdogan97.sinifyoklama.network;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import androidx.core.content.FileProvider;

import com.kadirdogan97.sinifyoklama.network.model.DiscontinuityService;
import com.kadirdogan97.sinifyoklama.network.model.Lesson;

import java.io.File;
import java.util.Locale;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelExporter {

    public static void export(DiscontinuityService discontinuityService, Lesson lesson) {
        File sd = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        String csvFile = "test.xls";

        File directory = new File(sd.getAbsolutePath());

        //create directory if not exist
        if (!directory.isDirectory()) {
            directory.mkdirs();
        }
        try {

            //file path
            File file = new File(directory, csvFile);
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale(Locale.GERMAN.getLanguage(), Locale.GERMAN.getCountry()));
            WritableWorkbook workbook;
            workbook = Workbook.createWorkbook(file, wbSettings);

            //Excel sheetA first sheetA
            WritableSheet sheetA = workbook.createSheet(lesson.getDers_adi()+" devamsizliklar", 0);
            sheetA.addCell(new Label(0, 0, "Ogrenci No"));
            sheetA.addCell(new Label(1, 0, "Ogrenci Ad Soyad"));
            sheetA.addCell(new Label(2, 0, "Devamsizlik"));
            for(int i =0; i<discontinuityService.getDevamsizliklar().size();i++) {
                sheetA.addCell(new Label(0, i+1, discontinuityService.getDevamsizliklar().get(i).getOgr_no()));
                sheetA.addCell(new Label(1, i+1, discontinuityService.getDevamsizliklar().get(i).getOgr_ad_soyad()));
                sheetA.addCell(new Label(2, i+1, discontinuityService.getDevamsizliklar().get(i).getDevamsizlik()));
            }
            // close workbook
            workbook.write();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void sendEmailWithAttachment(Context context, String to, String subject, String message, String fileAndLocation)
    {
        // try {
        File excelFile = new File(fileAndLocation);
        Uri imageUri = FileProvider.getUriForFile(
                context,
                "com.kadirdogan97.sinifyoklama.provider", //(use your app signature + ".provider" )
                excelFile);

        final Intent emailIntent = new Intent(
                android.content.Intent.ACTION_SEND);
        emailIntent.setType("application/excel");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                new String[] { to });
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                subject);
        emailIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
        context.startActivity(Intent.createChooser(emailIntent,
                "Sending email..."));
/*
        } catch (Throwable t) {
            Toast.makeText(this,
                    "Request failed try again: " + t.toString(),
                    Toast.LENGTH_LONG).show();
        }*/



    }//end method
}