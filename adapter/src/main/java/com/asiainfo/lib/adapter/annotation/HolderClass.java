package com.asiainfo.lib.adapter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类名
 *
 * @Author 温维远｛wenwy@asiainfo.com｝<br/>
 * @Date 2016/12/29 下午3:30
 * 功能介绍：
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HolderClass {

}
