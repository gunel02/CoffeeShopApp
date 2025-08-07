package com.example.coffeeshop.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.coffeeshop.databinding.ItemCartRvBinding
import com.example.coffeeshop.domain.models.PopularModel
import com.example.coffeeshop.helper.ChangeNumberItemsListener
import com.example.coffeeshop.helper.ManagmentCart

class CartAdapter(
    private val listItemSelected: ArrayList<PopularModel>,
    context: Context,
    var changeNumberItemListener: ChangeNumberItemsListener? = null

) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private val managementCart = ManagmentCart(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.CartViewHolder {
        val binding =
            ItemCartRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.CartViewHolder, position: Int) {
        val item = listItemSelected[position]

        holder.binding.titleTxt.text = item.title
        holder.binding.feeEachItem.text = "$${item.price}"
        holder.binding.totalEachItem.text = "$${item.numberInCart * item.price}"
        holder.binding.numberInCartTxt.text = item.numberInCart.toString()

        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .apply(RequestOptions().transform(CenterCrop()))
            .into(holder.binding.picCart)

        holder.binding.plusBtn.setOnClickListener {
            managementCart.plusItem(listItemSelected, position, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemListener?.onChanged()
                }
            })
        }
        holder.binding.minusBtn.setOnClickListener {
            managementCart.minusItem(
                listItemSelected,
                position,
                object : ChangeNumberItemsListener {
                    override fun onChanged() {
                        notifyDataSetChanged()
                        changeNumberItemListener?.onChanged()
                    }
                })
        }
        holder.binding.removeItemBtn.setOnClickListener {
            managementCart.removeItem(
                listItemSelected,
                position,
                object : ChangeNumberItemsListener {
                    override fun onChanged() {
                        notifyDataSetChanged()
                        changeNumberItemListener?.onChanged()
                    }
                })
        }


    }

    override fun getItemCount(): Int = listItemSelected.size



    inner class CartViewHolder(val binding: ItemCartRvBinding) :
        RecyclerView.ViewHolder(binding.root)
}