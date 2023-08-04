# TransparencyProcessor
A drag-and-drop or command-line tool that removes all but one RGB color and only leaves alpha in PNG inputs.

# Usage
Aaaa don't ask me yet!

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