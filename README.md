# http4k and GraalVM AOT native-image

### What?

[GraalVM](https://www.graalvm.org/) supports building native binary images, which are compiled ahead of time. I wanted
to see how easy it is to build an application using [http4k](http://http4k.org/) in Kotlin, and compile ahead of time.

### Why?

* It's pretty cool
* It starts up really fast
* In theory it could live in a tiny Docker image

### How?

Make sure you have GraalVM installed (use [sdkman](https://sdkman.io/)), and selected:

```
sdk install java 20.2.0.r11-grl
sdk use java 20.2.0.r11-grl
```

Make sure you have `native-image` installed:

```gu install native-image```

Then build the jar and use it to build a native application:
```
mkdir -p build/bin
./gradlew shadowJar
native-image -jar ./build/libs/test-http4k-graal-1.0-SNAPSHOT-all.jar ./build/bin/test-http4k-graal --initialize-at-build-time --no-fallback
```

which will be output to `build/bin/`, and you can execute the binary directly.

There's also a sample Dockerfile