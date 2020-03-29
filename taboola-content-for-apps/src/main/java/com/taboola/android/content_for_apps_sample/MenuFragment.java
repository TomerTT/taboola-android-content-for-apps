package com.taboola.android.content_for_apps_sample;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.taboola.android.content_for_apps_sample.sdk_via_js.MidWidgetWithFeedJsFragment;
import com.taboola.android.content_for_apps_sample.sdk_via_native.FeedWithMiddleArticleInsideRecyclerViewFragment;
import com.taboola.android.content_for_apps_sample.webview_in_app.WebViewInAppFragment;

public class MenuFragment extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewGroup viewGroup = view.findViewById(R.id.main_menu_lyt);

        addHeader(getString(R.string.sdk_via_native), viewGroup);
        addButton(getString(R.string.std_mid_article_with_feed_rv), R.id.std_mid_article_with_feed_rv, viewGroup);

        addHeader(getString(R.string.sdk_via_js), viewGroup);
        addButton(getString(R.string.js_mid_article_with_feed), R.id.js_mid_article_with_feed, viewGroup);

        addHeader(getString(R.string.webview_in_app), viewGroup);
        addButton(getString(R.string.tbl_custom_tab), R.id.tbl_custom_tab, viewGroup);
    }


    @Override
    public void onClick(View v) {

        String screenName = v.getTag().toString();
        Fragment fragmentToOpen = null;
        switch (v.getId()) {

            case R.id.js_mid_article_with_feed:
                fragmentToOpen = new MidWidgetWithFeedJsFragment();
                break;

            case R.id.std_mid_article_with_feed_rv:
                fragmentToOpen = new FeedWithMiddleArticleInsideRecyclerViewFragment();
                break;

            case R.id.tbl_custom_tab:
                fragmentToOpen = new WebViewInAppFragment();
                break;

        }

        if (fragmentToOpen != null) {
            openFragment(fragmentToOpen, screenName);
        }
    }

    private void openFragment(Fragment fragment, String screenName) {
        if (mListener != null) {
            mListener.onMenuItemClicked(fragment, screenName);
        }
    }

    private void addHeader(String title, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        TextView textView = (TextView) LayoutInflater.from(context).inflate(R.layout.menu_header_item,
                viewGroup, false);
        textView.setText(title);
        viewGroup.addView(textView, viewGroup.getChildCount() - 1);
    }

    private void addButton(String screenName, int id, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        TextView textView = (TextView) LayoutInflater.from(context).inflate(R.layout.button_item, viewGroup, false);
        textView.setText(screenName);
        textView.setTag(screenName);
        textView.setId(id);
        textView.setOnClickListener(this);

        viewGroup.addView(textView, viewGroup.getChildCount() - 1);
    }

    public interface OnFragmentInteractionListener {
        void onMenuItemClicked(Fragment fragment, String screenName);
    }
}