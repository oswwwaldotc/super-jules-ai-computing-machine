package com.framework.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

/**
 * Utility class for logging messages throughout the test framework.
 * Uses Log4j2 for storing logs both in console and local file (logs/app.log).
 * Provides convenient methods for logging at different levels: INFO, WARN, ERROR, DEBUG.
 */
public class LoggerUtil {

    private static final Logger logger = LogManager.getLogger(LoggerUtil.class);

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private LoggerUtil() {
        // Utility class - should not be instantiated
    }

    /**
     * Logs an informational message.
     * @param message The message to log
     */
    public static void logInfo(String message) {
        logger.info(message);
    }

    /**
     * Logs an informational message with formatted parameters.
     * @param message The message template
     * @param params Parameters to format into the message
     */
    public static void logInfo(String message, Object... params) {
        logger.info(message, params);
    }

    /**
     * Logs a warning message.
     * @param message The message to log
     */
    public static void logWarn(String message) {
        logger.warn(message);
    }

    /**
     * Logs a warning message with formatted parameters.
     * @param message The message template
     * @param params Parameters to format into the message
     */
    public static void logWarn(String message, Object... params) {
        logger.warn(message, params);
    }

    /**
     * Logs an error message.
     * @param message The message to log
     */
    public static void logError(String message) {
        logger.error(message);
    }

    /**
     * Logs an error message with an exception.
     * @param message The message to log
     * @param exception The exception to log
     */
    public static void logError(String message, Throwable exception) {
        logger.error(message, exception);
    }

    /**
     * Logs an error message with formatted parameters.
     * @param message The message template
     * @param params Parameters to format into the message
     */
    public static void logError(String message, Object... params) {
        logger.error(message, params);
    }

    /**
     * Logs a debug message.
     * @param message The message to log
     */
    public static void logDebug(String message) {
        logger.debug(message);
    }

    /**
     * Logs a debug message with formatted parameters.
     * @param message The message template
     * @param params Parameters to format into the message
     */
    public static void logDebug(String message, Object... params) {
        logger.debug(message, params);
    }

    /**
     * Sets a context value that will be included in all log messages for this thread.
     * Useful for tracking test execution context.
     * @param key The context key
     * @param value The context value
     */
    public static void setThreadContext(String key, String value) {
        ThreadContext.put(key, value);
    }

    /**
     * Clears a specific context value.
     * @param key The context key to remove
     */
    public static void clearThreadContext(String key) {
        ThreadContext.remove(key);
    }

    /**
     * Clears all thread context values.
     */
    public static void clearAllThreadContext() {
        ThreadContext.clearAll();
    }

    /**
     * Logs the start of a test step.
     * @param stepName The name of the step
     */
    public static void logStepStart(String stepName) {
        logger.info("========== STARTING STEP: {} ==========", stepName);
    }

    /**
     * Logs the completion of a test step.
     * @param stepName The name of the step
     */
    public static void logStepEnd(String stepName) {
        logger.info("========== STEP COMPLETED: {} ==========", stepName);
    }

    /**
     * Logs a test execution event.
     * @param eventName The name of the event
     * @param details Additional details about the event
     */
    public static void logTestEvent(String eventName, String details) {
        logger.info("[TEST EVENT] {} -> {}", eventName, details);
    }
}
