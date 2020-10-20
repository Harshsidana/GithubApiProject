package com.example.githubapiproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.githubapiproject.base.BaseAdapter
import com.example.githubapiproject.base.BaseModel
import com.example.githubapiproject.base.BaseViewHolder
import com.example.githubapiproject.R
import com.example.githubapiproject.vh.GitItemVH

class GithubAdapter : BaseAdapter<BaseViewHolder<BaseModel>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BaseModel> {
        val inflator = LayoutInflater.from(parent.context)
        return GitItemVH(inflator.inflate(R.layout.git_item_layout, parent, false))
    }

    override fun getItemCount(): Int {
       return listItem.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BaseModel>, position: Int) {
      holder.bindData(getItemAtPosition(position))
    }
}