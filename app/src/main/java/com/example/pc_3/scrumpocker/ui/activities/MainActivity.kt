package com.example.pc_3.scrumpocker.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.Gravity
import android.view.MenuItem
import com.example.pc_3.scrumpocker.*
import com.example.pc_3.scrumpocker.data.Card
import com.example.pc_3.scrumpocker.ui.adapters.CardsAdapter
import com.example.pc_3.scrumpocker.ui.dialogs.SettingsDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_and_toolbar.*
import android.text.style.TextAppearanceSpan
import android.text.SpannableString



class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            return intent
        }
    }

    var STANDARD_TYPE = "STANDARD"
    var FIBONACCI_TYPE = "FIBONACCI"
    var TSHIRT_TYPE = "TSHIRT"

    val standardDeck = listOf(Card("0"), Card("1/2"), Card("1"), Card("2"), Card("3"), Card("5"), Card("8"), Card("13"),
            Card("20"), Card("40"), Card("100"), Card(type = "infinite"), Card(type = "unknown"), Card(type = "break"))

    val fibonacciDeck = listOf(Card("0"), Card("1"), Card("2"), Card("3"), Card("5"), Card("8"), Card("13"),
            Card("21"), Card("34"), Card("55"), Card("89"), Card(type = "infinite"),
            Card(type = "unknown"), Card(type = "break"))

    val tShirtDeck = listOf(Card("XS"), Card("S"), Card("M"), Card("L"), Card("XL"), Card("XXL"), Card(type = "infinite"),
            Card(type = "unknown"), Card(type = "break"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        setupToolBar()
        setupNavView()
        setupCardsRecycler()
    }

    private fun setupCardsRecycler() {
        rvCards.layoutManager = GridLayoutManager(this, 3)
        rvCards.adapter = CardsAdapter(this, getCardList()) { startActivity(CardActivity.newIntent(this, it)) }
    }

    private fun setupNavView() {
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        toggle.isDrawerIndicatorEnabled = false
        toggle.setHomeAsUpIndicator(R.drawable.toolbar_menu)
        toggle.syncState()
        toggle.setToolbarNavigationClickListener { drawerLayout.openDrawer(GravityCompat.START) }
        navView.menu.clear()
        navView.inflateMenu(R.menu.menu_main_drawer)
        navView.itemIconTintList = null
        setupTitle()
        navView.setNavigationItemSelectedListener(this)
    }

    private fun setupTitle() {
        val menu = navView.menu
        val tools = menu.findItem(R.id.deckTypeTitle)
        val s = SpannableString(tools.title)
        s.setSpan(TextAppearanceSpan(this, R.style.TextAppearanceTitleDrawer), 0, s.length, 0)
        tools.title = s
    }

    private fun setupToolBar() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.title = null
    }

    private fun getCardList(): List<Card> {
        val type = getDeckType()
        when (type) {
            STANDARD_TYPE -> return standardDeck
            FIBONACCI_TYPE -> return fibonacciDeck
            TSHIRT_TYPE -> return tShirtDeck
            else -> return standardDeck
        }
    }

    private fun updateRvCards(type: String) {
        saveDeckType(type)
        setupCardsRecycler()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            android.R.id.home -> if (drawerLayout.isDrawerOpen(Gravity.START)) {
                drawerLayout.closeDrawer(Gravity.START)
            } else drawerLayout.openDrawer(Gravity.START)
            R.id.menu_standard -> updateRvCards(STANDARD_TYPE)
            R.id.menu_fibonacci -> updateRvCards(FIBONACCI_TYPE)
            R.id.menu_settings -> showSettingsDialog()
            //R.id.menu_about ->
        }
        drawerLayout.closeDrawers()
        return true
    }

    private fun showSettingsDialog() {
        val dialog = SettingsDialog()
        dialog.show(this.supportFragmentManager, "Settings Dialog")
    }
}