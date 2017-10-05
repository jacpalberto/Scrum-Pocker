package com.example.pc_3.scrumpocker.ui.activities

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.pc_3.scrumpocker.R
import com.example.pc_3.scrumpocker.data.Card
import com.example.pc_3.scrumpocker.getFileName
import com.example.pc_3.scrumpocker.getHideDeckSetting
import kotlinx.android.synthetic.main.activity_card.*


/**
 * Created by PC-3 on 16/09/2017.
 */
class CardActivity : AppCompatActivity() {
    companion object {
        fun newIntent(context: Context, card: Card): Intent {
            val intent = Intent(context, CardActivity::class.java)
            intent.putExtra("CARD_VALUE", card.value)
            intent.putExtra("CARD_TYPE", card.type)
            return intent
        }
    }

    var mShowingBack = true
    var cardValue: String = ""
    var cardType: String = ""
    private var idBack = 0
    private var idFront = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)
        init()
    }

    private fun init() {
        cardValue = intent.extras.get("CARD_VALUE") as String
        cardType = intent.extras.get("CARD_TYPE") as String
        setupToolbar()
        setupImage()
        ivCard.setOnClickListener { flipCard() }
    }


    private fun setupImage() {
        val fileName = getFileName(cardValue, cardType)
        idBack = resources.getIdentifier("drawable/card_back", null, packageName)
        idFront = resources.getIdentifier(fileName, null, packageName)
        val hideCard = getHideDeckSetting()
        if (hideCard) ivCard.setImageResource(idBack)
        else ivCard.setImageResource(idFront)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = null
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.arrow_back)
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
        ivCard.rotationY = 0f
        ivCard.animate().rotationY(90f).setListener(object : AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                ivCard.setImageResource(idFront)
                ivCard.rotationY = 270f
                ivCard.animate().rotationY(360f).setListener(null)
            }

            override fun onAnimationCancel(animation: Animator) {}
        })
    }

    private fun flipToBackCard() {
        ivCard.rotationY = 0f
        ivCard.animate().rotationY(90f).setListener(object : AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                ivCard.setImageResource(idBack)
                ivCard.rotationY = 270f
                ivCard.animate().rotationY(360f).setListener(null)
            }

            override fun onAnimationCancel(animation: Animator) {}
        })
    }
}