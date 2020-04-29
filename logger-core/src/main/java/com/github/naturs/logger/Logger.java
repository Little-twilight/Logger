package com.github.naturs.logger;

import com.github.naturs.logger.adapter.LogAdapter;
import com.github.naturs.logger.internal.ObjectConverter;
import com.github.naturs.logger.strategy.converter.ConverterStrategy;
import com.github.naturs.logger.strategy.converter.LogConverter;
import com.github.naturs.logger.strategy.format.FormatStrategy;
import org.jetbrains.annotations.Nullable;

/**
 * But more pretty, simple and powerful
 */
public class Logger {

	public static final int VERBOSE = 2;
	public static final int DEBUG = 3;
	public static final int INFO = 4;
	public static final int WARN = 5;
	public static final int ERROR = 6;
	public static final int ASSERT = 7;

	protected static Printer printer = new LoggerPrinter();

	protected Logger() {
		throw new AssertionError("No instances.");
	}

	public static void printer(Printer printer) {
		if (printer != null) {
			Logger.printer = printer;
		}
	}

	public static void setDebuggable(boolean debuggable) {
		Logger.printer.setDebuggable(debuggable);
	}

	public static void addLogAdapter(LogAdapter adapter) {
		printer.addAdapter(adapter);
	}

	public static void clearLogAdapters() {
		printer.clearLogAdapters();
	}

	public static void setLogConverter(LogConverter converter) {
		if (converter != null) {
			ObjectConverter.converter(converter);
		}
	}

	public static void addConverterStrategy(ConverterStrategy strategy) {
		ObjectConverter.add(strategy);
	}

	@Deprecated
	public static Printer t(String tag) {
		return printer.t(tag);
	}

	/**
	 * Given tag will be used as tag only once for this method call regardless of the tag that's been
	 * set during initialization. After this invocation, the general tag that's been set will
	 * be used for the subsequent log calls
	 */
	public static Printer tag(String tag) {
		return printer.tag(tag);
	}

	/**
	 * Given FormatStrategy will be used only once for this method call regardless of the tag that's been
	 * set during initialization. After this invocation, the general strategy that's been set will
	 * be used for the subsequent log calls.
	 */
	public static Printer strategy(FormatStrategy strategy) {
		return printer.strategy(strategy);
	}

	public static Printer invokeClass(Class clazz) {
		return printer.invokeClass(clazz);
	}

	/**
	 * General log function that accepts all configurations as parameter
	 */
	public static void log(int priority, String tag, String message, Throwable throwable,
                           @Nullable FormatStrategy strategy, @Nullable Class invokeClass) {
		printer.log(priority, tag, message, throwable, strategy, invokeClass);
	}

	public static void log(int priority, String tag, String message, Object... args) {
		switch (priority) {
			case VERBOSE:
				printer.tag(tag);
				printer.v(tag, message, args);
				break;
			case DEBUG:
				printer.tag(tag);
				printer.d(tag, message, args);
				break;
			case INFO:
				printer.tag(tag);
				printer.i(tag, message, args);
				break;
			case WARN:
				printer.tag(tag);
				printer.w(tag, message, args);
				break;
			case ERROR:
				printer.tag(tag);
				printer.e(tag, message, args);
				break;
			case ASSERT:
			default:
				throw new RuntimeException(String.format("Invalid priority: %s", priority));
		}
	}

	public static void d(String message, Object... args) {
		printer.d(message, args);
	}

	public static void obj(Object object) {
		printer.obj(object);
	}

	public static void obj(String message, Object object) {
		printer.obj(message, object);
	}

	public static void obj(int priority, String message, Object object) {
		printer.obj(priority, message, object);
	}

	public static void e(String message, Object... args) {
		printer.e(null, message, args);
	}

	public static void e(Throwable throwable, String message, Object... args) {
		printer.e(throwable, message, args);
	}

	public static void i(String message, Object... args) {
		printer.i(message, args);
	}

	public static void v(String message, Object... args) {
		printer.v(message, args);
	}

	public static void w(String message, Object... args) {
		printer.w(message, args);
	}

	/**
	 * Tip: Use this for exceptional situations to log
	 * ie: Unexpected errors etc
	 */
	public static void wtf(String message, Object... args) {
		printer.wtf(message, args);
	}

	/**
	 * Formats the given json content and print it
	 */
	public static void json(String json) {
		printer.json(json);
	}

	/**
	 * Formats the given xml content and print it
	 */
	public static void xml(String xml) {
		printer.xml(xml);
	}

	public static void printException(String tag, Throwable throwable) {
		printer.tag(tag);
		printer.e(throwable, "");
	}

	public static void printException(Throwable throwable) {
		printer.e(throwable, "");
	}

	public static void d(String tag, String message, Object... args) {
		printer.tag(tag);
		printer.d(message, args);
	}

	public static void obj(String tag, String message, Object object) {
		printer.tag(tag);
		printer.obj(message, object);
	}

	public static void obj(int priority, String tag, String message, Object object) {
		printer.tag(tag);
		printer.obj(priority, message, object);
	}

	public static void e(String tag, String message, Object... args) {
		printer.tag(tag);
		printer.e(message, args);
	}

	public static void e(String tag, Throwable throwable, String message, Object... args) {
		printer.tag(tag);
		printer.e(throwable, message, args);
	}

	public static void i(String tag, String message, Object... args) {
		printer.tag(tag);
		printer.i(message, args);
	}

	public static void v(String tag, String message, Object... args) {
		printer.tag(tag);
		printer.v(message, args);
	}

	public static void w(String tag, String message, Object... args) {
		printer.tag(tag);
		printer.w(message, args);
	}

	/**
	 * Tip: Use this for exceptional situations to log
	 * ie: Unexpected errors etc
	 */
	public static void wtf(String tag, String message, Object... args) {
		printer.tag(tag);
		printer.wtf(message, args);
	}

	/**
	 * Formats the given json content and print it
	 */
	public static void json(String tag, String json) {
		printer.tag(tag);
		printer.json(json);
	}

	/**
	 * Formats the given xml content and print it
	 */
	public static void xml(String tag, String xml) {
		printer.tag(tag);
		printer.xml(xml);
	}

}
