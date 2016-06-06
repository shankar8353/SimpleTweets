// Generated code from Butter Knife. Do not modify!
package com.codepath.apps.simpletweets.adapters;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class TweetsAdapter$ViewHolder$$ViewBinder<T extends TweetsAdapter.ViewHolder> implements ViewBinder<T> {
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
    view = finder.findRequiredView(source, 2131492988, "field 'tvRelTime'");
    target.tvRelTime = finder.castView(view, 2131492988, "field 'tvRelTime'");
    view = finder.findRequiredView(source, 2131492987, "field 'tvText'");
    target.tvText = finder.castView(view, 2131492987, "field 'tvText'");
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends TweetsAdapter.ViewHolder> implements Unbinder {
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
      target.tvRelTime = null;
      target.tvText = null;
    }
  }
}
