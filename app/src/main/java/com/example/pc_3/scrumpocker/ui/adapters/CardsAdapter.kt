package com.example.pc_3.scrumpocker.ui.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pc_3.scrumpocker.data.Card
import com.example.pc_3.scrumpocker.R
import kotlinx.android.synthetic.main.item_card.view.*
import android.animation.Animator
import android.animation.Animator.AnimatorListener
import com.example.pc_3.scrumpocker.getFileName
import com.example.pc_3.scrumpocker.getSmallFileName


/**
 * Created by PC-3 on 16/09/2017.
 */
class CardsAdapter(val context: Context, val cards: List<Card>, private val itemClick: (Card) -> Unit)
    : RecyclerView.Adapter<CardsAdapter.ViewHolder>() {

    override fun getItemCount() = cards.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindElements(cards[position], context, itemClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_card, parent, false))

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bindElements(card: Card, context: Context, itemClick: (Card) -> Unit) {
            val fileName= context.getSmallFileName(card.value,card.type)
            val id = context.resources.getIdentifier(fileName, null, context.packageName)
            itemView.ivSmallCard.setImageResource(id)
            itemView.setOnClickListener {
                itemClick(card)
            }
        }
    }
}