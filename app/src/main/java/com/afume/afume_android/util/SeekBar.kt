package com.afume.afume_android.util

import android.content.Context
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.afume.afume_android.R
import com.afume.afume_android.ui.note.NoteViewModel

private fun setSelectedSeekBarTxtBold(list: List<TextView>, select: Int){
    for(i in list.indices){
        if(i==select){
            list[i].setSelectedSeekBarTxt(true)
        }else{
            list[i].setSelectedSeekBarTxt(false)
        }
    }
}

class SeekBarListener(val context: Context, val seekBar: SeekBar, val list: List<TextView>, val vm : NoteViewModel, val type: String) : SeekBar.OnSeekBarChangeListener{
    override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
        setSelectedSeekBarTxtBold(list,progress)
        when (type){
            "longevity" -> {
                vm.longevityProgress.value = progress
            }
            "reverb" -> {
                vm.reverbProgress.value = progress
            }
            "gender" -> {
                vm.genderProgress.value = progress
            }
        }

    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
        seekBar.thumb = ContextCompat.getDrawable(context, R.drawable.seekbar_note_thumb)
        if(seekBar.progress==0){
            setSelectedSeekBarTxtBold(list,0)
        }
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
    }

}