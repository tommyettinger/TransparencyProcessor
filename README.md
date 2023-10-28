# TransparencyProcessor
A drag-and-drop or command-line tool that removes all but one RGB color and only leaves alpha in PNG inputs.

# Usage
There are two modes here: a default mode, which emits white images with varying alpha, and the `-g` mode, which emits
grayscale images with no alpha. Both formats use indexed color, which is well-supported by both older and more-recent
OpenGL and OpenGL ES. This is the main reason to use the `-g` mode, since it can convert grayscale PNGs to indexed color
and so make those images usable by recent OpenGL (ES).

The default mode only works on PNGs with one visible color (which should be white), but a different RGB value for
fully-transparent pixels; often this is (0, 0, 0), which would be black if it weren't completely transparent. This tool
will change the fully-transparent
color to the visible color, such as to white, which can drastically improve how some linear-filtered images look.

The `-g` mode is typically used on PNGs that use either luminance (grayscale) or alpha only, and converts them to use
indexed mode with a grayscale palette. The only purpose for this is compatibility; the files are a tiny bit larger
(unless you compress the files very carefully, such as by specifying `--ng` when using oxipng).

There's a .zip in the Releases tab for x64 Windows users; it is fully self-contained and doesn't need Java installed.
If that works for you (that is, if you're on 64-bit Windows, which is nearly all Windows users), then you can extract
the .exe out of the .zip, drag and drop an appropriate file onto the .exe, and see no visible change other than a
smaller file size.

If the Windows .exe doesn't work for you, there's a JAR that does require Java to run (Java 8 or higher will work). This
needs to be run from the command line, as with: 

`java -jar TransparencyProcessor.jar file1.png file2.png file3.png`

That approach will also work or the .exe, though it doesn't use `java -jar ` at the start.

# Thanks
This project uses the great [PicoCLI](https://picocli.info/) library for clean command-line handling.
Of course, this uses [libGDX](https://libgdx.com/); I can't get by without it.
This uses Graal Native Image, which is wonderful for apps like this. I just have to thank some of the early pioneers
who combined Graal Native Image with libGDX: [Yi An](https://github.com/anyicomplex), [ByerN](https://github.com/ByerN),
and [Berstanio](https://github.com/Berstanio) have all made a great deal of knowledge available for working with what
has usually been a very complex tool.

# Notes
To build the native EXE, I drop TransparencyProcessor.jar into `graalvm-env/`, run the appropriate Visual
Studio variable setter (`setup_build.bat`), then `build_native.bat`. This is a mix of ByerN's work and Berstanio's later
findings, so big thanks to both of you!

Note that you may need to edit `setup_build.bat` to have the right path for your `vcvarsall.bat` file that it calls (and
potentially change the architecture). You will **almost certainly** need to edit the first line of `build_native.bat`,
which has the absolute path of Graal's `native-image.cmd` on your machine. Using the Java 17 version of Graal seems to
produce much smaller binaries than the Java 20 Graal.