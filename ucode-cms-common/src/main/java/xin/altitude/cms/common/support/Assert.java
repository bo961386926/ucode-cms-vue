package xin.altitude.cms.common.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

public abstract class Assert {

    public static void state(Boolean expression, String message) {
        if (expression == null || !expression) {
            printInvokeMethod();
            throw new IllegalStateException(message);
        }
    }

    public static void state(Boolean expression, String format, Object... args) {
        String message = MessageFormatter.arrayFormat(format, args).getMessage();
        if (expression == null || !expression) {
            printInvokeMethod();
            throw new IllegalStateException(message);
        }
    }


    public static void state(Boolean expression, Supplier<String> messageSupplier) {
        if (expression == null || !expression) {
            printInvokeMethod();
            throw new IllegalStateException(nullSafeGet(messageSupplier));
        }
    }


    public static void isTrue(Boolean expression, String message) {
        if (expression == null || !expression) {
            printInvokeMethod();
            throw new IllegalArgumentException(message);
        }
    }

    public static void isTrue(Boolean expression, String format, Object... args) {
        String message = MessageFormatter.arrayFormat(format, args).getMessage();
        if (expression == null || !expression) {
            printInvokeMethod();
            throw new IllegalStateException(message);
        }
    }


    public static void isTrue(Boolean expression, Supplier<String> messageSupplier) {
        if (expression == null || !expression) {
            printInvokeMethod();
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }


    public static void isNull(@Nullable Object object, String message) {
        if (object != null) {
            printInvokeMethod();
            throw new IllegalArgumentException(message);
        }
    }

    public static void isNull(@Nullable Object object, String format, Object... args) {
        String message = MessageFormatter.arrayFormat(format, args).getMessage();
        if (object != null) {
            printInvokeMethod();
            throw new IllegalStateException(message);
        }
    }


    public static void isNull(@Nullable Object object, Supplier<String> messageSupplier) {
        if (object != null) {
            printInvokeMethod();
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }


    public static void notNull(@Nullable Object object, String message) {
        if (object == null) {
            printInvokeMethod();
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(@Nullable Object object, String format, Object... args) {
        String message = MessageFormatter.arrayFormat(format, args).getMessage();
        if (object == null) {
            printInvokeMethod();
            throw new IllegalStateException(message);
        }
    }


    public static void notNull(@Nullable Object object, Supplier<String> messageSupplier) {
        if (object == null) {
            printInvokeMethod();
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }


    public static void hasLength(@Nullable String text, String message) {
        if (!StringUtils.hasLength(text)) {
            printInvokeMethod();
            throw new IllegalArgumentException(message);
        }
    }


    public static void hasLength(@Nullable String text, Supplier<String> messageSupplier) {
        if (!StringUtils.hasLength(text)) {
            printInvokeMethod();
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }


    public static void hasText(@Nullable String text, String message) {
        if (!StringUtils.hasText(text)) {
            printInvokeMethod();
            throw new IllegalArgumentException(message);
        }
    }


    public static void hasText(@Nullable String text, Supplier<String> messageSupplier) {
        if (!StringUtils.hasText(text)) {
            printInvokeMethod();
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }


    public static void doesNotContain(@Nullable String textToSearch, String substring, String message) {
        if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) && textToSearch.contains(substring)) {
            printInvokeMethod();
            throw new IllegalArgumentException(message);
        }
    }


    public static void doesNotContain(@Nullable String textToSearch, String substring, Supplier<String> messageSupplier) {
        if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) && textToSearch.contains(substring)) {
            printInvokeMethod();
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }


    public static void notEmpty(@Nullable Object[] array, String message) {
        if (ObjectUtils.isEmpty(array)) {
            printInvokeMethod();
            throw new IllegalArgumentException(message);
        }
    }


    public static void notEmpty(@Nullable Object[] array, Supplier<String> messageSupplier) {
        if (ObjectUtils.isEmpty(array)) {
            printInvokeMethod();
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }


    public static void noNullElements(@Nullable Object[] array, String message) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    printInvokeMethod();
                    throw new IllegalArgumentException(message);
                }
            }
        }
    }


    public static void noNullElements(@Nullable Object[] array, Supplier<String> messageSupplier) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    printInvokeMethod();
                    throw new IllegalArgumentException(nullSafeGet(messageSupplier));
                }
            }
        }
    }


    public static void notEmpty(@Nullable Collection<?> collection, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            printInvokeMethod();
            throw new IllegalArgumentException(message);
        }
    }


    public static void notEmpty(@Nullable Collection<?> collection, Supplier<String> messageSupplier) {
        if (CollectionUtils.isEmpty(collection)) {
            printInvokeMethod();
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }


    public static void noNullElements(@Nullable Collection<?> collection, String message) {
        if (collection != null) {
            for (Object element : collection) {
                if (element == null) {
                    printInvokeMethod();
                    throw new IllegalArgumentException(message);
                }
            }
        }
    }


    public static void noNullElements(@Nullable Collection<?> collection, Supplier<String> messageSupplier) {
        if (collection != null) {
            for (Object element : collection) {
                if (element == null) {
                    printInvokeMethod();
                    throw new IllegalArgumentException(nullSafeGet(messageSupplier));
                }
            }
        }
    }


    public static void notEmpty(@Nullable Map<?, ?> map, String message) {
        if (CollectionUtils.isEmpty(map)) {
            printInvokeMethod();
            throw new IllegalArgumentException(message);
        }
    }


    public static void notEmpty(@Nullable Map<?, ?> map, Supplier<String> messageSupplier) {
        if (CollectionUtils.isEmpty(map)) {
            printInvokeMethod();
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }


    public static void isInstanceOf(Class<?> type, @Nullable Object obj, String message) {
        notNull(type, "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            printInvokeMethod();
            instanceCheckFailed(type, obj, message);
        }
    }


    public static void isInstanceOf(Class<?> type, @Nullable Object obj, Supplier<String> messageSupplier) {
        notNull(type, "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            printInvokeMethod();
            instanceCheckFailed(type, obj, nullSafeGet(messageSupplier));
        }
    }


    public static void isInstanceOf(Class<?> type, @Nullable Object obj) {
        isInstanceOf(type, obj, "");
    }


    public static void isAssignable(Class<?> superType, @Nullable Class<?> subType, String message) {
        notNull(superType, "Supertype to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            printInvokeMethod();
            assignableCheckFailed(superType, subType, message);
        }
    }


    public static void isAssignable(Class<?> superType, @Nullable Class<?> subType, Supplier<String> messageSupplier) {
        notNull(superType, "Supertype to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            printInvokeMethod();
            assignableCheckFailed(superType, subType, nullSafeGet(messageSupplier));
        }
    }


    public static void isAssignable(Class<?> superType, Class<?> subType) {
        isAssignable(superType, subType, "");
    }


    private static void instanceCheckFailed(Class<?> type, @Nullable Object obj, @Nullable String msg) {
        String className = (obj != null ? obj.getClass().getName() : "null");
        String result = "";
        boolean defaultMessage = true;
        if (StringUtils.hasLength(msg)) {
            if (endsWithSeparator(msg)) {
                result = msg + " ";
            } else {
                result = messageWithTypeName(msg, className);
                defaultMessage = false;
            }
        }
        if (defaultMessage) {
            result = result + ("Object of class [" + className + "] must be an instance of " + type);
        }
        throw new IllegalArgumentException(result);
    }

    private static void assignableCheckFailed(Class<?> superType, @Nullable Class<?> subType, @Nullable String msg) {
        String result = "";
        boolean defaultMessage = true;
        if (StringUtils.hasLength(msg)) {
            if (endsWithSeparator(msg)) {
                result = msg + " ";
            } else {
                result = messageWithTypeName(msg, subType);
                defaultMessage = false;
            }
        }
        if (defaultMessage) {
            result = result + (subType + " is not assignable to " + superType);
        }
        throw new IllegalArgumentException(result);
    }

    private static boolean endsWithSeparator(String msg) {
        return (msg.endsWith(":") || msg.endsWith(";") || msg.endsWith(",") || msg.endsWith("."));
    }

    private static String messageWithTypeName(String msg, @Nullable Object typeName) {
        return msg + (msg.endsWith(" ") ? "" : ": ") + typeName;
    }

    @Nullable
    private static String nullSafeGet(@Nullable Supplier<String> messageSupplier) {
        return (messageSupplier != null ? messageSupplier.get() : null);
    }

    private static void printInvokeMethod() {
        // 获取当前线程的堆栈跟踪
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        // 第二个元素通常是我们想要的信息
        if (stackTraceElements.length > 3) {
            StackTraceElement caller = stackTraceElements[3];
            String className = caller.getClassName();
            Logger log = LoggerFactory.getLogger(className);
            // 获取类名、方法名、行号
            log.debug("断言异常调用方法的位置：{}#{}:{}", className, caller.getMethodName(), caller.getLineNumber());
        }
    }
}
