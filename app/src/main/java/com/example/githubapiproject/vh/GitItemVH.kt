package com.example.githubapiproject.vh

import android.view.View
import com.example.githubapiproject.base.BaseModel
import com.example.githubapiproject.base.BaseViewHolder
import com.example.githubapiproject.response.GitResponseModel
import kotlinx.android.synthetic.main.git_item_layout.view.*

class GitItemVH(private val view: View) : BaseViewHolder<BaseModel>(view) {
    override fun bindData(baseModel: BaseModel) {
        with(view)
        {
            if (baseModel is GitResponseModel) {
                tvFirstName.text = baseModel.firstName
                tvLastName.text = baseModel.lastName
                tvEmail.text = baseModel.email
                tvId.text = baseModel.id
            }
        }

    }
}