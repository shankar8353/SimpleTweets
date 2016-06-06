// Generated code from Butter Knife. Do not modify!
package com.codepath.apps.simpletweets.activities;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class ComposeActivity$$ViewBinder<T extends ComposeActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131492969, "field 'ivProfile'");
    target.ivProfile = finder.castView(view, 2131492969, "field 'ivProfile'");
    view = finder.findRequiredView(source, 2131492970, "field 'tvUser'");
    target.tvUser = finder.castView(view, 2131492970, "field 'tvUser'");
    view = finder.findRequiredView(source, 2131492971, "field 'tvScreenName'");
    target.tvScreenName = finder.castView(view, 2131492971, "field 'tvScreenName'");
    view = finder.findRequiredView(source, 2131492972, "field 'etBody'");
    target.etBody = finder.castView(view, 2131492972, "field 'etBody'");
    view = finder.findRequiredView(source, 2131492974, "field 'tvCount'");
    target.tvCount = finder.castView(view, 2131492974, "field 'tvCount'");
    view = finder.findRequiredView(source, 2131492973, "field 'btnTweet'");
    target.btnTweet = finder.castView(view, 2131492973, "field 'btnTweet'");
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends ComposeActivity> implements Unbinder {
    private T target;

    protected InnerUnbinder(T target) {
      this.target = target;
    }

    @Override
    public final void unbind() {
      if (target == null) throw new IllegalStateException("Bindings already cleared.");
      unbind(target);
      target = null;
    }

    protected void unbind(T target) {
      target.ivProfile = null;
      target.tvUser = null;
      target.tvScreenName = null;
      target.etBody = null;
      target.tvCount = null;
      target.btnTweet = null;
    }
  }
}
