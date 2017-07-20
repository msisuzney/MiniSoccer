package com.msisuzney.minisoccer.view.activities

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceActivity
import com.msisuzney.minisoccer.DQDApi.model.twins.Feedlist
import com.msisuzney.minisoccer.R
import com.msisuzney.minisoccer.adapter.TwinsRVAdapter
import com.msisuzney.minisoccer.presenter.TwinsPresenter
import com.msisuzney.minisoccer.view.TwinsView

class TwinsActivityKotlin : MvpLceActivity<SwipeRefreshLayout, List<Feedlist>, TwinsView, TwinsPresenter>(),
        TwinsView, SwipeRefreshLayout.OnRefreshListener {

    lateinit var toolbar: Toolbar
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: TwinsRVAdapter
    private var isLoadingMore = false
    private  var kind :String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twins)
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        toolbar = findViewById<Toolbar>(R.id.toolbar)
        val intent:Intent? = intent
        kind = intent?.getStringExtra(TwinsPresenter.KIND)
        if (intent == null || kind == null) {
            showError(Exception("请求数据错误"), false)
        } else {
            toolbar.title = if (kind.equals(TwinsPresenter.TWINS_KIND)) "Twins by kotlin" else "怡人 by kotlin"
        }
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { finish() }
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        adapter = TwinsRVAdapter(this)
        adapter.setListener { imgUrl, transitionView ->
            val intent = Intent(this@TwinsActivityKotlin, ImageActivity::class.java)
            intent.putExtra(ImageActivity.img_url, imgUrl)
            if (Build.VERSION.SDK_INT >= 21)
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this@TwinsActivityKotlin,
                        transitionView, "sharedImgView").toBundle())
            else startActivity(intent)
        }
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItemPos = linearLayoutManager.findLastVisibleItemPosition()
                if (lastVisibleItemPos+1 == adapter.itemCount){
                    if(!isLoadingMore){
                        isLoadingMore = true
                        loadData(TwinsPresenter.LOAD_MORE,kind)
                    }
                }
            }
        })
        contentView.setOnRefreshListener(this)
        loadData(TwinsPresenter.LOAD_FROM_DB,kind)
    }

    override fun setData(data: List<Feedlist>?) {
        adapter.setData(data)
        adapter.notifyDataSetChanged()
        contentView.isRefreshing  = false
    }

    override fun loadData(pullToRefresh: Boolean) {

    }

    fun loadData(mode:Int,kind:String?){
        presenter.loadData(mode,kind)
    }

    override fun getErrorMessage(e: Throwable?, pullToRefresh: Boolean): String = e.toString()

    override fun createPresenter(): TwinsPresenter = TwinsPresenter()

    override fun addData(list: MutableList<Feedlist>?) {
        adapter.addData(list)
    }

    override fun haveLoadMore(b: Boolean) {
        isLoadingMore = false
    }

    override fun onRefresh() {
        loadData(TwinsPresenter.LOAD_REFRESH,kind)
    }

    override fun showError(e: Throwable?, pullToRefresh: Boolean) {
        super.showError(e, pullToRefresh)
        contentView.isRefreshing = false
    }

}
