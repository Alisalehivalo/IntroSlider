package ir.laitec.introsliderapplication

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.graphics.drawable.DrawableCompat


class FirstFragment : Fragment() {

    var pageTitle : String =""
    var back : Int = Color.WHITE

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
          return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val titleFragment=view.findViewById<TextView>(R.id.fragmentTitle)
        val fragmentBackground=view.findViewById<LinearLayout>(R.id.fragment)
        titleFragment?.text=pageTitle
        fragmentBackground.setBackgroundColor(back)
    }

    fun SetTitle(title : String){
        pageTitle=title
    }
    fun SetBack(color : Int){
        back=color
    }
}