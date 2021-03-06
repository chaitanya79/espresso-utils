package com.vanniktech.espresso.core.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.support.annotation.CheckResult;
import android.support.annotation.DrawableRes;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.TextView;
import org.hamcrest.Description;

import static android.os.Build.VERSION_CODES.JELLY_BEAN_MR1;
import static com.vanniktech.espresso.core.utils.TextViewDrawableMatcher.Type.DRAWABLE_BOTTOM;
import static com.vanniktech.espresso.core.utils.TextViewDrawableMatcher.Type.DRAWABLE_LEFT;
import static com.vanniktech.espresso.core.utils.TextViewDrawableMatcher.Type.DRAWABLE_RELATIVE_BOTTOM;
import static com.vanniktech.espresso.core.utils.TextViewDrawableMatcher.Type.DRAWABLE_RELATIVE_LEFT;
import static com.vanniktech.espresso.core.utils.TextViewDrawableMatcher.Type.DRAWABLE_RELATIVE_RIGHT;
import static com.vanniktech.espresso.core.utils.TextViewDrawableMatcher.Type.DRAWABLE_RELATIVE_TOP;
import static com.vanniktech.espresso.core.utils.TextViewDrawableMatcher.Type.DRAWABLE_RIGHT;
import static com.vanniktech.espresso.core.utils.TextViewDrawableMatcher.Type.DRAWABLE_TOP;
import static com.vanniktech.espresso.core.utils.Utils.INDEX_BOTTOM;
import static com.vanniktech.espresso.core.utils.Utils.INDEX_LEFT;
import static com.vanniktech.espresso.core.utils.Utils.INDEX_RIGHT;
import static com.vanniktech.espresso.core.utils.Utils.INDEX_TOP;
import static com.vanniktech.espresso.core.utils.Utils.NO_DRAWABLE;
import static com.vanniktech.espresso.core.utils.Utils.drawableMatches;

public final class TextViewDrawableMatcher extends BoundedMatcher<View, TextView> {
  @CheckResult public static TextViewDrawableMatcher withTextViewDrawableLeft(@DrawableRes final int resourceId) {
    return new TextViewDrawableMatcher(resourceId, DRAWABLE_LEFT);
  }

  @CheckResult public static TextViewDrawableMatcher withNoTextViewDrawableLeft() {
    return new TextViewDrawableMatcher(NO_DRAWABLE, DRAWABLE_LEFT);
  }

  @CheckResult @TargetApi(JELLY_BEAN_MR1) public static TextViewDrawableMatcher withTextViewDrawableRelativeLeft(@DrawableRes final int resourceId) {
    return new TextViewDrawableMatcher(resourceId, DRAWABLE_RELATIVE_LEFT);
  }

  @CheckResult @TargetApi(JELLY_BEAN_MR1) public static TextViewDrawableMatcher withNoTextViewDrawableRelativeLeft() {
    return new TextViewDrawableMatcher(NO_DRAWABLE, DRAWABLE_RELATIVE_LEFT);
  }

  @CheckResult public static TextViewDrawableMatcher withTextViewDrawableTop(@DrawableRes final int resourceId) {
    return new TextViewDrawableMatcher(resourceId, DRAWABLE_TOP);
  }

  @CheckResult public static TextViewDrawableMatcher withNoTextViewDrawableTop() {
    return new TextViewDrawableMatcher(NO_DRAWABLE, DRAWABLE_TOP);
  }

  @CheckResult @TargetApi(JELLY_BEAN_MR1) public static TextViewDrawableMatcher withTextViewDrawableRelativeTop(@DrawableRes final int resourceId) {
    return new TextViewDrawableMatcher(resourceId, DRAWABLE_RELATIVE_TOP);
  }

  @CheckResult @TargetApi(JELLY_BEAN_MR1) public static TextViewDrawableMatcher withNoTextViewDrawableRelativeTop() {
    return new TextViewDrawableMatcher(NO_DRAWABLE, DRAWABLE_RELATIVE_TOP);
  }

