#---------------------------------基本指令区----------------------------------
-ignorewarnings  #忽略警告文件
-optimizationpasses 5  # 指定代码的压缩级别
-dontusemixedcaseclassnames  # 是否使用大小写混合
-dontskipnonpubliclibraryclasses # 指定不去忽略非公共的库的类
-dontskipnonpubliclibraryclassmembers # 指定不去忽略非公共的库的类的成员
-dontpreverify   # 混淆时是否做预校验
-verbose  # 混淆时是否记录日志
-printmapping proguardMapping.txt # 生成原类名和混淆后的类名的映射文件
-optimizations !code/simplification/cast,!field/*,!class/merging/*  # 混淆时所采用的算法
-keepattributes *Annotation*,InnerClasses # 不混淆Annotation
-keepattributes Signature # 不混淆泛型
-keepattributes SourceFile,LineNumberTable # 抛出异常时保留代码行号
-keepattributes Annotation
-keepattributes Signature

#---------------------------------默认保留区---------------------------------
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}
-keep public class * extends android.support.v4.**
-keep class android.support.v4.** { *; }

-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}

-keepclasseswithmembernames class * { # 保持 native 方法不被混淆
    native <methods>;
}
-keepclassmembers class * extends android.app.Activity{  # 保持自定义控件类不被混淆
    public void *(android.view.View);
}
-keepclassmembers enum * { # 保持枚举 enum 类不被混淆
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep public class * extends android.view.View{ # 保持自定义控件类不被混淆
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * { # 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public <init>(android.content.Context);

}
-keep class * implements android.os.Parcelable { # 保持 Parcelable 不被混淆
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keep class **.R$* { # R 文件不被混淆
 *;
}
-keepclassmembers class * {
    void *(**On*Event);
}

###########################################################################################
#这句非常重要，主要是滤掉 com.example.external.ui 下的.class文件不进行混淆编译
-keep class com.example.external.ui.** {*;}
-keep class com.example.external.mvp.bean.** {*;}

# Bean
-keep class com.example.external.mvp.bean.**{*;} # 自定义数据模型的bean目录
-keep class com.example.external.base.**{*;}

###########################################################################################
# glide
#-libraryjars libs/glide-3.7.0.jar
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.Glide { *; }
-keep class com.bumptech.glide.request.RequestOptions {*;}
-dontwarn com.bumptech.glide.**
###########################################################################################
# gson
# Gson specific classes
#-libraryjars libs/gson-2.8.1.jar
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.** { *;}
-keep class io.victoralbertos.jolyglot.** {*;}
-keep class com.google.gson.examples.android.model.** { *; }
-keep class com.google.gson.** { *; }

###########################################################################################
# OkHttp3
#-libraryjars libs/okhttp-3.8.1.jar
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**
-dontwarn com.squareup.okhttp.**
-dontwarn okio.**
-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault

-keep class okhttp3.internal.**{*;}
-dontwarn okhttp3.**
-dontwarn javax.annotation.**
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# okhttp
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }

###########################################################################################
# Retrofit
#-libraryjars libs/retrofit-2.3.0.jar
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-keep class io.rx_cache.** {*;}
-keep class me.dm7.barcodescanner.core.** {*;}
-dontnote retrofit2.Platform
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
-dontwarn retrofit2.Platform$Java8

###########################################################################################
# RxJava RxAndroid
#-libraryjars libs/rxjava-1.3.0.jar
#-libraryjars libs/rxandroid-1.2.1.aar
-dontwarn sun.misc.**
-keep class rx.** {*;}
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

#rxjava和rxandroid
-dontwarn javax.annotation.**
-dontwarn javax.inject.**
