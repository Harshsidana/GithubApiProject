package com.example.githubapiproject.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<in BaseModel>(view: View):RecyclerView.ViewHolder(view) {
    abstract fun bindData(baseModel:BaseModel)
}