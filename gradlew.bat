// Generated code from Butter Knife. Do not modify!
package com.fashiongallery.info.admin;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.fashiongallery.info.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrderDetailsActivity_ViewBinding implements Unbinder {
  private OrderDetailsActivity target;

  private View view7f0a0125;

  private View view7f0a006f;

  @UiThread
  public OrderDetailsActivity_ViewBinding(OrderDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OrderDetailsActivity_ViewBinding(final OrderDetailsActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_back, "field 'ivBack' and method 'onViewClicked'");
    target.ivBack = Utils.castView(view, R.id.iv_back, "field 'ivBack'", ImageView.class);
    view7f0a0125 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvShippingAddress = Utils.findRequiredViewAsType(source, R.id.tvShippingAddress, "field 'tvShippingAddress'", TextView.class);
    target.rvProducts = Utils.findRequiredViewAsType(source, R.id.rvProducts, "field 'rvProducts'", RecyclerView.class);
    target.tvTotalAmount = Utils.findRequiredViewAsType(source, R.id.tvTotalAmount, "field 'tvTotalAmount'", TextView.class);
    target.tvDeliveryCharge = Utils.findRequiredViewAsType(source, R.id.tvDeliveryCharge, "field 'tvDeliveryCharge'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btnDelivered, "field 'btnDelivered' and method 'onViewClicked'");
    target.btnDelivered = Utils.castView(view, R.id.btnDelivered, "field 'btnDelivered'", Button.class);
    view7f0a006f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    OrderDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvShippingAddress = null;
    target.rvProducts = null;
    target.tvTotalAmount = null;
    target.tvDeliveryCharge = null;
    target.btnDelivered = null;

    view7f0a0125.setOnClickL