  @CheckResult public static TextViewDrawableMatcher withTextViewDrawableRight(@DrawableRes final int resourceId) {
    return new TextViewDrawableMatcher(resourceId, DRAWABLE_RIGHT);
  }

  @CheckResult public static TextViewDrawableMatcher withNoTextViewDrawableRight() {
    return new TextViewDrawableMatcher(NO_DRAWABLE, DRAWABLE_RIGHT);
  }

  @CheckResult @TargetApi(JELLY_BEAN_MR1) public static TextViewDrawableMatcher withTextViewDrawableRelativeRight(@DrawableRes final int resourceId) {
    return new TextViewDrawableMatcher(resourceId, DRAWABLE_RELATIVE_RIGHT);
  }

  @CheckResult @TargetApi(JELLY_BEAN_MR1) public static TextViewDrawableMatcher withNoTextViewDrawableRelativeRight() {
    return new TextViewDrawableMatcher(NO_DRAWABLE, DRAWABLE_RELATIVE_RIGHT);
  }

  @CheckResult public static TextViewDrawableMatcher withTextViewDrawableBottom(@DrawableRes final int resourceId) {
    return new TextViewDrawableMatcher(resourceId, DRAWABLE_BOTTOM);
  }

  @CheckResult public static TextViewDrawableMatcher withNoTextViewDrawableBottom() {
    return new TextViewDrawableMatcher(NO_DRAWABLE, DRAWABLE_BOTTOM);
  }

  @CheckResult @TargetApi(JELLY_BEAN_MR1) public static TextViewDrawableMatcher withTextViewDrawableRelativeBottom(@DrawableRes final int resourceId) {
    return new TextViewDrawableMatcher(resourceId, DRAWABLE_RELATIVE_BOTTOM);
  }

  @CheckResult @TargetApi(JELLY_BEAN_MR1) public static TextViewDrawableMatcher withNoTextViewDrawableRelativeBottom() {
    return new TextViewDrawableMatcher(NO_DRAWABLE, DRAWABLE_RELATIVE_BOTTOM);
  }

  private final int expectedId;
  private final Type type;

  private TextViewDrawableMatcher(final int expectedId, final Type type) {
    super(TextView.class);

    this.expectedId = expectedId;
    this.type = type;
  }

  @Override protected boolean matchesSafely(final TextView textView) {
    return drawableMatches(textView, type.getDrawable(textView), expectedId);
  }

  @Override public void describeTo(final Description description) {
    if (expectedId == NO_DRAWABLE) {
      description.appendText("with no ")
          .appendText(type.string)
          .appendText(" drawable");
    } else {
      description.appendText("with ")
          .appendText(type.string)
          .appendText(" drawable from resource id: ")
          .appendValue(expectedId);
    }
  }

  enum Type {
    DRAWABLE_LEFT(INDEX_LEFT, false, "left"),
    DRAWABLE_RELATIVE_LEFT(INDEX_LEFT, true, "relative left"),
    DRAWABLE_TOP(INDEX_TOP, false, "top"),
    DRAWABLE_RELATIVE_TOP(INDEX_TOP, true, "relative top"),
    DRAWABLE_RIGHT(INDEX_RIGHT, false, "right"),
    DRAWABLE_RELATIVE_RIGHT(INDEX_RIGHT, true, "relative right"),
    DRAWABLE_BOTTOM(INDEX_BOTTOM, false, "bottom"),
    DRAWABLE_RELATIVE_BOTTOM(INDEX_BOTTOM, true, "relative bottom");

    private final int index;
    private final boolean isRelative;
    final String string;

    Type(final int index, final boolean isRelative, final String string) {
      this.index = index;
      this.isRelative = isRelative;
      this.string = string;
    }

    @SuppressLint("NewApi") public Drawable getDrawable(final TextView textView) {
      final Drawable[] drawables = isRelative ? textView.getCompoundDrawablesRelative() : textView.getCompoundDrawables();
      return drawables[index];
    }
  }
}
