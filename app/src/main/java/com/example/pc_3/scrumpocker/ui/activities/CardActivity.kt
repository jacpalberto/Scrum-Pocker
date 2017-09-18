package com.example.pc_3.scrumpocker.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.pc_3.scrumpocker.R
import com.example.pc_3.scrumpocker.data.Card
import com.example.pc_3.scrumpocker.getHideDeckSetting
import com.example.pc_3.scrumpocker.ui.fragments.BackCardFragment
import com.example.pc_3.scrumpocker.ui.fragments.FrontCardFragment
import kotlinx.android.synthetic.main.activity_card.*

/**
 * Created by PC-3 on 16/09/2017.
 */
class CardActivity : AppCompatActivity() {
    companion object {
        fun newIntent(context: Context, card: Card): Intent {
            val intent: Intent = Intent(context, CardActivity::class.java)
            intent.putExtra("CARD_VALUE", card.value)
            intent.putExtra("CARD_TYPE", card.type)
            return intent
        }
    }

    var mShowingBack = true
    var cardValue: String = ""
    var cardType: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)
        init()
    }

    private fun init() {
        cardValue = intent.extras.get("CARD_VALUE") as String
        cardType = intent.extras.get("CARD_TYPE") as String
        setupToolbar()
        setupCardFragments()
        container.setOnClickListener { flipCard() }
    }

    private fun setupCardFragments() {
        val hideCard = getHideDeckSetting()
        if (hideCard) {
            fragmentManager.beginTransaction()
                    .add(R.id.container, BackCardFragment())
                    .commit()
            mShowingBack = true
        } else {
            val frontCardFragment = FrontCardFragment()
            val bundle = Bundle()
            bundle.putString("value", cardValue)
            bundle.putSerializable("type", cardType)
            frontCardFragment.arguments = bundle
            fragmentManager.beginTransaction()
                    .add(R.id.container, frontCardFragment)
                    .commit()
            mShowingBack = false
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = null
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_material)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    private fun flipCard() {
        if (mShowingBack) flipToFrontCard()
        else flipToBackCard()
        mShowingBack = !mShowingBack
    }

    private fun flipToFrontCard() {
        val frontCardFragment = FrontCardFragment()
        val bundle = Bundle()
        bundle.putString("value", cardValue)
        bundle.putSerializable("type", cardType)
        frontCardFragment.arguments = bundle
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out,
                        R.animator.card_flip_rigth_in,
                        R.animator.card_flip_rigth_out)
                .replace(R.id.container, frontCardFragment)
                .commit()
    }

    private fun flipToBackCard() {
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out,
                        R.animator.card_flip_rigth_in,
                        R.animator.card_flip_rigth_out)
                .replace(R.id.container, BackCardFragment())
                .commit()
    }

}