package com.asiainfo.lib.adapter.annotation;

import android.support.annotation.LayoutRes;

import com.zhy.adapter.recyclerview.base.ItemViewDelegate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 类名
 *
 * @Author 温维远｛wenwy@asiainfo.com｝<br/>
 * @Date 2016/12/29 下午3:42
 * 功能介绍：
 */

public abstract class AnnotationDelegate<T> implements ItemViewDelegate<T>{

    private int layoutID = 0;

    @LayoutRes
    @Override
    public int getItemViewLayoutId() {
        if (layoutID > 0) {
            return layoutID;
        }
        layoutID = getLayoutFromAnnotation();
        return layoutID;
    }

    public int getLayoutFromAnnotation () {
        Class clz = this.getClass();
        int layoutID = 0;
        Annotation anno = clz.getAnnotation(DelegateInfo.class);
        if (anno != null) {
            Class<? extends Annotation> annoClz = anno.annotationType();
            try {
                Method method = annoClz.getMethod("layoutID");
                layoutID = (int)method.invoke(anno);
                return layoutID;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        layoutID = getLayoutFromAnnotationLayout();
        return layoutID;
    }

    private int getLayoutFromAnnotationLayout() {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            LayoutID anno = field.getAnnotation(LayoutID.class);
            if (anno != null) {
                try {
                    return field.getInt(this);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }
}
