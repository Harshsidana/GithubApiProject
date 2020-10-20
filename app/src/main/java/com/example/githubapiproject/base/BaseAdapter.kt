package com.example.githubapiproject.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T:BaseViewHolder<BaseModel>>:RecyclerView.Adapter<T>(){
    open var listItem:MutableList<BaseModel> = mutableListOf()

    fun addList(list:List<BaseModel>)
    {
        listItem=list.toMutableList()
        notifyDataSetChanged()
    }
    fun getItemAtPosition(position:Int):BaseModel
    {
        return listItem[position]
    }


}