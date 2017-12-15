package com.example.android.thenews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

class NewsLoader extends AsyncTaskLoader<List<News>> {

	/** URL for querying the API */
	private String mQueryUrl;
	private List<News> mData;

	NewsLoader(Context context, String queryUrl) {
		super(context);
		this.mQueryUrl = queryUrl;
	}

	@Override
	protected void onStartLoading() {
		if (mData != null) {
			deliverResult(mData); // Serve cache data
		} else {
			forceLoad(); // Data does not exist
		}
	}

	@Override
	public List<News> loadInBackground() {
		// Exit early when no URL string is provided
		if (mQueryUrl == null) {
			return null;
		}

		// Query the API and return the list of top news stories
		return NewsQueryUtils.fetchNews(mQueryUrl);
	}

	@Override
	public void deliverResult(@Nullable List<News> data) {
		mData = data; // Cache data
		super.deliverResult(data);
	}
}
