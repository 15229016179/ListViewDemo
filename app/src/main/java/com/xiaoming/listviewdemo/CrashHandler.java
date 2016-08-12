package com.xiaoming.listviewdemo;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * 	Copyright	2016	CoderDream's Eclipse
 * 
 * 	All right reserved.
 * 	
 * 	Created on 2016年3月2日 下午6:29:25
 * 	
 * 	Update on 2016年3月2日 下午6:29:25
 * 
 * 	@author xiaoming
 *	
 * 	@mail wangfeng.wf@warmdoc.com
 * 
 * 	@tags An overview of this file: 捕获程序全局异常
 * 
 */
public class CrashHandler implements UncaughtExceptionHandler {

	private static CrashHandler instance;

	private CrashHandler() {
	}

	public synchronized static CrashHandler getInstance() { // 同步方法，以免单例多线程环境下出现异常
		if (instance == null) {
			instance = new CrashHandler();
		}
		return instance;
	}

	public void init() { // 初始化，把当前对象设置成UncaughtExceptionHandler处理器
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) { // 当有未处理的异常发生时，就会来到这里。。
		Log.d("CrashHandler", "-------uncaughtException, thread: " + thread + " name: " + thread.getName() + " id: " + thread.getId() + "exception: "
				+ ex);
		ex.printStackTrace();
		System.exit(0);
	}
}
