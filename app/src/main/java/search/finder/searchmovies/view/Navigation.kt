package search.finder.searchmovies.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import search.finder.searchmovies.R

class Navigation(private val fragmentManager: FragmentManager) {

    fun addFragment(fragment: Fragment, container: Int, useBackStack: Boolean) {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.enter_fragment, R.anim.exit_fragment)
        fragmentTransaction.replace(container, fragment)
        if (useBackStack) {
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commit()
    }
}