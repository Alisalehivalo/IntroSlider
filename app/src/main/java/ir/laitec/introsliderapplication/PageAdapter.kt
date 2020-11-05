package ir.laitec.introsliderapplication

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.ArrayList

class PageAdapter(val manager :FragmentManager) :FragmentPagerAdapter(manager) {
    val list :MutableList<Fragment> = ArrayList()
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Fragment {
        return list[position]
    }

}