package com.example.internalstorage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var editFile:EditText=findViewById(R.id.editFile)
        var editData:EditText=findViewById(R.id.editData)
        var btnSave: Button =findViewById(R.id.btnSave)
        var btnView:Button=findViewById(R.id.btnView)
        btnSave.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                var file=editFile.text.toString()
                var data=editData.text.toString()
                var fileOutputStream:FileOutputStream
                try {
                    fileOutputStream=openFileOutput(file,Context.MODE_PRIVATE)
                    fileOutputStream.write(data.toByteArray())
                }
                catch(e: FileNotFoundException){
                    e.printStackTrace()
                }catch (e: NumberFormatException){
                    e.printStackTrace()
                }catch (e: IOException){
                    e.printStackTrace()
                }catch (e: Exception){
                    e.printStackTrace()
                }
                Toast.makeText(applicationContext,"data Saved",Toast.LENGTH_LONG).show()
                editFile.text.clear()
                editData.text.clear()
            }
        })

        btnView.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
                var file=editFile.text.toString()
                if(file.toString()!=null && file.toString().trim()!="") {
                    var fileInputStream: FileInputStream? = null
                    fileInputStream = openFileInput(file)
                    var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
                    var bufferReader: BufferedReader = BufferedReader(inputStreamReader)
                    var stringBuilder: StringBuilder = StringBuilder()
                    var text: String? =null
                    while ({text=bufferReader.readLine(); text}()!=null){
                        stringBuilder.append(text)
                    }
                    editData.setText(stringBuilder.toString()).toString()
                }else{
                    Toast.makeText(applicationContext,"File cannot be empty",Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}