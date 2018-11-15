package com.example.user.componentes.view

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import com.example.user.componentes.R
import kotlinx.android.synthetic.main.activity_date.*
import java.text.SimpleDateFormat
import java.util.*

class DateActivity : AppCompatActivity(), View.OnClickListener,DatePickerDialog.OnDateSetListener, TimePicker.OnTimeChangedListener {

    private val mSimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date)

        setListeners()
    }

    private fun setListeners(){
        dateActBtnDatePicker.setOnClickListener(this)
        dateActBtnGetTime.setOnClickListener(this)
        dateActBtnSetTime.setOnClickListener(this)
        //
        dateActTimePicker.setOnTimeChangedListener(this)
    }

    override fun onClick(view: View) {
        val id = view.id

        if(id == R.id.dateActBtnDatePicker){
            openDatePickerDialog()
        } else if(id == R.id.dateActBtnGetTime){
            //Aqui acho que faltou format da data, pois quando minuto menor que 10, não aparece com o 0 a direita.
            //valida versão do android o usr para saber qual codigo usar
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
              val value = "${dateActTimePicker.hour} :${dateActTimePicker.minute}"
                Toast.makeText(this,value,Toast.LENGTH_SHORT).show()
            }else{
                val value = "${dateActTimePicker.currentHour}:${dateActTimePicker.currentMinute}"
                Toast.makeText(this,value,Toast.LENGTH_SHORT).show()
            }

        }else if(id == R.id.dateActBtnSetTime){
            //valida versão do android o usr para saber qual codigo usar
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                dateActTimePicker.hour = 20
                dateActTimePicker.minute = 15
            }else{
                dateActTimePicker.currentHour = 20
                dateActTimePicker.currentMinute = 15
            }

        }
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year,month,day)
        //
        dateActBtnDatePicker.text = mSimpleDateFormat.format(calendar.time)
    }

    override fun onTimeChanged(timePicker: TimePicker?, hourOfDay: Int, minute: Int) {
        Toast.makeText(this,"$hourOfDay:$minute",Toast.LENGTH_SHORT).show()
    }

    private fun openDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        //
        DatePickerDialog(this,this,year,month,day).show()
    }
}
