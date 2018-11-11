package com.example.user.componentes.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.DatePicker
import com.example.user.componentes.R
import kotlinx.android.synthetic.main.activity_date.*
import java.text.SimpleDateFormat
import java.util.*

class DateActivity : AppCompatActivity(), View.OnClickListener,DatePickerDialog.OnDateSetListener {

    private val mSimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date)

        setListeners()
    }

    private fun setListeners(){
        dateActBtnDatePicker.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val id = view.id

        if(id == R.id.dateActBtnDatePicker){
            openDatePickerDialog()
        }
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year,month,day)
        //
        dateActBtnDatePicker.text = mSimpleDateFormat.format(calendar.time)
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
