// Generated code from Butter Knife. Do not modify!
package com.codepath.apps.simpletweets.activities;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class TimelineActivity$$ViewBinder<T extends TimelineActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131492977, "field 'rvTweets'");
    target.rvTweets = finder.castView(view, 2131492977, "field 'rvTweets'");
    view = finder.findRequiredView(source, 2131492976, "field 'swipeContainer'");
    target.swipeContainer = finder.castView(view, 2131492976, "field 'swipeContainer'");
    view = finder.findRequiredView(source, 2131492975, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131492975, "field 'toolbar'");
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends TimelineActivity> implements Unbinder {
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
      target.rvTweets = null;
      target.swipeContainer = null;
      target.toolbar = null;
    }
  }
}
