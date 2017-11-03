package com.messcat.kotlin.base

import android.support.v7.widget.RecyclerView
import android.view.View
import com.messcat.mclibrary.model.SelectBean

/**
 * Created by Administrator on 2017/8/16 0016.
 */
abstract class BaseRecyclerViewAdapter<T, D : BaseRecyclerViewHolder?> : RecyclerView.Adapter<D>() {

    private var dataList: MutableList<T> = ArrayList<T>()
    private var selectList = ArrayList<SelectBean>()


    /**
     * 如果headerView不为空，也就是第一个Item有内容，所以要从第二个Item开始获取数据
     */
    override fun onBindViewHolder(holder: D?, position: Int) {
        selectList.add(SelectBean(false))
        onBaseBindViewHolder(holder, dataList.get(position), position, holder?.mView, selectList[position].isSelect)
    }

    abstract fun onBaseBindViewHolder(holder: D?, objectData: T, position: Int, itemView: View?, isSelect: Boolean?)
    /**
     * 如果headerView不为空就加一
     */
    override fun getItemCount(): Int = if (dataList == null) 0 else dataList.size

    /**
     * 是否选中
     */
    fun initSelect(position: Int) {
        if (selectList.size > 0) {
            selectList.clear()
        }
        for ((index, value) in dataList.withIndex()) {
            if (index == position) {
                selectList.add(SelectBean(true))
            } else {
                selectList.add(SelectBean(false))
            }
        }
    }

    /**
     * 刷新
     */
    fun refreshList(list: MutableList<T>?) {
        if (dataList.size > 0) {
            dataList.clear()
        }
        loadList(list)
    }

    /**
     * 加载
     */
    fun loadList(list: MutableList<T>?) {
        dataList.addAll(list!!)
        notifyDataSetChanged()
    }

    /**
     * 删除Item
     */
    fun removeItem(position: Int) {
        if (dataList.size > 0 && dataList.size - 1 >= position) {
            if (dataList.get(position) != null) {
                dataList.remove(dataList.get(position))
            }
        }
        notifyDataSetChanged()
    }

    /**
     * 获取集合
     */
    fun getList(): MutableList<T> = dataList

    /**
     * 清除所有数据
     */
    fun clearData() {
        dataList.clear()
        notifyDataSetChanged()
    }

    /**
     * 获取集合的数量
     */
    fun getListSize(): Int = dataList.size
}