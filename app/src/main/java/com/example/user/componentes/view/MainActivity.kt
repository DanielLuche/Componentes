package com.example.user.componentes.view

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import com.example.user.componentes.R
import com.example.user.componentes.mock.Mock
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener,SeekBar.OnSeekBarChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListeners()
        //
        loadSpinner()
    }

    private fun setListeners() {
        main_btn_toast_me.setOnClickListener(this)
        mainBtnSnack.setOnClickListener(this)
        mainBtnSpinnerGetData.setOnClickListener(this)
        mainBtnSpinnerSetData.setOnClickListener(this)
        //Linha abaixo equivale ao setOnItemSelectedListner do Java
        mainSpinnerDynamic.onItemSelectedListener = this
        //
        mainBtnProgress.setOnClickListener(this)
        mainBtnGetSeekValue.setOnClickListener(this)
        mainBtnSetSeekValue.setOnClickListener(this)
        //
        mainBtnCallDateAct.setOnClickListener(this)
        //
        mainSeekbar.setOnSeekBarChangeListener(this)

    }

    override fun onClick(v: View) {
        val id = v.id
        //
        if (id == R.id.main_btn_toast_me) {
            generateCustomToast()
        } else if (id == R.id.mainBtnSnack) {
            generateCustomSnack()
        } else if(id == R.id.mainBtnSpinnerGetData){
            //val value = mainSpinnerDynamic.selectedItem.toString()
            val value = mainSpinnerDynamic.selectedItemPosition.toString()
            Toast.makeText(this,value,Toast.LENGTH_LONG).show()
        }else if(id == R.id.mainBtnSpinnerSetData){
            mainSpinnerDynamic.setSelection(3)
        }else if(id == R.id.mainBtnProgress){
            generateProgress()
        }else if(id == R.id.mainBtnGetSeekValue){
            getSeekVal()
        }else if(id == R.id.mainBtnSetSeekValue){
            setSeekVal()
        }else if(id == R.id.mainBtnCallDateAct){
            callDateAct()
        }

        //14:28 a 15:24 tela fica preta sem video e audio
    }

    private fun callDateAct() {
        val mIntent = Intent(this,DateActivity::class.java)
        startActivity(mIntent)
    }

    private fun setSeekVal() {
        mainSeekbar.progress = 15
    }

    private fun getSeekVal() {
        val value = mainSeekbar.progress.toString()
        Toast.makeText(this,value,Toast.LENGTH_LONG).show()
    }

    //region Spinner Listners
    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val id = view.id
        //
        if(id == R.id.mainSpinnerDynamic) {
            val value = parent.getItemAtPosition(position).toString()
            //
            Toast.makeText(this, value, Toast.LENGTH_LONG).show()
        }
    }
    //endregion

    //region SeekBar Listners
    //Interface disparada quando o valor foi alterado
    override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
        val id = seek.id
        //
        if(id == R.id.mainSeekbar){
            mainTvSeekValue.text = progress.toString()
        }
    }
    //Interface disparada quando o USR COMEÇA a arrastar a seekbar
    override fun onStartTrackingTouch(p0: SeekBar) {
        //
    }
    //Interface disparada quando o USR PARA a arrastar a seekbar
    override fun onStopTrackingTouch(p0: SeekBar) {
        //
    }
    //endregion

    private fun loadSpinner() {
        val list = Mock.getList()
        //
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            list
        )
        //
        mainSpinnerDynamic.adapter = adapter
    }


    private fun generateProgress() {
        val progress : ProgressDialog = ProgressDialog(this)
        progress.setTitle("Download")
        progress.setMessage("Baixando Arquivo")
        progress.show()
        //
//        progress.hide()
//        progress.dismiss()
    }

    private fun generateCustomSnack() {
        //Snackbar Simples
//        Snackbar
//            .make(
//                mainConstraint,//View sobre a qual o snack bar vai se sobepor
//                "Snackbar", // Texto
//                Snackbar.LENGTH_SHORT //Duração
//                )
//            .show()
        //Custom
        val snack = Snackbar.make(mainConstraint,"Snackbar", Snackbar.LENGTH_SHORT)
        //Cor do texto
        snack.view.findViewById<TextView>(android.support.design.R.id.snackbar_text).setTextColor(Color.YELLOW)
        //Cor Background
        snack.view.setBackgroundColor(Color.RED)
        //Pegar cor do arquivo
        //resources.getColor(R.color.colorAccent)//Deprecated
        ContextCompat.getColor(this, R.color.colorAccent)//Novo metodo para pegar cor
        //Mostrar implementação da Action
        snack.setAction(
            "Desfazer"//Texto da ação
            //Abaixo temos o "OnClickListner" da action, porem se colocado esse lambda como abaixo
            //a Snackbar era exibida antes a primeira snack bar o.O . Para funcionar, foi necessario mover
            //o lambda para DEPOIS DO FECHAMENTO DO PARANTESES O.O
            //,{ Snackbar.make(mainConstraint,"Ação desfeita !!!", Snackbar.LENGTH_SHORT).show()}
        ) { Snackbar.make(mainConstraint,"Ação desfeita !!!", Snackbar.LENGTH_SHORT).show()}
        //Mudar cor do texto da action
        snack.setActionTextColor(Color.YELLOW)
        //
        snack.show()
    }

    /**
     * É possivel customizarmos o layout do Toast.
     * Por padrão, o android utiliza um layout que tem apenas um TextView, cujo Id é android.R.id.message.
     * É possivel acessar essa view e modifcar suas propriedades.
     * Ex: val toast_tv = toast.view.findViewById<TextView>(android.R.id.message)
     * É possivel tb criar um layout customizado inserir no Toast.
     * O metodo generateCustomToast() faz exatamente a customização do Toast utilizando um layout criado
     * por nos.
     */
    private fun generateCustomToast() {
        val toast = Toast.makeText(this, "Toast Notification!", Toast.LENGTH_LONG)
        //val toast_tv = toast.view.findViewById<TextView>(android.R.id.message).setTextColor(Color.GREEN)
        val toastLayout = layoutInflater.inflate(R.layout.toast_custom, null)
        toast.view = toastLayout
        val toastTv = toast.view.findViewById<TextView>(R.id.toast_tv_msg)
        toastTv.setTextColor(Color.WHITE)
        toastTv.setText("Custom Toast xD")
        toast.show()
    }
}
