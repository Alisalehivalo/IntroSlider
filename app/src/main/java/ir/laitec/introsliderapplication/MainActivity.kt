package ir.laitec.introsliderapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.viewpager.widget.ViewPager

class MainActivity : AppCompatActivity() {
    lateinit var preferences : SharedPreferences
    var show="Intro"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = PageAdapter(supportFragmentManager)
        val pager = findViewById<ViewPager>(R.id.pager)
        val btnNext=findViewById<Button>(R.id.btnNext)
        val btnSkip=findViewById<Button>(R.id.btnSkip)
        val indicator1=findViewById<TextView>(R.id.indicator1)
        val indicator2=findViewById<TextView>(R.id.indicator2)
        val indicator3=findViewById<TextView>(R.id.indicator3)

        val fragment1 = FirstFragment()
        fragment1.SetTitle("Wellcome")
        fragment1.SetBack(Color.GREEN)

        val fragment2 = FirstFragment()
        fragment2.SetTitle("To Android Class")
        fragment2.SetBack(Color.BLUE)

        val fragment3 = FirstFragment()
        fragment3.SetTitle("Instructor: Eng. Ali Akbar Salehi")
        fragment3.SetBack(Color.RED)

        preferences =getSharedPreferences("IntroSlider", Context.MODE_PRIVATE)

        if (!preferences.getBoolean(show,true)){
            startActivity(Intent(this@MainActivity,DashboardActivity::class.java))
            finish()
        }

        adapter.list.add(fragment1)
        adapter.list.add(fragment2)
        adapter.list.add(fragment3)
        pager.adapter=adapter

        btnNext.setOnClickListener {
            pager.currentItem++
        }

        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(position: Int,positionOffset: Float,positionOffsetPixels: Int){}

            override fun onPageSelected(position: Int) {
                if (position == adapter.list.size -1) {
                    //Last Page
                    btnNext.text = "Done"
                    btnNext.setOnClickListener {
                        gotoDashboard()
                }

                }else {
                    //Next Page
                    btnNext.text="Next"
                    btnNext.setOnClickListener {
                        pager.currentItem++
                    }
                }
                when(pager.currentItem){
                    0 -> {
                        indicator1.setTextColor(Color.BLACK)
                        indicator2.setTextColor(Color.GRAY)
                        indicator3.setTextColor(Color.GRAY)
                    }
                    1 -> {
                        indicator1.setTextColor(Color.GRAY)
                        indicator2.setTextColor(Color.BLACK)
                        indicator3.setTextColor(Color.GRAY)
                    }
                    2 -> {
                        indicator1.setTextColor(Color.GRAY)
                        indicator2.setTextColor(Color.GRAY)
                        indicator3.setTextColor(Color.BLACK)
                    }
                }
            }
            override fun onPageScrollStateChanged(state: Int) {}

        })
        btnSkip.setOnClickListener(View.OnClickListener { gotoDashboard()})
    }

    fun gotoDashboard(){
        startActivity(Intent(this@MainActivity,DashboardActivity::class.java))
        finish()
        val editor=preferences.edit()
        editor.putBoolean(show,false)
        editor.apply()
    }
}