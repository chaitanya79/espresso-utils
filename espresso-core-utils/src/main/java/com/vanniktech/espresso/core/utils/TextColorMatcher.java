package com.vanniktech.espresso.core.utils;

import android.graphics.Color;
import android.support.annotation.CheckResult;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.TextView;
import org.hamcrest.Description;

public final class TextColorMatcher extends BoundedMatcher<View, TextView> {
  @CheckResult public static TextColorMatcher withTextColorRes(@ColorRes final int colorRes) {
    return new TextColorMatcher(ColorChecker.fromRes(colorRes));
  }

  @CheckResult public static TextColorMatcher withTextColor(@ColorInt final int color) {
    return new TextColorMatcher(ColorChecker.from(color));
  }

  @CheckResult public static TextColorMatcher withTextColor(final String color) {
    return withTextColor(Color.parseColor(color));
  }

  private final ColorChecker colorChecker;

  private TextColorMatcher(final ColorChecker colorChecker) {
    super(TextView.class);

    this.colorChecker = colorChecker;
  }

  @Override protected boolean matchesSafely(final TextView item) {
    final int color = item.getCurrentTextColor();
    return colorChecker.matches(color, item.getContext());
  }

  @Override public void describeTo(final Description description) {
    description.appendText("with text color: ");
  }
}
