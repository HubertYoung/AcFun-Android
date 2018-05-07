package com.kento.common.baserx.event.inner;


import com.kento.common.baserx.event.Subscribe;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author:Yang
 * @date:2017/11/30 10:31
 * @since:V1.0
 * @desc:ddframework.gent.common.baserx.event.inner
 * @param: 根据注解查找事件接收方法
 */
public class EventFind {
	public static EventComposite findAnnotatedSubscriberMethods( Object listenerClass, CompositeDisposable compositeDisposable ) {
		Set< EventSubscriber > producerMethods = new HashSet<>();
		return findAnnotatedMethods( listenerClass, producerMethods, compositeDisposable );
	}

	private static EventComposite findAnnotatedMethods( Object listenerClass, Set< EventSubscriber > subscriberMethods, CompositeDisposable compositeDisposable ) {
		for (Method method : listenerClass.getClass()
										  .getDeclaredMethods()) {
			if ( method.isBridge() ) {
				continue;
			}
			if ( method.isAnnotationPresent( Subscribe.class ) ) {
				Class< ? >[] parameterTypes = method.getParameterTypes();
				if ( parameterTypes.length != 1 ) {
					throw new IllegalArgumentException( "Method " + method + " has @Subscribe annotation but requires " + parameterTypes.length + " arguments.  Methods must require a single argument." );
				}

				Class< ? > parameterClazz = parameterTypes[ 0 ];
				if ( ( method.getModifiers() & Modifier.PUBLIC ) == 0 ) {
					throw new IllegalArgumentException( "Method " + method + " has @EventSubscribe annotation on " + parameterClazz + " " + "but is not 'public'." );
				}

				Subscribe annotation = method.getAnnotation( Subscribe.class );
				ThreadMode thread = annotation.threadMode();

				EventSubscriber subscriberEvent = new EventSubscriber( listenerClass, method, thread );
				if ( !subscriberMethods.contains( subscriberEvent ) ) {
					subscriberMethods.add( subscriberEvent );//添加事件订阅者
					compositeDisposable.add( subscriberEvent.getDisposable() );//管理订阅，方便取消订阅
				}
			}
		}
		return new EventComposite( compositeDisposable, listenerClass, subscriberMethods );
	}
}
