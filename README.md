Create the image 
```./mvnw -DskipTests=true -Pnative -Dagent package```
Run the image
```target/native-test```

Error 
```
Exception in thread "main" java.lang.UnsatisfiedLinkError: Unsupported JNI version 0xffffffff, required by /tmp/jnilib-14776856256143355203.tmp
        at org.graalvm.nativeimage.builder/com.oracle.svm.core.jni.JNILibraryInitializer.checkSupportedJNIVersion(JNILibraryInitializer.java:79)
        at org.graalvm.nativeimage.builder/com.oracle.svm.core.jni.JNILibraryInitializer.callOnLoadFunction(JNILibraryInitializer.java:72)
        at org.graalvm.nativeimage.builder/com.oracle.svm.core.jni.JNILibraryInitializer.initialize(JNILibraryInitializer.java:132)
        at org.graalvm.nativeimage.builder/com.oracle.svm.core.jdk.NativeLibrarySupport.addLibrary(NativeLibrarySupport.java:204)
        at org.graalvm.nativeimage.builder/com.oracle.svm.core.jdk.NativeLibrarySupport.loadLibrary0(NativeLibrarySupport.java:160)
        at org.graalvm.nativeimage.builder/com.oracle.svm.core.jdk.NativeLibrarySupport.loadLibraryAbsolute(NativeLibrarySupport.java:97)
        at java.base@21.0.4/java.lang.ClassLoader.loadLibrary(ClassLoader.java:114)
        at java.base@21.0.4/java.lang.Runtime.load0(Runtime.java:852)
        at java.base@21.0.4/java.lang.System.load(System.java:2025)
        at org.apache.arrow.c.jni.JniLoader.load(JniLoader.java:92)
        at org.apache.arrow.c.jni.JniLoader.loadRemaining(JniLoader.java:76)
        at org.apache.arrow.c.jni.JniLoader.ensureLoaded(JniLoader.java:60)
        at org.apache.arrow.c.jni.JniWrapper.get(JniWrapper.java:27)
        at org.apache.arrow.c.ArrowArrayStream.getNext(ArrowArrayStream.java:149)
        at org.apache.arrow.c.ArrowArrayStreamReader.loadNextBatch(ArrowArrayStreamReader.java:67)
        at org.example.Main.main(Main.java:37)
        at java.base@21.0.4/java.lang.invoke.LambdaForm$DMH/sa346b79c.invokeStaticInit(LambdaForm$DMH)
        Suppressed: java.lang.UnsupportedOperationException: null not supported as key!
                at org.graalvm.collections/org.graalvm.collections.EconomicMapImpl.checkKeyNonNull(EconomicMapImpl.java:640)
                at org.graalvm.collections/org.graalvm.collections.EconomicMapImpl.get(EconomicMapImpl.java:242)
                at org.graalvm.nativeimage.builder/com.oracle.svm.core.jni.access.JNIReflectionDictionary.getDeclaredMethod(JNIReflectionDictionary.java:224)
                at org.graalvm.nativeimage.builder/com.oracle.svm.core.jni.access.JNIReflectionDictionary.findMethod(JNIReflectionDictionary.java:189)
                at org.graalvm.nativeimage.builder/com.oracle.svm.core.jni.access.JNIReflectionDictionary.getMethodID(JNIReflectionDictionary.java:234)
                at org.graalvm.nativeimage.builder/com.oracle.svm.core.jni.functions.JNIFunctions$Support.getMethodID(JNIFunctions.java:1330)
                at org.graalvm.nativeimage.builder/com.oracle.svm.core.jni.functions.JNIFunctions.ThrowNew(JNIFunctions.java:896)
                at org.graalvm.nativeimage.builder/com.oracle.svm.core.jni.JNIOnLoadFunctionPointer.invoke(JNILibraryInitializer.java)
                at org.graalvm.nativeimage.builder/com.oracle.svm.core.jni.JNILibraryInitializer.callOnLoadFunction(JNILibraryInitializer.java:71)
                ... 15 more
        Suppressed: java.lang.IllegalStateException: Memory was leaked by query. Memory leaked: (64)
```